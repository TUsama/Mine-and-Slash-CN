package com.robertx22.mine_and_slash.potion_effects.druid;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.nature.MortalitySapSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.nature.ThoughtSeizeSpell;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalResist;
import com.robertx22.mine_and_slash.database.stats.types.offense.*;
import com.robertx22.mine_and_slash.database.stats.types.resources.Health;
import com.robertx22.mine_and_slash.database.stats.types.resources.HealthRegen;
import com.robertx22.mine_and_slash.database.stats.types.resources.MagicShieldRegen;
import com.robertx22.mine_and_slash.database.stats.types.resources.ManaRegen;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.potion_effects.bases.IApplyStatPotion;
import com.robertx22.mine_and_slash.potion_effects.bases.IOneOfATypePotion;
import com.robertx22.mine_and_slash.potion_effects.bases.OnTickAction;
import com.robertx22.mine_and_slash.potion_effects.bases.data.PotionStat;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class QuietusEffect extends BasePotionEffect implements IApplyStatPotion, IOneOfATypePotion {
    @Override
    public IOneOfATypePotion.Type getOneOfATypeType() {
        return IOneOfATypePotion.Type.NATURE_CURSE;
    }

    public static final QuietusEffect INSTANCE = new QuietusEffect();

    private QuietusEffect() {
        super(EffectType.HARMFUL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));

        this.tickActions.add(new OnTickAction(ctx -> {
            ParticleEnum.sendToClients(
                    ctx.entity, new ParticlePacketData(ctx.entity.getPosition(), ParticleEnum.AOE).type(
                            ParticleTypes.CRIT)
                            .motion(new Vec3d(0, 0, 0))
                            .amount(15));
            return ctx;
        }, null));
    }

    @Override
    public String GUID() {
        return "quietus";
    }

    @Override
    public String locNameForLangFile() {
        return "Quietus";
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
        list.add(new PotionStat(25, ReduceDamage.getInstance()));
        list.add(new PotionStat(-20, HealthRegen.getInstance()));

        return list;
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs p = new PreCalcSpellConfigs();
        p.set(SC.TICK_RATE, 40, 40);
        return p;
    }

    @Nullable
    @Override
    public BaseSpell getSpell() {
        return MortalitySapSpell.getInstance();
    }

    @Override
    public Masteries getMastery() {
        return getSpell().getMastery();
    }

    @Override
    public List<ITextComponent> getEffectTooltip(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();
        return list;
    }

}

