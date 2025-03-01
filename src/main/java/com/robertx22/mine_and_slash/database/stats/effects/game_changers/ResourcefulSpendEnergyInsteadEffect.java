package com.robertx22.mine_and_slash.database.stats.effects.game_changers;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseStatEffect;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.ModifyResourceEffect;

public class ResourcefulSpendEnergyInsteadEffect extends BaseStatEffect<ModifyResourceEffect> {

    private ResourcefulSpendEnergyInsteadEffect() {
        super(ModifyResourceEffect.class);
    }

    public static ResourcefulSpendEnergyInsteadEffect getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public int GetPriority() {
        return Priority.Last.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Source;
    }

    @Override
    public ModifyResourceEffect activate(ModifyResourceEffect effect, StatData data, Stat stat) {

        effect.ctx.type = ResourcesData.Type.ENERGY;

        return effect;
    }

    @Override
    public boolean canActivate(ModifyResourceEffect effect, StatData data, Stat stat) {

        if (effect.ctx.use == ResourcesData.Use.SPEND) {
            if (effect.ctx.amount > 0) {
                if (effect.ctx.type == ResourcesData.Type.MANA) {
                    return true;
                }
            }
        }

        return false;
    }

    private static class SingletonHolder {
        private static final ResourcefulSpendEnergyInsteadEffect INSTANCE = new ResourcefulSpendEnergyInsteadEffect();
    }
}

