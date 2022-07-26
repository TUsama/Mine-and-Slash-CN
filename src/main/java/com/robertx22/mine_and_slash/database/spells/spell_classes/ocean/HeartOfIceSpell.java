package com.robertx22.mine_and_slash.database.spells.spell_classes.ocean;

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
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class HeartOfIceSpell extends BaseSpell {

    private HeartOfIceSpell() {
        super(
            new ImmutableSpellConfigs() {

                @Override
                public Masteries school() {
                    return Masteries.OCEAN;
                }

                @Override
                public SpellCastType castType() {
                    return SpellCastType.SELF_HEAL;
                }

                @Override
                public SoundEvent sound() {
                    return ModSounds.FREEZE.get();
                }

                @Override
                public Elements element() {
                    return Elements.Water;
                }
            }.cooldownIfCanceled(true).setSwingArmOnCast());
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.HEALTH_COST, 0, 0);
        c.set(SC.MANA_COST, 9, 14);
        c.set(SC.ENERGY_COST, 0, 0);
        c.set(SC.MAGIC_SHIELD_COST, 0, 0);
        c.set(SC.BASE_VALUE, 12, 28);
        c.set(SC.RADIUS, 5, 10);
        c.set(SC.CAST_TIME_TICKS, 60, 60);
        c.set(SC.TIMES_TO_CAST, 3, 5);
        c.set(SC.COOLDOWN_SECONDS, 11, 8);

        c.setMaxLevel(8);

        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(7, 4);
    }

    public static HeartOfIceSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "heart_of_ice";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Spell"));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Area, Heal"));

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent("Heal allies around you: "));

        list.addAll(getCalculation(ctx).GetTooltipString(info, ctx));

        return list;

    }

    @Override
    public Words getName() {
        return Words.HeartOfIce;
    }

    @Override
    public void castExtra(SpellCastContext ctx) {
        try {
            //SoundUtils.playSound(ctx.caster, ModSounds.FREEZE.get(), 1, 1);
            ParticleUtils.spawnParticles(ParticleRegister.BUBBLE, ctx.caster, 80);
        } catch (Exception e) {
            e.printStackTrace();
        }

        float RADIUS = ctx.getConfigFor(this)
                .get(SC.RADIUS)
                .get(ctx.spellsCap, this);

        List<LivingEntity> list = EntityFinder.start(ctx.caster, LivingEntity.class, ctx.caster.getPositionVector())
                .finder(EntityFinder.Finder.RADIUS)
                .radius(RADIUS)
                .searchFor(EntityFinder.SearchFor.ALLIES)
                .build();

        for (LivingEntity en : list) {

            int num = ctx.getConfigFor(this)
                    .getCalc(ctx.spellsCap, this)
                    .getCalculatedValue(ctx.data, ctx.spellsCap, this);

            SpellUtils.heal(this, en, num);

            ParticleEnum.sendToClients(
                    en.getPosition(), en.world,
                    new ParticlePacketData(en.getPositionVector(), ParticleEnum.AOE).radius(RADIUS)
                            .motion(new Vec3d(0, 0, 0))
                            .type(ParticleTypes.ITEM_SNOWBALL)
                            .amount((int) (90)));
            ParticleEnum.sendToClients(
                    en.getPosition(), en.world,
                    new ParticlePacketData(en.getPositionVector(), ParticleEnum.AOE).radius(RADIUS)
                            .motion(new Vec3d(0, 0, 0))
                            .type(ParticleTypes.HEART)
                            .amount((int) (45)));
        }

    }

    private static class SingletonHolder {
        private static final HeartOfIceSpell INSTANCE = new HeartOfIceSpell();
    }
}
