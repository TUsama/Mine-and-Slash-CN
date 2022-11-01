package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.Trait;
import com.robertx22.mine_and_slash.database.stats.mods.AllTraitMods;
import com.robertx22.mine_and_slash.database.stats.mods.Mod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.corestats.AllAttributesFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.corestats.CoreStatFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.*;
import com.robertx22.mine_and_slash.database.stats.mods.flat.elemental.AllDotDmgFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.elemental.AllEleDmgFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.misc.*;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.*;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.*;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.conversions.EnergyToManaConvFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.conversions.ManaToEnergyConvFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.*;
import com.robertx22.mine_and_slash.database.stats.mods.mob_spells.*;
import com.robertx22.mine_and_slash.database.stats.mods.multi.defense.ArmorMulti;
import com.robertx22.mine_and_slash.database.stats.mods.multi.defense.CriticalHitMulti;
import com.robertx22.mine_and_slash.database.stats.mods.multi.defense.DodgeRatingMulti;
import com.robertx22.mine_and_slash.database.stats.mods.multi.defense.HealthMulti;
import com.robertx22.mine_and_slash.database.stats.mods.multi.offence.PhysicalDamageMulti;
import com.robertx22.mine_and_slash.database.stats.mods.multi.resources.ManaMulti;
import com.robertx22.mine_and_slash.database.stats.mods.percent.*;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.CriticalDamagePercent;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.CriticalHitPercent;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.PhysicalDamagePercent;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.SpellDamagePercent;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Dexterity;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.registry.empty_entries.EmptySpell;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.LootType;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.List;

public class StatMods implements ISlashRegistryInit {

    @Override
    public void registerAll() {

        List<StatMod> list = new ArrayList<StatMod>() {
            {
                {

                    add(new PlusAllSkillLevelsFlat());
                    add(new PlusAbiliyLevelFlat(new EmptySpell()));
                    add(new PlusAllSkillLevelsInSchoolFlat(Masteries.OCEAN));
                    add(new ElementalInfusionPercent(Elements.Nature));
                    add(new LootTypeBonusFlat(LootType.NormalItem));
                    add(new WeaponDamageFlat(WeaponTypes.None));
                    add(new ElementalAttackDamageFlat(Elements.Nature));
                    add(new ElementalAttackDamagePercent(Elements.Nature));
                    add(new AllElementalDamageMulti(Elements.Nature));
                    add(new ElementalInfusionFlat(Elements.Nature));
                    add(new ElementalSpellDamagePercent(Elements.Nature));
                    add(new ElementalSpellDamageFlat(Elements.Nature));
                    add(new ElementalResistFlat(Elements.Nature));
                    add(new ElementalSpellDamageMulti(Elements.Nature));
                    add(new ElementalPeneFlat(Elements.Nature));
                    add(new ElementalPenePercent(Elements.Nature));
                    add(new ElementalFocusFlat(Elements.Nature));
                    add(new BlockReflectFlat(Elements.Nature));
                    add(new CoreStatFlat(Dexterity.INSTANCE));
                    add(new CoreStatPercent(Dexterity.INSTANCE));

                    add(Mod.LUCK_FLAT());

                    add(new IncreasedDurationFlat());
                    add(new IncreasedAreaFlat());
                    add(new IncreasedProjSpeedFlat());
                    add(new CooldownReductionFlat());
                    add(new ReducedManaCostFlat());
                    add(new FasterCastRateFlat());
                    add(new SLowOnDamageTakenFlat());

                    // mob spells
                    add(new FireStrikeFlat());
                    add(new FrostNovaFlat());
                    add(new LightningDischargeFlat());
                    add(new WeakenCurseFlat());
                    add(new SpeedyFlat());
                    add(new BloodyStrikeFlat());

                    add(new CompletePhysDispersionFlat());
                    add(new HealPowerFlat());
                    add(new AllEleDmgFlat());
                    add(new AllDotDmgFlat());
                    add(new EleToPhysTransferFlat());
                    add(new PhysToFireConvFlat());
                    add(new PhysToNatureConvFlat());
                    add(new PhysToWaterConvFlat());
                    add(new PhysToThunderConvFlat());

                    add(new BlockStrengthPercent());
                    add(new SpellDamageFlat());
                    add(new SummonDamageFlat());
                    add(new SpellDamagePercent());
                    add(new ArmorPenetrationFlat());
                    //add(new DodgeIgnoreFlat());

                    add(new BonusExpFlat());

                    // spell buffs

                    add(new ManaMulti());

                    add(new PhysicalDamageMulti());

                    add(new HealthMulti());
                    add(new DodgeRatingMulti());
                    add(new CriticalHitMulti());
                    add(new ArmorMulti());

                    //
                    add(new EnergyToManaConvFlat());
                    add(new ManaToEnergyConvFlat());

                    // weapon damages

                    add(new ArmorFlat());
                    add(new CriticalHitFlat());
                    add(new CriticalDamageFlat());
                    add(new PhysicalDamageFlat());
                    add(new CriticalHitPercent());
                    add(new PhysicalDamagePercent());
                    add(new CriticalDamagePercent());
                    add(new DodgeRatingPercent());
                    add(new BlockStrengthFlat());

                    // Resources

                    add(new HealthFlat());
                    add(new HealthPercent());
                    add(new HealthRegenPercent());
                    add(new HealthRegenFlat());
                    add(new ManaRegenFlat());
                    add(new EnergyRegenFlat());
                    add(new EnergyRegenPercent());
                    add(new ManaRegenPercent());
                    add(new EnergyFlat());
                    add(new MagicShieldFlat());
                    add(new MagicStealFlat());
                    add(new MagicShieldRegenFlat());
                    add(new MagicShieldPercent());
                    add(new MagicShieldRegenPercent());

                    add(new LifestealFlat());
                    add(new LifestealPercent());
                    add(new LifeOnHitFlat());
                    add(new LifeOnHitPercent());
                    add(new ManaFlat());
                    add(new ManaOnHitFlat());
                    add(new SpellStealFlat());
                    add(new SpellStealPercent());
                    add(new DoTStealFlat());
                    add(new DoTStealPercent());
                    // Resources

                    add(new ArmorPercent());
                    add(new DodgeRatingFlat());
                    add(new SpellDodgeFlat());
                    add(new DamageShieldFlat());
                    add(new IncreaseDamageFlat());
                    add(new DamageTakenFlat());

                    // bonus dmg

                    // Map mods

                    add(new AllAttributesFlat());

                }
            }
        };

        List<StatMod> All = new ArrayList<>();

        for (StatMod stat : list) {
            if (stat instanceof IGenerated) {
                IGenerated<StatMod> gen = (IGenerated<StatMod>) stat;
                for (StatMod statmod : gen.generateAllPossibleStatVariations()) {
                    All.add(statmod);
                }
            } else {
                All.add(stat);
            }
        }

        // this makes all StatMod classes for traits cus they are all the same!
        for (Stat stat : SlashRegistry.Stats()
            .getList()) {
            if (stat instanceof Trait) {
                Trait trait = (Trait) stat;
                AllTraitMods traitMod = new AllTraitMods(trait);
                All.add(traitMod);
            }
        }

        All.forEach(x -> {
            x.getAllSizeVariations()
                .forEach(v -> v.registerToSlashRegistry());
        });

    }

}
