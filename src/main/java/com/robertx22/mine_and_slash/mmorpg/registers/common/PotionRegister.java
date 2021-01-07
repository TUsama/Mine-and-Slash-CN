package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.all.BleedPotion;
import com.robertx22.mine_and_slash.potion_effects.all.TeleportProtection;
import com.robertx22.mine_and_slash.potion_effects.divine.*;
import com.robertx22.mine_and_slash.potion_effects.druid.*;
import com.robertx22.mine_and_slash.potion_effects.ember_mage.BurnEffect;
import com.robertx22.mine_and_slash.potion_effects.ocean_mystic.*;
import com.robertx22.mine_and_slash.potion_effects.ranger.*;
import com.robertx22.mine_and_slash.potion_effects.shaman.StaticEffect;
import com.robertx22.mine_and_slash.potion_effects.shaman.ThunderEssenceEffect;
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
        reg.register(TeleportProtection.INSTANCE);
        reg.register(BleedPotion.INSTANCE);

        reg.register(BraveryEffect.INSTANCE);
        reg.register(WizardryEffect.INSTANCE);
        reg.register(TrickeryEffect.INSTANCE);
        reg.register(FrostShieldEffect.INSTANCE);
        reg.register(NourishmentEffect.INSTANCE);
        reg.register(IceBladeEffect.INSTANCE);
        reg.register(FrozenEffect.INSTANCE);

        reg.register(FrostEffect.INSTANCE);
        reg.register(ThornsEffect.INSTANCE);
        reg.register(PetrifyEffect.INSTANCE);
        reg.register(ShiverEffect.INSTANCE);
        reg.register(ColdEssenceEffect.INSTANCE);
        reg.register(JudgementEffect.INSTANCE);
        reg.register(ThornArmorEffect.INSTANCE);
        reg.register(StaticEffect.INSTANCE);
        reg.register(BurnEffect.INSTANCE);
        reg.register(ThunderEssenceEffect.INSTANCE);
        reg.register(PoisonedWeaponsEffect.getInstance());
        reg.register(ImbueEffect.getInstance());
        reg.register(HunterInstinctEffect.getInstance());
        reg.register(WindWalkEffect.INSTANCE);
        reg.register(SilentWindEffect.INSTANCE);
        reg.register(SnareEffect.INSTANCE);
        reg.register(WeakenEffect.INSTANCE);
        reg.register(WoundsEffect.getInstance());
        reg.register(EnrageEffect.INSTANCE);

    }

}
