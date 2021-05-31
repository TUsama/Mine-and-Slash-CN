package com.robertx22.mine_and_slash.database.spells.spell_classes.nature;

import com.robertx22.mine_and_slash.database.spells.entities.cloud.BlizzardEntity;
import com.robertx22.mine_and_slash.database.spells.entities.cloud.RockSlideEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModSounds;
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

public class RockSlideSpell extends BaseSpell {

    private RockSlideSpell() {
        super(
            new ImmutableSpellConfigs() {

                @Override
                public Masteries school() {
                    return Masteries.NATURE;
                }

                @Override
                public SpellCastType castType() {
                    return SpellCastType.AT_SIGHT;
                }

                @Override
                public SoundEvent sound() {
                    return ModSounds.STONE_CRACK.get();
                }

                @Override
                public Elements element() {
                    return Elements.Nature;
                }
            }.summonsEntity(w -> new RockSlideEntity(w))
                .setSwingArmOnCast());
    }

    public static RockSlideSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();

        c.set(SC.MANA_COST, 14, 22);
        c.set(SC.BASE_VALUE, 5, 13);
        c.set(SC.ATTACK_SCALE_VALUE, 0.15F, 0.35F);
        c.set(SC.CAST_TIME_TICKS, 20, 20);
        c.set(SC.COOLDOWN_SECONDS, 8, 6);
        c.set(SC.TICK_RATE, 4, 4);
        c.set(SC.RADIUS, 2, 2);
        c.set(SC.DURATION_TICKS, 40, 40);

        c.setMaxLevel(12);

        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(3, 3);
    }

    @Override
    public String GUID() {
        return "rock_slide";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent("Summon a stone avalanche that damages enemies inside: "));

        list.addAll(getCalculation(ctx).GetTooltipString(info, ctx));

        return list;

    }

    @Override
    public Words getName() {
        return Words.RockSlide;
    }

    private static class SingletonHolder {
        private static final RockSlideSpell INSTANCE = new RockSlideSpell();
    }
}

