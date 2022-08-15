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
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.SpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalAttackDamageFlat;
import com.robertx22.mine_and_slash.database.unique_items.ISpecificStatReq;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.items.gearitems.weapons.ItemDagger;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Dagger extends BaseWeapon implements ISpecificStatReq {
    public static GearItemSlot INSTANCE = new Dagger();

    private Dagger() {

    }

    @Override
    public String resourceID() {
        return "dagger";
    }

    @Override
    public boolean isMageWeapon() {
        return true;
    }

    @Override
    public boolean isMeleeWeapon() {
        return true;
    }

    static StatReq req = new StatReq(LvlPointStat.INTELLIGENCE, StatReq.Size.MEDIUM, LvlPointStat.STAMINA, StatReq.Size.TINY);

    @Override
    public PlayStyle getPlayStyle() {
        return PlayStyle.THIEF;
    }

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public String GUID() {
        return "dagger";
    }

    @Override
    public WeaponDamageMulti weaponDamageMulti() {
        return new WeaponDamageMulti(1);
    }

    @Override
    public Item getDefaultItem() {
        return ItemDagger.Items.get(0);
    }

    @Override
    public HashMap<Integer, Item> getItemsForRaritiesMap() {
        return ItemDagger.Items;
    }

    @Override
    public WeaponSwingCost getSwingCosts() {
        return new WeaponSwingCost(4);
    }

    @Override
    public WeaponMechanic getWeaponMechanic() {
        return new NormalWeaponMechanic();
    }

    @Override
    public WeaponTypes weaponType() {
        return WeaponTypes.Dagger;
    }

    @Override
    public int Weight() {
        return 1000;
    }

    @Override
    public List<PosStats> getPossiblePrimaryStats() {
        return Arrays.asList(
            new PosStats(new PhysicalDamageFlat().size(StatMod.Size.LOW), new SpellDamageFlat()).weight(16000),
                    new PosStats(new PhysicalDamageFlat().size(StatMod.Size.HALF), new ElementalAttackDamageFlat(Elements.Nature).size(StatMod.Size.HALF), new SpellDamageFlat()),
                    new PosStats(new PhysicalDamageFlat().size(StatMod.Size.HALF), new ElementalAttackDamageFlat(Elements.Fire).size(StatMod.Size.HALF), new SpellDamageFlat()),
                    new PosStats(new PhysicalDamageFlat().size(StatMod.Size.HALF), new ElementalAttackDamageFlat(Elements.Water).size(StatMod.Size.HALF), new SpellDamageFlat()),
                    new PosStats(new PhysicalDamageFlat().size(StatMod.Size.HALF), new ElementalAttackDamageFlat(Elements.Thunder).size(StatMod.Size.HALF), new SpellDamageFlat())
        );
    }

    @Override
    public StatModsHolder getPossibleSecondaryStats() {
        return new StatModsHolder(new CriticalDamageFlat(), new CriticalHitFlat(), new SpellDamageFlat());
    }

    @Override
    public String locNameForLangFile() {
        return "Dagger";
    }
}