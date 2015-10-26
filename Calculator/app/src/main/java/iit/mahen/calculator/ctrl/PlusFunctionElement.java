package iit.mahen.calculator.ctrl;

public class PlusFunctionElement extends FunctionElement {

    public String toString() {
        if (getArg(0) instanceof ConstantElement && getArg(1) instanceof ConstantElement) {
            return ((ConstantElement) getArg(0)).getValue() + ((ConstantElement) getArg(1)).getValue() + "";
        }

        return getArg(0).toString() + "+" + getArg(1).toString();
    }

    public double evaluate() {
        return getArg(0).evaluate() + getArg(1).evaluate();
    }
}
