package com.robertx22.mine_and_slash.database.spells.synergies.unholy;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.unholy.ChillingTouchSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.base.OnAttackSpellDmgDoneSynergy;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.necromancer.CrippleEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.AttackSpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ChillingTouchHexSynergy extends OnAttackSpellDmgDoneSynergy {

    @Override
    public List<ITextComponent> getSynergyTooltipInternal(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Synergy"));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Modifies Chilling Touch"));

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent("Hits cause nearby summons to attack the target."));

        list.addAll(getCalc(Load.spells(info.player)).GetTooltipString(info, Load.spells(info.player), this));

        return list;
    }

    @Override
    public Place getSynergyPlace() {
        return Place.SECOND;
    }

    @Override
    public void alterSpell(PreCalcSpellConfigs c) {
        c.set(SC.MANA_COST, 4, 1);
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.BASE_VALUE, 0, 0);
        c.set(SC.RADIUS, 6, 18);
        c.setMaxLevel(4);
        return c;
    }

    @Nullable
    @Override
    public IAbility getRequiredAbility() {
        return ChillingTouchSpell.getInstance();
    }

    @Override
    public void tryActivate(AttackSpellDamageEffect ctx) {
        float radius = getContext(ctx.source).getConfigFor(this)
                .get(SC.RADIUS)
                .get(Load.spells(ctx.source), this);

        List<LivingEntity> entities = EntityFinder.start(ctx.source, LivingEntity.class, ctx.target.getPositionVector())
                .radius(radius)
                .searchFor(EntityFinder.SearchFor.ALLIES)
                .build();

        for (LivingEntity en : entities) {
            if (en instanceof TameableEntity) {
                TameableEntity tameableEntity = (TameableEntity) en;
                if (tameableEntity.isTamed()) {
                    if (tameableEntity.isOwner(ctx.source)) {
                        ((MobEntity) en).setAttackTarget(ctx.target);
                    }
                }
            }
        }
    }

    @Override
    public String locNameForLangFile() {
        return "Necromancer's Hex";
    }
}
