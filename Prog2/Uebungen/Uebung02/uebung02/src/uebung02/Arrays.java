/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uebung02;

/**
 *
 * @author huegel
 */
public class Arrays {

    public static void print(double [] in){
        for (int i = 0; i < in.length ; i++){
            if (i < in.length - 1){
                System.out.print(in[i] + " ");
            }
            else{
                System.out.println(in[i]);
            }
        }
    }
    public static void mirror(double [] in){
        double tmp;
        for (int i = 0, j = in.length -1; i <= j; i++, j--){
            tmp = in[i];
            in[i] = in[j];
            in[j] = tmp;
        }
    }
    public static double [] rotate(double [] in, int k){
        assert k > 0;
        double [] out = new double [in.length];
        
            for (int i = 0; i < in.length; i++){
                out[(i + k) % in.length] = in[i];
            }
            return out;
    }
    public static double [] zip(double [] a, double [] b){
        double [] out = new double [a.length + b.length];
        boolean aorb = false;
        for (int i = 0, j = 0; i < out.length; i++){
            if (i < a.length){
                out[j] = a[i];
                j++;
            }
            if (i < b.length){
                out[j] = b [i];
                j++;
            }
        }
        return out;
    }
    
    public static void main(String[] args) {
        // Arrays a und b durch Array−Initialisierer konstruieren.
        // Zum Beispiel:
        double [] a = { 3, 7, 9 }, b = { 2, 8, 6, 4, 10 };
        System.out.print("a: "); print(a);
        System.out.print("b: "); print(b);
        System.out.println("mirror(a)");
        mirror(a); print(a);
        mirror(a); print(a);
        System.out.println("rotate(b, 2)");
        print(rotate(b, 2));
        System.out.println("zip(a, b)");
        print(zip(a, b));
        print(zip(b, a));
    }
}
