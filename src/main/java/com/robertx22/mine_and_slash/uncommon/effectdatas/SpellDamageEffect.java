package com.robertx22.mine_and_slash.uncommon.effectdatas;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.IHasSpellEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import net.minecraft.entity.LivingEntity;

public class SpellDamageEffect extends DamageEffect implements IHasSpellEffect {

    public BaseSpell spell;

    public SpellDamageEffect(LivingEntity source, LivingEntity target, int dmg, EntityCap.UnitData sourceData,
                             EntityCap.UnitData targetData, BaseSpell spell) {
        super(null, source, target, dmg, sourceData, targetData, EffectTypes.SPELL, WeaponTypes.None);

        this.spell = spell;
        this.element = spell.getElement();
    }

    @Override
    public BaseSpell getSpell() {
        return spell;
    }
}
