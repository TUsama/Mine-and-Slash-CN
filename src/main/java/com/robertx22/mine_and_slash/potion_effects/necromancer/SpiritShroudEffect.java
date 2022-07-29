package com.robertx22.mine_and_slash.potion_effects.necromancer;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.divine.buffs.BraverySpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.unholy.buffs.SpiritShroudSpell;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Strength;
import com.robertx22.mine_and_slash.database.stats.types.defense.BlockStrength;
import com.robertx22.mine_and_slash.database.stats.types.resources.Health;
import com.robertx22.mine_and_slash.database.stats.types.resources.MagicShield;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.potion_effects.bases.IApplyStatPotion;
import com.robertx22.mine_and_slash.potion_effects.bases.IOneOfATypePotion;
import com.robertx22.mine_and_slash.potion_effects.bases.data.PotionStat;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class SpiritShroudEffect extends BasePotionEffect implements IApplyStatPotion {

    public static final SpiritShroudEffect INSTANCE = new SpiritShroudEffect();

    private SpiritShroudEffect() {
        super(EffectType.BENEFICIAL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));
    }

    @Override
    public String GUID() {
        return "spirit_shroud";
    }

    @Override
    public String locNameForLangFile() {
        return "Spirit Shroud";
    }

    @Override
    public int getMaxStacks() {
        return 1;
    }

    @Override
    public List<PotionStat> getPotionStats() {
        List<PotionStat> list = new ArrayList<>();
        list.add(new PotionStat(25, MagicShield.getInstance()));
        return list;
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs p = new PreCalcSpellConfigs();
        return p;
    }

    @Nullable
    @Override
    public BaseSpell getSpell() {
        return SpiritShroudSpell.getInstance();
    }

    @Override
    public Masteries getMastery() {
        return getSpell().getMastery();
    }

    @Override
    public List<ITextComponent> getEffectTooltip(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();
        return list;
    }

}

