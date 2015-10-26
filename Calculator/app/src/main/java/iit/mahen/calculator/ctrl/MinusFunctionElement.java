package iit.mahen.calculator.ctrl;

public class MinusFunctionElement extends FunctionElement {

    public String toString() {
        if (getArg(0) instanceof ConstantElement && getArg(1) instanceof ConstantElement) {
            return ((ConstantElement) getArg(0)).getValue() - ((ConstantElement) getArg(1)).getValue() + "";
        }

        String s1 = "", s2 = "";
        if (getArg(0) instanceof PlusFunctionElement) {
            s1 = "(" + getArg(0).toString() + ")";
        } else {
            s1 = getArg(0).toString();
        }
        if (getArg(1) instanceof PlusFunctionElement) {
            s2 = "(" + getArg(1).toString() + ")";
        } else {
            s2 = getArg(1).toString();
        }
        return s1 + "-" + s2;
    }

    public double evaluate() {
        return getArg(0).evaluate() - getArg(1).evaluate();
    }
}
