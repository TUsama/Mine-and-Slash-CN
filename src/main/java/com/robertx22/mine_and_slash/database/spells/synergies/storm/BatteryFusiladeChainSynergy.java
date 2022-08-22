package com.robertx22.mine_and_slash.database.spells.synergies.storm;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.storm.BatteryFusiladeSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.base.OnDamageDoneSynergy;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.shaman.ThunderEssenceEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class BatteryFusiladeChainSynergy extends OnDamageDoneSynergy {

    @Override
    public List<ITextComponent> getSynergyTooltipInternal(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Synergy (Bolt)"));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Modifies Battery Fusillade"));

        TooltipUtils.addEmpty(list);
        list.add(new StringTextComponent(TextFormatting.GRAY + "Bolt damage is a special damage type and is"));
        list.add(new StringTextComponent(TextFormatting.GRAY + "unaffected by spell damage modifiers."));

        TooltipUtils.addEmpty(list);
        list.add(new StringTextComponent(TextFormatting.GRAY + "Converts Mana to Lightning DMG."));
        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent("If user has Lightning Essence, projectiles"));
        list.add(new StringTextComponent("have a chance to release a small nova upon"));
        list.add(new StringTextComponent("hitting an enemy, dealing bolt damage. The"));
        list.add(new StringTextComponent("chance is multiplied by the caster's number"));
        list.add(new StringTextComponent("of stacks of Lightning Essence: "));

        list.addAll(getCalc(Load.spells(info.player)).GetTooltipString(info, Load.spells(info.player), this));

        return list;
    }

    @Override
    public void alterSpell(PreCalcSpellConfigs c) {
        c.set(SC.MANA_COST, 2, 5);
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.BASE_VALUE, 0, 0);
        c.set(SC.MANA_ATTACK_SCALE_VALUE, 0.03F, 0.12F);
        c.set(SC.CHANCE, 10, 25);
        c.set(SC.RADIUS, 2F, 3F);
        c.setMaxLevel(8);
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

        int stacks = PotionEffectUtils.getStacks(ctx.source, ThunderEssenceEffect.INSTANCE);
        float chance = getContext(ctx.source).getConfigFor(this)
                .get(SC.CHANCE)
                .get(Load.spells(ctx.source), this) * stacks;

        if (stacks > 0 && RandomUtils.roll(chance) && ctx.getEffectType() == EffectData.EffectTypes.SPELL) {
            float radius = getContext(ctx.source).getConfigFor(this)
                    .get(SC.RADIUS)
                    .get(Load.spells(ctx.source), this);

            int num = getCalcVal(ctx.source);

            List<LivingEntity> entities = EntityFinder.start(ctx.source, LivingEntity.class, ctx.target.getPositionVector())
                    .radius(radius).searchFor(EntityFinder.SearchFor.ENEMIES)
                    .build();

            ParticlePacketData pdata = new ParticlePacketData(ctx.target.getPosition()
                    .up(1), ParticleEnum.CHARGED_NOVA);
            pdata.radius = radius;
            ParticleEnum.CHARGED_NOVA.sendToClients(ctx.source, pdata);

            SpellUtils.summonLightningStrike(ctx.target);

            SoundUtils.playSound(ctx.target, SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, 0.75F, 1);

            for (LivingEntity en : entities) {
                DamageEffect dmg = new DamageEffect(
                        null, ctx.source, en, num, EffectData.EffectTypes.BOLT, WeaponTypes.None);
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
