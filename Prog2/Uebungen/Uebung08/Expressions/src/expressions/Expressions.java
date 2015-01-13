/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expressions;

/**
 *
 * @author huegel
 */
public class Expressions {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    // Ausdruck 2 * 3 + 4 erzeugen, ausgeben und berechnen.
    Expr x = new Add(new Mul(new Const(2), new Const(3)), new Const(4));
    System.out.println(x + " = " + x.compute());
    // Ausdruck x mit anderen Ausdrücken und Objekten vergleichen.
    Expr y = new Add(new Mul(new Const(2), new Const(3)), new Const(4));
    System.out.println(x.equals(y));
    Expr z = new Add(new Mul(new Const(3), new Const(2)), new Const(4));
    System.out.println(x.equals(z));
    System.out.println(x.equals(x.toString()));
    }
    
}
