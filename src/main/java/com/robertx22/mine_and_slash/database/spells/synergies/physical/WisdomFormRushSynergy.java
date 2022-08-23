package com.robertx22.mine_and_slash.database.spells.synergies.physical;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.physical.buffs.PowerFormSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.physical.buffs.WisdomFormSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.base.OnSpellCastSynergy;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Wisdom;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.physical.EmpowerEffect;
import com.robertx22.mine_and_slash.potion_effects.physical.EnlightenEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
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

public class WisdomFormRushSynergy extends OnSpellCastSynergy {

    @Override
    public List<ITextComponent> getSynergyTooltipInternal(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Synergy"));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Modifies Wisdom Form"));

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent( EnlightenEffect.INSTANCE.locNameForLangFile() + " is also applied to nearby allies."));

        return list;
    }

    @Override
    public Place getSynergyPlace() {
        return Place.FIRST;
    }

    @Override
    public BaseSpell getRequiredAbility() {
        return WisdomFormSpell.getInstance();
    }

    @Override
    public void alterSpell(PreCalcSpellConfigs c) {
        c.set(SC.MANA_COST, 2, 3);
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.BASE_VALUE, 0, 0);
        c.set(SC.RADIUS, 4F, 6F);
        c.setMaxLevel(4);
        return c;
    }

    @Override
    public void tryActivate(SpellCastContext ctx) {
        float radius = this.getPreCalcConfig()
            .get(SC.RADIUS)
            .get(ctx.spellsCap, this);

        BlockPos pos = ctx.caster.getPosition();

        ParticleEnum.sendToClients(pos, ctx.caster.world,
            new ParticlePacketData(pos, ParticleEnum.NOVA).radius(radius)
                .motion(new Vec3d(0, 0, 0))
                .type(ParticleTypes.INSTANT_EFFECT)
                .amount(60)
        );

        EntityFinder.start(ctx.caster, LivingEntity.class, ctx.caster.getPositionVector())
            .radius(radius)
            .searchFor(EntityFinder.SearchFor.ALLIES)
            .build()
            .forEach(x -> PotionEffectUtils.apply(EnlightenEffect.INSTANCE, ctx.caster, x));
    }

    @Override
    public String locNameForLangFile() {
        return "Wisdom Rush";
    }
}
