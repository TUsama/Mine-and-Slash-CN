package com.robertx22.mine_and_slash.database.spells.spell_classes.unholy;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModSounds;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.necromancer.SoulShredEffect;
import com.robertx22.mine_and_slash.potion_effects.ocean_mystic.FrozenEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.block.SoundType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class SoulShredSpell extends BaseSpell {

    private SoulShredSpell() {
        super(new ImmutableSpellConfigs() {
            @Override
            public Masteries school() {
                return Masteries.UNHOLY;
            }

            @Override
            public SpellCastType castType() {
                return SpellCastType.SPECIAL;
            }

            @Override
            public SoundEvent sound() {
                return SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE;
            }

            @Override
            public Elements element() {
                return Elements.Physical;
            }
        }.setSwingArmOnCast());
    }

    public static SoulShredSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(5, 4);
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();

        c.set(SC.HEALTH_COST, 0.07F, 0.21F);
        c.set(SC.MANA_COST, 6, 13);
        c.set(SC.ENERGY_COST, 0, 0);
        c.set(SC.MAGIC_SHIELD_COST, 0, 0);
        c.set(SC.MAGIC_SHIELD_ATTACK_SCALE_VALUE, 0.02F, 0.07F);
        c.set(SC.BASE_VALUE, 1, 4);
        c.set(SC.SHOOT_SPEED, 0.8F, 1.4F);
        c.set(SC.CAST_TIME_TICKS, 40, 20);
        c.set(SC.COOLDOWN_SECONDS, 20, 12);
        c.set(SC.TICK_RATE, 20, 20);
        c.set(SC.DURATION_TICKS, 140, 200);

        c.setMaxLevel(8);

        return c;
    }

    @Override
    public String GUID() {
        return "soul_shred";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Spell"));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Area, Debuff"));

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent("Corrupt your own spirit transmit fragments to"));
        list.add(new StringTextComponent("enemies in front of you, causing them to take"));
        list.add(new StringTextComponent("damage when attacked and damage over time."));
        list.add(new StringTextComponent("Applies: "));

        list.addAll(SoulShredEffect.INSTANCE.GetTooltipStringWithNoExtraSpellInfo(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.SoulShred;
    }

    @Override
    public void castExtra(SpellCastContext ctx) {

        LivingEntity caster = ctx.caster;

        World world = caster.world;

        SoundUtils.playSound(caster, SoundEvents.ENTITY_WITCH_THROW, 0.7F, 0.5F);

        EntityFinder.start(caster, LivingEntity.class, caster.getPositionVector())
            .radius(2)
            .distance(6)
            .finder(EntityFinder.Finder.IN_FRONT)
                .searchFor(EntityFinder.SearchFor.ENEMIES)
            .build()
            .forEach(x -> PotionEffectUtils.apply(SoulShredEffect.INSTANCE, caster, x));

    }

    private static class SingletonHolder {
        private static final SoulShredSpell INSTANCE = new SoulShredSpell();
    }
}
