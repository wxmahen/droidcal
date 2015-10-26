package iit.mahen.calculator.ctrl;

public class TangentFunctionElement extends FunctionElement {

    public void addArg(FormulaElement element) {
        if (this.getArgs().size() == 0) {
            super.addArg(element);
        }
    }

    public String toString() {
        return "tan(" + this.getArg(0) + ")";
    }

    public double evaluate() {
        return Math.tan(getArg(0).evaluate());
    }
}
