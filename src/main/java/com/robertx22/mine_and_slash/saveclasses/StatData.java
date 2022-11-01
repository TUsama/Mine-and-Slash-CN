package com.robertx22.mine_and_slash.saveclasses;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.util.math.MathHelper;

import java.text.DecimalFormat;

@Storable
public class StatData {

    private static StatData empty = new StatData();

    public static StatData empty() {
        return empty;
    }

    public StatData() {

    }

    public StatData(Stat stat) {
        this.id = stat.GUID();
    }

    public Stat GetStat() {
        return SlashRegistry.Stats()
            .get(id);
    }

    @Store// guid
    private String id = "";

    private float Flat = 0;
    private float Flat2 = 0;
    private float Percent = 0;
    private float Multi = 0;

    @Store
    private float val = 0;
    @Store
    private float v2 = 0;

    public String toSerializationString() {
        return id + ":" + val + ":" + v2;
    }

    public static StatData fromSerializationString(String str) {

        StatData obj = new StatData();

        String[] parts = str.split(":");
        obj.id = parts[0];
        obj.val = Float.parseFloat(parts[1]);
        obj.v2 = Float.parseFloat(parts[2]);

        return obj;

    }

    public void addFlat(float val1) {
        this.Flat += val1;
        this.Flat2 += val1;
    }

    public float getFlatAverage() {
        return (Flat + Flat2) / 2;
    }

    public float getPercentAverage() {
        return Percent;
    }

    public float getMultiAverage() {
        return Multi;
    }

    public float getTotalVal() {
        Stat stat = this.GetStat();
        float finalValue = stat.BaseFlat;

        finalValue += (Flat + Flat2) / 2;
;
        finalValue *= 1 + Percent / 100;

        finalValue *= 1 + Multi / 100;

        val = MathHelper.clamp(finalValue, stat.minimumValue, stat.maximumValue);

        return val;
    }

    public void CalcVal(EntityCap.UnitData data) {

        Stat stat = this.GetStat();

        calcFirstValue(data);
        calcSecondValue(data);

    }

    private void calcFirstValue(EntityCap.UnitData data) {
        Stat stat = this.GetStat();

        if (stat.isTrait()) {
            if (Flat > 0) {
                val = 1;

            } else {
                val = 0;

            }
            return;
        } else {
            float finalValue = stat.BaseFlat;

            finalValue = stat.getScaling()
                .scale(stat.BaseFlat, data.getLevel());

            finalValue += Flat;

            finalValue *= 1 + Percent / 100;

            finalValue *= 1 + Multi / 100;

            val = MathHelper.clamp(finalValue, stat.minimumValue, stat.maximumValue);

        }
    }

    private void calcSecondValue(EntityCap.UnitData data) {
        Stat stat = this.GetStat();

        if (stat.isTrait()) {
            if (Flat2 > 0) {
                v2 = 1;

            } else {
                v2 = 0;

            }
            return;
        } else {
            float finalValue = stat.BaseFlat;

            finalValue = stat.getScaling()
                .scale(stat.BaseFlat, data.getLevel());

            finalValue += Flat2;

            finalValue *= 1 + Percent / 100;

            finalValue *= 1 + Multi / 100;

            v2 = MathHelper.clamp(finalValue, stat.minimumValue, stat.maximumValue);

        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setValue(float val) {
        Stat stat = GetStat();

        this.val = MathHelper.clamp(val, stat.minimumValue, stat.maximumValue);

    }

    public void addMulti(float multi) {
        this.Multi += multi;
    }

    public void addPercent(float percent) {
        this.Percent += percent;
    }

    public float getFirstValue() {
        return val;
    }

    public float getSecondValue() {
        return v2;
    }

    public float getAverageValue() {
        float val = (getFirstValue() + getSecondValue()) / 2;

        Stat stat = GetStat();
        return MathHelper.clamp(val, stat.minimumValue, stat.maximumValue);
    }

    public boolean isNotZero() {
        return val != 0 && v2 != 0;
    }

    public float getRandomRangeValue() {
        return RandomUtils.RandomRange(getFirstValue(), getSecondValue());
    }

    public boolean isMoreThanZero() {
        return val > 0 || v2 > 0;
    }

    public void add(StatModData modData, int level) {
        StatModTypes type = modData.getType();

        Float v1 = modData.getFirstValue(level);
        Float v2 = modData.getSecondValue(level);

        Float v = (v1 + v2) / 2;

        if (type == StatModTypes.Flat) {
            Flat += v1;
            Flat2 += v2;
        } else if (type == StatModTypes.Percent) {
            Percent += v;
        } else if (type == StatModTypes.Multi) {
            Multi += v;
        }

    }

    public void transferAllPreCalcTo(StatData other) {
        addFullyTo(other);

        this.Clear();
        this.val = 0;
        this.v2 = 0;
    }

    public void addFullyTo(StatData other) {
        other.Flat += Flat;
        other.Flat2 += Flat2;
        other.Percent += Percent;
        other.Multi += Multi;
    }

    public void addExact(StatModTypes type, float value) {
        if (type == StatModTypes.Flat) {
            this.addFlat(value);
        } else if (type == StatModTypes.Percent) {
            this.Percent += value;
        } else {
            this.Multi += value;
        }
    }

    public void addExact(StatData data) {
        if (data.id.equals(this.id)) {
            this.Flat += data.Flat;
            this.Flat2 += data.Flat2;
            this.Percent += data.Percent;
            this.Multi += data.Multi;
        }
    }

    public void addFlat(float val, int lvl) {
        this.Flat += this.GetStat()
            .calculateScalingStatGrowth(val, lvl);
        this.Flat2 += this.GetStat()
            .calculateScalingStatGrowth(val, lvl);

    }

    public void Clear() {
        Flat = 0;
        Flat2 = 0;
        Percent = 0;
        Multi = 0;
    }

    public String formattedValue() {

        float val = this.getAverageValue();

        DecimalFormat format = new DecimalFormat();

        if (Math.abs(val) < 10) {
            format.setMaximumFractionDigits(1);

            return format.format(val);

        } else {
            int intval = (int) val;
            return intval + "";
        }

    }

    public String formattedEleResValue() {

        float val = Math.min(this.getAverageValue(), 75);

        DecimalFormat format = new DecimalFormat();

        if (Math.abs(val) < 10) {
            format.setMaximumFractionDigits(1);

            return format.format(val);

        } else {
            int intval = (int) val;
            return intval + "";
        }

    }

    public float getMultiplier() {
        return 1 + getAverageValue() / 100;
    }

    public float getReverseMultiplier() {
        return 1 - getAverageValue() / 100;
    }

    public boolean isNotEmpty() {
        return Flat != 0 || val != 0 || Percent != 0 || Multi != 0;
    }

    public void multiplyFlat(float multi) {
        this.Flat *= multi;
        this.Flat2 *= multi;
    }

    public void multiplyFlat(double multi) {
        this.Flat *= multi;
        this.Flat2 *= multi;
    }

}
