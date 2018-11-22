package com.robertx22.mmorpg;

import java.util.Arrays;

import com.libraries.prospector.traverse.RegisterBiomes;
import com.libraries.rabbit.gui.RabbitGui;
import com.robertx22.advanced_blocks.gear_factory_station.StartupGearFactory;
import com.robertx22.advanced_blocks.item_modify_station.StartupModify;
import com.robertx22.advanced_blocks.repair_station.StartupRepair;
import com.robertx22.advanced_blocks.salvage_station.StartupSalvage;
import com.robertx22.customitems.ores.ItemOre;
import com.robertx22.dimensions.blocks.TilePortalBlock;
import com.robertx22.mmorpg.proxy.IProxy;
import com.robertx22.mmorpg.registers.CommandRegisters;
import com.robertx22.network.DamageNumberPackage;
import com.robertx22.network.EntityPackage;
import com.robertx22.network.ParticlePackage;
import com.robertx22.network.UnitPackage;
import com.robertx22.network.WorldPackage;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.capability.WorldData;
import com.robertx22.uncommon.oregen.OreGen;
import com.robertx22.uncommon.testing.TestManager;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber
@Mod(modid = Ref.MODID, version = Ref.VERSION, name = Ref.NAME, dependencies = "required-after:baubles;required-after:patchouli ")
public class Main {

	@SidedProxy(clientSide = "com.robertx22.mmorpg.proxy.ClientProxy", serverSide = "com.robertx22.mmorpg.proxy.ServerProxy")
	public static IProxy proxy;

	@Instance(value = Ref.MODID)
	public static Main instance;

	public static final SimpleNetworkWrapper Network = NetworkRegistry.INSTANCE.newSimpleChannel(Ref.MODID);

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		GameRegistry.registerTileEntity(TilePortalBlock.class, Ref.MODID + ":tile_portal_block");

		proxy.preInit(event);

		proxy.RegisterEntityRenders();

		ItemOre.Register();
		StartupRepair.preInitCommon(event);
		StartupSalvage.preInitCommon(event);
		StartupModify.preInitCommon(event);
		StartupGearFactory.preInitCommon(event);

		MinecraftForge.EVENT_BUS.register(new UnitPackage());
		MinecraftForge.EVENT_BUS.register(new EntityPackage());
		MinecraftForge.EVENT_BUS.register(new DamageNumberPackage());
		MinecraftForge.EVENT_BUS.register(new ParticlePackage());
		MinecraftForge.EVENT_BUS.register(new WorldPackage());

		Network.registerMessage(UnitPackage.Handler.class, UnitPackage.class, 0, Side.CLIENT);
		Network.registerMessage(EntityPackage.Handler.class, EntityPackage.class, 1, Side.CLIENT);
		Network.registerMessage(DamageNumberPackage.Handler.class, DamageNumberPackage.class, 2, Side.CLIENT);
		Network.registerMessage(ParticlePackage.Handler.class, ParticlePackage.class, 3, Side.CLIENT);
		Network.registerMessage(WorldPackage.Handler.class, WorldPackage.class, 4, Side.CLIENT);

		CapabilityManager.INSTANCE.register(EntityData.UnitData.class, new EntityData.Storage(),
				EntityData.DefaultImpl.class);

		CapabilityManager.INSTANCE.register(WorldData.IWorldData.class, new WorldData.Storage(),
				WorldData.DefaultImpl.class);

		ModMetadata modMeta = event.getModMetadata();
		modMeta.name = Ref.NAME;
		modMeta.version = Ref.VERSION;
		modMeta.authorList = Arrays.asList("robertx22");
		modMeta.autogenerated = false;
		modMeta.description = Ref.DESC;
		modMeta.url = "https://minecraft.curseforge.com/projects/mine-and-slash-reloaded";

	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		RabbitGui.proxy.init();
		proxy.init(event);

		TestManager.RunAllTests();

		RegisterBiomes.initBiomeManagerAndDictionary();

		int chance = 6;
		int amount = 7;

		for (int i = 0; i < ItemOre.Blocks.values().size(); i++) {
			GameRegistry.registerWorldGenerator(new OreGen(ItemOre.Blocks.get(i), amount - i, 10, 75, chance - i), 0);

		}

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

		proxy.postInit(event);
		RabbitGui.proxy.postInit();

	}

	@EventHandler
	public void start(FMLServerStartingEvent event) {
		proxy.serverStarting(event);

		CommandRegisters.Register(event);

	}

	@EventHandler
	public static void onWorldLoad(FMLServerStartedEvent event) {

	}
}
