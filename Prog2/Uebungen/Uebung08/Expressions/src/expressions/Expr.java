package expressions;

public abstract class Expr {
   public abstract double compute();
}

public class Const extends Expr{
    private double value;
    public Const (double v){
        value = v;
    }
    @Override
    public double compute(){
        return value;
    }
    @Override
    public String toString(){
        return "" + value;
    }
    @Override
    public boolean equals(Object o){
        if (!(o instanceof Expr))
            return false;
        Const that = (Const) o;
        return this.value == that.value;
        
    }
}

public abstract class Bin extends Expr{
    protected Expr left, right;
    abstract String oper();
    @Override
    public abstract String toString();
    
    public boolean equals(Object o){
        if (!(o instanceof Bin))
            return false;
        Bin that = (Bin) o;
        return this.oper().equals(that.oper()) && this.left.equals(that.left)
                && this.right.equals(that.right);
    }
}

public class Add extends Bin{
    public Add(Expr left, Expr right){
        super(left,right);
    }
    @Override
    String oper(){
        return "+";
    }
    double combine(double l, double r){
        return l + r;
    }
    @Override
    public double compute(){
        return combine(left.compute(), right.compute());
    }
    @Override
    public String toString(){
        
        return "(" + left.toString() + oper() + right.toString() + ")";
    }
}

public class Sub extends Bin{
    public Sub(Expr left, Expr right){
        super(left,right);
    }
    @Override
    String oper(){
        return "-";
    }
    double combine(double l, double r){
        return l - r;
    }
    @Override
    public double compute(){
        return combine(.left.compute(), right.compute());
    }
    @Override
    public String toString(){
        
        return "(" + left.toString() + oper() + right.toString() + ")";
    }
}

public class Mul extends Bin{
    public Mul(Expr left, Expr right){
        super(left,right);
    }
    @Override
    String oper(){
        return "*";
    }
    double combine(double l, double r){
        return l * r;
    }
    @Override
    public double compute(){
        return combine(left.compute(), right.compute());
    }
    @Override
    public String toString(){
        return "(" + left.toString() + oper() + right.toString() + ")";
    }
}

public class Div extends Bin{
    public Div(Expr left, Expr right){
        super(left,right);
    }
    @Override
    String oper(){
        return "/";
    }
    double combine(double l, double r){
        return l / r;
    }
    @Override
    public double compute(){
        return combine(left.compute(), right.compute());
    }
    @Override
    public String toString(){
        
        return "(" + left.toString() + oper() + right.toString() + ")";
    }
}