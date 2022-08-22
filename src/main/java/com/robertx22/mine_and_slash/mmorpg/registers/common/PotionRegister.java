package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.all.*;
import com.robertx22.mine_and_slash.potion_effects.divine.*;
import com.robertx22.mine_and_slash.potion_effects.druid.*;
import com.robertx22.mine_and_slash.potion_effects.ember_mage.*;
import com.robertx22.mine_and_slash.potion_effects.necromancer.*;
import com.robertx22.mine_and_slash.potion_effects.ocean_mystic.*;
import com.robertx22.mine_and_slash.potion_effects.physical.*;
import com.robertx22.mine_and_slash.potion_effects.ranger.*;
import com.robertx22.mine_and_slash.potion_effects.shaman.*;
import net.minecraft.potion.Effect;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PotionRegister {

    @SubscribeEvent
    public static void register(RegistryEvent.Register<Effect> event) {

        IForgeRegistry<Effect> reg = event.getRegistry();

        reg.register(RegenerateEffect.INSTANCE);
        reg.register(RechargeEffect.INSTANCE);
        reg.register(TeleportProtection.INSTANCE);
        reg.register(BleedPotion.INSTANCE);
        reg.register(SummonTauntEffect.INSTANCE);
        reg.register(WeakenCurseEffect.INSTANCE);
        reg.register(MobChillEffect.INSTANCE);
        reg.register(ChainLightningEffect.INSTANCE);

        reg.register(PowerChargeEffect.INSTANCE);
        reg.register(FortitudeChargeEffect.INSTANCE);

        reg.register(DefendEffect.INSTANCE);
        reg.register(BraveryEffect.INSTANCE);
        reg.register(WizardryEffect.INSTANCE);
        reg.register(TrickeryEffect.INSTANCE);
        reg.register(FrostShieldEffect.INSTANCE);
        reg.register(NourishmentEffect.INSTANCE);
        reg.register(PurityEffect.INSTANCE);
        reg.register(IceBladeEffect.INSTANCE);
        reg.register(FrozenEffect.INSTANCE);
        reg.register(FirePowerEffect.INSTANCE);

        reg.register(FrostEffect.INSTANCE);
        reg.register(ThornsEffect.INSTANCE);
        reg.register(PetrifyEffect.INSTANCE);
        reg.register(RootEffect.INSTANCE);
        reg.register(MindRotEffect.INSTANCE);
        reg.register(CorrosionEffect.INSTANCE);
        reg.register(QuietusEffect.INSTANCE);
        //reg.register(ShiverEffect.INSTANCE);
        reg.register(ColdEssenceEffect.INSTANCE);
        reg.register(JudgementEffect.INSTANCE);
        //reg.register(ShockwaveEffect.INSTANCE);
        //reg.register(ShatterCastEffect.INSTANCE);
        reg.register(ThornArmorEffect.INSTANCE);
        reg.register(BlazingShellEffect.INSTANCE);
        reg.register(ArcticArmorEffect.INSTANCE);
        reg.register(StaticEffect.INSTANCE);
        reg.register(CriticalSurgeEffect.INSTANCE);
        reg.register(PowerSurgeEffect.INSTANCE);
        reg.register(QuickChargeEffect.INSTANCE);
        reg.register(NaturesGiftEffect.INSTANCE);
        reg.register(BurnEffect.INSTANCE);
        reg.register(SpellSiphonEffect.INSTANCE);
        reg.register(AttackSiphonEffect.INSTANCE);
        reg.register(SpellBladeEffect.INSTANCE);
        reg.register(ThunderEssenceEffect.INSTANCE);
        reg.register(PoisonedWeaponsEffect.getInstance());
        reg.register(ImbueEffect.getInstance());
        reg.register(ExertEffect.getInstance());
        reg.register(HunterInstinctEffect.getInstance());
        reg.register(WindWalkEffect.INSTANCE);
        reg.register(ThunderDashEffect.INSTANCE);
        reg.register(SilentWindEffect.INSTANCE);
        reg.register(SnareEffect.INSTANCE);
        reg.register(WeakenEffect.INSTANCE);
        reg.register(WoundsEffect.getInstance());
        reg.register(EnrageEffect.INSTANCE);
        reg.register(CrippleEffect.INSTANCE);
        reg.register(NecroticTetherEffect.INSTANCE);
        reg.register(BlightEffect.INSTANCE);
        reg.register(VenomLoadEffect.INSTANCE);
        reg.register(SpiritShroudEffect.INSTANCE);
        reg.register(FleshandMindSacrificeEffect.INSTANCE);
        reg.register(SoulShredEffect.INSTANCE);
        reg.register(SummonedZombieEffect.INSTANCE);
        reg.register(SummonedArchonEffect.INSTANCE);
        reg.register(SummonedSkeletonEffect.INSTANCE);
        reg.register(SummonedWolfEffect.INSTANCE);

        reg.register(ComboStarterEffect.INSTANCE);
        reg.register(ComboLinkerEffect.INSTANCE);
        reg.register(ArmorBreakEffect.INSTANCE);
        reg.register(EarthenShellEffect.INSTANCE);;
        reg.register(DebilitatedEffect.INSTANCE);

    }

}
