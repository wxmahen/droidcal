package iit.mahen.calculator.ctrl;

public class LogarithmFunctionElement extends FunctionElement {

    public void addArg(FormulaElement element) {
        if (this.getArgs().size() == 0) {
            super.addArg(element);
        } else {
            System.out.println("Log Functions can only have one arg");
        }
    }

    public String toString() {
        return "log(" + this.getArg(0) + ")";
    }

    public double evaluate() {
        return Math.log10(getArg(0).evaluate());
    }
}
