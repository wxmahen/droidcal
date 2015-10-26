package iit.mahen.calculator.ctrl;

public class MultipleFunctionElement extends FunctionElement {

    public String toString() {
        FormulaElement e1 = getArg(0), e2 = getArg(1);
        if (e1 instanceof ConstantElement && e2 instanceof ConstantElement) {
            return ((ConstantElement) e1).getValue() * ((ConstantElement) e2).getValue() + "";
        }

        String s1 = "", s2 = "";
        if (e1 instanceof PlusFunctionElement || e1 instanceof MinusFunctionElement) {
            s1 = "(" + e1.toString() + ")";
        } else {
            s1 = e1.toString();
        }
        if (e2 instanceof PlusFunctionElement || e2 instanceof MinusFunctionElement) {
            s2 = "(" + e2.toString() + ")";
        } else {
            s2 = e2.toString();
        }
        return s1 + "" + s2;
    }

    public double evaluate() {
        return getArg(0).evaluate() * getArg(1).evaluate();
    }
}
