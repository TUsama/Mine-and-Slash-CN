package com.robertx22.mine_and_slash.potion_effects.ranger;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.stats.types.resources.HealPower;
import com.robertx22.mine_and_slash.database.stats.types.resources.HealthRegen;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.potion_effects.bases.IApplyStatPotion;
import com.robertx22.mine_and_slash.potion_effects.bases.OnTickAction;
import com.robertx22.mine_and_slash.potion_effects.bases.data.PotionStat;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class WoundsEffect extends BasePotionEffect implements IApplyStatPotion {

    private WoundsEffect() {
        super(EffectType.HARMFUL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));

        this.tickActions.add(new OnTickAction(ctx -> {
            int num = getCalc(ctx.caster).getCalculatedValue(ctx.casterData, ctx.spellsCap, this);

            num *= ctx.data.getStacks();

            ParticleEnum.sendToClients(
                    ctx.entity, new ParticlePacketData(ctx.entity.getPosition(), ParticleEnum.AOE).type(
                            ParticleTypes.DUST)
                            .motion(new Vec3d(0, 0, 0))
                            .amount(15));

            DamageEffect dmg = new DamageEffect(null, ctx.caster, ctx.entity, num, ctx.casterData, ctx.entityData,
                EffectData.EffectTypes.DOT_DMG, WeaponTypes.None
            );
            dmg.element = Elements.Physical;
            dmg.removeKnockback();
            dmg.Activate();

            return ctx;
        }, info -> {
            List<ITextComponent> list = new ArrayList<>();
            list.addAll(getCalc(info.player).GetTooltipString(info, Load.spells(info.player), this));
            return list;
        }));

    }

    public static WoundsEffect getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "wounds";
    }

    @Override
    public String locNameForLangFile() {
        return "Wounds";
    }

    @Override
    public Elements getElement(){
        return Elements.Physical;
    }

    @Override
    public int getMaxStacks() {
        return 5;
    }

    @Override
    public List<PotionStat> getPotionStats() {
        List<PotionStat> list = new ArrayList<>();
        list.add(new PotionStat(-5, HealthRegen.getInstance()));
        return list;
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs p = new PreCalcSpellConfigs();
        p.set(SC.BASE_VALUE, 1, 1);
        p.set(SC.ATTACK_SCALE_VALUE, 0.12F, 0.19F);
        p.set(SC.TICK_RATE, 20, 20);
        p.set(SC.DURATION_TICKS, 8 * 20, 12 * 20);
        return p;
    }

    @Nullable
    @Override
    public BaseSpell getSpell() {
        return null;
    }

    @Override
    public Masteries getMastery() {
        return Masteries.HUNTING;
    }

    private static class SingletonHolder {
        private static final WoundsEffect INSTANCE = new WoundsEffect();
    }

    @Override
    public List<ITextComponent> getEffectTooltip(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Physical DoT Damage"));
        return list;
    }
}

