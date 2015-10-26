package iit.mahen.calculator.ctrl;

public class ConstantElement extends FormulaElement {

    private double value;

    public ConstantElement(double d) {
        value = d;
    }

    public double getValue() {
        return value;
    }

    public String toString() {
        return Double.toString(value);
    }

    public double evaluate() {
        return value;
    }
}
