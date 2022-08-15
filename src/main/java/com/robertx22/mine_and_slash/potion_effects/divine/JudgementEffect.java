package com.robertx22.mine_and_slash.potion_effects.divine;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.divine.SpearOfJudgementSpell;
import com.robertx22.mine_and_slash.database.stats.types.defense.Armor;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalResist;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.bases.*;
import com.robertx22.mine_and_slash.potion_effects.bases.data.ExtraPotionData;
import com.robertx22.mine_and_slash.potion_effects.bases.data.PotionStat;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.entity.LivingEntity;
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

public class JudgementEffect extends BasePotionEffect implements IApplyStatPotion, IOnBasicAttackedPotion {

    public static final JudgementEffect INSTANCE = new JudgementEffect();

    private JudgementEffect() {
        super(EffectType.HARMFUL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));

        this.tickActions.add(new OnTickAction(ctx -> {
            ParticleUtils.spawnParticles(ParticleTypes.FALLING_HONEY, ctx.entity, 25);
            return ctx;
        }, null));

    }

    @Override
    public String GUID() {
        return "judgement";
    }

    @Override
    public String locNameForLangFile() {
        return "Judgment";
    }

    @Override
    public int getMaxStacks() {
        return 5;
    }

    @Override
    public List<PotionStat> getPotionStats() {
        List<PotionStat> list = new ArrayList<>();
        list.add(new PotionStat(-3, new ElementalResist(Elements.Elemental)));
        list.add(new PotionStat(-8, Armor.getInstance()));
        return list;
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs p = new PreCalcSpellConfigs();
        p.set(SC.BASE_VALUE, 0, 0);
        p.set(SC.ATTACK_SCALE_VALUE, 0.25F, 0.5F);
        p.set(SC.DURATION_TICKS, 100, 120);
        p.set(SC.TICK_RATE, 20, 20);
        return p;
    }

    @Nullable
    @Override
    public BaseSpell getSpell() {
        return null;
    }

    @Override
    public Masteries getMastery() {
        return Masteries.DIVINE;
    }

    @Override
    public List<ITextComponent> getEffectTooltip(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Lightning Spell Damage"));

        list.add(new StringTextComponent("Attack to add additional stacks. At max stacks,"));
        list.add(new StringTextComponent("consume the stacks to deal extra damage based"));
        list.add(new StringTextComponent("on the applicator's Weapon DMG: "));

        return list;

    }

    @Override
    public void onBasicAttacked(EffectInstance instance, LivingEntity source, LivingEntity target) {

        ExtraPotionData data = PotionDataSaving.getData(instance);

        LivingEntity caster = data.getCaster(source.world);

        if (data.getStacks() < this.getMaxStacks()) {
            PotionEffectUtils.apply(this, caster, target);
        } else {

            SpellUtils.summonLightningStrike(target);

            SoundUtils.playSound(target, SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, 1, 1);

            int num = getCalc(source).getCalculatedValue(Load.Unit(caster), Load.spells(caster), this);
            DamageEffect dmg = new DamageEffect(null, source, target, num, EffectData.EffectTypes.SPELL, WeaponTypes.None);

            dmg.element = Elements.Thunder;
            dmg.Activate();

            target.removePotionEffect(this);

        }
    }
}
