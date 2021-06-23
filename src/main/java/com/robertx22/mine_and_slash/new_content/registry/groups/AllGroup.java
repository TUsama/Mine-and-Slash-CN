package com.robertx22.mine_and_slash.new_content.registry.groups;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AllGroup extends RoomGroup {

    public AllGroup() {
        super("all", 5);
        this.canBeMainTheme = false;
    }

    @Override
    public List<RoomGroup> possibleOtherTypes() {
        return Arrays.asList();
    }

    @Override
    public RoomGroup getFallbackGroup(Random rand) {
        return RoomGroup.MINESHAFT;
    }
}
