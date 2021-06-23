package com.robertx22.mine_and_slash.new_content.registry.rooms.adders;

import com.robertx22.mine_and_slash.new_content.enums.RoomType;
import com.robertx22.mine_and_slash.new_content.registry.groups.RoomGroup;
import com.robertx22.mine_and_slash.new_content.registry.rooms.base.BaseRoomAdder;

public class AllRoomAdder extends BaseRoomAdder {

    public AllRoomAdder() {
        super(RoomGroup.ALL);
    }

    @Override
    public void addAllRooms() {

        add("vaults", RoomType.END);
    }
}
