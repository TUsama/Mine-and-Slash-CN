package com.robertx22.mine_and_slash.potion_effects.divine;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.divine.ShockwaveSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ocean.ShatterCastSpell;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalSpellDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.potion_effects.bases.*;
import com.robertx22.mine_and_slash.potion_effects.bases.data.ExtraPotionData;
import com.robertx22.mine_and_slash.potion_effects.bases.data.PotionStat;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ShockwaveEffect extends BasePotionEffect implements IApplyStatPotion, IOnBasicAttackPotion {

    public static final ShockwaveEffect INSTANCE = new ShockwaveEffect();

    private ShockwaveEffect() {
        super(EffectType.BENEFICIAL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));
    }

    @Override
    public String GUID() {
        return "shockwave";
    }

    @Override
    public String locNameForLangFile() {
        return "Divinity";
    }

    @Override
    public int getMaxStacks() {
        return 1;
    }

    @Override
    public List<PotionStat> getPotionStats() {
        List<PotionStat> list = new ArrayList<>();
        list.add(new PotionStat(3, PhysicalDamage.getInstance()));
        return list;
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs p = new PreCalcSpellConfigs();
        p.set(SC.CHANCE, 0.5F, 0.5F);
        return p;
    }

    @Nullable
    @Override
    public BaseSpell getSpell() {
        return ShockwaveSpell.getInstance();
    }

    @Override
    public Masteries getMastery() {
        return getSpell().getMastery();
    }

    @Override
    public List<ITextComponent> getEffectTooltip(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent("Send out a divine wave on every basic attack and"));
        list.add(new StringTextComponent("deal physical damage to enemies in front of you."));
        list.add(new StringTextComponent("There is a 50 percent chance that the wave will"));
        list.add(new StringTextComponent("convert into another element with a random effect: "));

        return list;

    }

    @Override
    public void OnBasicAttack(EffectInstance instance, LivingEntity source, LivingEntity target) {

        ExtraPotionData data = PotionDataSaving.getData(instance);

        LivingEntity caster = data.getCaster(source.world);
        List<LivingEntity> entities = null;

        int num = getCalc(source).getCalculatedValue(Load.Unit(source), Load.spells(source), getAbilityThatDeterminesLevel());

        float radius = 4;

        int element = RandomUtils.RandomRange(1,4);
        System.out.println("element: " + element);
        int rand = RandomUtils.RandomRange(1,2);

        if (element == 2) {
            radius *= 0.75;
        } else if (element == 4) {
            radius *= 2;
        }

        if (element == 1) {
            entities = EntityFinder.start(source, LivingEntity.class, source.getPositionVector())
                    .radius(radius)
                    .build();
        } else {
            entities = EntityFinder.start(source, LivingEntity.class, source.getPositionVector())
                    .finder(EntityFinder.Finder.IN_FRONT)
                    .radius(radius / 2F)
                    .distance(radius)
                    .build();
        }

        for (LivingEntity en : entities) {
            DamageEffect dmg = new DamageEffect(
                    null, source, en, num, EffectData.EffectTypes.SPELL, WeaponTypes.None);
            dmg.element = Elements.Physical;

            if (rand == 1) {

                if (element == 1) {

                    dmg.element = Elements.Water;
                    ParticlePacketData pdata = new ParticlePacketData(caster.getPosition()
                            .up(1), ParticleEnum.FROST_NOVA);
                    pdata.radius = radius;
                    ParticleEnum.FROST_NOVA.sendToClients(caster, pdata);
                    en.playSound(SoundEvents.BLOCK_GLASS_BREAK, 0.7F, 1.3F);
                    System.out.println("Water");

                } else if (element == 2) {

                    dmg.number *= 3;
                    dmg.element = Elements.Fire;
                    ParticleEnum.sendToClients(
                            en.getPosition(), en.world,
                            new ParticlePacketData(en.getPositionVector(), ParticleEnum.AOE).radius(1)
                                    .motion(new Vec3d(0, 0, 0))
                                    .type(ParticleTypes.FLAME)
                                    .amount((int) (25)));
                    en.playSound(SoundEvents.BLOCK_FIRE_EXTINGUISH, 0.7F, 0.8F);
                    System.out.println("Fire");

                } else if (element == 3) {

                    dmg.element = Elements.Thunder;
                    SpellUtils.summonLightningStrike(en);
                    SoundUtils.playSound(en, SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, 0.7F, 1.1F);
                    dmg.Activate();
                    System.out.println("Thunder");

                } else {

                    dmg.element = Elements.Nature;
                    ParticleEnum.sendToClients(
                            en.getPosition(), en.world,
                            new ParticlePacketData(en.getPositionVector(), ParticleEnum.AOE).radius(1)
                                    .motion(new Vec3d(0, 0, 0))
                                    .type(ParticleTypes.SNEEZE)
                                    .amount((int) (25)));
                    en.playSound(SoundEvents.ENTITY_HORSE_BREATHE, 0.7F, 1.3F);
                    System.out.println("Nature");

                }
            } else {

                ParticleEnum.sendToClients(
                        en.getPosition(), en.world,
                        new ParticlePacketData(en.getPositionVector(), ParticleEnum.AOE).radius(1)
                                .motion(new Vec3d(0, 0, 0))
                                .type(ParticleTypes.POOF)
                                .amount((int) (25)));
                en.playSound(SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, 0.5F, 1.3F);

            }

            dmg.Activate();

        }
    }
}
