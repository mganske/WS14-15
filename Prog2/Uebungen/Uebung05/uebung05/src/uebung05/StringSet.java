package uebung05;

/**
 *
 * @author huegel
 */
public class StringSet {
   private String [] elems;
   private int card;
   
   private int search(String s){
       for (int i = 0; i < card; i++){
           if (elems[i].equals(s)){
               return i;
           }
       }
       return -1;
   }
   
   public StringSet(int n){
       elems = new String[n];
       card = 0;
   }
   public StringSet(int n, String s){
       this(n);
       insert(s);
   }
   public int capacity(){
       return elems.length;
   }
   public int card(){
       return card;
   }
   public void print(){
       String out = "{ ";
       for(int i = 0; i < elems.length - 1; i++){
           out += i + ", ";
       }
       out += elems[elems.length] + " }";
       System.out.println(out);
   }
   public boolean contains(String s){
      return search(s) >= 0;
   }
   public boolean insert(String s){
        if (s == null)
           return false;
        if(elems.length == card)
           return true;
        if (search(s) >= 0)
            return false;
        elems[card++] = s;
        return true;
   }
   public boolean remove(String s){
       if (s == null)
           return false;
       int i = search(s);
       if (i == -1)
           return false;
       elems[i] = elems[--card];
       elems[card] = null;
       return true;
   }
   public static StringSet intersection(StringSet first, StringSet second){
       
       int n = first.card < second.card ? first.card : second.card;
       StringSet result = new StringSet(n);
       for (int i = 0; i < first.card; i++){
           String s = first.elems[i];
           if (second.contains(s))
               result.insert(s);
       }
       return result;
   }
}