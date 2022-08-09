package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.game_changers.ManaBatteryEffect;
import com.robertx22.mine_and_slash.database.stats.effects.game_changers.ShulkerShellEffect;
import com.robertx22.mine_and_slash.database.stats.types.defense.Armor;
import com.robertx22.mine_and_slash.database.stats.types.defense.DodgeRating;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalResist;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

import java.util.Arrays;
import java.util.List;

public class ShulkerShell extends BaseGameChangerTrait implements IStatEffects {

    private ShulkerShell() {
    }

    public static final Stat INSTANCE = new ShulkerShell();

    @Override
    public String locDescForLangFile() {
        return "60 percent of non-physical damage is taken as physical damage instead.";
    }

    @Override
    public String getIconPath() {
        return "game_changers/shulker_shell";
    }

    @Override
    public String locNameForLangFile() {
        return "Shulker Shell";
    }

    @Override
    public String GUID() {
        return "shulker_shell_trait";
    }

    @Override
    public List<ExactStatData> getExactStats() {
        return Arrays.asList(
            new ExactStatData(-20, StatModTypes.Multi, Armor.getInstance()),
            new ExactStatData(-12, StatModTypes.Flat, new ElementalResist(Elements.Elemental))
        );
    }

    @Override
    public IStatEffect getEffect() {
        return ShulkerShellEffect.INSTANCE;
    }
}


