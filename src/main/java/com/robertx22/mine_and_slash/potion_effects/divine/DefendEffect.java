package com.robertx22.mine_and_slash.potion_effects.divine;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.divine.DefendSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.fire.SpellBladeSpell;
import com.robertx22.mine_and_slash.database.stats.mods.percent.ArmorPercent;
import com.robertx22.mine_and_slash.database.stats.types.defense.Armor;
import com.robertx22.mine_and_slash.database.stats.types.defense.DamageShield;
import com.robertx22.mine_and_slash.database.stats.types.generated.BlockReflect;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalInfusion;
import com.robertx22.mine_and_slash.database.stats.types.offense.conversions.PhysicalToFireConversion;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.potion_effects.bases.IApplyStatPotion;
import com.robertx22.mine_and_slash.potion_effects.bases.data.PotionStat;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class DefendEffect extends BasePotionEffect implements IApplyStatPotion {

    public static final DefendEffect INSTANCE = new DefendEffect();

    private DefendEffect() {
        super(EffectType.BENEFICIAL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));
    }

    @Override
    public String GUID() {
        return "defend";
    }

    @Override
    public String locNameForLangFile() {
        return "Defend";
    }

    @Override
    public int getMaxStacks() {
        return 1;
    }

    @Override
    public List<PotionStat> getPotionStats() {
        List<PotionStat> list = new ArrayList<>();
        list.add(new PotionStat(25, DamageShield.getInstance()));
        list.add(new PotionStat(75, Armor.getInstance()));
        list.add(new PotionStat(20, new BlockReflect(Elements.Physical)));
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
        return DefendSpell.getInstance();
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