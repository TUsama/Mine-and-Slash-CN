package com.robertx22.mine_and_slash.saveclasses;

import com.robertx22.mine_and_slash.uncommon.utilityclasses.Utilities;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.server.ServerWorld;

import java.util.*;

@Storable
public class EntityDmgStatsData {

    @Store
    private HashMap<String, Float> map = new HashMap<>();

    @Store
    private float enviroDmg = 0;

    public void onDamage(LivingEntity entity, float dmg) {

        if (map.size() >= 500) {
            enviroDmg = 0; // on death, reset enviro dmg
            map.clear(); // hacky solution where if damage sources reaches some obnoxious amount (500 in this case), clear everything. should still be fine for mobs and not really affect players
        }

        if (entity == null) {
            enviroDmg += dmg;
            return;
        } else {
            String id = entity.getUniqueID().toString();
            map.put(id, dmg + map.getOrDefault(id, 0F));
        }
    }

    public LivingEntity getHighestDamageEntity(ServerWorld world) {

        Optional<Map.Entry<String, Float>> opt = map.entrySet()
                .stream().max((one, two) -> one.getValue() >= two.getValue() ? 1 : -1);

        if (opt.isPresent()) {

            Map.Entry<String, Float> entry = opt.get();

            float total_dmg = 0.0f;
            for (float f : map.values()) { total_dmg += f; }

            String id = entry.getKey();
            // Float val = entry.getValue();

            if (enviroDmg / 1.5 > total_dmg) {
                enviroDmg = 0; // on death, reset enviro dmg
                map.clear(); // on death clear nbt data
                return null; // means enviroment did more damage than the highest entity dmg dealer
            }

            Entity en = Utilities.getEntityByUUID(world, UUID.fromString(id));

            enviroDmg = 0; // on death, reset enviro dmg
            map.clear(); // on death clear nbt data

            if (en instanceof LivingEntity) {
                return (LivingEntity) en;
            }
        }
        return null;

    }
}
