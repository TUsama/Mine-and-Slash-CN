package com.robertx22.mine_and_slash.potion_effects.ranger;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.hunting.ExertSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.hunting.ImbueSpell;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.potion_effects.bases.IOneOfATypePotion;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ExertEffect extends BasePotionEffect {

    private ExertEffect() {
        super(EffectType.BENEFICIAL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));
    }

    public static ExertEffect getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "exert";
    }

    @Override
    public String locNameForLangFile() {
        return "Exert";
    }

    @Override
    public int getMaxStacks() {
        return 1;
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs p = new PreCalcSpellConfigs();
        p.set(SC.BASE_VALUE, 0, 0);
        return p;
    }

    @Nullable
    @Override
    public BaseSpell getSpell() {
        return ExertSpell.getInstance();
    }

    @Override
    public Masteries getMastery() {
        return Masteries.HUNTING;
    }

    @Override
    public List<ITextComponent> getEffectTooltip(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Physical Spell Damage"));
        list.add(new SText(TextFormatting.GREEN + "Hunting spell arrows explode on contact"));
        list.add(new SText(TextFormatting.GREEN + "and deal half damage in a small area."));
        list.add(new SText(TextFormatting.GREEN + "This damage is affected by Elemental"));
        list.add(new SText(TextFormatting.GREEN + "Quiver."));

        list.addAll(getCalc(info.player)
            .GetTooltipString(info, Load.spells(info.player), this));

        return list;

    }

    private static class SingletonHolder {
        private static final ExertEffect INSTANCE = new ExertEffect();
    }
}

