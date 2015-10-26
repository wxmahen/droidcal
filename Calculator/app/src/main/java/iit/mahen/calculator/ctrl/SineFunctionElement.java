package iit.mahen.calculator.ctrl;


public class SineFunctionElement extends FunctionElement {

    public void addArg(FormulaElement element) {
        if (this.getArgs().size() == 0) {
            super.addArg(element);
        } else {
            System.out.println("Trig Functions can only have one arg");
        }
    }

    public String toString() {
        return "sin(" + this.getArg(0) + ")";
    }

    public double evaluate() {
        return Math.sin(getArg(0).evaluate());
    }
}
