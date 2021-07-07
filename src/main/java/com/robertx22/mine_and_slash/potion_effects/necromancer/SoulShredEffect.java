package com.robertx22.mine_and_slash.potion_effects.necromancer;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.unholy.SoulShredSpell;
import com.robertx22.mine_and_slash.database.stats.types.defense.Armor;
import com.robertx22.mine_and_slash.database.stats.types.generated.AllElementalDamage;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.potion_effects.bases.IApplyStatPotion;
import com.robertx22.mine_and_slash.potion_effects.bases.IOnBasicAttackedPotion;
import com.robertx22.mine_and_slash.potion_effects.bases.OnTickAction;
import com.robertx22.mine_and_slash.potion_effects.bases.data.PotionStat;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class SoulShredEffect extends BasePotionEffect implements IOnBasicAttackedPotion, IApplyStatPotion {

    public static final SoulShredEffect INSTANCE = new SoulShredEffect();

    private SoulShredEffect() {
        super(EffectType.HARMFUL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));

        this.tickActions.add(new OnTickAction(ctx -> {
            int num = getCalc(ctx.caster).getCalculatedValue(ctx.casterData, ctx.spellsCap, this);

            num *= ctx.data.getStacks();

            DamageEffect dmg = new DamageEffect(null, ctx.caster, ctx.entity, num, ctx.casterData, ctx.entityData,
                    EffectData.EffectTypes.DOT_DMG, WeaponTypes.None
            );

            SoundUtils.playSound(ctx.entity, SoundEvents.ENTITY_BLAZE_SHOOT, 0.5F, 1.5F);

            ParticleUtils.spawnParticles(ParticleTypes.CRIT, ctx.entity, 25);

            dmg.element = Elements.Physical;
            dmg.removeKnockback();
            dmg.Activate();

            return ctx;
        }, null));

    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs p = new PreCalcSpellConfigs();
        return p;
    }

    @Override
    public List<PotionStat> getPotionStats() {
        List<PotionStat> list = new ArrayList<>();
        list.add(new PotionStat(-20, Armor.getInstance()));
        return list;
    }

    @Nullable
    @Override
    public BaseSpell getSpell() {
        return SoulShredSpell.getInstance();
    }

    @Override
    public Masteries getMastery() {
        return Masteries.UNHOLY;
    }

    @Override
    public String GUID() {
        return "soul_shred";
    }

    @Override
    public String locNameForLangFile() {
        return "Soul Shred";
    }

    @Override
    public Elements getElement(){
        return Elements.Physical;
    }

    @Override
    public int getMaxStacks() {
        return 1;
    }

    @Override
    public List<ITextComponent> getEffectTooltip(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Physical Spell Damage"));
        list.add(new StringTextComponent("Enemies with Soul Shred will take"));
        list.add(new StringTextComponent("extra physical damage on attack and"));
        list.add(new StringTextComponent("damage over time. Both of these"));
        list.add(new StringTextComponent("effects scale with Magic Shield: "));

        list.addAll(getCalc(info.player).GetTooltipString(info, Load.spells(info.player), this));
        return list;
    }

    @Override
    public void onBasicAttacked(EffectInstance instance, LivingEntity source, LivingEntity target) {

        int num = getCalc(source).getCalculatedValue(Load.Unit(source), Load.spells(source), this);

        DamageEffect dmg = new DamageEffect(null, source, target, num, EffectData.EffectTypes.SPELL, WeaponTypes.None);
        dmg.element = Elements.Physical;
        dmg.Activate();

        ParticleEnum.sendToClients(
                target, new ParticlePacketData(target.getPosition(), ParticleEnum.FREEZE).radius(1)
                        .type(ParticleTypes.EFFECT)
                        .amount(25));

        target.playSound(SoundEvents.BLOCK_LADDER_STEP, 0.5F, 0.8F);

    }
}