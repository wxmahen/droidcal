package iit.mahen.calculator.ctrl;

public class CosineFunctionElement extends FunctionElement {

    // here we redefine addArg() in subclass to allow only one argument
    public void addArg(FormulaElement element) {
        if (this.getArgs().size() == 0) {
            super.addArg(element);
        } else {
            System.out.println("Trig Functions can only have one argument");
        }
    }

    public String toString() {
        return "cos(" + this.getArg(0) + ")";
    }

    public double evaluate() {
        return Math.cos(getArg(0).evaluate());
    }
}
