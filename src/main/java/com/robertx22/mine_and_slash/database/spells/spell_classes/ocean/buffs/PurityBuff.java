package com.robertx22.mine_and_slash.database.spells.spell_classes.ocean.buffs;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.potion_effects.ocean_mystic.NourishmentEffect;
import com.robertx22.mine_and_slash.potion_effects.ocean_mystic.PurityEffect;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class PurityBuff extends BaseOceanBuffSpell{
    private PurityBuff() {
        super(new ImmutableSpellConfigs() {
            @Override
            public Masteries school() {
                return Masteries.OCEAN;
            }

            @Override
            public SpellCastType castType() {
                return SpellCastType.AOE_ALLIES_EFFECT;
            }

            @Override
            public SoundEvent sound() {
                return SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP;
            }

            @Override
            public Elements element() {
                return Elements.Elemental;
            }
        }.addsEffect(PurityEffect.INSTANCE));
    }

    public static PurityBuff getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "purity";
    }

    @Override
    public Words getName() {
        return Words.Purity;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(7, 1);
    }

    private static class SingletonHolder {
        private static final PurityBuff INSTANCE = new PurityBuff();
    }
}
