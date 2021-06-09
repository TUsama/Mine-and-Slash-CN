package com.robertx22.mine_and_slash.potion_effects.ocean_mystic;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ocean.ShatterCastSpell;
import com.robertx22.mine_and_slash.database.stats.types.defense.Armor;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalAttackDamage;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalResist;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalSpellDamage;
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
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ShatterCastEffect extends BasePotionEffect implements IApplyStatPotion, IOnSpellCastPotion {

    public static final ShatterCastEffect INSTANCE = new ShatterCastEffect();

    private ShatterCastEffect() {
        super(EffectType.BENEFICIAL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));
    }

    @Override
    public String GUID() {
        return "shatter_cast";
    }

    @Override
    public String locNameForLangFile() {
        return "Shatter Cast";
    }

    @Override
    public int getMaxStacks() {
        return 1;
    }

    @Override
    public List<PotionStat> getPotionStats() {
        List<PotionStat> list = new ArrayList<>();
        list.add(new PotionStat(10, new ElementalSpellDamage(Elements.Water)));
        return list;
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs p = new PreCalcSpellConfigs();
        p.set(SC.BASE_VALUE, 2, 8);
        p.set(SC.RADIUS, 2F, 2F);
        return p;
    }

    @Nullable
    @Override
    public BaseSpell getSpell() {
        return ShatterCastSpell.getInstance();
    }

    @Override
    public Masteries getMastery() {
        return getSpell().getMastery();
    }

    @Override
    public List<ITextComponent> getEffectTooltip(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent("Send out an ice nova upon casting a spell and"));
        list.add(new StringTextComponent("deal frost damage: "));

        return list;

    }

    @Override
    public void onSpellCast(EffectInstance instance, LivingEntity source, LivingEntity target) {

        int num = getCalc(source).getCalculatedValue(Load.Unit(source), Load.spells(source), this);

        float radius = 2;

        List<LivingEntity> entities = EntityFinder.start(source, LivingEntity.class, source.getPositionVector())
                .radius(radius)
                .build();

        ParticlePacketData pdata = new ParticlePacketData(source.getPosition()
                .up(1), ParticleEnum.FROST_NOVA);
        pdata.radius = radius;
        ParticleEnum.FROST_NOVA.sendToClients(source, pdata);

        for (LivingEntity en : entities) {
            DamageEffect dmg = new DamageEffect(
                    null, source, en, num, EffectData.EffectTypes.SPELL, WeaponTypes.None);
            dmg.element = Elements.Water;
            dmg.Activate();
        }

        target.playSound(SoundEvents.BLOCK_GLASS_BREAK, 0.5F, 0.7F);
    }
}
