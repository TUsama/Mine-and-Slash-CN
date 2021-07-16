package com.robertx22.mine_and_slash.database.spells.spell_classes.hunting.buffs;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ocean.buffs.BaseOceanBuffSpell;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModSounds;
import com.robertx22.mine_and_slash.potion_effects.ocean_mystic.FrostShieldEffect;
import com.robertx22.mine_and_slash.potion_effects.ranger.WindWalkEffect;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class WindWalkBuff extends BaseHuntingBuffSpell {
    private WindWalkBuff() {
        super(new ImmutableSpellConfigs() {
            @Override
            public Masteries school() {
                return Masteries.HUNTING;
            }

            @Override
            public SpellCastType castType() {
                return SpellCastType.AOE_ALLIES_EFFECT;
            }

            @Override
            public SoundEvent sound() {
                return SoundEvents.ENTITY_HORSE_BREATHE;
            }

            @Override
            public Elements element() {
                return Elements.Physical;
            }
        }.addsEffect(WindWalkEffect.INSTANCE));
    }

    public static WindWalkBuff getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "wind_walk";
    }

    @Override
    public Words getName() {
        return Words.WindWalk;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(2, 5);
    }

    private static class SingletonHolder {
        private static final WindWalkBuff INSTANCE = new WindWalkBuff();
    }
}
