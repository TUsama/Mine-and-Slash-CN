/*package com.robertx22.mine_and_slash.database.gearitemslots.weapons;

import com.robertx22.mine_and_slash.data_generation.wrappers.StatModsHolder;
import com.robertx22.mine_and_slash.database.gearitemslots.WeaponDamageMulti;
import com.robertx22.mine_and_slash.database.gearitemslots.WeaponSwingCost;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseWeapon;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.PosStats;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.mechanics.NormalWeaponMechanic;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.mechanics.WeaponMechanic;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.misc.CooldownReductionFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.PhysicalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.SpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.EnergyRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealPowerFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalAttackDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalPeneFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.ElementalAttackDamagePercent;
import com.robertx22.mine_and_slash.database.stats.mods.percent.EnergyRegenPercent;
import com.robertx22.mine_and_slash.database.stats.mods.percent.ManaRegenPercent;
import com.robertx22.mine_and_slash.database.unique_items.ISpecificStatReq;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.items.gearitems.weapons.ItemGauntlets;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Gauntlets extends BaseWeapon implements ISpecificStatReq {
    public static GearItemSlot INSTANCE = new Gauntlets();

    private Gauntlets() {

    }

    @Override
    public String resourceID() {
        return "gauntlets";
    }

    @Override
    public boolean isMageWeapon() {
        return true;
    }

    @Override
    public boolean isMeleeWeapon() {
        return true;
    }

    static StatReq req = new StatReq(LvlPointStat.STAMINA, StatReq.Size.MEDIUM, LvlPointStat.INTELLIGENCE, StatReq.Size.TINY);

    @Override
    public PlayStyle getPlayStyle() {
        return PlayStyle.MAGE;
    }

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public String GUID() {
        return "gauntlets";
    }

    @Override
    public WeaponDamageMulti weaponDamageMulti() {
        return new WeaponDamageMulti(1);
    }

    @Override
    public Item getDefaultItem() {
        return ItemGauntlets.Items.get(0);
    }

    @Override
    public HashMap<Integer, Item> getItemsForRaritiesMap() {
        return ItemGauntlets.Items;
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
        return WeaponTypes.Gauntlets;
    }

    @Override
    public int Weight() {
        return 1000;
    }

    @Override
    public List<PosStats> getPossiblePrimaryStats() {
        return Arrays.asList(
            new PosStats(new PhysicalDamageFlat().size(StatMod.Size.LOW)).weight(16000),
                    new PosStats(new PhysicalDamageFlat().size(StatMod.Size.QUARTER), new ElementalAttackDamageFlat(Elements.Nature).size(StatMod.Size.LOW)).weight(3000),
                    new PosStats(new PhysicalDamageFlat().size(StatMod.Size.QUARTER), new ElementalAttackDamageFlat(Elements.Fire).size(StatMod.Size.LOW)).weight(3000),
                    new PosStats(new PhysicalDamageFlat().size(StatMod.Size.QUARTER), new ElementalAttackDamageFlat(Elements.Water).size(StatMod.Size.LOW)).weight(3000),
                    new PosStats(new PhysicalDamageFlat().size(StatMod.Size.QUARTER), new ElementalAttackDamageFlat(Elements.Thunder).size(StatMod.Size.LOW)).weight(3000),
                    new PosStats(new ElementalAttackDamageFlat(Elements.Water).size(StatMod.Size.HALF), new ElementalAttackDamageFlat(Elements.Fire).size(StatMod.Size.HALF)),
                    new PosStats(new ElementalAttackDamageFlat(Elements.Water).size(StatMod.Size.HALF), new ElementalAttackDamageFlat(Elements.Thunder).size(StatMod.Size.HALF)),
                    new PosStats(new ElementalAttackDamageFlat(Elements.Water).size(StatMod.Size.HALF), new ElementalAttackDamageFlat(Elements.Nature).size(StatMod.Size.HALF)),
                    new PosStats(new ElementalAttackDamageFlat(Elements.Fire).size(StatMod.Size.HALF), new ElementalAttackDamageFlat(Elements.Thunder).size(StatMod.Size.HALF)),
                    new PosStats(new ElementalAttackDamageFlat(Elements.Fire).size(StatMod.Size.HALF), new ElementalAttackDamageFlat(Elements.Nature).size(StatMod.Size.HALF)),
                    new PosStats(new ElementalAttackDamageFlat(Elements.Thunder).size(StatMod.Size.HALF), new ElementalAttackDamageFlat(Elements.Nature).size(StatMod.Size.HALF))
        );
    }

    @Override
    public StatModsHolder getPossibleSecondaryStats() {
        return new StatModsHolder(new EnergyRegenPercent(), new ManaRegenPercent());
    }

    @Override
    public String locNameForLangFile() {
        return "Gauntlets";
    }
}
 */