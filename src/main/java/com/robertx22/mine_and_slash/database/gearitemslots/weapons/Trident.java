package com.robertx22.mine_and_slash.database.gearitemslots.weapons;

import com.robertx22.mine_and_slash.data_generation.wrappers.StatModsHolder;
import com.robertx22.mine_and_slash.database.gearitemslots.WeaponDamageMulti;
import com.robertx22.mine_and_slash.database.gearitemslots.WeaponSwingCost;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseWeapon;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.mechanics.NormalWeaponMechanic;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.mechanics.WeaponMechanic;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.ArmorPenetrationFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalHitFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.LifeOnHitFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.LifestealFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.MagicStealFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalPeneFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.PhysicalDamagePercent;
import com.robertx22.mine_and_slash.database.unique_items.ISpecificStatReq;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.HashMap;

public class Trident extends BaseWeapon implements ISpecificStatReq {
    public static GearItemSlot INSTANCE = new Trident();

    private Trident() {

    }

    static StatReq req = new StatReq(LvlPointStat.INTELLIGENCE, StatReq.Size.TINY, LvlPointStat.VITALITY, StatReq.Size.TINY);

    @Override
    public boolean isMeleeWeapon() {
        return false;
    }

    @Override
    public PlayStyle getPlayStyle() {
        return PlayStyle.WARRIOR;
    }

    @Override
    public WeaponDamageMulti weaponDamageMulti() {
        return new WeaponDamageMulti(1);
    }

    @Override
    public WeaponSwingCost getSwingCosts() {
        return new WeaponSwingCost(7);
    }

    @Override
    public WeaponTypes weaponType() {
        return WeaponTypes.Trident;
    }

    @Override
    public WeaponMechanic getWeaponMechanic() {
        return new NormalWeaponMechanic();
    }

    @Override
    public String resourceID() {
        return "trident";
    }

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public String GUID() {
        return "trident";
    }

    @Override
    public Item getDefaultItem() {
        return Items.TRIDENT;
    }

    @Override
    public HashMap<Integer, Item> getItemsForRaritiesMap() {
        return new HashMap<>();
    }

    @Override
    public StatModsHolder getPossibleSecondaryStats() {
        return new StatModsHolder(new ArmorPenetrationFlat(), new ElementalPeneFlat(Elements.Fire), new ElementalPeneFlat(Elements.Water)
                , new ElementalPeneFlat(Elements.Thunder), new ElementalPeneFlat(Elements.Nature));
    }

    @Override
    public int Weight() {
        return 800;
    }

    @Override
    public String locNameForLangFile() {
        return "Trident";
    }
}