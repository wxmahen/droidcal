package iit.mahen.calculator.ctrl;

import java.util.StringTokenizer;
import java.util.Vector;

public class FormulaElement {

    public static FormulaElement parseFormula(String formula, String vars) {
        if (formula.startsWith("-")) {
            formula = "0" + formula;
        }
        String token = "";
        StringTokenizer tokenizer = new StringTokenizer(formula, "+-*/^()[] \t", true);
        Vector<Object> vec = new Vector<Object>();
        while (tokenizer.hasMoreTokens()) {
            token = tokenizer.nextToken();
            if (!token.equals(" ")) {
                if (token.length() > 1) {
                    if (Character.isDigit(token.charAt(0)) && Character.isDigit(token.charAt(token.length() - 1))) {
                        vec.add(token);
                    } else if (Character.isDigit(token.charAt(0)) && Character.isLetter(token.charAt(token.length() - 1))) {
                        int splitIdx = 0;
                        for (int i = 0; i < token.length(); i++) {
                            if (Character.isLetter(token.charAt(i))) {
                                splitIdx = i;
                                break;
                            }
                        }
                        vec.add(token.substring(0, splitIdx));
                        vec.add(token.substring(splitIdx, token.length()));
                    } else if (token.equals("cos") || token.equals("sin") || token.equals("tan") || token.equals("log")) {
                        vec.add(token);
                    }
                } else {
                    vec.add(token);
                }
            }
        }
        for (int i = 0; i < vec.size(); i++) {
            if (vec.get(i) instanceof String) {
                try {
                    double d = Double.parseDouble(vec.get(i).toString());
                    vec.set(i, new ConstantElement(d));
                } catch (NumberFormatException nfe) {
                }
                try {
                    String s = vec.get(i).toString();
                    if (Character.isLetter(s.charAt(0)) && !s.equals("cos") && !s.equals("sin") && !s.equals("tan") && !s.equals("log")) {
                        vec.set(i, new VariableElement(s));
                    }
                } catch (Exception e) {
                }
            }
        }
        Object cur = null, prev = null;
        for (int i = 0; i < vec.size(); i++) {
            cur = vec.get(i);
            if (cur instanceof VariableElement && (prev instanceof VariableElement || prev instanceof ConstantElement)) {
                vec.set(i, new MultipleFunctionElement());
                ((MultipleFunctionElement) vec.get(i)).addArg((FormulaElement) prev);
                ((MultipleFunctionElement) vec.get(i)).addArg((FormulaElement) cur);
                vec.remove(i - 1);
                i = i - 1;
            }
            prev = vec.get(i);
        }
        cur = null;
        prev = null;
        for (int i = 0; i < vec.size(); i++) {
            cur = vec.get(i);
            if (cur.toString().equals("(") || cur.toString().equals("[")) {
                String s = "";
                int j = i + 1;
                int openBracketCounter = 0;
                while (!vec.get(j).toString().equals(")") && !vec.get(j).toString().equals("]") || openBracketCounter != 0) {
                    if (vec.get(j).toString().equals("(") || vec.get(j).toString().equals("[")) {
                        openBracketCounter++;
                    }
                    if (vec.get(j).toString().equals(")") || vec.get(j).toString().equals("]")) {
                        openBracketCounter--;
                    }
                    s = s + vec.get(j).toString();
                    vec.remove(j);
                }
                vec.remove(j);
                if (prev != null) {
                    if (prev.toString().equals("cos")) {
                        vec.set(i, new CosineFunctionElement());
                        ((CosineFunctionElement) vec.get(i)).addArg((FormulaElement) parseFormula(s, vars));
                        vec.remove(i - 1);
                        i = i - 1;
                    } else if (prev.toString().equals("sin")) {
                        vec.set(i, new SineFunctionElement());
                        ((SineFunctionElement) vec.get(i)).addArg((FormulaElement) parseFormula(s, vars));
                        vec.remove(i - 1);
                        i = i - 1;
                    } else if (prev.toString().equals("tan")) {
                        vec.set(i, new TangentFunctionElement());
                        ((TangentFunctionElement) vec.get(i)).addArg((FormulaElement) parseFormula(s, vars));
                        vec.remove(i - 1);
                        i = i - 1;
                    } else if (prev.toString().equals("log")) {
                        vec.set(i, new LogarithmFunctionElement());
                        ((LogarithmFunctionElement) vec.get(i)).addArg((FormulaElement) parseFormula(s, vars));
                        vec.remove(i - 1);
                        i = i - 1;
                    } else {
                        vec.set(i, parseFormula(s, vars));
                    }
                } else {
                    vec.set(i, parseFormula(s, vars));
                }
            }
            prev = vec.get(i);
        }
        cur = null;
        prev = null;
        for (int i = 0; i < vec.size(); i++) {
            cur = vec.get(i);
            if (cur.toString().equals("^")) {
                vec.set(i, new PowerFunctionElement());
                ((PowerFunctionElement) vec.get(i)).addArg((FormulaElement) prev);
                ((PowerFunctionElement) vec.get(i)).addArg((FormulaElement) vec.get(i + 1));
                vec.remove(i + 1);
                vec.remove(i - 1);
                i = i - 1;
            }
            prev = vec.get(i);
        }
        cur = null;
        prev = null;
        for (int i = 0; i < vec.size(); i++) {
            cur = vec.get(i);
            if (cur instanceof FormulaElement && prev instanceof FormulaElement) {
                vec.set(i, new MultipleFunctionElement());
                ((MultipleFunctionElement) vec.get(i)).addArg((FormulaElement) prev);
                ((MultipleFunctionElement) vec.get(i)).addArg((FormulaElement) cur);
                vec.remove(i - 1);
                i = i - 1;
            }
            if (cur.toString().equals("/")) {
                vec.set(i, new DivideFunctionElement());
                ((DivideFunctionElement) vec.get(i)).addArg((FormulaElement) prev);
                ((DivideFunctionElement) vec.get(i)).addArg((FormulaElement) vec.get(i + 1));
                vec.remove(i + 1);
                vec.remove(i - 1);
                i = i - 1;
            }
            if (cur.toString().equals("*")) {
                vec.set(i, new MultipleFunctionElement());
                ((MultipleFunctionElement) vec.get(i)).addArg((FormulaElement) prev);
                ((MultipleFunctionElement) vec.get(i)).addArg((FormulaElement) vec.get(i + 1));
                vec.remove(i + 1);
                vec.remove(i - 1);
                i = i - 1;
            }
            prev = vec.get(i);
        }
        cur = null;
        prev = null;
        for (int i = 0; i < vec.size(); i++) {
            cur = vec.get(i);
            if (cur.toString().equals("+")) {
                vec.set(i, new PlusFunctionElement());
                ((PlusFunctionElement) vec.get(i)).addArg((FormulaElement) prev);
                ((PlusFunctionElement) vec.get(i)).addArg((FormulaElement) vec.get(i + 1));
                vec.remove(i + 1);
                vec.remove(i - 1);
                i = i - 1;
            }
            if (cur.toString().equals("-")) {
                vec.set(i, new MinusFunctionElement());
                ((MinusFunctionElement) vec.get(i)).addArg((FormulaElement) prev);
                ((MinusFunctionElement) vec.get(i)).addArg((FormulaElement) vec.get(i + 1));
                vec.remove(i + 1);
                vec.remove(i - 1);
                i = i - 1;
            }
            prev = vec.get(i);
        }
        return (FormulaElement) vec.get(0);
    }

    public String toString() {
        return this.toString();
    }

    public void setVariableValue(String n, double v) {
        this.setVariableValue(n, v);
    }

    public boolean isFullyGrounded() {
        return this.isFullyGrounded();
    }

    public double evaluate() {
        return this.evaluate();
    }
}

