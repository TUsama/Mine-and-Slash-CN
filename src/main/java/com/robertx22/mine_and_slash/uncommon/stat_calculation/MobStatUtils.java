package com.robertx22.mine_and_slash.uncommon.stat_calculation;

import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.config.whole_mod_entity_configs.ModEntityConfig;
import com.robertx22.mine_and_slash.database.rarities.MobRarity;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.types.defense.Armor;
import com.robertx22.mine_and_slash.database.stats.types.defense.ArmorPenetration;
import com.robertx22.mine_and_slash.database.stats.types.defense.DodgeRating;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalPene;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalResist;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalSpellDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalHit;
import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.SpellDamage;
import com.robertx22.mine_and_slash.database.stats.types.resources.Health;
import com.robertx22.mine_and_slash.database.stats.types.resources.HealthRegen;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

import java.util.stream.Collectors;

public class MobStatUtils {

    public static void increaseMobStatsPerTier(UnitData mobdata, Unit unit) {

        for (StatData data : unit.getStats()
            .values()
            .stream()
            .filter(x -> {
                return x.GetStat()
                    .IsPercent() == false;
            })
            .collect(Collectors.toList())) {

            data.multiplyFlat(mobdata.getMapTier().mob_stat_multi);
        }

    }

    public static void addAffixStats(UnitData data) {

        if (data.getUnit()
            .getPrefix() != null) {
            data.getUnit()
                .getPrefix()
                .applyStats(data);
        }
        if (data.getUnit()
            .getSuffix() != null) {
            data.getUnit()
                .getSuffix()
                .applyStats(data);
        }
    }

    public static void increaseMobStatsPerLevel(UnitData mobdata) {

        float lvlMulti = (float) (Math.pow(mobdata.getLevel(), mobdata.getLevel() / ModConfig.INSTANCE.Server.MOB_STRENGTH_PER_LEVEL_MULTI.get()
                        .floatValue())) - 0.25F;

        for (StatData data : mobdata.getUnit()
            .getStats()
            .values()
            .stream()
            .filter(x -> x.GetStat()
                .IsPercent() == false)
            .collect(Collectors.toList())) {

            data.multiplyFlat(lvlMulti);
        }

    }

    public static void worldMultiplierStats(World world, Unit unit) {
        for (StatData stat : unit.getStats()
            .values()) {
            stat.multiplyFlat(SlashRegistry.getDimensionConfig(world).MOB_STRENGTH_MULTIPLIER);
            if (stat.getId()
                    .equals(Health.GUID)) {
                stat.multiplyFlat(SlashRegistry.getDimensionConfig(world).MOB_HP_MULTIPLIER);
            }
        }

    }

    public static void modifyMobStatsByConfig(LivingEntity entity, UnitData unitdata) {
        Unit unit = unitdata.getUnit();
        ModEntityConfig config = SlashRegistry.getEntityConfig(entity, unitdata);

        for (StatData data : unit.getStats()
            .values()) {
            Stat stat = data.GetStat();
            if (stat instanceof PhysicalDamage || stat instanceof SpellDamage || stat instanceof CriticalDamage || stat instanceof CriticalHit) {
                data.multiplyFlat(config.DMG_MULTI);
            } else if (data.getId()
                .equals(Health.GUID)) {
                data.multiplyFlat(config.HP_MULTI);
            } else {
                data.multiplyFlat(config.STAT_MULTI);
            }
        }

    }

    public static void modifyMobHealthByPlayercount(UnitData unitdata, int playercount) {
        Unit unit = unitdata.getUnit();

        for (StatData stat : unit.getStats()
                .values()) {
            if (stat.getId()
                    .equals(Health.GUID)) {
                stat.multiplyFlat(0.9 + 0.1 * playercount);
            }
        }

    }

    public static void AddMobcStats(UnitData unitdata, int level) {

        MobRarity rar = Rarities.Mobs.get(unitdata.getRarity());
        Unit unit = unitdata.getUnit();
        unit.getCreateStat(HealthRegen.GUID)
                .addFlat(1 * rar.StatMultiplier(), level);
        unit.getCreateStat(Armor.GUID)
            .addFlat(Armor.getInstance()
                .AverageStat() * rar.StatMultiplier(), level);
        unit.getCreateStat(PhysicalDamage.GUID).addFlat(8 * rar.StatMultiplier(), level); // base amount used in weakencurse and bloody strike
        unit.getCreateStat(SpellDamage.GUID)
                .addFlat(6 * rar.StatMultiplier(), level);
        //unit.getCreateStat(DodgeRating.GUID).addFlat(DodgeRating.getInstance().AverageStat() * rar.StatMultiplier() * 0.33F, level);
        unit.getCreateStat(CriticalHit.GUID)
            .addFlat(1 * rar.StatMultiplier(), level);
        unit.getCreateStat(CriticalDamage.GUID)
            .addFlat(5 * rar.StatMultiplier(), level);
        unit.getCreateStat("water_resist")
                .addFlat(15F * rar.StatMultiplier());
        unit.getCreateStat("thunder_resist")
                .addFlat(15F * rar.StatMultiplier());
        unit.getCreateStat("fire_resist")
                .addFlat(15F * rar.StatMultiplier());
        unit.getCreateStat("nature_resist")
                .addFlat(15F * rar.StatMultiplier());
    }

}
