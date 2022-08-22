package com.robertx22.mine_and_slash.database.spells.spell_classes.ocean.buffs;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModSounds;
import com.robertx22.mine_and_slash.potion_effects.ocean_mystic.FrostShieldEffect;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.util.SoundEvent;

public class FrostShieldBuff extends BaseOceanBuffSpell{
    private FrostShieldBuff() {
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
                return ModSounds.FREEZE.get();
            }

            @Override
            public Elements element() {
                return Elements.Elemental;
            }
        }.addsEffect(FrostShieldEffect.INSTANCE));
    }

    public static FrostShieldBuff getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.HEALTH_COST, 0, 0);
        c.set(SC.MANA_COST, 10, 15);
        c.set(SC.ENERGY_COST, 0, 0);
        c.set(SC.MAGIC_SHIELD_COST, 0, 0);
        c.set(SC.CAST_TIME_TICKS, 20, 10);
        c.set(SC.COOLDOWN_SECONDS, 75, 60);
        c.set(SC.DURATION_TICKS, 20 * 20, 30 * 20);
        c.set(SC.RADIUS, 4, 8);

        c.setMaxLevel(4);
        return c;
    }

    @Override
    public String GUID() {
        return "frost_shield";
    }

    @Override
    public Words getName() {
        return Words.FrostShield;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(5, 4);
    }

    private static class SingletonHolder {
        private static final FrostShieldBuff INSTANCE = new FrostShieldBuff();
    }
}
