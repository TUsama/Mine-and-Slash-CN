package com.robertx22.mine_and_slash.database.spells.spell_classes.divine;

import com.robertx22.mine_and_slash.database.spells.entities.proj.GroundSlamEntity;
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

public class GroundSlamSpell extends BaseSpell {

    private GroundSlamSpell() {
        super(
            new ImmutableSpellConfigs() {

                @Override
                public Masteries school() {
                    return Masteries.DIVINE;
                }

                @Override
                public SpellCastType castType() {
                    return SpellCastType.PROJECTILE;
                }

                @Override
                public SoundEvent sound() {
                    return SoundEvents.BLOCK_ANVIL_PLACE;
                }

                @Override
                public Elements element() {
                    return Elements.Physical;
                }
            }.cooldownIfCanceled(true)
                    .rightClickFor(AllowedAsRightClickOn.MELEE_WEAPON)
                    .summonsEntity(w -> new GroundSlamEntity(w))
                    .setSwingArmOnCast());
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();

        c.set(SC.MANA_COST, 12, 20);
        c.set(SC.BASE_VALUE, 0, 0);
        c.set(SC.ARMOR_ATTACK_SCALE_VALUE, 0.8F, 1.4F);
        c.set(SC.SHOOT_SPEED, 1.2F, 1.6F);
        c.set(SC.PROJECTILE_COUNT, 3, 7);
        c.set(SC.CAST_TIME_TICKS, 0, 0);
        c.set(SC.COOLDOWN_TICKS, 180, 100);
        c.set(SC.TIMES_TO_CAST, 1, 1);
        c.set(SC.DURATION_TICKS, 40, 60);

        c.setMaxLevel(16);

        return c;
    }

    public static GroundSlamSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(5, 3);
    }

    @Override
    public String GUID() {
        return "ground_slam";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent("Converts armor to damage, unleash to"));
        list.add(new StringTextComponent("damage and pierce through enemies in"));
        list.add(new StringTextComponent("of you: "));

        list.addAll(getCalculation(ctx).GetTooltipString(info, ctx));

        return list;

    }

    @Override
    public Words getName() {
        return Words.GroundSlam;
    }

    private static class SingletonHolder {
        private static final GroundSlamSpell INSTANCE = new GroundSlamSpell();
    }
}
