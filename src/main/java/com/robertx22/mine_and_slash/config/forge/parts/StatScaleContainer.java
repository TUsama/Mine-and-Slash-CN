package com.robertx22.mine_and_slash.config.forge.parts;

import net.minecraftforge.common.ForgeConfigSpec;

public class StatScaleContainer {

    public StatScaleValue NORMAL_SCALING;
    public StatScaleValue CORE_STAT_SCALING;
    public StatScaleValue WEAPON_SCALING;

    public StatScaleContainer(ForgeConfigSpec.Builder builder) {
        builder.push("STAT SCALING");

        // scale ends at lvl 80, power = 164.854
        NORMAL_SCALING = builder.comment(
            "val * (float) Math.pow(lvl, MathHelper.clamp(FIRST_VALUE + (float) lvl / " + "SECOND_VALUE, " +
                "THIRD_VALUE, FOURTH_VALUE))")
            .configure((ForgeConfigSpec.Builder b) -> new StatScaleValue(b, "Normal", 0.525D, 100D, 0.525D, 1.225D))
            .getLeft();

        //scale ends at lvl 80, power = 147.748
        WEAPON_SCALING = builder.comment(
                "val * (float) Math.pow(lvl, MathHelper.clamp(FIRST_VALUE + (float) lvl / " + "SECOND_VALUE, " +
                        "THIRD_VALUE, FOURTH_VALUE))")
                .configure((ForgeConfigSpec.Builder b) -> new StatScaleValue(b, "Weapon", 0.5D, 100D, 0.5D, 1.2D))
                .getLeft();

        CORE_STAT_SCALING = builder.comment("val * (FIRST_VALUE + (float) lvl / SECOND_VALUE)")
            .configure((ForgeConfigSpec.Builder b) -> new StatScaleValue(b, "Core Stat", 1D, 100D, 0D, 0D))
            .getLeft();

        builder.pop();
    }

}
