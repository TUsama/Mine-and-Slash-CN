package com.robertx22.mine_and_slash.database.gearitemslots.weapons;

import com.robertx22.mine_and_slash.data_generation.wrappers.StatModsHolder;
import com.robertx22.mine_and_slash.database.gearitemslots.WeaponDamageMulti;
import com.robertx22.mine_and_slash.database.gearitemslots.WeaponSwingCost;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseWeapon;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.PosStats;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.mechanics.NormalWeaponMechanic;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.mechanics.WeaponMechanic;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalHitFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.PhysicalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.*;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalAttackDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.PhysicalDamagePercent;
import com.robertx22.mine_and_slash.database.unique_items.ISpecificStatReq;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.items.gearitems.weapons.ItemAxe;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Axe extends BaseWeapon implements ISpecificStatReq {
    public static GearItemSlot INSTANCE = new Axe();

    private Axe() {

    }

    static StatReq req = new StatReq(LvlPointStat.STRENGTH, StatReq.Size.MEDIUM, LvlPointStat.STAMINA, StatReq.Size.SMALL);

    @Override
    public boolean isMeleeWeapon() {
        return true;
    }

    @Override
    public WeaponDamageMulti weaponDamageMulti() {
        return new WeaponDamageMulti(1);
    }

    @Override
    public WeaponMechanic getWeaponMechanic() {
        return new NormalWeaponMechanic();
    }

    @Override
    public PlayStyle getPlayStyle() {
        return PlayStyle.WARRIOR;
    }

    @Override
    public String resourceID() {
        return "axe";
    }

    @Override
    public WeaponTypes weaponType() {
        return WeaponTypes.Axe;
    }

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public String GUID() {
        return "axe";
    }

    @Override
    public Item getDefaultItem() {
        return ItemAxe.Items.get(0);
    }

    @Override
    public HashMap<Integer, Item> getItemsForRaritiesMap() {
        return ItemAxe.Items;
    }

    @Override
    public int Weight() {
        return 1000;
    }

    @Override
    public String locNameForLangFile() {
        return "Axe";
    }

    @Override
    public List<PosStats> getPossiblePrimaryStats() {
        return Arrays.asList(
                new PosStats(new PhysicalDamageFlat().size(StatMod.Size.QUARTER_MORE)).weight(26000),
                new PosStats(new PhysicalDamageFlat().size(StatMod.Size.LOW), new ElementalAttackDamageFlat(Elements.Nature).size(StatMod.Size.LOW)),
                new PosStats(new PhysicalDamageFlat().size(StatMod.Size.LOW), new ElementalAttackDamageFlat(Elements.Fire).size(StatMod.Size.LOW)),
                new PosStats(new PhysicalDamageFlat().size(StatMod.Size.LOW), new ElementalAttackDamageFlat(Elements.Water).size(StatMod.Size.LOW)),
                new PosStats(new PhysicalDamageFlat().size(StatMod.Size.LOW), new ElementalAttackDamageFlat(Elements.Thunder).size(StatMod.Size.LOW))
        );
    }

    @Override
    public StatModsHolder getPossibleSecondaryStats() {
        return new StatModsHolder(new CriticalDamageFlat(), new CriticalHitFlat(), new PhysicalDamagePercent());
    }

    @Override
    public WeaponSwingCost getSwingCosts() {
        return new WeaponSwingCost(8);
    }
}