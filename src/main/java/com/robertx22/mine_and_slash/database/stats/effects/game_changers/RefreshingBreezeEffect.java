package com.robertx22.mine_and_slash.database.stats.effects.game_changers;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseDamageEffect;
import com.robertx22.mine_and_slash.database.stats.types.game_changers.RefreshingBreeze;
import com.robertx22.mine_and_slash.database.stats.types.resources.Energy;
import com.robertx22.mine_and_slash.database.stats.types.resources.EnergyRegen;
import com.robertx22.mine_and_slash.database.stats.types.resources.Health;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;

public class RefreshingBreezeEffect extends BaseDamageEffect {

    public static final RefreshingBreezeEffect INSTANCE = new RefreshingBreezeEffect();

    @Override
    public int GetPriority() {
        return Priority.Last.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Target;
    }

    @Override
    public DamageEffect activate(DamageEffect effect, StatData data, Stat stat) {

        float health = effect.targetData.getUnit()
            .peekAtStat(Health.GUID)
            .getAverageValue() * RefreshingBreeze.PERCENT / 100;

        float energy = effect.targetData.getUnit()
                .peekAtStat(Energy.GUID)
                .getAverageValue() * RefreshingBreeze.PERCENT / 100;

        ResourcesData.Use use = ResourcesData.Use.RESTORE;

        if (effect.isDodged) {
            ResourcesData.Context hp = new ResourcesData.Context(
                    effect.targetData, effect.target, ResourcesData.Type.HEALTH, health, use);
            effect.targetData.getResources()
                    .modify(hp);
        } else {
            ResourcesData.Context ene = new ResourcesData.Context(
                    effect.targetData, effect.target, ResourcesData.Type.ENERGY, -energy, use);
            effect.targetData.getResources()
                    .modify(ene);
        }

        return effect;
    }

    @Override
    public boolean canActivate(DamageEffect effect, StatData data, Stat stat) {
        return true;
    }

}
