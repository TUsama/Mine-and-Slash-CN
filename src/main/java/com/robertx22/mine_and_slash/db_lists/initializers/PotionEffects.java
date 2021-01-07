package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.potion_effects.divine.EnrageEffect;
import com.robertx22.mine_and_slash.potion_effects.divine.JudgementEffect;
import com.robertx22.mine_and_slash.potion_effects.druid.*;
import com.robertx22.mine_and_slash.potion_effects.ember_mage.BurnEffect;
import com.robertx22.mine_and_slash.potion_effects.ocean_mystic.*;
import com.robertx22.mine_and_slash.potion_effects.ranger.*;
import com.robertx22.mine_and_slash.potion_effects.shaman.StaticEffect;
import com.robertx22.mine_and_slash.potion_effects.shaman.ThunderEssenceEffect;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;

public class PotionEffects implements ISlashRegistryInit {

    @Override
    public void registerAll() {

        PetrifyEffect.INSTANCE.registerToSlashRegistry();
        PoisonedWeaponsEffect.getInstance()
            .registerToSlashRegistry();
        RegenerateEffect.INSTANCE.registerToSlashRegistry();
        ThornArmorEffect.INSTANCE.registerToSlashRegistry();
        ThornsEffect.INSTANCE.registerToSlashRegistry();

        BurnEffect.INSTANCE.registerToSlashRegistry();

        ColdEssenceEffect.INSTANCE.registerToSlashRegistry();
        FrostEffect.INSTANCE.registerToSlashRegistry();
        ShiverEffect.INSTANCE.registerToSlashRegistry();
        JudgementEffect.INSTANCE.registerToSlashRegistry();
        FrozenEffect.INSTANCE.registerToSlashRegistry();
        IceBladeEffect.INSTANCE.registerToSlashRegistry();
        NourishmentEffect.INSTANCE.registerToSlashRegistry();
        FrostShieldEffect.INSTANCE.registerToSlashRegistry();

        HunterInstinctEffect.getInstance()
            .registerToSlashRegistry();
        ImbueEffect.getInstance()
            .registerToSlashRegistry();
        WoundsEffect.getInstance()
            .registerToSlashRegistry();
        WindWalkEffect.INSTANCE.registerToSlashRegistry();
        SilentWindEffect.INSTANCE.registerToSlashRegistry();
        SnareEffect.INSTANCE.registerToSlashRegistry();
        WeakenEffect.INSTANCE.registerToSlashRegistry();

        StaticEffect.INSTANCE.registerToSlashRegistry();
        ThunderEssenceEffect.INSTANCE.registerToSlashRegistry();

        EnrageEffect.INSTANCE.registerToSlashRegistry();

    }
}
