package com.robertx22.mine_and_slash.uncommon.localization;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;

import java.util.Locale;

public enum Words implements IAutoLocName {
    Trickery("Trickery"),
    Bravery("Bravery"),
    Wizardry("Wizardry"),
    CooldownSeconds("Cooldown Seconds"), NatureBalm("Nature's Balm"),
    CooldownTicks("Cooldown Ticks"),
    CastTimeTicks("Cast Time Ticks"),
    Radius("Radius"),
    ProjectileCount("Projectile Count"),
    ShootSpeed("Shoot Speed"),
    SummonedEntities("Max Summoned Entities"),
    Chance("Chance"),
    CooldownReductionEfficiency("Cooldown Reduction Efficiency"),
    DurationTicks("Duration in Ticks"),
    TickRate("Tick Rate"),
    TimesToCast("Times to Cast"), BonusHealth("Bonus Health"),
    Amount("Amount"),

    RightClickToTeleport("Use to teleport back from Adventure Map."),
    RightClickToIdentifyFirst("Use to identify the first item in your inventory."),
    RightClickToIdentifyAll("Use to identify all items in your inventory."),
    ItemIsUnidentified("This item is currently sealed."),
    UseAnIdentifyScroll("An identification item can reveal it."),
    KillBoss("Kill Boss: "), BlinkStrike("Blink Strike"),
    Kill("Kill"),
    Magic_Missile("Magic Missiles"),
    Arcanist("Arcanist"),
    OpenAnyLootCrates("Open Any Loot Crates"),
    ThornBush("Thorn Bush"), HolyFlower("Holy Flower"),
    MagmaFlower("Magma Flower"),
    Fire("Fire"), Mastery("Mastery"),
    Ocean("Ocean"), Hunting("Hunting"), Rogue("Rogue"),
    Storm("Storm"), Divine("Divine"), Unholy("Unholy"),
    Nature("Nature"), Physical("Physical"),
    Cleric("Cleric"), BatteryFusilade("Battery Fusilade"),
    HeartOfIce("Heart of Ice"),
    ThornArmor("Thorn Armor"), Recharge("Recharge"), ArcticArmor("Arctic Armor"), BlazingShell("Blazing Shell"),
    PoisonedWeapons("Poisoned Weapons"),
    BlazingInferno("Blazing Inferno"), SpearOfJudgement("Spear of Judgement"),
    ChargedNova("Charged Nova"), PoisonCloud("Poison Cloud"),
    DivineTribulation("Divine Tribulation"), PurifyingFires("Purifying Fires"),
    HeavyStrike("Heavy Strike"), GroundSlam("Ground Slam"), Whirlwind("Whirlwind"), PiercingStrike("Piercing Strike"), TripleAttackLinker("Triple Attack Linker"),
    Fireball("Fireball"), FireBomb("Fire Bombs"), ElectricalDischarge("Electrical Discharge"), EarthenSmashFinisher("Earthen Smash Finisher"),
    PoisonBall("Poison Ball"), RockSlide("RockSlide"), MagicBurst("Magic Burst"), Charge("Charge"), DebilitatingStrike("Debilitating Strike"),
    Frostball("Frostball"), FrozenOrb("Ice Orb"), FrostShield("Frost Shield"), FlameBlitz("Flame Blitz"), GoadingStrike("Goading Strike"),
    Freeze("Freeze"), Root("Root"), InfernoQuake("Inferno Quake"), RimeBlade("Rime Blade"), Shockwave("Divine Blade"),
    Nourishment("Nourishment"), Purity("Purity"), IceBlade("Ice Blade"), SnareTrap("Snare Trap"), WeakenTrap("Enfeeble Trap"), BlastTrap("Blast Trap"),
    ThunderSpear("Thunder Spear"), ShatterCast("Shatter Cast"), CraftArrow("Craft Arrows"), DoubleShot("Double Shot"),
    ThunderDash("Thunder Dash"), TripleSlash("Triple Slash"), Dash("Dash"), ChillingTouch("Chilling Touch"), ChainLightning("Chain Lightning"),
    Thunderstorm("Thunderstorm"), Stealth("Stealth"), UnholyMeditation("Unholy Meditation"), SummonZombie("Summon Zombie"), SummonArchon("Summon Archon"),
    ArrowBarrage("Arrow Barrage"), WideShot("Wide Shot"), ArrowStorm("Arrow Storm"), HuntingPotion("Hunter's Potion"),
    RecoilShot("Recoil Shot"), TidalWave("Tidal Wave"), ThrowFlames("Meteoric Strike"), TotalMastery("Total Mastery"),
    LightningTotem("Lightning Totem"), HealingAura("Healing Aura"), ThoughtSeize("Thought Seize"), MortalitySap("Mortality Sap"),
    CorrosiveShadow("Corrosive Shadow"), ChillingField("Chilling Field"), SteamCloud("Steam Cloud"), Defend("Defend"), FrostTotem("Frost Totem"),
    Wish("Wish"), HuntingWolf("Summon Hunting Wolf"), SpiritShroud("Spirit Shroud"), ArrowTotem("Arrow Totem"),
    Volcano("Volcano"), VampiricBlood("Vampiric Blood"), DraconicBlood("Draconic Blood"),
    Blizzard("Blizzard"), QuickCharge("Quick Charge"), LifeSiphon("Life Siphon"), BloodTether("Blood Tether"), NaturesGift("Nature's Gift"), FirePower("Fire Power"),
    Whirpool("Whirpool"), AnimateScythe("Animate Scythe"), Blight("Blight"),
    Geyser("Geyser"), FleshAndMindSacrifice("Flesh & Mind Sacrifice"), MalevolentOffering("Malevolent Offering"),
    GorgonsGaze("Gorgon's Gaze"), SoulShred("Soul Shred"), BoneSquall("Bone Squall"), VenomLoad("Venom Load"),
    Regenerate("Regenerate"), SummonSkeletalArmy("Summon Skeletal Army"),
    InstantHeal("Instant Heal"), HellfireCloak("Hellfire Cloak"), Provoke("Provoke"),
    RighteousFury("Righteous Fury"),
    DivineShield("Divine Shield"), Imbue("Imbue"), Exert("exert"), ElementalQuiver("Elemental Quiver"),
    Spellbar("Spellbar"), SpellBlade("Spell Blade"), PowerSurge("Power Surge"), CriticalSurge("Critical Surge"),
    StormCloudSpellDesc("Summons a storm cloud that deals damage over time."),
    Crate("Crate"), SweepingStrike("Sweeping Strike"), ElementalStrike("Elemental Strike"), FlowingRiverLinker("Flowing River Linker"), RagingDragonLinker("Raging Dragon Linker"),
    RallyingSweepLinker("Rallying Sweep Linker"), SpiritDrainLinker("Spirit Drain Linker"), EmpoweringFinisher("Empowering Finisher"), ElementalVengeanceFinisher("Elemental Vengeance Finisher"),
    AntiGravityFinisher("Anti-Gravity Finisher"), OmnislashFinisher("Omnislash Finisher"), PowerForm("Power Form"), WisdomForm("Wisdom Form"), Asura("Asura"),
    MythicCrate("Mythic Crate"), Seethe("Seethe"), Blessing("Blessing"), AngelicRaiment("Angelic Raiment"),
    JewerlyCrafterCrate("Jewerly Crafter's Crate"),
    ArmorCrafterCrate("Armor Crafter's Crate"),
    WeaponcraftersCrate("Weapon Crafter's Crate"),
    Talents("Talents"),
    StatOverview("Stat Info"),
    StatPoints("Stat Points"),
    MapInfo("Map Info"),
    Compendium("Compendium"),
    KillMobsCollectRarityPoints("Kill mobs, collect rarity points"),
    KillMobs("Kill Mobs"),
    Bad("Bad"),
    Good("Good"),
    Average("Average"),
    Great("Great"),
    Amazing("Amazing"),
    CurrencyCrate("Crafter's Paradise Crate"),
    SilentWind("Silent Wind"),
    RuneCrate("Runecrafter's Crate"),
    CartographerCrate("Cartographer's Crate"),
    CommonerCrate("Commoner's Dream Crate"),
    UniqueCrate("Pharaoh Crate"),
    PressAltForStatInfo("Press Alt for Stat Desc"),
    MustBeMap("Must be an Adventure Map"),
    MustBeGear("Must be a Gear Item"),
    NotSpell("Not a Spell Item"),
    NotRune("Not a Rune Item"),
    Locked("Locked"),
    Broken("Broken"),
    Alchemy("Alchemy"),
    InstabilityLimitReached("Instability Limit Reached"),
    BreakChance("Break Chance"),
    Instability("Instability"),
    CraftingDeletesItemsInside("Crafting deletes items inside, empty beforehand!"),
    CurrentMapInfo("Current Map Info"),
    Decreased("Decreased"),
    Increased("Increased"),
    Flat("Flat"),
    PressShiftForRequirements("Press Shift for Requirements"),
    isUnique("Is Unique"),
    OnlyOneUniqueRune("Only One Unique Rune Per Gear"),
    hasMatchingRunes("Matching Runes are in gear"),
    canUpgradeInfusion("Can Upgrade Infusion"),
    noGroupAffix("No Group Map Affix"),
    Runelvlnothigherthanitemlvl("Rune lvl not higher than item lvl"),
    ItemHasRuneSlots("Item has empty rune slot"),
    NoDuplicateRunes("No duplicate runes of same type"),
    hasUniqueStats("Has Unique stats"),
    hasChaosStats("Has Chaos or Major Arcana Chaos stats"),
    hasSet("Has Set"),
    hasPrimaryStats("Has Primary Stats"),
    hasSecondaryStats("Has Secondary Stats"),
    hasSuffix("Has Suffix"),
    hasPrefix("Has Prefix"),
    isNotUnique("Is Not Unique"),
    isLowerThanLegendary("Is Not Legendary"),
    IsCommon("Is Common Rarity"),
    IsNotCommon("Is Not Common Rarity"),
    LvlLessThanMax("Lvl Less than max lvl"),
    NoChaosStats("Doesn't have Chaos Stats"),
    HasChaosStats("Has Chaos Stats"),
    HasInfusion("Has Infusion Stats"),
    NoSuffix("Doesn't have suffix"),
    NoPrefix("Doesn't have prefix"),
    CanOnlyUseOnce("Can only use once"),
    CanOnlyUse10times("Can only use 10 times"),
    Doesnthaveset("Doesn't have set"),
    TierLessThanMax("Tier less than maximum"),
    AllowedOn("Allowed on"),
    NotAllowedOn("Not Allowed on: "),
    Unique_Gear("Unique Gear"),
    Normal_Gear("Normal Gear"),
    Runed_Gear("Runed Gear"),
    PicksUpItemsAuto("Automatically picks up certain items!"),
    HoldToPreventPickup("Hold in your hand to prevent item pickup."),
    BewareCreativeBagBug1("Beware, switching to Creative"),
    BewareCreativeBagBug2("while on server could clear the bag!"),
    AddRarestAffixes("Add Rarest Affixes"),
    PefectPrimaryStats("Perfect Primary Stats"),
    BetterPrimaryStats("Better Primary Stats"),
    AddSet("Add Set"),
    AddChaosStats("Add ChaosStats"),
    NeedsGearWithRunesInserted("Needs Gear with these runes inserted"),
    Requirements("Requirements"),
    RequirementsNotMet("Requirements not met"),
    Blueprint("Blueprint"),
    AddMajorArcana("Add Major Arcana Chaos Stats"),
    AlwaysMythicAffixes("Always has Mythic Affixes"),
    AlwaysChaosStats("Always has Chaos Stats"),
    AlwaysSet("Always has Set"),
    Armor("Armor"),
    Weapon("Weapon"),
    WeaponOffhand("Weapon/Offhand"),
    Jewerly("Jewerly"),
    Offhand("Offhand"),
    RunesNeeded("Runes needed"),
    WindWalk("Wind Walk"),

    Item_modifiable_in_station("Can be used inside Modify Station"),
    unlocks_runeword_combo("Unlocks RuneWord combination."),

    Press_Shift_For_Setup_Info("Press Shift For Setup Info"),

    Press_Shift_For_More_Info("Press Shift For More Info"),

    Penetration("Penetration"),

    Map_Device("Map Device"),

    Core_Stat("Core Stat"),

    Elemental_Attack_Damage("Elemental Attack Damage"),

    Main("Main"),
    Unidentified("Unidentified"),
    Activation_Time("Activation Time"),

    Affixes_Affecting_All("Affixes Affecting All"),

    Animal("Animal"),

    Attack("Attack"),

    BaseValue("Base Value"),

    Works_when_equipped("Works when equipped"),

    Blocks("Blocks"),

    Automatically_salvages_items("Automatically salvages items"),
    Gives("Gives"),
    ClickToUse("Click to Use"),
    Loot("Loot"), Exp("Exp"),

    Bonus_Salvage_Chance("Bonus Salvage Chance"),

    Bonus_Success_Rate("Bonus Success Rate"),

    By("By"),

    Chaos_Stats("Chaos Stats"),

    Cooldown("Cooldown"),

    Currency("Currency"),

    Damage("Damage"),

    Dealt("Dealt"),

    Defenses("Defenses"),

    Distance("Distance"),

    Empty("Empty"),

    From("From"),

    Fuel("Fuel"),

    Gears("Gears"),

    Infusion("Infusion"),

    Item("Item"),

    Left("Left"),

    Level("Level"),

    Major_Arcana("Major Arcana"),

    Major_Failure_Chance("Major Failure Chance"),

    Major_Success_Bonus("Major Success Bonus"),

    Major_Success_Chance("Major Success Chance"),

    Mana_Cost("Mana Cost"),

    Health_Cost("Health Cost"),

    Magic_Shield_Cost("Magic Shield Cost"),

    PhysicalAttackScaleValue("Physical Attack Scaling"),

    Energy_Cost("Energy Cost"),

    Map("Map"),

    Maps("Maps"),

    Minutes("Minutes"),

    Misc("Misc"),

    Mob("Mob"),

    Mob_Affixes("Mob Affixes"),

    Multi("Multi"),

    None("None"),

    Not_a_Map_World("Not a Map World"),

    Npc("Npc"),

    Percent("Percent"),

    GroupPlay("Group Play"),

    PartySize("Party Size"),

    Permadeath("Permadeath"),

    Player_Affixes("Player Affixes"),

    Position("Position"),

    Prefix("Prefix"),

    Primary_Stats("Primary Stats"),

    Progress("Progress"),

    Put_Map("Put Map"),

    Rarity("Rarity"),

    Regeneration("Regenerate"),

    Resources("Resources"),

    Runed("Runed"),

    Runes("Runes"),

    Runeword("Runeword"),

    Sacrifice_Map_For_Level("Sacrifice Map For Level"),

    Sacrifice_Map_For_Tier("Sacrifice Map For Tier"),

    Scaling_Value("Scaling Value"),

    Secondary_Stats("Secondary Stats"),

    Seconds("Seconds"),

    Set("Set"),

    Socket("Socket"),

    Sockets("Sockets"),

    Spell("Spell"),

    Spell_Damage("Spell Damage"),

    Spells("Spells"),

    Start("Start"),

    Stats("Stats"),

    Suffix("Suffix"), SummonSpiders("Summon Spiders"), SpiritWolves("Spirit Wolves"),

    Tier("Tier"),

    To("To"),

    Took("Took"),

    Type("Type"),

    Unique_Stats("Unique Stats"),

    Unsalvagable("Unsalvagable"),

    UsableOn("Usable on"),

    Use_Time("Use Time"),

    Uses("Uses"),

    Beware("Beware"),

    DoNotBuildInMaps("Do not build in maps!"),

    World_Type("World Type"),

    Repair_Station("Repair Station");

    private String localization = "";

    Words(String str) {
        this.localization = str;

    }

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Words;
    }

    @Override
    public String locNameLangFileGUID() {
        return Ref.MODID + ".word." + formattedGUID();
    }

    @Override
    public String locNameForLangFile() {
        return localization;
    }

    @Override
    public String GUID() {
        return this.name()
            .toLowerCase(Locale.ROOT);
    }
}
