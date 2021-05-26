package com.robertx22.mine_and_slash.database.spells.synergies.storm;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.storm.BatteryFusiladeSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.storm.ThunderspearSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.base.OnDamageDoneSynergy;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.shaman.StaticEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class BatteryFusiladeChainSynergy extends OnDamageDoneSynergy {

    @Override
    public List<ITextComponent> getSynergyTooltipInternal(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Hitting an enemy releases a small"));
        list.add(new StringTextComponent("area of effect, dealing damage: "));

        list.addAll(getCalc(Load.spells(info.player)).GetTooltipString(info, Load.spells(info.player), this));

        return list;
    }

    @Override
    public void alterSpell(PreCalcSpellConfigs c) {
        c.set(SC.MANA_COST, 2, 6);
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.MANA_COST, 2, 4);
        c.set(SC.BASE_VALUE, 0, 0);
        c.set(SC.MANA_ATTACK_SCALE_VALUE, 0.02F, 0.06F);
        c.setMaxLevel(10);
        return c;
    }

    @Override
    public Place getSynergyPlace() {
        return Place.FIRST;
    }

    @Nullable
    @Override
    public IAbility getRequiredAbility() {
        return BatteryFusiladeSpell.getInstance();
    }

    @Override
    public void tryActivate(SpellDamageEffect ctx) {

        float radius = 2;

        int num = getCalcVal(ctx.source);

        List<LivingEntity> entities = EntityFinder.start(ctx.source, LivingEntity.class, ctx.target.getPositionVector())
                .radius(radius)
                .build();

        ParticlePacketData pdata = new ParticlePacketData(ctx.target.getPosition()
                .up(1), ParticleEnum.CHARGED_NOVA);
        pdata.radius = radius;
        ParticleEnum.CHARGED_NOVA.sendToClients(ctx.source, pdata);

        for (LivingEntity en : entities) {
            if (en != ctx.target) {
                DamageEffect dmg = new DamageEffect(
                        null, ctx.source, en, num, EffectData.EffectTypes.SPELL, WeaponTypes.None);
                dmg.element = Elements.Thunder;
                dmg.Activate();
            }
        }
    }

    @Override
    public String locNameForLangFile() {
        return "Overload";
    }
}
