package com.robertx22.mine_and_slash.potion_effects.necromancer;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.unholy.SummonSkeletalArmySpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.unholy.SummonZombieSpell;
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

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class SummonedSkeletonEffect extends BasePotionEffect implements IApplyStatPotion {

    public static final SummonedSkeletonEffect INSTANCE = new SummonedSkeletonEffect();

    private SummonedSkeletonEffect() {
        super(EffectType.BENEFICIAL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));
    }

    @Override
    public String GUID() {
        return "summoned_skeleton";
    }

    @Override
    public String locNameForLangFile() {
        return "Summoned Skeleton";
    }

    @Override
    public Elements getElement(){
        return Elements.Physical;
    }

    @Override
    public int getMaxStacks() {
        return 10;
    }

    @Override
    public List<PotionStat> getPotionStats() {
        List<PotionStat> list = new ArrayList<>();
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
        return SummonSkeletalArmySpell.getInstance();
    }

    @Override
    public Masteries getMastery() {
        return Masteries.UNHOLY;
    }

    @Override
    public List<ITextComponent> getEffectTooltip(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        return list;

    }

}

