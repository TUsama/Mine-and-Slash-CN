package com.robertx22.mine_and_slash.database.spells.spell_classes.divine;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class HeavyStrikeSpell extends BaseSpell {

    private HeavyStrikeSpell() {
        super(
            new ImmutableSpellConfigs() {

                @Override
                public Masteries school() {
                    return Masteries.DIVINE;
                }

                @Override
                public SpellCastType castType() {
                    return SpellCastType.SPECIAL;
                }

                @Override
                public SoundEvent sound() {
                    return SoundEvents.BLOCK_ANVIL_LAND;
                }

                @Override
                public Elements element() {
                    return Elements.Physical;
                }
            }.cooldownIfCanceled(true)
                .rightClickFor(AllowedAsRightClickOn.MELEE_WEAPON)
                .setSwingArmOnCast());
    }

    @Override
    public void castExtra(SpellCastContext ctx) {

        if (ctx.caster instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) ctx.caster;
            player.spawnSweepParticles();
        }

        ctx.caster.world.playSound((PlayerEntity) null, ctx.caster.getPosX(), ctx.caster.getPosY(), ctx.caster.getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 1.0F, 1.0F);

        Vec3d look = ctx.caster.getLookVec()
            .scale(3);

        List<LivingEntity> list = EntityFinder.start(ctx.caster, LivingEntity.class, ctx.caster.getPositionVector()
            .add(look)
            .add(0, ctx.caster.getHeight() / 2, 0))
            .finder(EntityFinder.Finder.RADIUS)
            .radius(2)
            .height(2)
            .build();

        SoundUtils.playSound(ctx.caster, SoundEvents.BLOCK_ANVIL_LAND, 0.8F, 0.5F);

        for (LivingEntity en : list) {

            int num = ctx.getConfigFor(this)
                .getCalc(ctx.spellsCap, this)
                .getCalculatedValue(ctx.data, ctx.spellsCap, this);

            SpellDamageEffect dmg = new SpellDamageEffect(ctx.caster, en, num, ctx.data, Load.Unit(en),
                this
            );
            dmg.removeKnockback();
            dmg.Activate();

            ParticleEnum.sendToClients(
                en.getPosition(), en.world,
                new ParticlePacketData(en.getPositionVector(), ParticleEnum.AOE).radius(1)
                    .motion(new Vec3d(0, 0, 0))
                    .type(ParticleTypes.INSTANT_EFFECT)
                    .amount((int) (45)));

        }
    }

    public static HeavyStrikeSpell getInstance() {
        return HeavyStrikeSpell.SingletonHolder.INSTANCE;
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();

        c.set(SC.MANA_COST, 3, 10);
        c.set(SC.BASE_VALUE, 0, 0);
        c.set(SC.ATTACK_SCALE_VALUE, 2.0F, 2.5F);
        c.set(SC.CAST_TIME_TICKS, 0, 0);
        c.set(SC.COOLDOWN_TICKS, 30, 15);
        c.set(SC.TIMES_TO_CAST, 1, 1);

        c.setMaxLevel(16);

        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(4, 1);
    }

    @Override
    public String GUID() {
        return "heavy_strike";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent("Attack enemies in melee: "));

        list.addAll(getCalculation(ctx).GetTooltipString(info, ctx));

        return list;

    }

    @Override
    public Words getName() {
        return Words.HeavyStrike;
    }

    private static class SingletonHolder {
        private static final HeavyStrikeSpell INSTANCE = new HeavyStrikeSpell();
    }
}