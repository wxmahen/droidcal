package iit.mahen.calculator.ctrl;

public class VariableElement extends FormulaElement {

    private String name;
    private double value;
    private boolean isGrounded;

    public VariableElement(String n) {
        name = n;
        isGrounded = false;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    private void setValue(double value) {
        this.value = value;
        isGrounded = true;
    }

    public String toString() {
        return name;
    }

    public void setVariableValue(String n, double v) {
        if (getName().equalsIgnoreCase(n)) {
            setValue(v);
            System.out.println(name + " set to " + value);
        }
    }

    public boolean isFullyGrounded() {
        return isGrounded;
    }

    public double evaluate() {
        return value;
    }
}
