package iit.mahen.calculator.ctrl;

import java.util.Vector;

public class FunctionElement extends FormulaElement {

    private Vector<FormulaElement> arguments;

    public FunctionElement() {
        arguments = new Vector<FormulaElement>();
    }

    public void addArg(FormulaElement element) {
        arguments.add(element);
    }

    public void setArgs(Vector<FormulaElement> arguments) {
        this.arguments = arguments;
    }

    public Vector<FormulaElement> getArgs() {
        return arguments;
    }

    public FormulaElement getArg(int i) {
        return arguments.get(i);
    }

    public String toString() {
        return this.toString();
    }

    public void setVariableValue(String n, double v) {
        for (int i = 0; i < getArgs().size(); i++) {
            if (!(getArg(i) instanceof ConstantElement)) {
                getArg(i).setVariableValue(n, v);
            }
        }
    }

    public boolean isFullyGrounded() {
        for (int i = 0; i < getArgs().size(); i++) {
            if (!(getArg(i) instanceof ConstantElement)) {
                if (getArg(i).isFullyGrounded() == false)
                {
                    return false;
                }
            }
        }
        return true;
    }

    public double evaluate() {
        return this.evaluate();
    }
}
