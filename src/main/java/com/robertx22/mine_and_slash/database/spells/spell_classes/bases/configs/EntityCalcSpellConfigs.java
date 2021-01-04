package com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs;

import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.saveclasses.spells.calc.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
import info.loenwind.autosave.annotations.Factory;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

import javax.annotation.Nullable;
import java.util.HashMap;

@Storable
public class EntityCalcSpellConfigs {

    @Nullable
    @Store
    public SpellCalcData calc = null;

    @Store
    private HashMap<SC, Float> map = new HashMap<>();

    @Factory
    private EntityCalcSpellConfigs() {

    }

    public Float get(SC sc) {

        if (!map.containsKey(sc)) {

            try {
                throw new RuntimeException("Trying to get non existent value: " + sc.name());
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }

        return map.get(sc);
    }

    public EntityCalcSpellConfigs(EntityCap.UnitData data, PlayerSpellCap.ISpellsCap spellsCap, IAbility ability) {
        int lvl = spellsCap != null ? spellsCap.getLevelOf(ability) : 1;
        int userLvl = data.getLevel();

        PreCalcSpellConfigs pre = ability.getPreCalcConfig();

        if (ability.getAbilityType() == IAbility.Type.SPELL) {
            pre.modifyBySynergies(ability.getSpell(), spellsCap);
        }

        if (pre.has(SC.BASE_VALUE)) {
            if (pre.has(SC.ATTACK_SCALE_VALUE)) {
                this.calc = SpellCalcData.scaleWithAttack(pre.get(SC.ATTACK_SCALE_VALUE)
                    .get(spellsCap, ability), pre.get(SC.BASE_VALUE)
                    .get(spellsCap, ability));
            }
            else if (pre.has(SC.ELEMENTAL_ATTACK_SCALE_VALUE)) {
                this.calc = SpellCalcData.scaleWithElementalAttack(pre.get(SC.ELEMENTAL_ATTACK_SCALE_VALUE)
                        .get(spellsCap, ability), pre.get(SC.BASE_VALUE)
                        .get(spellsCap, ability));
            }
            else if (pre.has(SC.PHYSICAL_ATTACK_SCALE_VALUE)) {
                this.calc = SpellCalcData.scaleWithPhysicalAttack(pre.get(SC.PHYSICAL_ATTACK_SCALE_VALUE)
                        .get(spellsCap, ability), pre.get(SC.BASE_VALUE)
                        .get(spellsCap, ability));
            }
            else if (pre.has(SC.FIRE_ATTACK_SCALE_VALUE)) {
                this.calc = SpellCalcData.scaleWithFireAttack(pre.get(SC.FIRE_ATTACK_SCALE_VALUE)
                        .get(spellsCap, ability), pre.get(SC.BASE_VALUE)
                        .get(spellsCap, ability));
            }
            else if (pre.has(SC.WATER_ATTACK_SCALE_VALUE)) {
                this.calc = SpellCalcData.scaleWithWaterAttack(pre.get(SC.WATER_ATTACK_SCALE_VALUE)
                        .get(spellsCap, ability), pre.get(SC.BASE_VALUE)
                        .get(spellsCap, ability));
            }
            else if (pre.has(SC.THUNDER_ATTACK_SCALE_VALUE)) {
                this.calc = SpellCalcData.scaleWithThunderAttack(pre.get(SC.THUNDER_ATTACK_SCALE_VALUE)
                        .get(spellsCap, ability), pre.get(SC.BASE_VALUE)
                        .get(spellsCap, ability));
            }
            else if (pre.has(SC.NATURE_ATTACK_SCALE_VALUE)) {
                this.calc = SpellCalcData.scaleWithFireAttack(pre.get(SC.NATURE_ATTACK_SCALE_VALUE)
                        .get(spellsCap, ability), pre.get(SC.BASE_VALUE)
                        .get(spellsCap, ability));
            }
            else if (pre.has(SC.ARMOR_ATTACK_SCALE_VALUE)) {
                this.calc = SpellCalcData.scaleWithArmorAttack(pre.get(SC.ARMOR_ATTACK_SCALE_VALUE)
                        .get(spellsCap, ability), pre.get(SC.BASE_VALUE)
                        .get(spellsCap, ability));
            }
            else if (pre.has(SC.HEALTH_ATTACK_SCALE_VALUE)) {
                this.calc = SpellCalcData.scaleWithHealthAttack(pre.get(SC.HEALTH_ATTACK_SCALE_VALUE)
                        .get(spellsCap, ability), pre.get(SC.BASE_VALUE)
                        .get(spellsCap, ability));
            }
            else {
                this.calc = SpellCalcData.base(pre.get(SC.BASE_VALUE)
                    .get(spellsCap, ability));
            }
        }

        pre.getMap()
            .entrySet()
            .forEach(x -> {
                this.map.put(x.getKey(), x.getValue()
                    .get(spellsCap, ability));
            });

    }
}
