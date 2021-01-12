package com.robertx22.mine_and_slash.database.spells.spell_classes.storm;

import com.robertx22.mine_and_slash.database.spells.entities.single_target_bolt.FrostballEntity;
import com.robertx22.mine_and_slash.database.spells.entities.single_target_bolt.LightningBallEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.SpellTooltips;
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

import java.util.ArrayList;
import java.util.List;

public class BatteryFusiladeSpell extends BaseSpell {

    private BatteryFusiladeSpell() {
        super(
            new ImmutableSpellConfigs() {

                @Override
                public Masteries school() {
                    return Masteries.STORM;
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
                    return Elements.Thunder;
                }
            }.summonsEntity(world -> new LightningBallEntity(world)));
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();

        c.set(SC.MANA_COST, 22, 26);
        c.set(SC.BASE_VALUE, 0, 0);
        c.set(SC.MANA_ATTACK_SCALE_VALUE, 0.2F, 0.4F);
        c.set(SC.SHOOT_SPEED, 0.8F, 1.1F);
        c.set(SC.PROJECTILE_COUNT, 3, 11);
        c.set(SC.CAST_TIME_TICKS, 30, 30);
        c.set(SC.COOLDOWN_TICKS, 150, 75);
        c.set(SC.DURATION_TICKS, 40, 60);

        c.setMaxLevel(12);

        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(1, 5);
    }

    public static BatteryFusiladeSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "battery_fusilade";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(SpellTooltips.singleTargetProjectile());

        list.addAll(getCalculation(ctx).GetTooltipString(info, ctx));

        return list;

    }

    @Override
    public Words getName() {
        return Words.BatteryFusilade;
    }

    private static class SingletonHolder {
        private static final BatteryFusiladeSpell INSTANCE = new BatteryFusiladeSpell();
    }
}