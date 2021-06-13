package com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs;

import com.robertx22.mine_and_slash.uncommon.localization.Words;

public enum SC {

    MANA_COST(0, Words.Mana_Cost),
    MAGIC_SHIELD_COST(0, Words.Magic_Shield_Cost),
    ENERGY_COST(0, Words.Energy_Cost),
    RADIUS(0, Words.Radius),
    PROJECTILE_COUNT(1, Words.ProjectileCount),
    CAST_TIME_TICKS(0, Words.CastTimeTicks),
    COOLDOWN_SECONDS(0, Words.CooldownSeconds),
    COOLDOWN_TICKS(0, Words.CooldownTicks),
    SHOOT_SPEED(0.05F, Words.ShootSpeed),
    SUMMONED_ENTITIES(1, Words.SummonedEntities),
    CHANCE(0, Words.Chance),
    CDR_EFFICIENCY(0, Words.CooldownReductionEfficiency),
    DURATION_TICKS(0, Words.DurationTicks),
    TICK_RATE(-100, Words.TickRate),
    TIMES_TO_CAST(1, Words.TimesToCast),
    BASE_VALUE(0, null),
    AMOUNT(0, Words.Amount),
    ATTACK_SCALE_VALUE(0, null),
    ELEMENTAL_ATTACK_SCALE_VALUE(0, null),
    PHYSICAL_ATTACK_SCALE_VALUE(0, null),
    FIRE_ATTACK_SCALE_VALUE(0, null),
    WATER_ATTACK_SCALE_VALUE(0, null),
    THUNDER_ATTACK_SCALE_VALUE(0, null),
    NATURE_ATTACK_SCALE_VALUE(0, null),
    ARMOR_ATTACK_SCALE_VALUE(0, null),
    HEALTH_ATTACK_SCALE_VALUE(0, null),
    MANA_ATTACK_SCALE_VALUE(0, null),
    MAGIC_SHIELD_ATTACK_SCALE_VALUE(0, null),
    ENERGY_ATTACK_SCALE_VALUE(0, null);

    public float min;

    SC(float min, Words word) {
        this.word = word;
        this.min = min;
    }

    public Words word;

    public final boolean shouldAddToTooltip() {
        return word != null;
    }
}
