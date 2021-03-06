package lecho.lib.hellocharts.model;

import java.util.Arrays;

import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.Chart;

/**
 * Single sub-column valueTop for ColumnChart.
 */
public class SubcolumnValue {

    private float valueTop;
    private float valueBottom;
    private float originValue;
    private float diff;
    private int color = ChartUtils.DEFAULT_COLOR;
    private int darkenColor = ChartUtils.DEFAULT_DARKEN_COLOR;
    private char[] label;

    public SubcolumnValue() {
        setValueTop(0);
    }

    public SubcolumnValue(float valueTop) {
        // point and targetPoint have to be different objects
        setValueTop(valueTop);
    }

    public SubcolumnValue(float valueTop, int color) {
        // point and targetPoint have to be different objects
        setValueTop(valueTop);
        setColor(color);
    }

    public SubcolumnValue(float valueTop, float valueBottom,int color) {
        // point and targetPoint have to be different objects
        setValueTop(valueTop);
        setValueBottom(valueBottom);
        setColor(color);
    }

    public SubcolumnValue(SubcolumnValue columnValue) {
        setValueTop(columnValue.valueTop);
        setColor(columnValue.color);
        this.label = columnValue.label;
    }

    public void update(float scale) {
        valueTop = originValue + diff * scale;
    }

    public void finish() {
        setValueTop(originValue + diff);
    }

    public float getValueTop() {
        return valueTop;
    }

    public SubcolumnValue setValueTop(float valueTop) {
        this.valueTop = valueTop;
        this.originValue = valueTop;
        this.diff = 0;
        return this;
    }

    public float getValueBottom() {
        return valueBottom;
    }

    public void setValueBottom(float valueBottom) {
        this.valueBottom = valueBottom;
    }

    /**
     * Set target valueTop that should be reached when data animation finish then call {@link Chart#startDataAnimation()}
     *
     * @param target
     * @return
     */
    public SubcolumnValue setTarget(float target) {
        setValueTop(valueTop);
        this.diff = target - originValue;
        return this;
    }

    public int getColor() {
        return color;
    }

    public SubcolumnValue setColor(int color) {
        this.color = color;
        this.darkenColor = ChartUtils.darkenColor(color);
        return this;
    }

    public int getDarkenColor() {
        return darkenColor;
    }

    @Deprecated
    public char[] getLabel() {
        return label;
    }

    public SubcolumnValue setLabel(String label) {
        this.label = label.toCharArray();
        return this;
    }

    public char[] getLabelAsChars() {
        return label;
    }

    @Deprecated
    public SubcolumnValue setLabel(char[] label) {
        this.label = label;
        return this;
    }

    @Override
    public String toString() {
        return "ColumnValue [valueTop=" + valueTop + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubcolumnValue that = (SubcolumnValue) o;

        if (color != that.color) return false;
        if (darkenColor != that.darkenColor) return false;
        if (Float.compare(that.diff, diff) != 0) return false;
        if (Float.compare(that.originValue, originValue) != 0) return false;
        if (Float.compare(that.valueTop, valueTop) != 0) return false;
        if (!Arrays.equals(label, that.label)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (valueTop != +0.0f ? Float.floatToIntBits(valueTop) : 0);
        result = 31 * result + (originValue != +0.0f ? Float.floatToIntBits(originValue) : 0);
        result = 31 * result + (diff != +0.0f ? Float.floatToIntBits(diff) : 0);
        result = 31 * result + color;
        result = 31 * result + darkenColor;
        result = 31 * result + (label != null ? Arrays.hashCode(label) : 0);
        return result;
    }
}
