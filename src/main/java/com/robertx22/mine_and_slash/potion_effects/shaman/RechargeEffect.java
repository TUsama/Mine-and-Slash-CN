package com.robertx22.mine_and_slash.potion_effects.shaman;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.nature.NatureBalmSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.storm.RechargeSpell;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ParticleRegister;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.potion_effects.bases.OnTickAction;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class RechargeEffect extends BasePotionEffect {

    public static final RechargeEffect INSTANCE = new RechargeEffect();

    private RechargeEffect() {
        super(EffectType.BENEFICIAL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));

        this.tickActions.add(new OnTickAction(ctx -> {
            if (ctx.entity.world.isRemote) {
                ParticleUtils.spawnParticles(ParticleRegister.THUNDER, ctx.entity, 5);
            } else {

                ParticleUtils.spawnParticles(ParticleRegister.THUNDER, ctx.entity, 5);

                SpellUtils.healCasterMana(getCtx(ctx.caster));
            }
            return ctx;
        }, info -> {
            List<ITextComponent> list = new ArrayList<>();
            list.add(new StringTextComponent("Recovers user's mana:"));
            list.addAll(getCalc(info.player).GetTooltipString(info, Load.spells(info.player), getAbilityThatDeterminesLevel()));
            return list;
        }));
    }

    @Override
    public String GUID() {
        return "recharge";
    }

    @Override
    public String locNameForLangFile() {
        return "Recharge";
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs p = new PreCalcSpellConfigs();
        return p;
    }

    @Nullable
    @Override
    public BaseSpell getSpell() {
        return RechargeSpell.getInstance();
    }

    @Override
    public Masteries getMastery() {
        if (getSpell() != null) {
            return getSpell().getMastery();
        } else {
            return null;
        }
    }
}
