package com.robertx22.spells.self;

import com.robertx22.customitems.spells.self.ItemInstantHeal;
import com.robertx22.database.stat_types.resources.Health;
import com.robertx22.effectdatas.SpellBuffEffect;
import com.robertx22.effectdatas.interfaces.IBuffableSpell.SpellBuffType;
import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.spells.bases.EffectCalculation;
import com.robertx22.spells.bases.SpellBuffCheck;
import com.robertx22.spells.potion_effects.all.AoeRegenPotion;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.utilityclasses.SoundUtils;
import com.robertx22.uncommon.utilityclasses.WizardryUtilities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class SpellInstantHeal extends BaseSpell {

    @Override
    public SpellType Type() {
	return SpellType.Self_Heal;
    }

    @Override
    public String GUID() {
	return "instant_heal";
    }

    @Override
    public String Name() {
	return "Instant Heal";
    }

    @Override
    public int ManaCost() {
	return 20;
    }

    @Override
    public int BaseValue() {
	return 5;
    }

    @Override
    public EffectCalculation ScalingValue() {
	return new EffectCalculation(Health.GUID, 0.3F);

    }

    @Override
    public Elements Element() {
	return Elements.None;
    }

    @Override
    public Item SpellItem() {
	return ItemInstantHeal.ITEM;
    }

    @Override
    public String GetDescription(SpellItemData data) {
	return CLOC.tooltip("spell_instant_heal");
    }

    public void checkZephyrSpeedBoost(EntityPlayer caster, SpellBuffCheck buffable) {

	if (buffable.getBuff().equals(SpellBuffType.Zephyr_Speed_Boost)) {
	    caster.addPotionEffect(new PotionEffect(MobEffects.SPEED, 200));
	}

    }

    public void checkAddLightBuff(EntityPlayer caster, SpellBuffCheck buffable) {
	if (buffable.getBuff().equals(SpellBuffType.Light_Aoe_Regen)) {
	    caster.addPotionEffect(new PotionEffect(AoeRegenPotion.INSTANCE, 200));
	}

    }

    public void checkSpellBuffs(EntityPlayer caster, SpellBuffCheck buffable) {
	checkZephyrSpeedBoost(caster, buffable);
	checkAddLightBuff(caster, buffable);
    }

    public static void spawnHealParticles(EntityLivingBase en, int amount) {
	for (int i = 0; i < amount; i++) {
	    double d0 = (double) ((float) en.posX + en.world.rand.nextFloat() * 2 - 1.0F);
	    // Apparently the client side spawns the particles 1 block higher than it
	    // should... hence the -
	    // 0.5F.
	    double d1 = (double) ((float) WizardryUtilities.getPlayerEyesPos(en) - 0.5F + en.world.rand.nextFloat());
	    double d2 = (double) ((float) en.posZ + en.world.rand.nextFloat() * 2 - 1.0F);

	    en.world.spawnParticle(EnumParticleTypes.HEART, d0, d1, d2, 0, 48 + en.world.rand.nextInt(12), 1.0f);
	}
    }

    public static void spawnParticles(EnumParticleTypes particle, EntityLivingBase en, int amount) {
	for (int i = 0; i < amount; i++) {
	    double d0 = (double) ((float) en.posX + en.world.rand.nextFloat() * 2 - 1.0F);
	    // Apparently the client side spawns the particles 1 block higher than it
	    // should... hence the -
	    // 0.5F.
	    double d1 = (double) ((float) WizardryUtilities.getPlayerEyesPos(en) - 0.5F + en.world.rand.nextFloat());
	    double d2 = (double) ((float) en.posZ + en.world.rand.nextFloat() * 2 - 1.0F);

	    en.world.spawnParticle(particle, d0, d1, d2, 0, 48 + en.world.rand.nextInt(12), 1.0f);
	}
    }

    @Override
    public boolean cast(World world, EntityPlayer caster, EnumHand hand, int ticksInUse, SpellItemData data) {

	try {

	    if (!world.isRemote) {

		UnitData unit = Load.Unit(caster);
		unit.heal(caster, data.GetDamage(unit.getUnit()));
		SoundUtils.playSoundAtPlayer(caster, SoundEvents.ENTITY_GENERIC_DRINK, 1, 1);
		// spell buffs
		SpellBuffCheck check = new SpellBuffCheck(this.Type());
		SpellBuffEffect spelleffect = new SpellBuffEffect(caster, check);
		spelleffect.Activate();
		checkSpellBuffs(caster, check);
		//
	    } else {

		spawnHealParticles(caster, 10);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return true;
    }

}
