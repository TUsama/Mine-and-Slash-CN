package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.divine.*;
import com.robertx22.mine_and_slash.database.spells.spell_classes.divine.buffs.BraverySpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.divine.buffs.TrickerySpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.divine.buffs.WizardrySpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.fire.*;
import com.robertx22.mine_and_slash.database.spells.spell_classes.fire.buffs.DraconicBloodSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.fire.buffs.VampiricBloodSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.hunting.*;
import com.robertx22.mine_and_slash.database.spells.spell_classes.hunting.buffs.SilentWindBuff;
import com.robertx22.mine_and_slash.database.spells.spell_classes.hunting.buffs.WindWalkBuff;
import com.robertx22.mine_and_slash.database.spells.spell_classes.nature.*;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ocean.*;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ocean.buffs.FrostShieldBuff;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ocean.buffs.IceBladeBuff;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ocean.buffs.NourishmentBuff;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ocean.buffs.PurityBuff;
import com.robertx22.mine_and_slash.database.spells.spell_classes.physical.finishers.EarthenSmashFinisherSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.physical.linkers.TripleAttackLinkerSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.physical.strikes.DebilitatingStrikeSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.physical.strikes.PiercingStrikeSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.storm.*;
import com.robertx22.mine_and_slash.database.spells.spell_classes.unholy.*;
import com.robertx22.mine_and_slash.database.spells.spell_classes.unholy.buffs.SpiritShroudSpell;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;

import java.util.ArrayList;
import java.util.List;

public class Spells implements ISlashRegistryInit {

    @Override
    public void registerAll() {

        List<BaseSpell> All = new ArrayList<BaseSpell>() {
            {
                {

                    add(BraverySpell.getInstance());
                    add(TrickerySpell.getInstance());
                    add(WizardrySpell.getInstance());

                    add(HolyFlowerSpell.getInstance());
                    add(SpearOfJudgementSpell.getInstance());
                    add(PurifyingFiresSpell.getInstance());
                    add(HealingAuraSpell.getInstance());
                    add(DefendSpell.getInstance());
                    add(WishSpell.getInstance());
                    add(ProvokeSpell.getInstance());
                    //add(ShockwaveSpell.getInstance());

                    add(FrostballSpell.getInstance());
                    //add(ShatterCastSpell.getInstance());
                    add(WhirlpoolSpell.getInstance());
                    add(HeartOfIceSpell.getInstance());
                    add(TidalWaveSpell.getInstance());
                    add(BlizzardSpell.getInstance());
                    add(FrozenOrbSpell.getInstance());
                    add(FrostShieldBuff.getInstance());
                    add(NourishmentBuff.getInstance());
                    add(PurityBuff.getInstance());
                    add(IceBladeBuff.getInstance());
                    add(FreezeSpell.getInstance());
                    add(ChillingFieldSpell.getInstance());
                    add(FrostTotemSpell.getInstance());
                    add(ArcticArmorSpell.getInstance());
                    add(RimeBladeSpell.getInstance());

                    add(RockSlideSpell.getInstance());
                    add(NatureBalmSpell.getInstance());
                    add(GorgonsGazeSpell.getInstance());
                    add(ThornArmorSpell.getInstance());
                    add(BlazingShellSpell.getInstance());
                    add(ThornBushSpell.getInstance());
                    add(PoisonedWeaponsSpell.getInstance());
                    add(PoisonBallSpell.getInstance());
                    add(PoisonCloudSpell.getInstance());
                    add(RootSpell.getInstance());
                    add(CorrosiveShadowSpell.getInstance());
                    add(MortalitySapSpell.getInstance());
                    add(ThoughtSeizeSpell.getInstance());
                    add(NaturesGiftSpell.getInstance());
                    add(VenomLoadSpell.getInstance());

                    add(ThunderstormSpell.getInstance());
                    add(ThunderspearSpell.getInstance());
                    add(ThunderDashSpell.getInstance());
                    add(LightningTotemSpell.getInstance());
                    add(ElectricalDischargeSpell.getInstance());
                    add(ChargedNovaSpell.getInstance());
                    add(CriticalSurgeSpell.getInstance());
                    add(PowerSurgeSpell.getInstance());
                    add(QuickChargeSpell.getInstance());
                    add(BatteryFusiladeSpell.getInstance());
                    add(RechargeSpell.getInstance());
                    add(ChainLightningSpell.getInstance());

                    add(BlazingInfernoSpell.getInstance());
                    add(FireballSpell.getInstance());
                    add(VolcanoSpell.getInstance());
                    add(MagmaFlowerSpell.getInstance());
                    add(ThrowFlamesSpell.getInstance());
                    add(FireBombsSpell.getInstance());
                    add(FlameBlitzSpell.getInstance());
                    add(DraconicBloodSpell.getInstance());
                    add(VampiricBloodSpell.getInstance());
                    add(SpellBladeSpell.getInstance());
                    add(InfernoQuakeSpell.getInstance());
                    add(SteamCloudSpell.getInstance());
                    add(FirePowerSpell.getInstance());
                    add(SummonArchonSpell.getInstance());

                    add(ArrowBarrageSpell.getInstance());
                    add(DoubleShotSpell.getInstance());
                    add(RecoilShotSpell.getInstance());
                    add(MultiShotSpell.getInstance());
                    add(ImbueSpell.getInstance());
                    add(ExertSpell.getInstance());
                    add(HuntingPotionSpell.getInstance());
                    add(ArrowStormSpell.getInstance());
                    add(HuntingWolfSpell.getInstance());
                    add(WindWalkBuff.getInstance());
                    add(SilentWindBuff.getInstance());
                    add(SnareTrapSpell.getInstance());
                    add(WeakenTrapSpell.getInstance());
                    add(BlastTrapSpell.getInstance());
                    add(CraftArrowSpell.getInstance());
                    add(DashSpell.getInstance());
                    add(HeavyStrikeSpell.getInstance());
                    add(GroundSlamSpell.getInstance());
                    add(WhirlwindSpell.getInstance());
                    add(MagicBurstSpell.getInstance());
                    add(ChargeSpell.getInstance());
                    add(ArrowTotemSpell.getInstance());

                    // unholy spells
                    add(ChillingTouchSpell.getInstance());
                    add(UnholyMeditationSpell.getInstance());
                    add(SummonZombieSpell.getInstance());
                    add(AnimateScytheSpell.getInstance());
                    add(BlightSpell.getInstance());
                    add(SpiritShroudSpell.getInstance());
                    add(LifeSiphonSpell.getInstance());
                    add(BloodTetherSpell.getInstance());
                    add(FleshandMindSacrificeSpell.getInstance());
                    add(MalevolentOfferingSpell.getInstance());
                    add(SoulShredSpell.getInstance());
                    add(HellfireCloakSpell.getInstance());
                    add(BoneSquallSpell.getInstance());
                    add(SummonSkeletalArmySpell.getInstance());

                    // physical spells
                    add(PiercingStrikeSpell.getInstance());
                    add(TripleAttackLinkerSpell.getInstance());
                    add(EarthenSmashFinisherSpell.getInstance());
                    add(DebilitatingStrikeSpell.getInstance());

                }
            }

        };

        All.forEach(x -> x.registerToSlashRegistry());

    }
}
