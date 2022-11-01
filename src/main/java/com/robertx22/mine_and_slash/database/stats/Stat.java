package com.robertx22.mine_and_slash.database.stats;

import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.saveclasses.item_classes.tooltips.TooltipStatInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocDesc;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.StatUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public abstract class Stat implements IGUID, IAutoLocName, IWeighted, IRarity, IAutoLocDesc, ISlashRegistryEntry {

    public Stat() {
    }

    public boolean isInt = false;

    @Override
    public boolean isRegistryEntryValid() {

        return true;
    }

    public TextFormatting getIconFormat() {
        if (this.getElement() != null) {
            return this.getElement().format;
        } else {
            return Elements.Physical.format;
        }
    }

    public String getIcon() {
        if (this.getElement() != null) {
            return this.getElement().icon;
        } else {
            return Elements.Physical.icon;
        }
    }

    public String getFormattedIcon() {
        return getIconFormat() + getIcon();
    }

    public List<ITextComponent> getCutDescTooltip() {
        List<ITextComponent> list = new ArrayList<>();

        List<ITextComponent> cut = TooltipUtils.cutIfTooLong(locDesc());

        for (int i = 0; i < cut.size(); i++) {

            ITextComponent comp = Styles.BLUECOMP();
            if (i == 0) {
                comp.appendText(" [");
            }
            comp.appendSibling(cut.get(i));

            if (i == cut.size() - 1) {
                comp.appendText("]");
            }

            list.add(comp);

        }
        return list;
    }

    public enum StatType {
        NORMAL, CORE, TRAIT
    }

    public StatType getStatType() {
        return StatType.NORMAL;
    }

    public boolean isTrait() {
        return getStatType().equals(StatType.TRAIT);
    }

    @Override
    public int getRarityRank() {
        return IRarity.Uncommon;
    }

    // this is used for alltraitmods, check if confused
    @Override
    public int Weight() {
        return this.getRarity()
            .Weight();
    }

    @Override
    public Rarity getRarity() {
        return Rarities.Gears.get(getRarityRank());
    }

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.STAT;
    }

    public String getIconPath() {
        return "";
    }

    public ResourceLocation getIconLocation() {
        if (getIconPath().isEmpty()) {
            return new ResourceLocation(Ref.MODID, "textures/gui/stat_icons/default.png");
        } else {
            return new ResourceLocation(Ref.MODID, "textures/gui/stat_icons/" + getIconPath() + ".png");
        }

    }

    @Override
    public AutoLocGroup locDescGroup() {
        return AutoLocGroup.Stats;

    }

    @Override
    public String locNameLangFileGUID() {
        return Ref.MODID + ".stat." + formattedGUID();
    }

    @Override
    public String locDescLangFileGUID() {
        return Ref.MODID + ".stat_desc." + formattedGUID();
    }

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Stats;
    }

    public int maximumValue = Integer.MAX_VALUE;

    public float minimumValue = -1000;

    public abstract boolean IsPercent();

    public boolean IsEleRes() {
        return false;
    }

    public abstract Elements getElement();

    public int BaseFlat = 0;

    public final float calculateScalingStatGrowth(float stat, int lvl) {
        return getScaling().scale(stat, lvl);
    }

    public StatScaling getScaling() {
        return StatScaling.NORMAL;
    }

    public float roundScalingStatGrowth(float stat, int lvl) {
        return StatUtils.roundNumber(this.calculateScalingStatGrowth(stat, lvl));
    }

    public String printValue(float val) {

        DecimalFormat format = new DecimalFormat();

        if (val < 5) {
            format.setMaximumFractionDigits(1);

            return format.format(val);

        } else {

            int intval = (int) val;
            return intval + "";

        }

    }

    @OnlyIn(Dist.CLIENT)
    public List<ITextComponent> getTooltipList(TooltipStatInfo info) {
        return info.tooltipInfo.statTooltipType.impl.getTooltipList(info);
    }

    public boolean IsShownOnStatGui() {
        return true;
    }

    public StatGroup statGroup() {
        return StatGroup.Misc;
    }

    public enum StatGroup {
        Main(Words.Main, 0),
        Misc(Words.Misc, 8),
        CoreStat(Words.Core_Stat, 5),
        SpellDamage(Words.Spell_Damage, 3),
        EleAttackDamage(Words.Elemental_Attack_Damage, 2),
        Defenses(Words.Defenses, 4),
        Penetration(Words.Penetration, 6),
        Damage(Words.Damage, 1),
        Regeneration(Words.Regeneration, 7);

        StatGroup(Words word, int place) {
            this.place = place;
            this.word = word;
        }

        public Words word;

        public final int width = 18;
        public final int height = 18;

        public int place = 0;

        public final int Y = 8;

        public int X() {
            return 25 + width * place;
        }
    }

}
