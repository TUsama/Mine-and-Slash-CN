package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.database.spells.entities.cloud.*;
import com.robertx22.mine_and_slash.database.spells.entities.proj.*;
import com.robertx22.mine_and_slash.database.spells.entities.single_target_bolt.*;
import com.robertx22.mine_and_slash.database.spells.entities.summons.*;
import com.robertx22.mine_and_slash.database.spells.entities.trident.SpearOfJudgementEntity;
import com.robertx22.mine_and_slash.database.spells.entities.trident.ThunderspearEntity;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.new_content.trader.TraderEntity;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.FMLPlayMessages;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.function.BiFunction;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityRegister {

    public static List<EntityType<? extends Entity>> ENTITY_TYPES = new LinkedList();
    public static List<EntityType<? extends Entity>> BOSSES = new LinkedList();
    public static List<EntityType<? extends Entity>> ENTITY_THAT_USE_ITEM_RENDERS = new LinkedList();

    public static EntityType randomBoss() {
        return RandomUtils.randomFromList(BOSSES);
    }

    @SubscribeEvent
    public static void registerEntityTypes(final RegistryEvent.Register<EntityType<?>> event) {

        ENTITY_TYPES.forEach(entityType -> event.getRegistry()
            .register(entityType));

    }

    public static final EntityType<? extends Entity> THUNDERSTORM;
    public static final EntityType<? extends TridentEntity> THUNDER_SPEAR;
    public static final EntityType<? extends TridentEntity> HOLY_SPEAR;
    public static final EntityType<? extends Entity> LIGHTNING_TOTEM;
    public static final EntityType<? extends Entity> LIGHTNING_BALL;
    public static final EntityType<? extends Entity> LIGHTNING;
    public static final EntityType<? extends Entity> CHAIN_LIGHTNING;

    public static final EntityType<? extends Entity> FIREBOLT;
    public static final EntityType<? extends Entity> ARCHONBALL;
    public static final EntityType<? extends Entity> FIRE_BOMB;
    public static final EntityType<? extends Entity> THROW_FLAMES;
    public static final EntityType<? extends Entity> VOLCANO;

    public static final EntityType<? extends Entity> ROCK_SLIDE;
    public static final EntityType<? extends Entity> POISON_BALL;

    public static final EntityType<? extends Entity> HELLFIRE;
    public static final EntityType<? extends Entity> STEAM_CLOUD;
    public static final EntityType<? extends Entity> CHILLING_FIELD;
    public static final EntityType<? extends Entity> FROSTBOLT;
    public static final EntityType<? extends Entity> RIMEBALL;
    public static final EntityType<? extends Entity> WHIRPOOL;
    public static final EntityType<? extends Entity> BLIZZARD;
    public static final EntityType<? extends Entity> TIDAL_WAVE;
    public static final EntityType<? extends Entity> GROUND_SLAM;
    public static final EntityType<? extends Entity> FROZEN_ORB;
    public static final EntityType<? extends Entity> FROST_TOTEM;
    public static final EntityType<? extends Entity> FROST_BLAST;
    public static final EntityType<? extends Entity> LIFE_SIPHON;

    public static final EntityType<RangerArrowEntity> RANGER_ARROW;
    public static final EntityType<StoneEntity> STONE_ENTITY;
    public static final EntityType<? extends Entity> ARROW_STORM;
    public static final EntityType<? extends Entity> SNARE_TRAP;
    public static final EntityType<? extends Entity> WEAKEN_TRAP;
    public static final EntityType<? extends Entity> BLAST_TRAP;
    public static final EntityType<? extends Entity> ARROW_TOTEM;

    public static final EntityType<? extends Entity> DIVINE_TRIBULATION;

    public static final EntityType<? extends Entity> SEED;

    public static final EntityType<TraderEntity> TRADER;

    public static final EntityType<SpiderPetEntity> SPIDER_PET;
    public static final EntityType<SpiritWolfPetEntity> SPIRIT_WOLF_PET;
    public static final EntityType<ZombiePetEntity> ZOMBIE_PET;
    public static final EntityType<SkeletonPetEntity> SKELETON_PET;
    public static final EntityType<ArchonPetEntity> ARCHON_PET;

    static {

        HOLY_SPEAR = projectile(SpearOfJudgementEntity::new, SpearOfJudgementEntity::new, "holy_spear", false);

        CHILLING_FIELD = projectile(ChillingFieldEntity::new, ChillingFieldEntity::new, "chilling_field");
        FROST_BLAST = projectile(FrostBlastEntity::new, FrostBlastEntity::new, "frost_blast");
        FROST_TOTEM = projectile(FrostTotemEntity::new, FrostTotemEntity::new, "frost_totem");
        STEAM_CLOUD = projectile(SteamCloudEntity::new, SteamCloudEntity::new, "steam_cloud");
        BLIZZARD = projectile(BlizzardEntity::new, BlizzardEntity::new, "blizzard");
        FROSTBOLT = projectile(FrostballEntity::new, FrostballEntity::new, "frostball");
        RIMEBALL = projectile(RimeballEntity::new, RimeballEntity::new, "rimeball");
        FROZEN_ORB = projectile(FrozenOrbEntity::new, FrozenOrbEntity::new, "frozen_orb");
        WHIRPOOL = projectile(WhirlpoolEntity::new, WhirlpoolEntity::new, "whirlpool");
        TIDAL_WAVE = projectile(TidalWaveEntity::new, TidalWaveEntity::new, "tidal_wave");
        GROUND_SLAM = projectile(GroundSlamEntity::new, GroundSlamEntity::new, "ground_slam");
        LIFE_SIPHON = projectile(LifeSiphonEntity::new, LifeSiphonEntity::new, "life_siphon");
        HELLFIRE = projectile(HellFireEntity::new, HellFireEntity::new, "hell_fire");

        POISON_BALL = projectile(PoisonBallEntity::new, PoisonBallEntity::new, "poison_ball");
        ROCK_SLIDE = projectile(RockSlideEntity::new, RockSlideEntity::new, "rock_slide");

        THUNDERSTORM = projectile(ThunderstormEntity::new, ThunderstormEntity::new, "thunderstorm");
        THUNDER_SPEAR = projectile(ThunderspearEntity::new, ThunderspearEntity::new, "thunder_spear", false);
        LIGHTNING_TOTEM = projectile(LightningTotemEntity::new, LightningTotemEntity::new, "lightning_totem");
        LIGHTNING_BALL = projectile(LightningBallEntity::new, LightningBallEntity::new, "lightning_ball");
        LIGHTNING = projectile(LightningEntity::new, LightningEntity::new, "lightning");
        CHAIN_LIGHTNING = projectile(ChainLightningEntity::new, ChainLightningEntity::new, "chain_lightning");

        FIREBOLT = projectile(FireballEntity::new, FireballEntity::new, "fireball");
        ARCHONBALL = projectile(ArchonballEntity::new, ArchonballEntity::new, "archon_ball");
        FIRE_BOMB = projectile(FireBombEntity::new, FireBombEntity::new, "fire_bomb");
        THROW_FLAMES = projectile(ThrowFlameEntity::new, ThrowFlameEntity::new, "seeker_flame");
        VOLCANO = projectile(VolcanoEntity::new, VolcanoEntity::new, "volcano");

        STONE_ENTITY = projectile(StoneEntity::new, StoneEntity::new, "stone_entity");
        RANGER_ARROW = projectile(RangerArrowEntity::new, RangerArrowEntity::new, "ranger_arrow");
        ARROW_STORM = projectile(ArrowStormEntity::new, ArrowStormEntity::new, "arrow_storm");
        SNARE_TRAP = projectile(SnareTrapEntity::new, SnareTrapEntity::new, "snare_trap");
        WEAKEN_TRAP = projectile(WeakenTrapEntity::new, WeakenTrapEntity::new, "weaken_trap");
        BLAST_TRAP = projectile(BlastTrapEntity::new, BlastTrapEntity::new, "blast_trap");
        ARROW_TOTEM = projectile(ArrowTotemEntity::new, ArrowTotemEntity::new, "arrow_totem");

        DIVINE_TRIBULATION = projectile(DivineTribulationEntity::new, DivineTribulationEntity::new, "divine_tribulation");

        SEED = projectile(SeedEntity::new, SeedEntity::new, "seed_entity");

        TRADER = EntityType.Builder.<TraderEntity>create(TraderEntity::new, EntityClassification.MISC).setCustomClientFactory(
            TraderEntity::new)
            .size(0.5F, 2F)
            .build(Ref.MODID + ":trader");
        TRADER.setRegistryName(new ResourceLocation(Ref.MODID, "trader"));
        ENTITY_TYPES.add(TRADER);

        SPIDER_PET = EntityType.Builder.<SpiderPetEntity>create(SpiderPetEntity::new, EntityClassification.MONSTER).setCustomClientFactory(
            SpiderPetEntity::new)
            .size(1.4F, 0.9F)
            .build(Ref.MODID + ":spider_pet");
        SPIDER_PET.setRegistryName(new ResourceLocation(Ref.MODID, "spider_pet"));
        ENTITY_TYPES.add(SPIDER_PET);

        SPIRIT_WOLF_PET = EntityType.Builder.<SpiritWolfPetEntity>create(SpiritWolfPetEntity::new, EntityClassification.MONSTER).setCustomClientFactory(
            SpiritWolfPetEntity::new)
            .size(0.6F, 0.85F)
            .build(Ref.MODID + ":spirit_wolf_pet");
        SPIRIT_WOLF_PET.setRegistryName(new ResourceLocation(Ref.MODID, "spirit_wolf_pet"));
        ENTITY_TYPES.add(SPIRIT_WOLF_PET);

        ZOMBIE_PET = EntityType.Builder.<ZombiePetEntity>create(ZombiePetEntity::new, EntityClassification.MONSTER).setCustomClientFactory(
                ZombiePetEntity::new)
                .size(0.6F, 1.8F)
                .build(Ref.MODID + ":zombie_pet");
        ZOMBIE_PET.setRegistryName(new ResourceLocation(Ref.MODID, "zombie_pet"));
        ENTITY_TYPES.add(ZOMBIE_PET);

        SKELETON_PET = EntityType.Builder.<SkeletonPetEntity>create(SkeletonPetEntity::new, EntityClassification.MONSTER).setCustomClientFactory(
                SkeletonPetEntity::new)
                .size(0.6F, 1.8F)
                .build(Ref.MODID + ":skeleton_pet");
        SKELETON_PET.setRegistryName(new ResourceLocation(Ref.MODID, "skeleton_pet"));
        ENTITY_TYPES.add(SKELETON_PET);

        ARCHON_PET = EntityType.Builder.<ArchonPetEntity>create(ArchonPetEntity::new, EntityClassification.MONSTER).setCustomClientFactory(
                ArchonPetEntity::new)
                .size(0.6F, 1.2F)
                .build(Ref.MODID + ":archon_pet");
        ARCHON_PET.setRegistryName(new ResourceLocation(Ref.MODID, "archon_pet"));
        ENTITY_TYPES.add(ARCHON_PET);
    }

    public static EntityType addBoss(EntityType type, String id) {
        type.setRegistryName(new ResourceLocation(Ref.MODID, id));
        ENTITY_TYPES.add(type);
        BOSSES.add(type);
        return type;
    }

    private static <T extends Entity> EntityType<T> projectile(EntityType.IFactory<T> factory,
                                                               BiFunction<FMLPlayMessages.SpawnEntity, World, T> bif,
                                                               String id) {

        return projectile(factory, bif, id, true);

    }

    private static <T extends Entity> EntityType<T> projectile(EntityType.IFactory<T> factory,
                                                               BiFunction<FMLPlayMessages.SpawnEntity, World, T> bif,
                                                               String id, boolean itemRender) {

        EntityType<T> type = EntityType.Builder.<T>create(factory, EntityClassification.MISC).setCustomClientFactory(
            bif)
            .size(0.5F, 0.5F)
            .build(Ref.MODID + ":" + id.toLowerCase(Locale.ROOT));

        type.setRegistryName(new ResourceLocation(Ref.MODID, id.toLowerCase(Locale.ROOT)));

        ENTITY_TYPES.add(type);

        if (itemRender) {
            ENTITY_THAT_USE_ITEM_RENDERS.add(type);
        }

        return type;
    }

}


