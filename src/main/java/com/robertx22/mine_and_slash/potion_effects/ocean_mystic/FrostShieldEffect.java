package com.robertx22.mine_and_slash.potion_effects.ocean_mystic;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.divine.buffs.BraverySpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ocean.buffs.FrostShieldBuff;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Strength;
import com.robertx22.mine_and_slash.database.stats.types.defense.Armor;
import com.robertx22.mine_and_slash.database.stats.types.resources.Health;
import com.robertx22.mine_and_slash.database.stats.types.resources.HealthRegen;
import com.robertx22.mine_and_slash.database.stats.types.resources.MagicShield;
import com.robertx22.mine_and_slash.database.stats.types.resources.MagicShieldRegen;
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

public class FrostShieldEffect extends BasePotionEffect implements IApplyStatPotion {

    public static final FrostShieldEffect INSTANCE = new FrostShieldEffect();

    private FrostShieldEffect() {
        super(EffectType.BENEFICIAL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));
    }

    @Override
    public String GUID() {
        return "frost_shield_effect";
    }

    @Override
    public String locNameForLangFile() {
        return "Frost Shield";
    }

    @Override
    public int getMaxStacks() {
        return 1;
    }

    @Override
    public List<PotionStat> getPotionStats() {
        List<PotionStat> list = new ArrayList<>();
        list.add(new PotionStat(40, MagicShield.getInstance()));
        list.add(new PotionStat(80, MagicShieldRegen.getInstance()));
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
        return FrostShieldBuff.getInstance();
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

