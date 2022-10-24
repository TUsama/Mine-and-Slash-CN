package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.database.currency.*;
import com.robertx22.mine_and_slash.database.currency.base.CurrencyItem;
import com.robertx22.mine_and_slash.database.currency.map.EndlessRoadItem;
import com.robertx22.mine_and_slash.database.currency.map.EndlessSkiesItem;
import com.robertx22.mine_and_slash.database.currency.map.OrbOfCompanionshipItem;
import com.robertx22.mine_and_slash.database.currency.map.PainfulLessonItem;
import com.robertx22.mine_and_slash.items.events.PumpkinJuiceItem;
import com.robertx22.mine_and_slash.items.misc.*;
import com.robertx22.mine_and_slash.items.reset_potions.*;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModItems {

    public static DeferredRegister<Item> REG = new DeferredRegister<>(ForgeRegistries.ITEMS, Ref.MODID);

    public static List<RegistryObject<CurrencyItem>> currencies = new ArrayList<>();

    public static RegistryObject<CurrencyItem> ORB_OF_TRANSMUTATION = of(() -> new OrbOfTransmutationItem());
    public static RegistryObject<CurrencyItem> ORB_OF_ALCHEMY = of(() -> new OrbOfAlchemyItem());
    public static RegistryObject<CurrencyItem> GEM_OF_UNIQUE_HEAVEN = of(() -> new GemOfUniqueHeaven());
    public static RegistryObject<CurrencyItem> CHAOTIC_WISP = of(() -> new ChaoticWispItem());
    public static RegistryObject<CurrencyItem> UNEARTH_PREFIX = of(() -> new UnearthPrefixItem());
    public static RegistryObject<CurrencyItem> UNEARTH_SUFFIX = of(() -> new UnearthSuffixItem());
    public static RegistryObject<CurrencyItem> KEY_OF_UNITY = of(() -> new KeyOfUnityItem());
    public static RegistryObject<CurrencyItem> CRYSTAL_OF_LEGEND = of(() -> new CrystalOfLegendItem());
    //public static RegistryObject<CurrencyItem> KEY_OF_ENTROPY = of(() -> new KeyOfEntropy());

    public static RegistryObject<CurrencyItem> CHAOS_ORB = of(() -> new ChaosOrbItem());
    public static RegistryObject<CurrencyItem> STONE_OF_CORRUPTION = of(() -> new StoneOfCorruptionItem());
    public static RegistryObject<CurrencyItem> ORB_OF_FORGETFULNESS = of(() -> new OrbOfForgetfulnessItem());
    public static RegistryObject<CurrencyItem> CRYSTAL_OF_ASCENSION = of(() -> new CrystalOfAscensionItem());

    public static RegistryObject<CurrencyItem> ORB_OF_EVER_CHANGING_PREFIX = of(() -> new OrbOfEverChangingPrefixItem());
    public static RegistryObject<CurrencyItem> ORB_OF_EVER_CHANGING_SUFFIX = of(() -> new OrbOfEverChangingSuffixItem());

    public static RegistryObject<CurrencyItem> ORB_OF_PREFIX_BLESSING = of(() -> new OrbOfPrefixBlessingItem());
    public static RegistryObject<CurrencyItem> ORB_OF_SUFFIX_BLESSING = of(() -> new OrbOfSuffixBlessingItem());

    public static RegistryObject<CurrencyItem> STONE_OF_HOPE = of(() -> new StoneOfHopeItem());
    public static RegistryObject<CurrencyItem> LEAF_OF_CHANGE = of(() -> new LeafOfChangeItem());
    public static RegistryObject<CurrencyItem> ORB_OF_CHANCES = of(() -> new OrbOfChancesItem());
    public static RegistryObject<CurrencyItem> KEY_OF_NEW_DAWN = of(() -> new KeyOfNewDawnItem());
    public static RegistryObject<CurrencyItem> ORB_OF_BLESSING = of(() -> new OrbOfBlessingItem());
    public static RegistryObject<CurrencyItem> ORB_OF_UNIQUE_BLESSING = of(() -> new OrbOfUniqueBlessingItem());

    public static RegistryObject<CurrencyItem> ENDLESS_SKIES = of(() -> new EndlessSkiesItem());
    public static RegistryObject<CurrencyItem> ENDLESS_ROAD = of(() -> new EndlessRoadItem());
    public static RegistryObject<CurrencyItem> PAINFUL_LESSON = of(() -> new PainfulLessonItem());
    public static RegistryObject<CurrencyItem> ORB_OF_COMPANIONSHP = of(() -> new OrbOfCompanionshipItem());

    public static RegistryObject<ResetStatsPotionItem> RESET_STATS = item(() -> new ResetStatsPotionItem());
    public static RegistryObject<ResetSpellsPotionItem> RESET_SPELLS = item(() -> new ResetSpellsPotionItem());
    public static RegistryObject<ResetTalentsPotionItem> RESET_TALENTS = item(() -> new ResetTalentsPotionItem());
    public static RegistryObject<ResetAllPotionItem> RESET_ALL = item(() -> new ResetAllPotionItem());

    public static RegistryObject<PumpkinJuiceItem> PUMPKIN_JUICE = item(() -> new PumpkinJuiceItem());

    public static RegistryObject<AddRemoveSpellPotionItem> ADD_RESET_SPELLS = item(() -> new AddRemoveSpellPotionItem());
    public static RegistryObject<AddRemoveTalentPotionItem> ADD_RESET_TALENTS = item(() -> new AddRemoveTalentPotionItem());

    public static RegistryObject<IdentifyTomeItem> IDENTIFY_TOME = item(() -> new IdentifyTomeItem(), "identify_tome");
    public static RegistryObject<IdentifyTomePlusItem> IDENTIFY_TOME_PLUS = item(() -> new IdentifyTomePlusItem(), "identify_tome_plus");
    public static RegistryObject<TeleportScrollItem> TELEPORT_SCROLL = item(() -> new TeleportScrollItem(), "teleport_scroll");
    public static RegistryObject<ExpItem> GIVE_EXP = item(() -> new ExpItem(), "give_exp");


    public static RegistryObject<BaseAffixItem> PREFIX_ITEM = item(() -> new BaseAffixItem(), "prefix");
    public static RegistryObject<BaseAffixItem> SUFFIX_ITEM = item(() -> new BaseAffixItem(), "suffix");

    static <T extends Item & IGUID> RegistryObject<T> item(Supplier<T> c) {

        RegistryObject<T> wrap = REG.register(c.get()
            .GUID(), c);

        return wrap;

    }

    static <T extends Item> RegistryObject<T> item(Supplier<T> c, String id) {
        RegistryObject<T> wrap = REG.register(id, c);
        return wrap;
    }

    static RegistryObject<CurrencyItem> of(Supplier<CurrencyItem> c) {

        RegistryObject<CurrencyItem> wrap = REG.register(c.get()
            .GUID(), c);

        currencies.add(wrap);

        return wrap;

    }

}
