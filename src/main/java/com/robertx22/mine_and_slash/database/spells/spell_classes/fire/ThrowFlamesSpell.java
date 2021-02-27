package com.robertx22.mine_and_slash.database.spells.spell_classes.fire;

import com.robertx22.mine_and_slash.database.spells.entities.proj.ThrowFlameEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class ThrowFlamesSpell extends BaseSpell {

    private ThrowFlamesSpell() {
        super(
                new ImmutableSpellConfigs() {

                    @Override
                    public Masteries school() {
                        return Masteries.FIRE;
                    }

                    @Override
                    public SpellCastType castType() {
                        return SpellCastType.PROJECTILE;
                    }

                    @Override
                    public SoundEvent sound() {
                        return SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE;
                    }

                    @Override
                    public Elements element() {
                        return Elements.Fire;
                    }
                }.cooldownIfCanceled(true)
                        .summonsEntity(w -> new ThrowFlameEntity(w))
                        .setSwingArmOnCast());
    }

    public static ThrowFlamesSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();

        c.set(SC.MANA_COST, 11, 16);
        c.set(SC.BASE_VALUE, 3, 5);
        c.set(SC.ATTACK_SCALE_VALUE, 0.75F, 1.0F);
        c.set(SC.SHOOT_SPEED, 1.0F, 1.25F);
        c.set(SC.PROJECTILE_COUNT, 3, 3);
        c.set(SC.CAST_TIME_TICKS, 0, 0);
        c.set(SC.COOLDOWN_SECONDS, 14, 11);
        c.set(SC.DURATION_TICKS, 60, 60);
        c.set(SC.TIMES_TO_CAST, 1, 1);

        c.setMaxLevel(12);

        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(7, 4);
    }

    @Override
    public String GUID() {
        return "throw_flames";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent("Converts Weapon DMG to Fire."));
        list.add(new StringTextComponent("Strike the air in front of you, sending out fiery waves: "));

        list.addAll(getCalculation(ctx).GetTooltipString(info, ctx));

        return list;

    }

    @Override
    public Words getName() {
        return Words.ThrowFlames;
    }

    private static class SingletonHolder {
        private static final ThrowFlamesSpell INSTANCE = new ThrowFlamesSpell();
    }

    @Override
    public void castExtra(SpellCastContext ctx) {

        if (ctx.caster instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) ctx.caster;
            player.spawnSweepParticles();
        }

        ctx.caster.world.playSound((PlayerEntity) null, ctx.caster.getPosX(), ctx.caster.getPosY(), ctx.caster.getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 1.0F, 1.0F);

    }
}

