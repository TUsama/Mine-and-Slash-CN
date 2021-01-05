package com.robertx22.mine_and_slash.database.spells.spell_classes.ocean.buffs;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModSounds;
import com.robertx22.mine_and_slash.potion_effects.ocean_mystic.IceBladeEffect;
import com.robertx22.mine_and_slash.potion_effects.ocean_mystic.NourishmentEffect;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class IceBladeBuff extends BaseOceanBuffSpell{
    private IceBladeBuff() {
        super(new ImmutableSpellConfigs() {
            @Override
            public Masteries school() {
                return Masteries.OCEAN;
            }

            @Override
            public SpellCastType castType() {
                return SpellCastType.AOE_EFFECT;
            }

            @Override
            public SoundEvent sound() {
                return ModSounds.FREEZE.get();
            }

            @Override
            public Elements element() {
                return Elements.Elemental;
            }
        }.addsEffect(IceBladeEffect.INSTANCE));
    }

    public static IceBladeBuff getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "ice_blade";
    }

    @Override
    public Words getName() {
        return Words.IceBlade;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(6, 2);
    }

    private static class SingletonHolder {
        private static final IceBladeBuff INSTANCE = new IceBladeBuff();
    }
}
