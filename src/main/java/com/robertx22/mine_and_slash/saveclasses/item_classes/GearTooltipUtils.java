package com.robertx22.mine_and_slash.saveclasses.item_classes;

import com.robertx22.mine_and_slash.database.items.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.rarities.GearRarity;
import com.robertx22.mine_and_slash.database.stats.stat_types.resources.Energy;
import com.robertx22.mine_and_slash.items.gearitems.bases.IWeapon;
import com.robertx22.mine_and_slash.items.gearitems.offhands.IEffectItem;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IStatsContainer;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.localization.CLOC;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.Tooltip;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import java.util.ArrayList;
import java.util.List;

public class GearTooltipUtils {

    public static void BuildTooltip(GearItemData gear, ItemStack stack,
                                    ItemTooltipEvent event, Unit unit, UnitData data) {

        List<ITextComponent> tip = event.getToolTip();

        TooltipInfo info = new TooltipInfo(data, gear.getRarity()
                .StatPercents(), gear.level);

        tip.clear();

        tip.add(gear.GetDisplayName(stack));

        if (gear.primaryStats != null) {
            tip.addAll(gear.primaryStats.GetTooltipString(info));
        }

        tip.add(new StringTextComponent(""));

        List<ITooltipList> list = new ArrayList<ITooltipList>();

        tip.add(TooltipUtils.lvlReq(gear.level, data));

        if (gear.runes != null) {
            tip.addAll(gear.runes.GetTooltipString(info));
        }

        tip.add(new StringTextComponent(""));

        if (Screen.hasShiftDown()) {
            list.add(gear.uniqueStats);
            list.add(gear.secondaryStats);
            list.add(gear.prefix);
            list.add(gear.suffix);
        } else {

            List<IStatsContainer.LevelAndStats> lvlstatsmerged = new ArrayList<>();

            if (gear.secondaryStats != null) {
                lvlstatsmerged.addAll(gear.secondaryStats.GetAllStats(gear.level));
            }
            if (gear.suffix != null) {
                lvlstatsmerged.addAll(gear.suffix.GetAllStats(gear.level));
            }
            if (gear.prefix != null) {
                lvlstatsmerged.addAll(gear.prefix.GetAllStats(gear.level));
            }
            if (gear.uniqueStats != null) {
                tip.addAll(gear.uniqueStats.GetTooltipString(info));
            }

            MergedStats merged = new MergedStats(lvlstatsmerged);
            list.add(merged);

        }

        list.add(gear.chaosStats);
        list.add(gear.infusion);
        list.add(gear.set);

        for (ITooltipList part : list) {

            if (part != null) {
                tip.addAll(part.GetTooltipString(info));
                tip.add(new StringTextComponent(""));
            }

        }

        if (gear.isUnique) {
            IUnique unique = gear.uniqueStats.getUniqueItem();

            tip.add(Styles.GREENCOMP()
                    .appendSibling(new StringTextComponent("'"))
                    .appendSibling(unique.locDesc())
                    .appendText("'"));

            tip.add(new StringTextComponent(""));

            tip.add(TooltipUtils.tier(unique.Tier()));

            tip.add(new StringTextComponent(""));
        }

        GearRarity rarity = gear.getRarity();
        tip.add(TooltipUtils.rarity(rarity));

        if (!gear.isSalvagable) {
            tip.add(Styles.REDCOMP().appendSibling(Words.Unsalvagable.locName()));
        }

        if (gear.GetBaseGearType() instanceof IWeapon) {
            IWeapon iwep = (IWeapon) gear.GetBaseGearType();
            tip.add(new StringTextComponent(""));
            tip.add(Styles.GREENCOMP()
                    .appendSibling(new Energy().locName()
                            .appendText(": " + iwep.mechanic().GetEnergyCost())));
            tip.add(new StringTextComponent(Styles.GREEN + "[Hit]: ").appendSibling(iwep.mechanic()
                    .tooltipDesc()));
        }

        List<ITextComponent> tool = TooltipUtils.removeDoubleBlankLines(tip, 35);
        tip.clear();
        tip.addAll(tool);

        if (Screen.hasShiftDown() == false) {
            event.getToolTip()
                    .add(Styles.BLUECOMP()
                            .appendSibling(CLOC.tooltip("press_shift_more_info")));
        } else {
            event.getToolTip()
                    .add(Styles.GOLDCOMP()
                            .appendSibling(new StringTextComponent("Power Level: " + gear.getPowerLevel())));

            if (gear.usesInstability()) {

                Tooltip.add("", event.getToolTip());

                event.getToolTip()
                        .add(Styles.REDCOMP()
                                .appendSibling(Words.Instability.locName()
                                        .appendText(": " + gear.getInstability() + "/" + gear
                                                .getMaxInstability())));

                if (gear.usesBreakChance()) {
                    event.getToolTip()
                            .add(Styles.REDCOMP()
                                    .appendSibling(Words.BreakChance.locName()
                                            .appendText(": " + gear.getBreakChance() + "%")));

                }

                Tooltip.add("", event.getToolTip());

            }

            event.getToolTip()
                    .add(Styles.BLUECOMP()
                            .appendSibling(new StringTextComponent("[Alt]: Show Detailed Stat Descriptions")));

        }

        if (stack.getItem() instanceof IEffectItem) {
            IEffectItem effect = (IEffectItem) stack.getItem();
            event.getToolTip().addAll(effect.getEffectTooltip(Screen.hasShiftDown()));
        }

    }
}
