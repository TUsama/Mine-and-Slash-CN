package com.robertx22.mine_and_slash.database.spells.spell_classes.unholy;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModSounds;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ParticleRegister;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class UnholyMeditationSpell extends BaseSpell {

    private UnholyMeditationSpell() {
        super(
            new ImmutableSpellConfigs() {

                @Override
                public Masteries school() {
                    return Masteries.UNHOLY;
                }

                @Override
                public SpellCastType castType() {
                    return SpellCastType.SELF_HEAL;
                }

                @Override
                public SoundEvent sound() {
                    return SoundEvents.ENTITY_WITCH_HURT;
                }

                @Override
                public Elements element() {
                    return Elements.Elemental;
                }
            }.cooldownIfCanceled(true).setSwingArmOnCast());
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.HEALTH_COST, 0, 0);
        c.set(SC.MANA_COST, 0, 0);
        c.set(SC.ENERGY_COST, 0, 0);
        c.set(SC.MAGIC_SHIELD_COST, 0, 0);
        c.set(SC.BASE_VALUE, 2, 9);
        c.set(SC.CAST_TIME_TICKS, 120, 120);
        c.set(SC.TIMES_TO_CAST, 6, 6);
        c.set(SC.COOLDOWN_SECONDS, 22, 16);

        c.setMaxLevel(8);

        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(7, 0);
    }

    public static UnholyMeditationSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "unholy_meditation";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Spell"));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Channel, Heal, Self"));

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent("Replenish your health and mana through meditation: "));

        list.addAll(getCalculation(ctx).GetTooltipString(info, ctx));

        return list;

    }

    @Override
    public Words getName() {
        return Words.UnholyMeditation;
    }

    @Override
    public void castExtra(SpellCastContext ctx) {
        try {
            //SoundUtils.playSound(ctx.caster, ModSounds.FREEZE.get(), 1, 1);
            ParticleUtils.spawnParticles(ParticleTypes.WITCH, ctx.caster, 50);
        } catch (Exception e) {
            e.printStackTrace();
        }

        LivingEntity en = ctx.caster;

       SpellUtils.healCasterMana(ctx);

       ParticleEnum.sendToClients(
            en.getPosition(), en.world,
            new ParticlePacketData(en.getPositionVector(), ParticleEnum.AOE).radius(2F)
                    .motion(new Vec3d(0, 0, 0))
                    .type(ParticleTypes.HEART)
                    .amount(45));


    }

    private static class SingletonHolder {
        private static final UnholyMeditationSpell INSTANCE = new UnholyMeditationSpell();
    }
}
