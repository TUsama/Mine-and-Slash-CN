package com.robertx22.mine_and_slash.database.gearitemslots.weapons;

import com.robertx22.mine_and_slash.data_generation.wrappers.StatModsHolder;
import com.robertx22.mine_and_slash.database.gearitemslots.WeaponDamageMulti;
import com.robertx22.mine_and_slash.database.gearitemslots.WeaponSwingCost;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseWeapon;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.PosStats;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.mechanics.HammerWeaponMechanic;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.mechanics.WeaponMechanic;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.ArmorPenetrationFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.PhysicalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.ManaFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.ManaOnHitFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalAttackDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.PhysicalDamagePercent;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.SpellDamagePercent;
import com.robertx22.mine_and_slash.database.unique_items.ISpecificStatReq;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.items.gearitems.weapons.ItemHammer;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Hammer extends BaseWeapon implements ISpecificStatReq {
    public static GearItemSlot INSTANCE = new Hammer();

    private Hammer() {

    }

    @Override
    public String resourceID() {
        return "hammer";
    }

    @Override
    public boolean isMeleeWeapon() {
        return true;
    }

    @Override
    public WeaponTypes weaponType() {
        return WeaponTypes.Hammer;
    }

    @Override
    public WeaponMechanic getWeaponMechanic() {
        return new HammerWeaponMechanic();
    }

    @Override
    public WeaponSwingCost getSwingCosts() {
        return new WeaponSwingCost(11);
    }

    @Override
    public WeaponDamageMulti weaponDamageMulti() {
        return new WeaponDamageMulti(1);
    }

    @Override
    public PlayStyle getPlayStyle() {
        return PlayStyle.WARRIOR;
    }

    static StatReq req = new StatReq(
        LvlPointStat.STRENGTH, StatReq.Size.NORMAL);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public String GUID() {
        return "hammer";
    }

    @Override
    public Item getDefaultItem() {
        return ItemHammer.Items.get(0);
    }

    @Override
    public HashMap<Integer, Item> getItemsForRaritiesMap() {
        return ItemHammer.Items;
    }

    @Override
    public int Weight() {
        return 1000;
    }

    @Override
    public List<PosStats> getPossiblePrimaryStats() {
        return Arrays.asList(
                new PosStats(new PhysicalDamageFlat().size(StatMod.Size.THREE_QUART_MORE))
        );
    }

    @Override
    public StatModsHolder getPossibleSecondaryStats() {
        return new StatModsHolder(new ArmorPenetrationFlat(), new CriticalDamageFlat(), new PhysicalDamagePercent());
    }

    @Override
    public String locNameForLangFile() {
        return "Hammer";
    }
}
