package com.robertx22.mine_and_slash.database.talent_tree.data;

import com.robertx22.mine_and_slash.database.stats.types.core_stats.*;
import com.robertx22.mine_and_slash.database.stats.types.defense.Armor;
import com.robertx22.mine_and_slash.database.stats.types.defense.ArmorPenetration;
import com.robertx22.mine_and_slash.database.stats.types.defense.BlockStrength;
import com.robertx22.mine_and_slash.database.stats.types.defense.DodgeRating;
import com.robertx22.mine_and_slash.database.stats.types.elementals.all_damage.AllDotDmg;
import com.robertx22.mine_and_slash.database.stats.types.generated.*;
import com.robertx22.mine_and_slash.database.stats.types.offense.*;
import com.robertx22.mine_and_slash.database.stats.types.offense.conversions.PhysicalToFireConversion;
import com.robertx22.mine_and_slash.database.stats.types.offense.conversions.PhysicalToNatureConversion;
import com.robertx22.mine_and_slash.database.stats.types.offense.conversions.PhysicalToThunderConversion;
import com.robertx22.mine_and_slash.database.stats.types.offense.conversions.PhysicalToWaterConversion;
import com.robertx22.mine_and_slash.database.stats.types.resources.*;
import com.robertx22.mine_and_slash.database.stats.types.spell_calc.*;
import com.robertx22.mine_and_slash.database.talent_tree.PerkEffectBuilder;
import com.robertx22.mine_and_slash.database.talent_tree.PerkEffectsWrapper;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

import java.util.HashMap;
import java.util.Locale;

public class PerkEffects {

    public static PerkEffectsWrapper SPELL_DMG;
    public static PerkEffectsWrapper CRIT_HIT;
    public static PerkEffectsWrapper CRIT_DMG;

    public static PerkEffectsWrapper INTELLIGENCE;
    public static PerkEffectsWrapper WISDOM;
    public static PerkEffectsWrapper STAMINA;
    public static PerkEffectsWrapper DEXTERITY;
    public static PerkEffectsWrapper VITALITY;
    public static PerkEffectsWrapper STRENGTH;

    public static PerkEffectsWrapper MAGIC_SHIELD_PERCENT;
    public static PerkEffectsWrapper MANA_PERCENT;
    public static PerkEffectsWrapper BLOCK_PERCENT;
    public static PerkEffectsWrapper HEALTH_PERCENT;
    public static PerkEffectsWrapper ENERGY_PERCENT;
    public static PerkEffectsWrapper DODGE_PERCENT;
    public static PerkEffectsWrapper ARMOR_PERCENT;
    public static PerkEffectsWrapper MANA_REGEN_PERCENT;
    public static PerkEffectsWrapper HEALTH_REGEN_PERCENT;
    public static PerkEffectsWrapper ENERGY_REGEN_PERCENT;
    public static PerkEffectsWrapper MAGIC_SHIELD_REGEN_PERCENT;
    public static PerkEffectsWrapper PHYSICAL_DMG;
    public static PerkEffectsWrapper SPELLSTEAL;
    public static PerkEffectsWrapper SPELLSTEAL_PERCENT;
    public static PerkEffectsWrapper LIFESTEAL;
    public static PerkEffectsWrapper LIFESTEAL_PERCENT;
    public static PerkEffectsWrapper SPELLSTEAL_HALF;
    public static PerkEffectsWrapper LIFESTEAL_HALF;
    public static PerkEffectsWrapper HEAL_PWR;
    public static PerkEffectsWrapper CDR;
    public static PerkEffectsWrapper PHYS_TO_FIRE;
    public static PerkEffectsWrapper PHYS_TO_WATER;
    public static PerkEffectsWrapper PHYS_TO_THUNDER;
    public static PerkEffectsWrapper PHYS_TO_NATURE;
    public static PerkEffectsWrapper DOT_DMG;
    public static PerkEffectsWrapper SUMMON_DMG;
    public static PerkEffectsWrapper ARMOR_PEN;
    public static PerkEffectsWrapper FCR;
    public static PerkEffectsWrapper MANA_COST;
    public static PerkEffectsWrapper PROJ_SPEED;
    public static PerkEffectsWrapper INCREASED_DURATION;
    public static PerkEffectsWrapper AREA;

    // COMBINED EFFECTS
    public static PerkEffectsWrapper MANA_PERC_PLUS_MAGIC_SHIELD_PERCENT, CRIT_HIT_CRIT_DMG, CDR_FCR, SPELL_SUMMON_DMG, CRIT_HIT_DODGE;
    public static PerkEffectsWrapper INT_WIS, STR_STA, STR_CRIT_DMG, ENE_ENE_REGEN, STA_DEX, DODGE_ENE_REGEN, DODGE_ENE, HP_ENE, HP_DODGE, HP_ARMOR;
    public static PerkEffectsWrapper HP_HP_REGEN, PHYS_DMG_CRIT_DMG, PHYS_DMG_CRIT_HIT, HP_PHYS_DMG, MANA_MANA_REGEN, HP_MANA, LIFE_SPELL_STEAL, MANA_ENE_REGEN;

    public static HashMap<WeaponTypes, PerkEffectsWrapper> WEP_DMG_MAP = new HashMap<>();
    public static HashMap<WeaponTypes, PerkEffectsWrapper> WEP_ELE_DMG_MAP = new HashMap<>();

    public static HashMap<Elements, PerkEffectsWrapper> ELE_RES_PERCENT_MAP = new HashMap<>();
    public static HashMap<Elements, PerkEffectsWrapper> SPELL_DMG_PERCENT_MAP = new HashMap<>();
    public static HashMap<Elements, PerkEffectsWrapper> ATTACK_DAMAGE_PERCENT_MAP = new HashMap<>();
    public static HashMap<Elements, PerkEffectsWrapper> SPELL_TO_ATK_DAMAGE_MAP = new HashMap<>();
    public static HashMap<Elements, PerkEffectsWrapper> SPELL_TO_ATK_DAMAGE_PERCENT_MAP = new HashMap<>();
    public static HashMap<Elements, PerkEffectsWrapper> All_ELE_DMG_MAP = new HashMap<>();
    public static HashMap<Elements, PerkEffectsWrapper> ELE_PENE_PERCENT_MAP = new HashMap<>();

    public static void create() {

        PHYSICAL_DMG = PerkEffectBuilder.build(
            "phys_dmg", PhysicalDamage.getInstance(), new ExactStatData(5, StatModTypes.Percent, PhysicalDamage.GUID));
        SPELL_DMG = PerkEffectBuilder.build(
            "spell_dmg", SpellDamage.getInstance(), new ExactStatData(3, StatModTypes.Flat, SpellDamage.GUID));
        HEAL_PWR = PerkEffectBuilder.build(
                "heal_pwr", HealPower.getInstance(), new ExactStatData(5, StatModTypes.Flat, HealPower.GUID));
        CDR = PerkEffectBuilder.build(
                "cdr", ReducedCooldownStat.getInstance(), new ExactStatData(2, StatModTypes.Flat, ReducedCooldownStat.GUID));
        CRIT_HIT = PerkEffectBuilder.build(
            "crit_hit", CriticalHit.getInstance(), new ExactStatData(1, StatModTypes.Flat, CriticalHit.GUID));
        CRIT_DMG = PerkEffectBuilder.build(
            "crit_dmg", CriticalDamage.getInstance(), new ExactStatData(3F, StatModTypes.Flat, CriticalDamage.GUID));
        BLOCK_PERCENT = PerkEffectBuilder.build(
            "block_percent", BlockStrength.INSTANCE, new ExactStatData(0.5F, StatModTypes.Flat, BlockStrength.GUID));
        SPELLSTEAL = PerkEffectBuilder.build(
                "spellsteal", SpellSteal.getInstance(), new ExactStatData(0.5F, StatModTypes.Flat, SpellSteal.GUID));
        SPELLSTEAL_PERCENT = PerkEffectBuilder.build(
                "spellsteal_percent", SpellSteal.getInstance(), new ExactStatData(5, StatModTypes.Percent, SpellSteal.GUID));
        LIFESTEAL = PerkEffectBuilder.build(
                "lifesteal", Lifesteal.getInstance(), new ExactStatData(0.5F, StatModTypes.Flat, Lifesteal.GUID));
        LIFESTEAL_PERCENT = PerkEffectBuilder.build(
                "lifesteal_percent", Lifesteal.getInstance(), new ExactStatData(5, StatModTypes.Percent, Lifesteal.GUID));
        SPELLSTEAL_HALF = PerkEffectBuilder.build(
                "spellsteal_half", SpellSteal.getInstance(), new ExactStatData(0.5F, StatModTypes.Flat, SpellSteal.GUID));
        LIFESTEAL_HALF = PerkEffectBuilder.build(
                "lifesteal_half", Lifesteal.getInstance(), new ExactStatData(0.5F, StatModTypes.Flat, Lifesteal.GUID));

        PHYS_TO_FIRE = PerkEffectBuilder.build(
                "phys_to_fire", new PhysicalToFireConversion(), new ExactStatData(10F, StatModTypes.Flat, PhysicalToFireConversion.GUID));
        PHYS_TO_WATER = PerkEffectBuilder.build(
                "phys_to_water", new PhysicalToWaterConversion(), new ExactStatData(10F, StatModTypes.Flat, PhysicalToWaterConversion.GUID));
        PHYS_TO_THUNDER = PerkEffectBuilder.build(
                "phys_to_thunder", new PhysicalToThunderConversion(), new ExactStatData(10F, StatModTypes.Flat, PhysicalToThunderConversion.GUID));
        PHYS_TO_NATURE = PerkEffectBuilder.build(
                "phys_to_nature", new PhysicalToNatureConversion(), new ExactStatData(10F, StatModTypes.Flat, PhysicalToNatureConversion.GUID));

        DOT_DMG = PerkEffectBuilder.build(
                "dot_dmg", new AllDotDmg(), new ExactStatData(6, StatModTypes.Flat, AllDotDmg.GUID));
        SUMMON_DMG = PerkEffectBuilder.build(
                "summon_dmg", SummonDamage.getInstance(), new ExactStatData(4, StatModTypes.Flat, SummonDamage.GUID));
        ARMOR_PEN = PerkEffectBuilder.build(
                "armor_pen", ArmorPenetration.getInstance(), new ExactStatData(3, StatModTypes.Flat, ArmorPenetration.GUID));
        FCR = PerkEffectBuilder.build(
                "fcr", FasterCastRate.getInstance(), new ExactStatData(2, StatModTypes.Flat, FasterCastRate.GUID));
        MANA_COST = PerkEffectBuilder.build(
                "mana_cost", ReducedManaCost.getInstance(), new ExactStatData(3, StatModTypes.Flat, ReducedManaCost.GUID));
        INCREASED_DURATION = PerkEffectBuilder.build(
                "increased_duration", IncreasedDurationStat.getInstance(), new ExactStatData(2, StatModTypes.Flat, IncreasedDurationStat.GUID));
        PROJ_SPEED = PerkEffectBuilder.build(
                "proj_speed", IncreasedProjSpeedStat.getInstance(), new ExactStatData(4, StatModTypes.Flat, IncreasedProjSpeedStat.GUID));
        AREA = PerkEffectBuilder.build(
                "area", IncreasedAreaStat.getInstance(), new ExactStatData(2, StatModTypes.Flat, IncreasedAreaStat.GUID));

        int core_amount = 1;

        INTELLIGENCE = PerkEffectBuilder.build(
            "int", Intelligence.INSTANCE, new ExactStatData(core_amount, StatModTypes.Flat, Intelligence.GUID));
        WISDOM = PerkEffectBuilder.build(
            "wis", Wisdom.INSTANCE, new ExactStatData(core_amount, StatModTypes.Flat, Wisdom.GUID));

        STAMINA = PerkEffectBuilder.build(
            "sta", Stamina.INSTANCE, new ExactStatData(core_amount, StatModTypes.Flat, Stamina.GUID));
        DEXTERITY = PerkEffectBuilder.build(
            "dex", Dexterity.INSTANCE, new ExactStatData(core_amount, StatModTypes.Flat, Dexterity.GUID));

        VITALITY = PerkEffectBuilder.build(
            "vit", Vitality.INSTANCE, new ExactStatData(core_amount, StatModTypes.Flat, Vitality.GUID));
        STRENGTH = PerkEffectBuilder.build(
            "str", Strength.INSTANCE, new ExactStatData(core_amount, StatModTypes.Flat, Strength.GUID));

        MAGIC_SHIELD_PERCENT = PerkEffectBuilder.build(
            "ms_percent", MagicShield.getInstance(), new ExactStatData(5, StatModTypes.Percent, MagicShield.GUID));
        MANA_PERCENT = PerkEffectBuilder.build(
            "mana_percent", Mana.getInstance(), new ExactStatData(3, StatModTypes.Percent, Mana.GUID));
        HEALTH_PERCENT = PerkEffectBuilder.build(
            "hp_percent", Health.getInstance(), new ExactStatData(3, StatModTypes.Percent, Health.GUID));
        ENERGY_PERCENT = PerkEffectBuilder.build(
            "energy_percent", Energy.getInstance(), new ExactStatData(4, StatModTypes.Percent, Energy.GUID));
        DODGE_PERCENT = PerkEffectBuilder.build(
            "dodge_percent", DodgeRating.getInstance(), new ExactStatData(6, StatModTypes.Percent, DodgeRating.GUID));
        ARMOR_PERCENT = PerkEffectBuilder.build(
            "armor_percent", Armor.getInstance(), new ExactStatData(6, StatModTypes.Percent, Armor.GUID));

        MANA_REGEN_PERCENT = PerkEffectBuilder.build(
            "mana_regen_percent", ManaRegen.getInstance(), new ExactStatData(4, StatModTypes.Percent, ManaRegen.GUID));
        HEALTH_REGEN_PERCENT = PerkEffectBuilder.build("hp_regen_percent", HealthRegen.getInstance(),
            new ExactStatData(4, StatModTypes.Percent, HealthRegen.GUID)
        );
        ENERGY_REGEN_PERCENT = PerkEffectBuilder.build("energy_regen_percent", EnergyRegen.getInstance(),
            new ExactStatData(5, StatModTypes.Percent, EnergyRegen.GUID)
        );
        MAGIC_SHIELD_REGEN_PERCENT = PerkEffectBuilder.build("ms_regen_percent", MagicShieldRegen.getInstance(),
            new ExactStatData(8, StatModTypes.Percent,
                MagicShieldRegen.GUID
            )
        );

        float wepDmg = 6;

        for (WeaponTypes wep : WeaponTypes.getAll()) {
            WEP_DMG_MAP.put(
                wep, PerkEffectBuilder.build(wep.name()
                        .toLowerCase(Locale.ROOT) + "_dmg_percent", new WeaponDamage(wep),
                    new ExactStatData(wepDmg, StatModTypes.Flat, new WeaponDamage(wep))
                ));

            WEP_ELE_DMG_MAP.put(
                wep, PerkEffectBuilder.build(wep.name()
                        .toLowerCase(Locale.ROOT) + "_ele_dmg_percent", EleWepDmg.MAP.get(wep),
                    new ExactStatData(wepDmg + 6, StatModTypes.Flat, EleWepDmg.MAP.get(wep))
                ));

        }

        float elenum = 5;

        for (Elements ele : Elements.values()) {

            ELE_RES_PERCENT_MAP.put(
                ele, PerkEffectBuilder.build(ele.guidName + "_res", new ElementalResist(ele),
                    new ExactStatData(4, StatModTypes.Flat, new ElementalResist(ele))
                ));

            SPELL_DMG_PERCENT_MAP.put(
                ele, PerkEffectBuilder.build(ele.guidName + "_spell_dmg", new ElementalSpellDamage(ele),
                    new ExactStatData(3, StatModTypes.Flat,
                        new ElementalSpellDamage(ele)
                    )
                ));

            ELE_PENE_PERCENT_MAP.put(
                ele, PerkEffectBuilder.build(ele.guidName + "_pene", new ElementalPene(ele),
                    new ExactStatData(2, StatModTypes.Flat, new ElementalPene(ele))
                ));

            ATTACK_DAMAGE_PERCENT_MAP.put(
                ele, PerkEffectBuilder.build(ele.guidName + "_attack_dmg",
                    new ElementalAttackDamage(ele),
                    new ExactStatData(elenum, StatModTypes.Percent,
                        new ElementalAttackDamage(ele)
                    )
                ));

            SPELL_TO_ATK_DAMAGE_MAP.put(
                ele, PerkEffectBuilder.build(ele.guidName + "_spell_to_attack_dmg",
                    new ElementalInfusion(ele),
                    new ExactStatData(elenum, StatModTypes.Flat,
                        new ElementalInfusion(ele)
                    )
                ));

            SPELL_TO_ATK_DAMAGE_PERCENT_MAP.put(
                ele, PerkEffectBuilder.build(ele.guidName + "_spell_to_attack_dmg_percent",
                    new ElementalInfusion(ele),
                    new ExactStatData(10, StatModTypes.Percent,
                        new ElementalInfusion(ele)
                    )
                ));

            All_ELE_DMG_MAP.put(
                ele, PerkEffectBuilder.build(ele.guidName + "_all_dmg", new AllElementalDamage(ele),
                    new ExactStatData(3, StatModTypes.Flat, new AllElementalDamage(ele))
                ));

        }

    }

    public static void createCombined() {

        MANA_PERC_PLUS_MAGIC_SHIELD_PERCENT = PerkEffectBuilder.build(
            "mana_ms_percent", MAGIC_SHIELD_PERCENT.combine(), MANA_PERCENT.combine());

        INT_WIS = PerkEffectBuilder.build("int_wis", INTELLIGENCE.combine(), WISDOM.combine());
        STA_DEX = PerkEffectBuilder.build("sta_dex", STAMINA.combine(), DEXTERITY.combine());
        STR_STA = PerkEffectBuilder.build("str_sta", STRENGTH.combine(), STAMINA.combine());
        HP_HP_REGEN = PerkEffectBuilder.build("hp_hp_regen", HEALTH_PERCENT.combine(), HEALTH_REGEN_PERCENT.combine());
        ENE_ENE_REGEN = PerkEffectBuilder.build("ene_ene_regen", ENERGY_REGEN_PERCENT.combine(), ENERGY_PERCENT.combine());
        MANA_MANA_REGEN = PerkEffectBuilder.build("mana_mana_regen", MANA_REGEN_PERCENT.combine(), MANA_PERCENT.combine());
        MANA_ENE_REGEN = PerkEffectBuilder.build("mana_ene_regen", MANA_REGEN_PERCENT.combine(), ENERGY_REGEN_PERCENT.combine());
        HP_MANA = PerkEffectBuilder.build("hp_mana", HEALTH_PERCENT.combine(), MANA_PERCENT.combine());
        HP_ENE = PerkEffectBuilder.build("hp_ene", HEALTH_PERCENT.combine(), ENERGY_PERCENT.combine());

        PHYS_DMG_CRIT_DMG = PerkEffectBuilder.build("phys_dmg_crit_dmg", PHYSICAL_DMG.combine(), CRIT_DMG.combine());
        SPELL_SUMMON_DMG = PerkEffectBuilder.build("spell_summon_dmg", SPELL_DMG.combine(), SUMMON_DMG.combine());

        PHYS_DMG_CRIT_HIT = PerkEffectBuilder.build("phys_dmg_crit_hit", PHYSICAL_DMG.combine(), CRIT_HIT.combine());
        HP_PHYS_DMG = PerkEffectBuilder.build("hp_phys_dmg", HEALTH_PERCENT.combine(), PHYSICAL_DMG.combine());
        CRIT_HIT_CRIT_DMG = PerkEffectBuilder.build("crit_hit_crit_dmg", CRIT_DMG.combine(), CRIT_HIT.combine());
        CDR_FCR = PerkEffectBuilder.build("cdr_fcr", CDR.combine(), FCR.combine());

        STR_CRIT_DMG = PerkEffectBuilder.build("str_crit_dmg", STRENGTH.combine(), CRIT_DMG.combine());
        CRIT_HIT_DODGE = PerkEffectBuilder.build("crit_hit_dodge", CRIT_HIT.combine(), DODGE_PERCENT.combine());
        DODGE_ENE_REGEN = PerkEffectBuilder.build(
            "dodge_ene_regen", DODGE_PERCENT.combine(), ENERGY_REGEN_PERCENT.combine());
        DODGE_ENE = PerkEffectBuilder.build("dodge_ene", DODGE_PERCENT.combine(), ENERGY_PERCENT.combine());
        HP_DODGE = PerkEffectBuilder.build("hp_dodge", HEALTH_PERCENT.combine(), DODGE_PERCENT.combine());
        HP_ARMOR = PerkEffectBuilder.build("hp_armor", HEALTH_PERCENT.combine(), ARMOR_PERCENT.combine());
        LIFE_SPELL_STEAL = PerkEffectBuilder.build("life_spell_steal", LIFESTEAL_HALF.combine(), SPELLSTEAL_HALF.combine());

    }

}

