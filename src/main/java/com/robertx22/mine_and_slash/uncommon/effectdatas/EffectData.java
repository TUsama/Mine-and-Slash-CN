package com.robertx22.mine_and_slash.uncommon.effectdatas;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect.EffectSides;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TeamUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public abstract class EffectData {

    public UnitData sourceData;
    public UnitData targetData;

    public boolean canceled = false;
    public Unit sourceUnit;
    public Unit targetUnit;

    public LivingEntity source;
    public LivingEntity target;

    public float number = 0;
    public float preIncNumber = 0;

    protected boolean activateSynergies = true;

    public EffectData(LivingEntity source, LivingEntity target) {

        this.source = source;
        this.target = target;

        if (target != null) {
            this.targetData = Load.Unit(target);
        }
        if (source != null) {
            this.sourceData = Load.Unit(source);

        }
        if (source != null) {

            try {
                if (target != null) {
                    targetUnit = targetData.getUnit();
                }

                sourceUnit = sourceData.getUnit();

                if (sourceUnit != null) {
                    sourceData.tryRecalculateStats(source);

                } else {
                    this.canceled = true;
                }
                if (targetUnit != null) {

                    targetData.tryRecalculateStats(target);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public EffectData(LivingEntity source, LivingEntity target, UnitData sourceData, UnitData targetData) {

        this.source = source;
        this.target = target;

        if (sourceData != null && targetData != null) {
            this.sourceData = sourceData;
            this.targetData = targetData;

            this.sourceUnit = sourceData.getUnit();
            this.targetUnit = targetData.getUnit();

        } else {
            this.canceled = true;
        }

    }

    private EffectTypes effectType = EffectTypes.BASIC_ATTACK;

    public EffectTypes getEffectType() {
        return effectType;
    }

    public void setEffectType(EffectTypes effectType, WeaponTypes weaponType) {
        this.effectType = effectType;
        this.weaponType = weaponType;
    }

    public WeaponTypes weaponType = WeaponTypes.None;

    public enum EffectTypes {
        SPELL,
        ATTACK_SPELL,
        BASIC_ATTACK,
        BONUS_ATTACK,
        REFLECT,
        DOT_DMG,
        SUMMON_DMG,
        BOLT
    }

    public Unit GetSource() {

        return sourceUnit;
    }

    public Unit GetTarget() {
        return targetUnit;
    }

    public void Activate() {

        if (this.source instanceof ServerPlayerEntity && this.target instanceof ServerPlayerEntity && this.source != this.target) { // do less damage in pvp and make sure its not on self
            if (!TeamUtils.areOnSameTeam((ServerPlayerEntity) source, (ServerPlayerEntity) target)) {
                this.number *= 0.25f;
            }
        }

        this.preIncNumber = this.number;

        calculateEffects();

        if (this.canceled != true) {
            activate();
        }

    }

    boolean effectsCalculated = false;

    public void calculateEffects() {
        if (!effectsCalculated) {
            effectsCalculated = true;
            if (source == null || target == null || canceled == true || sourceUnit == null || targetUnit == null || sourceData == null || targetData == null) {
                return;
            }

            logOnStartData();

            TryApplyEffects(this.GetSource(), EffectSides.Source);

            //  if (GetSource().GUID.equals(GetTarget().GUID) == false) { // todo unsure
            // this makes stats not apply twice if  caster is both target and source.. hmm
            TryApplyEffects(this.GetTarget(), EffectSides.Target);
            // }

            logOnEndData();

        }

    }

    public void calculateHealEffects() {
        if (!effectsCalculated) {
            effectsCalculated = true;
            if (source == null || target == null || canceled == true || sourceUnit == null || targetUnit == null || sourceData == null || targetData == null) {
                return;
            }

            logOnStartData();

            TryApplyHealEffects(this.GetSource(), this.GetTarget(), EffectSides.Source);

            logOnEndData();

        }

    }

    public void logOnStartData() {
        if (MMORPG.statEffectDebuggingEnabled()) {
            System.out.println(
                TextFormatting.DARK_PURPLE + "Starting to activate effects for: " + getClass().toString() + " " + "Starting Number: " + number);
        }
    }

    public void logOnEndData() {
        if (MMORPG.statEffectDebuggingEnabled()) {
            System.out.println(
                TextFormatting.DARK_PURPLE + "Effects for : " + getClass().toString() + " are finished.");
        }
    }

    public void logAfterEffect(IStatEffect effect) {
        if (MMORPG.statEffectDebuggingEnabled()) {
            System.out.println(TextFormatting.GREEN + "After : " + TextFormatting.BLUE + effect.getClass()
                .toString() + TextFormatting.WHITE + ": " + this.number);
        }
    }

    protected abstract void activate();

    protected EffectData TryApplyEffects(Unit unit, EffectSides side) { // source, target

        if (this.canceled) {
            return this;
        }

        List<EffectUnitStat> Effects = new ArrayList<EffectUnitStat>();

        Effects = AddEffects(Effects, unit, side); // source, target

        Effects.sort(new EffectUnitStat());

        for (EffectUnitStat item : Effects) {
            if (item.stat.isNotZero()) {
                if (item.effect.Side()
                    .equals(side)) {
                    item.effect.TryModifyEffect(this, item.source, item.stat, item.stat.GetStat());

                }

            }
        }

        return this;
    }

    protected EffectData TryApplyHealEffects(Unit source, Unit target, EffectSides side) { // source, target

        if (this.canceled) {
            return this;
        }

        List<EffectUnitStat> Effects = new ArrayList<EffectUnitStat>();

        Effects = AddHealEffects(Effects, source, target, side); // source, target

        Effects.sort(new EffectUnitStat());

        for (EffectUnitStat item : Effects) {
            if (item.stat.isNotZero()) {
                if (item.effect.Side()
                        .equals(side)) {
                    item.effect.TryModifyEffect(this, item.source, item.stat, item.stat.GetStat());

                }

            }
        }

        return this;
    }

    /*
    public boolean AffectsThisUnit(IStatEffect effect, EffectSides side, EffectData data, Unit source) {

        boolean affects = false;

        if (effect.Side()
            .equals(EffectSides.Target)) {
            affects = source.equals(data.targetUnit);
        } else {
            affects = source.equals(data.sourceUnit);
        }

        if (affects == false) {
            //System.out.println("works");
        }

        return affects;
    }


     */
    private List<EffectUnitStat> AddEffects(List<EffectUnitStat> effects, Unit unit, EffectSides side) {
        if (unit != null) {
            unit.getStats() // gets the stats of the unit
                .values()
                .forEach(data -> { // for each stat
                    if (data.isNotZero()) {
                        Stat stat = data.GetStat();
                        try {
                            if (stat instanceof IStatEffects) {
                                ((IStatEffects) stat).getEffects()
                                        .forEach(effect -> {
                                            effects.add(new EffectUnitStat(effect, unit, data)); // add to the list of effects data = the number, unit = who it is applied to
                                        });
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
        }

        return effects;
    }

    private List<EffectUnitStat> AddHealEffects(List<EffectUnitStat> effects, Unit source, Unit target, EffectSides side) {
        if (source != null && target != null) {
            source.getStats().values().forEach(data -> { // get the source's stats
                        if (data.isNotZero()) { // make sure the stat value is not 0
                            Stat stat = data.GetStat(); // actually get the stat
                            if (stat instanceof IStatEffects) { // for each effect, add data to the stat?
                                ((IStatEffects) stat).getEffects().forEach(effect -> {
                                            effects.add(new EffectUnitStat(effect, source, data)); // add to the list of effects data = the number, unit = who it is applied to
                                        });
                            } // always use source?
                        }
                    });
        }

        return effects;
    }

}