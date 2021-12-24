package com.robertx22.mine_and_slash.database.spells.synergies.divine;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.divine.PurifyingFiresSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.divine.SpearOfJudgementSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.base.OnAttackSpellDmgDoneSynergy;
import com.robertx22.mine_and_slash.database.spells.synergies.base.OnDamageDoneSynergy;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.druid.RegenerateEffect;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.AttackSpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class SpearEnlightenmentSynergy extends OnDamageDoneSynergy {

    @Override
    public List<ITextComponent> getSynergyTooltipInternal(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Synergy"));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Modifies Trident of Judgment"));

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent("Hits restore some mana to you and nearby allies: "));

        list.addAll(getCalc(Load.spells(info.player)).GetTooltipString(info, Load.spells(info.player), this));

        return list;
    }

    @Override
    public void alterSpell(PreCalcSpellConfigs c) {
        c.set(SC.MANA_COST, 1, 3);
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.BASE_VALUE, 2, 9);
        c.set(SC.RADIUS, 4, 4);
        c.setMaxLevel(8);
        return c;
    }

    @Override
    public void tryActivate(SpellDamageEffect ctx) {

        float radius = getContext(ctx.source).getConfigFor(this)
                .get(SC.RADIUS)
                .get(Load.spells(ctx.source), this);

        BlockPos pos = ctx.source.getPosition();

        ParticleEnum.sendToClients(pos, ctx.source.world,
                new ParticlePacketData(pos, ParticleEnum.NOVA).radius(radius)
                        .motion(new Vec3d(0, 0, 0))
                        .type(ParticleTypes.BUBBLE)
                        .amount(50)
        );

        int num = this.getContext(ctx.source)
                .getConfigFor(this)
                .getCalc(Load.spells(ctx.source), this)
                .getCalculatedValue(ctx.sourceData, Load.spells(ctx.source), this);

        ResourcesData.Context mana = new ResourcesData.Context(ctx.sourceData, ctx.source, ResourcesData.Type.MANA,
                num,
                ResourcesData.Use.RESTORE);

        EntityFinder.start(ctx.source, LivingEntity.class, ctx.source.getPositionVector())
                .radius(radius)
                .searchFor(EntityFinder.SearchFor.ALLIES)
                .build()
                .forEach(x -> ctx.sourceData.getResources()
                        .modify(mana));

    }

    @Override
    public Place getSynergyPlace() {
        return Place.FIRST;
    }

    @Override
    public IAbility getRequiredAbility() {
        return SpearOfJudgementSpell.getInstance();
    }

    @Override
    public String locNameForLangFile() {
        return "Enlightenment";
    }

}

