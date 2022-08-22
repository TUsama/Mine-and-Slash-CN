package com.robertx22.mine_and_slash.potion_effects.bases;

import com.robertx22.mine_and_slash.database.stats.effects.mob_spell_effects.FrostNovaEffect;
import com.robertx22.mine_and_slash.potion_effects.all.*;
import com.robertx22.mine_and_slash.potion_effects.bases.data.ExtraPotionData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;

public class PotionEffectUtils {

    public static void reApplyToSelf(BasePotionEffect effect, LivingEntity caster) {
        apply(effect, caster, caster);
    }

    public static void applyToSelf(BasePotionEffect effect, LivingEntity caster) {
        apply(effect, caster, caster);
    }

    // POTION EFFECT ISN'T USING LEVEL OF CASTER, IT'S USING THE POTION EFFECT LEVEL OF THE APPLIED...
    public static void apply(BasePotionEffect effect, LivingEntity caster, LivingEntity target) {

        //System.out.println("1. Effect: " + effect + "\nCaster: " + caster + "\nTarget: " + target);

        if (caster.world.isRemote) {
            //System.out.println("1b. Potion apply, caster world is remote.");
            return;
        }

        int duration = effect.getDurationInTicks(caster);
        //System.out.println("2. Duration: " + duration);

        EntityCap.UnitData casterData = Load.Unit(caster);
        PlayerSpellCap.ISpellsCap casterCap= Load.spells(caster);
        //System.out.println("3. Caster Data: " + casterData);

        EffectInstance instance = target.getActivePotionEffect(effect);
        //System.out.println("4. Effect Instance: " + instance);
        ExtraPotionData extraData;

        if (instance != null) {
            extraData = PotionDataSaving.getData(instance);
        } else {
            extraData = new ExtraPotionData();
        }

        if (extraData == null) {
            extraData = new ExtraPotionData();
        }

        //System.out.println("5. Extra Data: " + extraData);

        if (extraData.getInitialDurationTicks() > 0) {
            //System.out.println("5b. Potion reapplied.");
            duration = extraData.getInitialDurationTicks(); // if reapplied, apply existing duration
        }

        EffectInstance newInstance = new EffectInstance(effect, duration, extraData.getStacks(), false, false, true);
        //System.out.println("6. New Instance : " + newInstance);

        boolean nonMasteryEffects = effect != BleedPotion.INSTANCE && effect != WeakenCurseEffect.INSTANCE
                && effect != MobChillEffect.INSTANCE && effect != PowerChargeEffect.INSTANCE
                && effect != FortitudeChargeEffect.INSTANCE && effect != SummonTauntEffect.INSTANCE;

        if (instance == null) {

            extraData.casterLvl = casterData.getLevel();
            extraData.casterID = caster.getUniqueID()
                .toString();
            if (nonMasteryEffects) {
                extraData.setLevelPowerMulti(effect.getAbilityThatDeterminesLevel().getLevelPowerMulti(casterCap));
                extraData.setEffectiveAbilityLevel(effect.getAbilityThatDeterminesLevel().getEffectiveAbilityLevel(casterCap, casterData));
            }
            extraData.setInitialDurationTicks(duration);
            //System.out.println("7. Instance Null, Extra Data : " + extraData);

            PotionDataSaving.saveData(newInstance, extraData);

            target.addPotionEffect(newInstance);
        } else {

            if (instance.getDuration() > duration) {
                duration = instance.getDuration();
            }

            extraData.casterLvl = casterData.getLevel();
            extraData.casterID = caster.getUniqueID()
                .toString();
            if (nonMasteryEffects) {
                extraData.setLevelPowerMulti(effect.getAbilityThatDeterminesLevel().getLevelPowerMulti(casterCap));
                extraData.setEffectiveAbilityLevel(effect.getAbilityThatDeterminesLevel().getEffectiveAbilityLevel(casterCap, casterData));
            }
            extraData.setInitialDurationTicks(duration);
            extraData.addStacks(1, effect);
            //System.out.println("7b. Instance Refreshed, Extra Data : " + extraData);

            PotionDataSaving.saveData(newInstance, extraData);

            target.removePotionEffect(effect); // HAVE TO REMOVE OR IT WONT ACTUALLY ADD CORRECTLY
            target.addPotionEffect(newInstance); // so it can recalc stats cus onpotion remoove/add

        }

        //target.sendMessage(new SText("You have " + getStacks(target, effect) + " " + effect.GUID() + " stacks "));

    }

    public static boolean reduceStacks(LivingEntity target, BasePotionEffect effect) {
        return reduceStacks(target, effect, 1);
    }

    public static boolean has(LivingEntity entity, BasePotionEffect effect) {
        return entity.getActivePotionEffect(effect) != null;
    }

    public static boolean reduceStacks(LivingEntity target, BasePotionEffect effect, int num) {

        EffectInstance instance = target.getActivePotionEffect(effect);

        if (instance != null) {
            ExtraPotionData extraData = PotionDataSaving.getData(instance);

            extraData.decreaseStacks(num, effect);

            if (extraData.getStacks() <= 0) {
                target.removePotionEffect(effect);
            } else {
                PotionDataSaving.saveData(instance, extraData);
            }
            return true;
        }

        return false;
    }

    public static int getStacks(LivingEntity en, BasePotionEffect effect) {
        EffectInstance instance = en.getActivePotionEffect(effect);

        if (instance != null) {
            ExtraPotionData extraData = PotionDataSaving.getData(instance);

            if (extraData != null) {
                return extraData.getStacks();
            }

        }
        return 0;

    }
}
