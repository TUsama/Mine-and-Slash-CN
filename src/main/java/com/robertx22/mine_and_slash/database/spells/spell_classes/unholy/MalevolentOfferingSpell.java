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
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
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

public class MalevolentOfferingSpell extends BaseSpell {

    private MalevolentOfferingSpell() {
        super(
            new ImmutableSpellConfigs() {

                @Override
                public Masteries school() {
                    return Masteries.UNHOLY;
                }

                @Override
                public SpellCastType castType() {
                    return SpellCastType.SPECIAL;
                }

                @Override
                public SoundEvent sound() {
                    return SoundEvents.ENTITY_BAT_HURT;
                }

                @Override
                public Elements element() {
                    return Elements.Physical;
                }
            }.cooldownIfCanceled(true).setSwingArmOnCast());
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.HEALTH_COST, 0.2F, 0.2F);
        c.set(SC.MANA_COST, 4, 11);
        c.set(SC.ENERGY_COST, 0, 0);
        c.set(SC.MAGIC_SHIELD_COST, 0, 0);
        c.set(SC.HEALTH_ATTACK_SCALE_VALUE, 0.1F, 0.2F);
        c.set(SC.BASE_VALUE, 0, 0);
        c.set(SC.RADIUS, 5, 8);
        c.set(SC.CAST_TIME_TICKS, 120, 120);
        c.set(SC.TIMES_TO_CAST, 6, 6);
        c.set(SC.COOLDOWN_SECONDS, 35, 25);

        c.setMaxLevel(8);

        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(0, 4);
    }

    public static MalevolentOfferingSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "malevolent_offering";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Spell"));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Area, Heal"));

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent("Heal allies around you at the expense"));
        list.add(new StringTextComponent("of your own health (does not heal caster): "));

        list.addAll(getCalculation(ctx).GetTooltipString(info, ctx));

        return list;

    }

    @Override
    public Words getName() {
        return Words.MalevolentOffering;
    }

    @Override
    public void castExtra(SpellCastContext ctx) {

        float RADIUS = ctx.getConfigFor(this)
                .get(SC.RADIUS)
                .get(ctx.spellsCap, this);

        try {
            //SoundUtils.playSound(ctx.caster, ModSounds.FREEZE.get(), 1, 1);
            ParticleEnum.sendToClients(
                    ctx.caster.getPosition(), ctx.caster.world,
                    new ParticlePacketData(ctx.caster.getPositionVector(), ParticleEnum.AOE).radius(RADIUS)
                            .motion(new Vec3d(0, 0, 0))
                            .type(ParticleTypes.WITCH)
                            .amount((int) (RADIUS * 10)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<LivingEntity> list = EntityFinder.start(ctx.caster, LivingEntity.class, ctx.caster.getPositionVector())
                .finder(EntityFinder.Finder.RADIUS)
                .radius(RADIUS)
                .searchFor(EntityFinder.SearchFor.ALLIES)
                .build();

        SoundUtils.playSound(ctx.caster, SoundEvents.ENTITY_WITCH_DEATH, 1, 0.8F);

        for (LivingEntity en : list) {

            int num = ctx.getConfigFor(this)
                    .getCalc(ctx.spellsCap, this)
                    .getCalculatedValue(ctx.data, ctx.spellsCap, this);

            if (en != ctx.caster) {

                SpellUtils.heal(this, en, num);

                ParticleEnum.sendToClients(
                        en.getPosition(), en.world,
                        new ParticlePacketData(en.getPositionVector(), ParticleEnum.AOE).radius(RADIUS)
                                .motion(new Vec3d(0, 0, 0))
                                .type(ParticleTypes.HEART)
                                .amount((int) (45)));
            }
        }

    }

    private static class SingletonHolder {
        private static final MalevolentOfferingSpell INSTANCE = new MalevolentOfferingSpell();
    }
}
