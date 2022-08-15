package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.map_affixes.BaseMapAffix;
import com.robertx22.mine_and_slash.database.map_affixes.beneficial.*;
import com.robertx22.mine_and_slash.database.map_affixes.detrimental.*;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.List;

public class MapAffixes implements ISlashRegistryInit {

    @Override
    public void registerAll() {

        List<BaseMapAffix> All = new ArrayList<>();

        List<BaseMapAffix> list = new ArrayList<BaseMapAffix>() {
            {
                {

                    //add(new LessWeaponDamageMapAffix(WeaponTypes.None));
                    add(new LessPhysDmgAffix());

                    add(new BonusHealthAffix());
                    add(new BonusLifestealAffix());
                    add(new BonusArmorAffix());
                    add(new BonusDodgeAffix());
                    add(new BonusSpellDodgeAffix());
                    add(new BonusAllResistAffix());
                    add(new BonusArmorPenAffix());
                    //add(new BonusDodgeIgnoreAffix());
                    add(new TeamBonusAffix());
                    add(new BonusRegenerationAffix());

                    add(new BonusPhysDmgAffix());
                    add(new BonusPhysDispersionAffixx());

                    //add(new BonusEleDmgAffix(Elements.Nature));
                    add(new BonusEleResistAffix(Elements.Nature));
                    add(new BonusElePenAffix(Elements.Nature));
                    add(new BonusAllElePenAffx());
                    add(new LessEleDmgAffix(Elements.Nature));
                    add(new BonusPhysToFireAffix());
                    add(new BonusPhysToWaterAffix());
                    add(new BonusPhysToThunderAffix());
                    add(new BonusPhysToNatureAffix());

                    add(new LessDodgeAffix());
                    add(new LessArmorAffix());
                    add(new LessCriticalHitAffix());
                    add(new LessCriticalDamageAffix());
                    add(new LessSpellDmgAffix());
                    add(new LessCooldownAffix());
                    add(new MoreManaCostAffix());
                    add(new LessHealPowerAffix());
                    add(new OtherTeamBonusAffix());

                    // resources
                    add(new LessEnergyRegenAffix());
                    add(new LessManaRegenAffix());
                    add(new LessHealthRegenAffix());
                    add(new LessMagicShieldAffix());
                    add(new LessMagicShieldRegenAffix());
                    add(new LessLifeOnHitAffix());
                    add(new LessLifestealAffix());
                    add(new LessSpellstealAffix());
                    add(new LessHealthAffix());
                    add(new LessManaOnHitAffix());

                }
            }
        };

        for (BaseMapAffix affix : list) {

            if (affix instanceof IGenerated) {
                IGenerated<BaseMapAffix> gen = (IGenerated<BaseMapAffix>) affix;
                for (BaseMapAffix statmod : gen.generateAllPossibleStatVariations()) {
                    All.add(statmod);
                }
            } else {

                All.add(affix);
            }
        }

        All.forEach(x -> x.registerToSlashRegistry());

    }

}
