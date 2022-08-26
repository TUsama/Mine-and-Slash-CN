package com.robertx22.mine_and_slash.potion_effects.physical;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.stats.types.resources.EnergyRegen;
import com.robertx22.mine_and_slash.database.stats.types.spell_calc.FasterCastRate;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.potion_effects.bases.IApplyStatPotion;
import com.robertx22.mine_and_slash.potion_effects.bases.OnTickAction;
import com.robertx22.mine_and_slash.potion_effects.bases.data.PotionStat;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class EnlightenEffect extends BasePotionEffect implements IApplyStatPotion {

    public static final EnlightenEffect INSTANCE = new EnlightenEffect();

    private EnlightenEffect() {
        super(EffectType.BENEFICIAL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));

        this.tickActions.add(new OnTickAction(ctx -> {
            ParticleUtils.spawnParticles(ParticleTypes.WITCH, ctx.entity, 5);
            return ctx;
        }, null));
    }

    @Override
    public String GUID() {
        return "enlighten";
    }

    @Override
    public String locNameForLangFile() {
        return "Enlighten";
    }

    @Override
    public Elements getElement(){
        return Elements.Physical;
    }

    @Override
    public int getMaxStacks() {
        return 5;
    }

    @Override
    public List<PotionStat> getPotionStats() {
        List<PotionStat> list = new ArrayList<>();
        list.add(new PotionStat(3, EnergyRegen.getInstance()));
        list.add(new PotionStat(3, FasterCastRate.getInstance()));
        return list;
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs p = new PreCalcSpellConfigs();
        p.set(SC.DURATION_TICKS, 220, 260);
        return p;
    }

    @Nullable
    @Override
    public BaseSpell getSpell() {
        return null;
    }

    @Override
    public Masteries getMastery() {
        return Masteries.PHYSICAL;
    }

    @Override
    public List<ITextComponent> getEffectTooltip(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        return list;

    }

}

