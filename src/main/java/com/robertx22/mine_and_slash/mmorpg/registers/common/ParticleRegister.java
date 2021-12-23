package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.particles.EleParticleData;
import com.robertx22.mine_and_slash.particles.ParticleDeserializer;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ParticleRegister {

    private final static String eleId = Ref.MODID + ":drip";
    private final static String thunderId = Ref.MODID + ":thunder";
    private final static String thunder3Id = Ref.MODID + ":thunder3";
    private final static String thunder_purpleId = Ref.MODID + ":thunder_purple";
    private final static String bubbleID = Ref.MODID + ":bubble";

    @ObjectHolder(eleId)
    public static final ParticleType<EleParticleData> DRIP = null;

    @ObjectHolder(thunderId)
    public static final BasicParticleType THUNDER = null;

    @ObjectHolder(bubbleID)
    public static final BasicParticleType BUBBLE = null;

    @ObjectHolder(thunder3Id)
    public static final BasicParticleType THUNDER3 = null;

    @ObjectHolder(thunder_purpleId)
    public static final BasicParticleType THUNDER_PURPLE = null;

    @SubscribeEvent
    public static void register(RegistryEvent.Register<ParticleType<?>> event) {

        register(eleId, ParticleDeserializer.INSTANCE);
        register(thunderId, new BasicParticleType(false));
        register(bubbleID, new BasicParticleType(false));
        register(thunder3Id, new BasicParticleType(false));
        register(thunder_purpleId, new BasicParticleType(false));

    }

    private static <T extends ParticleType<?>> T register(String name, T particleType) {
        particleType.setRegistryName(name);
        ForgeRegistries.PARTICLE_TYPES.register(particleType);
        return particleType;
    }

    private static <T extends IParticleData> ParticleType register(String name, IParticleData.IDeserializer<T> deseri) {
        ParticleType<?> particleType = new ParticleType(false, deseri);
        return register(name, particleType);
    }

}
