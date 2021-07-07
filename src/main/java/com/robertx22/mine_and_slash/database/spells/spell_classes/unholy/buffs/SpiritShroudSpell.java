package com.robertx22.mine_and_slash.database.spells.spell_classes.unholy.buffs;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.divine.buffs.BaseDivineBuffSpell;
import com.robertx22.mine_and_slash.potion_effects.divine.BraveryEffect;
import com.robertx22.mine_and_slash.potion_effects.necromancer.SpiritShroudEffect;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class SpiritShroudSpell extends BaseUnholyBuffSpell {
    private SpiritShroudSpell() {
        super(new ImmutableSpellConfigs() {
            @Override
            public Masteries school() {
                return Masteries.UNHOLY;
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
        }.addsEffect(SpiritShroudEffect.INSTANCE));
    }

    public static SpiritShroudSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "spirit_shroud";
    }

    @Override
    public Words getName() {
        return Words.SpiritShroud;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(6, 2);
    }

    private static class SingletonHolder {
        private static final SpiritShroudSpell INSTANCE = new SpiritShroudSpell();
    }
}
