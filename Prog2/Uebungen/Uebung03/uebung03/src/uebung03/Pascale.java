package uebung03;

/**
 *
 * @author huegel
 */
public class Pascale {
    
    public static void print(int [][] in){
            for (int i = 0; i < in.length - 1; i++){
                for (int j = 0; j < in[i].length; j++){
                    System.out.print(in[i][j] + " ");
                }
                System.out.println();
            }
    }
    public static void leibPrint(int [][] in){
            for (int i = 0; i < in.length - 1; i++){
                for (int j = 0; j < in[i].length; j++){
                    System.out.print("1/" + in[i][j] + " ");
                }
                System.out.println();
            }
    }
    public static int [][] pascal(int n){
        assert n > 0;
        int [][] out = new int[n][];
        out[0] = new int [] {1};
        for (int i = 1; i < n; i++){
            out[i] = new int [i+1];
            out[i][0] = out[i][i] = 1;
            for (int j = 1; j < i; j++){
                out[i][j] = out[i - 1][j] + out[i - 1][j - 1];
            }
        }
        return out;
    }
    public static int [][] leibniz (int n){
        int [][] out = pascal(n);
        for (int i = 1; i < n; i++){
            for(int j = 0; j <= i; j++){
                out[i][j] *= (i + 1);
            }
        }
        return out;
    }
    public static boolean isPalindrome(String in){
        boolean state;
        if (in == null)
            return true;
        for (int i = 0, j = in.length() - 1; i < j; i++, j--){
            if (in.charAt(i) != in.charAt(j))
                return false;
        }
        return true;
    }
    public static String toLower(String in){
        String out = "";
        for (int i = 0; i < in.length() -1; i++){
            char c;
            c = in.charAt(i);
            if ('A' <= c && c <= 'Z')
                c += 'a' - 'A';
            out += c;
        }
        return out;
    }
    
    public static void main(String[] args) {
        print(pascal(1));
        print(pascal(10));
        System.out.println(isPalindrome("Otto")); // false
        System.out.println(isPalindrome("otto")); // true
        System.out.println(toLower("TEST 12 test!")); // test 12 test!
        leibPrint(leibniz (10));
    }
    
}
