package com.robertx22.mine_and_slash.database.spells.spell_classes.ocean;

import com.robertx22.mine_and_slash.database.spells.entities.cloud.BlizzardEntity;
import com.robertx22.mine_and_slash.database.spells.entities.single_target_bolt.FrozenOrbEntity;
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
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class FrozenOrbSpell extends BaseSpell {

    private FrozenOrbSpell() {
        super(
            new ImmutableSpellConfigs() {

                @Override
                public Masteries school() {
                    return Masteries.OCEAN;
                }

                @Override
                public SpellCastType castType() {
                    return SpellCastType.PROJECTILE;
                }

                @Override
                public SoundEvent sound() {
                    return SoundEvents.ENTITY_SNOWBALL_THROW;
                }

                @Override
                public Elements element() {
                    return Elements.Water;
                }
            }.summonsEntity(w -> new FrozenOrbEntity(w))
                    .setSwingArmOnCast());
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();

        c.set(SC.MANA_COST, 30, 44);
        c.set(SC.BASE_VALUE, 3, 7);
        c.set(SC.SHOOT_SPEED, 0.2F, 0.2F);
        c.set(SC.PROJECTILE_COUNT, 1, 1);
        c.set(SC.CAST_TIME_TICKS, 60, 40);
        c.set(SC.COOLDOWN_SECONDS, 50, 35);
        c.set(SC.TICK_RATE, 10, 5);
        c.set(SC.DURATION_TICKS, 240, 320);

        c.setMaxLevel(12);

        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(1, 6);
    }

    public static FrozenOrbSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "frozen_orb";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent("Summons an orb that damages enemies in its path: "));

        list.addAll(getCalculation(ctx).GetTooltipString(info, ctx));

        return list;

    }

    @Override
    public Words getName() {
        return Words.FrozenOrb;
    }

    private static class SingletonHolder {
        private static final FrozenOrbSpell INSTANCE = new FrozenOrbSpell();
    }
}
