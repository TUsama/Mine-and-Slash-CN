package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.potion_effects.divine.EnrageEffect;
import com.robertx22.mine_and_slash.potion_effects.divine.JudgementEffect;
import com.robertx22.mine_and_slash.potion_effects.druid.*;
import com.robertx22.mine_and_slash.potion_effects.ember_mage.*;
import com.robertx22.mine_and_slash.potion_effects.necromancer.*;
import com.robertx22.mine_and_slash.potion_effects.ocean_mystic.*;
import com.robertx22.mine_and_slash.potion_effects.physical.*;
import com.robertx22.mine_and_slash.potion_effects.ranger.*;
import com.robertx22.mine_and_slash.potion_effects.shaman.*;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;

public class PotionEffects implements ISlashRegistryInit {

    @Override
    public void registerAll() {

        PetrifyEffect.INSTANCE.registerToSlashRegistry();
        PoisonedWeaponsEffect.getInstance()
            .registerToSlashRegistry();
        RegenerateEffect.INSTANCE.registerToSlashRegistry();
        RechargeEffect.INSTANCE.registerToSlashRegistry();
        ThornArmorEffect.INSTANCE.registerToSlashRegistry();
        ThornsEffect.INSTANCE.registerToSlashRegistry();
        RootEffect.INSTANCE.registerToSlashRegistry();
        QuietusEffect.INSTANCE.registerToSlashRegistry();
        MindRotEffect.INSTANCE.registerToSlashRegistry();
        CorrosionEffect.INSTANCE.registerToSlashRegistry();
        VenomLoadEffect.INSTANCE.registerToSlashRegistry();

        BurnEffect.INSTANCE.registerToSlashRegistry();
        SpellSiphonEffect.INSTANCE.registerToSlashRegistry();
        SpellBladeEffect.INSTANCE.registerToSlashRegistry();
        AttackSiphonEffect.INSTANCE.registerToSlashRegistry();
        FirePowerEffect.INSTANCE.registerToSlashRegistry();
        BlazingShellEffect.INSTANCE.registerToSlashRegistry();

        ColdEssenceEffect.INSTANCE.registerToSlashRegistry();
        FrostEffect.INSTANCE.registerToSlashRegistry();
        //ShiverEffect.INSTANCE.registerToSlashRegistry();
        JudgementEffect.INSTANCE.registerToSlashRegistry();
        //ShockwaveEffect.INSTANCE.registerToSlashRegistry();
        //ShatterCastEffect.INSTANCE.registerToSlashRegistry();
        FrozenEffect.INSTANCE.registerToSlashRegistry();
        IceBladeEffect.INSTANCE.registerToSlashRegistry();
        NourishmentEffect.INSTANCE.registerToSlashRegistry();
        PurityEffect.INSTANCE.registerToSlashRegistry();
        FrostShieldEffect.INSTANCE.registerToSlashRegistry();
        ArcticArmorEffect.INSTANCE.registerToSlashRegistry();

        HunterInstinctEffect.getInstance()
            .registerToSlashRegistry();
        ImbueEffect.getInstance()
            .registerToSlashRegistry();
        ExertEffect.getInstance()
                .registerToSlashRegistry();
        WoundsEffect.getInstance()
            .registerToSlashRegistry();
        WindWalkEffect.INSTANCE.registerToSlashRegistry();
        ThunderDashEffect.INSTANCE.registerToSlashRegistry();
        SilentWindEffect.INSTANCE.registerToSlashRegistry();
        SummonedZombieEffect.INSTANCE.registerToSlashRegistry();
        SummonedArchonEffect.INSTANCE.registerToSlashRegistry();
        SummonedSkeletonEffect.INSTANCE.registerToSlashRegistry();
        SummonedWolfEffect.INSTANCE.registerToSlashRegistry();
        SnareEffect.INSTANCE.registerToSlashRegistry();
        WeakenEffect.INSTANCE.registerToSlashRegistry();
        NaturesGiftEffect.INSTANCE.registerToSlashRegistry();

        StaticEffect.INSTANCE.registerToSlashRegistry();
        ThunderEssenceEffect.INSTANCE.registerToSlashRegistry();
        CriticalSurgeEffect.INSTANCE.registerToSlashRegistry();
        PowerSurgeEffect.INSTANCE.registerToSlashRegistry();
        QuickChargeEffect.INSTANCE.registerToSlashRegistry();

        EnrageEffect.INSTANCE.registerToSlashRegistry();

        CrippleEffect.INSTANCE.registerToSlashRegistry();
        NecroticTetherEffect.INSTANCE.registerToSlashRegistry();
        SpiritShroudEffect.INSTANCE.registerToSlashRegistry();
        FleshandMindSacrificeEffect.INSTANCE.registerToSlashRegistry();
        SoulShredEffect.INSTANCE.registerToSlashRegistry();

        ComboStarterEffect.INSTANCE.registerToSlashRegistry();
        ComboLinkerEffect.INSTANCE.registerToSlashRegistry();
        ArmorBreakEffect.INSTANCE.registerToSlashRegistry();
        EarthenShellEffect.INSTANCE.registerToSlashRegistry();
        DebilitatedEffect.INSTANCE.registerToSlashRegistry();
        EmpowerEffect.INSTANCE.registerToSlashRegistry();
        EnlightenEffect.INSTANCE.registerToSlashRegistry();
        RallyEffect.INSTANCE.registerToSlashRegistry();

    }
}
