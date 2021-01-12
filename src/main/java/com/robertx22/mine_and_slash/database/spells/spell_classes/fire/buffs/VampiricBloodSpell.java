package com.robertx22.mine_and_slash.database.spells.spell_classes.fire.buffs;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.potion_effects.ember_mage.AttackSiphonEffect;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class VampiricBloodSpell extends BaseFireBuffSpell {
    private VampiricBloodSpell() {
        super(new ImmutableSpellConfigs() {
            @Override
            public Masteries school() {
                return Masteries.FIRE;
            }

            @Override
            public SpellCastType castType() {
                return SpellCastType.AOE_EFFECT;
            }

            @Override
            public SoundEvent sound() {
                return SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP;
            }

            @Override
            public Elements element() {
                return Elements.Elemental;
            }
        }.addsEffect(AttackSiphonEffect.INSTANCE));
    }

    public static VampiricBloodSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "vampiric_blood";
    }

    @Override
    public Words getName() {
        return Words.VampiricBlood;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(4, 3);
    }

    private static class SingletonHolder {
        private static final VampiricBloodSpell INSTANCE = new VampiricBloodSpell();
    }
}
