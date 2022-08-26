package com.robertx22.mine_and_slash.uncommon.enumclasses;

import com.robertx22.mine_and_slash.config.base_player_stat.BasePlayerStatContainer;
import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.localization.Chats;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public enum Masteries {

    OCEAN("ocean", Elements.Water.format, Words.Ocean, 1, Chats.OceanMastery),
    FIRE("fire", Elements.Fire.format, Words.Fire, 2, Chats.FireMastery),
    STORM("storm", Elements.Thunder.format, Words.Storm, 3, Chats.StormMastery),
    NATURE("nature", Elements.Nature.format, Words.Nature, 4, Chats.NatureMastery),
    HUNTING("hunting", TextFormatting.GREEN, Words.Hunting, 5, Chats.HuntingMastery),
    DIVINE("divine", TextFormatting.WHITE, Words.Divine, 6, Chats.DivineMastery),
    UNHOLY("unholy", TextFormatting.DARK_PURPLE, Words.Unholy, 7, Chats.UnholyMastery),
    PHYSICAL("physical", TextFormatting.GRAY, Words.Physical, 8, Chats.PhysicalMastery),
    TOTAL_MASTERY("total_mastery", TextFormatting.LIGHT_PURPLE, Words.TotalMastery, 10, Chats.TotalMastery);

    public static int MAXIMUM_POINTS = 50;

    Masteries(String id, TextFormatting format, Words locName, int place, Chats desc) {
        this.id = id;
        this.format = format;
        this.locName = locName;

        this.desc = desc;
        this.place = place;
    }

    public String id;
    public TextFormatting format;
    public Words locName;
    int place;

    public Chats desc;

    public int getBarTexXOffset() {
        return 46 + ((place - 1) * 21);
    }

    public ITextComponent getFullName() {
        return locName.locName()
            .appendText(" ")
            .appendSibling(Words.Mastery.locName());
    }

    public String getFullNameTranslated() {
        return locName.locNameForLangFile() + " " + Words.Mastery.locNameForLangFile();
    }

    public List<ExactStatData> getStatsFor(int total_mastery, EntityCap.UnitData data) { // TODO make it differ per school;
        List<ExactStatData> list = new ArrayList<>();

        BasePlayerStatContainer.INSTANCE.SPELL_MASTERY_STATS.entrySet() // gives the stats
            .forEach(x -> {
                    list.add(new ExactStatData(x.getValue()
                        .floatValue(), SlashRegistry.Stats()
                        .get(x.getKey())));
                }
            );

        // total mastery <= player level
        // effective level = total mastery - !school level
        // 30 mastery, 20 in fire, 10 in nature
        // clamp total mastery between 0 and level, not school level

        int level = getEffectiveLevel(total_mastery, data); // value you want: total mastery - !schoollevel = level
        ; // takes mastery level between 0 and player lvl

        list.sort(Comparator.comparingDouble(x -> x.getValue()));

        list.forEach(x -> x.scaleToLvl(level)); // scale to this level, not player level

        return list; // this is giving stats based on total mastery level, but clamped between 0 and player level
    }

    public int getEffectiveLevel(PlayerSpellCap.ISpellsCap spells, EntityCap.UnitData data) { // used for individual skills when they scale
        return getEffectiveLevel(spells.getAbilitiesData()
            .getTotalSchoolPoints(), data);

    }

    public static int LVL_TO_UNLOCK_2ND_SCHOOL = 10;
    public static int LVL_TO_UNLOCK_3RD_SCHOOL = 100;

    public int getEffectiveLevel(int totalSchoolLevel, EntityCap.UnitData data) { //need to fix so it scales properly

        //int total_mastery = Load.spells((PlayerEntity) data).getAbilitiesData().getTotalSchoolPoints();

        //int level = (int) (ModConfig.INSTANCE.Server.MAXIMUM_PLAYER_LEVEL.get() * ((float) totalSchoolLevel / (float) MAXIMUM_POINTS));
        int level = MathHelper.clamp(totalSchoolLevel, 0, data.getLevel());

        return level;

    }

    public ResourceLocation getGuiIconLocation() {
        return new ResourceLocation(Ref.MODID, "textures/gui/spell_schools/schools/" + id + ".png");
    }

}
