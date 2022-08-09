package com.robertx22.mine_and_slash.potion_effects.druid;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.unholy.BlightSpell;
import com.robertx22.mine_and_slash.database.stats.types.offense.SpellDamage;
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
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class VenomLoadEffect extends BasePotionEffect implements IApplyStatPotion {

    public static final VenomLoadEffect INSTANCE = new VenomLoadEffect();

    private VenomLoadEffect() {
        super(EffectType.HARMFUL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));

        this.tickActions.add(new OnTickAction(ctx -> {
            int num = getCalc(ctx.caster).getCalculatedValue(ctx.casterData, ctx.spellsCap, this);

            num *= ctx.data.getStacks();

            ParticleEnum.sendToClients(
                    ctx.entity, new ParticlePacketData(ctx.entity.getPosition(), ParticleEnum.AOE).type(
                            ParticleTypes.SNEEZE).radius()
                            .motion(new Vec3d(0, 0, 0))
                            .amount(40));

            ParticleEnum.sendToClients(
                    ctx.entity, new ParticlePacketData(ctx.entity.getPosition(), ParticleEnum.THORNS).amount(5));

            DamageEffect dmg = new DamageEffect(null, ctx.caster, ctx.entity, num, ctx.casterData, ctx.entityData,
                EffectData.EffectTypes.DOT_DMG, WeaponTypes.None
            );
            dmg.element = Elements.Nature;
            dmg.removeKnockback();
            dmg.Activate();

            SoundUtils.playSound(ctx.entity, SoundEvents.ENTITY_SLIME_DEATH, 1.2F, 0.7F);
            return ctx;
        }, info -> {
            List<ITextComponent> list = new ArrayList<>();
            list.addAll(getCalc(info.player).GetTooltipString(info, Load.spells(info.player), this));

            return list;
        }));
    }

    @Override
    public String GUID() {
        return "venom_load";
    }

    @Override
    public String locNameForLangFile() {
        return "Venom Load";
    }

    @Override
    public Elements getElement(){
        return Elements.Nature;
    }

    @Override
    public int getMaxStacks() {
        return 1;
    }

    @Override
    public List<PotionStat> getPotionStats() {
        List<PotionStat> list = new ArrayList<>();
        list.add(new PotionStat(30, SpellDamage.getInstance()));

        return list;
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs p = new PreCalcSpellConfigs();
        return p;
    }

    @Nullable
    @Override
    public BaseSpell getSpell() {
        return BlightSpell.getInstance();
    }

    @Override
    public Masteries getMastery() {
        return Masteries.UNHOLY;
    }

    @Override
    public List<ITextComponent> getEffectTooltip(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Nature DoT Damage"));
        return list;
    }

}

