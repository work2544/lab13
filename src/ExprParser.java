import java.util.Map;

interface Node {
    void prettyPrint(StringBuilder s);
}

interface Expr extends Node {
    int eval(Map<String, Integer> bindings) throws EvalError, SyntaxError;
}
class IntLit implements Expr {
    private int val;
    public IntLit(int val) {
      this.val = val;
    }
    public int eval(
        Map<String, Integer> bindings) {
      return val;
    }
    public void prettyPrint(StringBuilder s) {
      s.append(val);
    }
  }
  
class BinaryArithExpr implements Expr {
    private Expr left, right;
    private String op;
    public BinaryArithExpr(
        Expr left, String op, Expr right) {
      this.left = left;
      this.op = op;
      this.right = right;
    }
    @Override
    public int eval(Map<String, Integer> bindings) throws EvalError,SyntaxError {
      int lv = left.eval(bindings);
      int rv = right.eval(bindings);
      if (op.equals("+")) return lv + rv;
      else if (op.equals("-")) return lv - rv;
      else if (op.equals("*")) return lv * rv;
      else if (op.equals("/")) 
       { if(rv==0) throw new SyntaxError("/ by zero ");
        else return lv / rv;}
      else if (op.equals("%")) 
        {if(rv==0) throw new SyntaxError("% by zero ");
        else return lv % rv;}
      else throw new EvalError("unknown op: " + op);
      
    }
    public void prettyPrint(StringBuilder s) {
      s.append("(");
      left.prettyPrint(s);
      s.append(op);
      right.prettyPrint(s);
      s.append(")");
    }
}
public class ExprParser {
    private static ExprTokenizer tkz;

    public ExprParser(String src) throws SyntaxError {
        this.tkz = new ExprTokenizer(src);
    }

    public static Expr parseE() throws SyntaxError {
        Expr e = parseT();
        while (tkz.peek("+")|| tkz.peek("-")) {
            String op=tkz.consume();
            if (op.equals("+"))
                e =new BinaryArithExpr(e, "+", parseT());
            else if (op.equals("-"))
                e = new BinaryArithExpr(e, "-", parseT());
        }
        return e;
    }

    private static Expr parseT() throws SyntaxError {

        Expr v = parseF();
        while (tkz.peek("*")|| tkz.peek("/")|| tkz.peek("%")) {
            String op=tkz.consume();
            if (op.equals("*"))
                v =new BinaryArithExpr(v, "*", parseF());
            else if (op.equals("/")) {
                v =new BinaryArithExpr(v, "/", parseF());
                
            } else if (op.equals("%")) {
                
                v =new BinaryArithExpr(v, "%", parseF());
            }
        }
        return v;
    }

    private static Expr parseF() throws SyntaxError {
        String xL = tkz.peek();
        if (isNumber(xL)) {
            String x = tkz.consume();
            return new IntLit(Integer.parseInt(x));
        } else {
            tkz.consume("(");
            Expr v = parseE();
            tkz.consume(")");
            return v;
        }
    }

    private static boolean isNumber(String peek) {
        try {
            Double.parseDouble(peek);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
