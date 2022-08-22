package com.robertx22.mine_and_slash.database.spells.synergies.fire;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.fire.SummonArchonSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.base.OnBasicAttackSynergy;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModSounds;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class SummonArchonAOESynergy extends OnBasicAttackSynergy {

    @Override
    public List<ITextComponent> getSynergyTooltipInternal(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Synergy"));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Modifies All Summons"));

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent("Summon hits have a chance to deal fire spell"));
        list.add(new StringTextComponent("damage in a small AOE: "));

        list.addAll(getCalc(Load.spells(info.player)).GetTooltipString(info, Load.spells(info.player), this));

        return list;
    }

    @Override
    public Place getSynergyPlace() {
        return Place.FIRST;
    }

    @Override
    public void alterSpell(PreCalcSpellConfigs c) {
        c.set(SC.MANA_COST, 1, 3);
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.BASE_VALUE, 4, 9);
        c.set(SC.RADIUS, 1.5F, 2.5F);
        c.set(SC.CHANCE, 5, 25);
        c.setMaxLevel(4);
        return c;
    }

    @Nullable
    @Override
    public IAbility getRequiredAbility() {
        return SummonArchonSpell.getInstance();
    }

    @Override
    public void tryActivate(DamageEffect ctx) {
        if (RandomUtils.roll(getContext(ctx.source).getConfigFor(this)
                .get(SC.CHANCE)
                .get(Load.spells(ctx.source), this)) && ctx.getEffectType() == EffectData.EffectTypes.SUMMON_DMG) {

            float radius = getContext(ctx.source).getConfigFor(this)
                    .get(SC.RADIUS)
                    .get(Load.spells(ctx.source), this);

            int num = getPreCalcConfig().getCalc(Load.spells(ctx.source), this)
                    .getCalculatedValue(ctx.sourceData, Load.spells(ctx.source), this);

            List<LivingEntity> entities = EntityFinder.start(ctx.source, LivingEntity.class, ctx.target.getPositionVector())
                    .radius(radius).searchFor(EntityFinder.SearchFor.ENEMIES)
                    .build();

            ParticlePacketData pdata = new ParticlePacketData(ctx.target.getPosition()
                    .up(1), ParticleEnum.BLAZING_INFERNO);
            pdata.radius = radius;
            ParticleEnum.BLAZING_INFERNO.sendToClients(ctx.source, pdata);

            SoundUtils.playSound(ctx.target, ModSounds.FIREBALL.get(), 0.75F, 1);

            for (LivingEntity en : entities) {
                DamageEffect dmg = new DamageEffect(
                        null, ctx.source, en, num, EffectData.EffectTypes.SPELL, WeaponTypes.None);
                dmg.element = Elements.Fire;
                dmg.Activate();
            }
        }
    }

    @Override
    public String locNameForLangFile() {
        return "Incendiary Touch";
    }
}
