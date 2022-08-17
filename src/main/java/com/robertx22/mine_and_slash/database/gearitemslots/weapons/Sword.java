package com.robertx22.mine_and_slash.database.gearitemslots.weapons;

import com.robertx22.mine_and_slash.data_generation.wrappers.StatModsHolder;
import com.robertx22.mine_and_slash.database.gearitemslots.WeaponDamageMulti;
import com.robertx22.mine_and_slash.database.gearitemslots.WeaponSwingCost;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseWeapon;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.PosStats;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalHitFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.PhysicalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.*;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalAttackDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.EnergyRegenPercent;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.PhysicalDamagePercent;
import com.robertx22.mine_and_slash.database.unique_items.ISpecificStatReq;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.items.gearitems.weapons.ItemSword;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Sword extends BaseWeapon implements ISpecificStatReq {
    public static GearItemSlot INSTANCE = new Sword();

    private Sword() {

    }

    @Override
    public String resourceID() {
        return "sword";
    }

    @Override
    public boolean isMeleeWeapon() {
        return true;
    }

    static StatReq req = new StatReq(LvlPointStat.STRENGTH, StatReq.Size.MEDIUM, LvlPointStat.VITALITY, StatReq.Size.TINY);

    @Override
    public WeaponSwingCost getSwingCosts() {
        return new WeaponSwingCost(5F);
    }

    @Override
    public WeaponTypes weaponType() {
        return WeaponTypes.Sword;
    }

    @Override
    public PlayStyle getPlayStyle() {
        return PlayStyle.THIEF;
    }

    @Override
    public WeaponDamageMulti weaponDamageMulti() {
        return new WeaponDamageMulti(1);
    }

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public String GUID() {
        return "sword";
    }

    @Override
    public Item getDefaultItem() {
        return ItemSword.Items.get(0);
    }

    @Override
    public String locNameForLangFile() {
        return "Sword";
    }

    @Override
    public HashMap<Integer, Item> getItemsForRaritiesMap() {
        return ItemSword.Items;
    }

    @Override
    public List<PosStats> getPossiblePrimaryStats() {
        return Arrays.asList(
                new PosStats(new PhysicalDamageFlat().size(StatMod.Size.QUARTER_MORE), new EnergyRegenFlat().size(StatMod.Size.HALF)).weight(26000),
                new PosStats(new PhysicalDamageFlat().size(StatMod.Size.LOW), new ElementalAttackDamageFlat(Elements.Nature).size(StatMod.Size.HALF), new EnergyRegenFlat().size(StatMod.Size.HALF)),
                new PosStats(new PhysicalDamageFlat().size(StatMod.Size.LOW), new ElementalAttackDamageFlat(Elements.Fire).size(StatMod.Size.HALF), new EnergyRegenFlat().size(StatMod.Size.HALF)),
                new PosStats(new PhysicalDamageFlat().size(StatMod.Size.LOW), new ElementalAttackDamageFlat(Elements.Water).size(StatMod.Size.HALF), new EnergyRegenFlat().size(StatMod.Size.HALF)),
                new PosStats(new PhysicalDamageFlat().size(StatMod.Size.LOW), new ElementalAttackDamageFlat(Elements.Thunder).size(StatMod.Size.HALF), new EnergyRegenFlat().size(StatMod.Size.HALF))
        );
    }

    @Override
    public StatModsHolder getPossibleSecondaryStats() {
        return new StatModsHolder(new LifestealFlat(), new LifeOnHitFlat(), new ManaOnHitFlat(), new PhysicalDamagePercent());
    }

    @Override
    public int Weight() {
        return 1500;
    }

}
