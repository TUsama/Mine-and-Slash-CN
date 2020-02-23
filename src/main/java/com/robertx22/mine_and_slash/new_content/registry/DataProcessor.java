package com.robertx22.mine_and_slash.new_content.registry;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public abstract class DataProcessor {

    public DataProcessor(String data) {
        this.data = data;
    }

    String data;

    public final boolean process(String data, BlockPos pos, IWorld world) {
        if (this.data.equals(data)) {
            processImplementation(pos, world);
            return true;
        }
        return false;
    }

    public abstract void processImplementation(BlockPos pos, IWorld world);

}
