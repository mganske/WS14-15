
/**
 * 
 * 
 * @author 36603
 *
 */
public class Heute {

	static int A[] = {3, 5, 7, 1, 2, 2, 2, 1, 5};
	
	public static void ausgabe() {
		for (int i = 0; i < A.length; i++){
			System.out.print(A[i] + " ");
		}
		System.out.println();
	}
	
	public static void insertionsort1(){
		for (int j = 1; j < A.length; j++){
			int x = A[j];
			int k = j;
			while ((k > 0) && (A[k - 1]) > x){
				A[k] = A[k - 1];
				k = k - 1;	
			}
			A[k] = x;	
		}
	}
	
	public static void insertionsort2(){
		for (int j = (A.length - 2); j > -1; j--){
			int x = A[j];
			int k = j;
			while ((k < A.length - 1) && (A[k + 1] < x)){
				A[k] = A[k + 1];
				k = k + 1;
			}
			A[k] = x;
		}
	}
	
	public static void insertionsort3(){
		for (int j = 1; j < A.length; j++){
			int x = A[j];
			int k = j;
			while ((k > 0) && (A[k - 1]) < x){
				A[k] = A[k - 1];
				k = k - 1;	
			}
			A[k] = x;	
		}
	}
	
	public static void insertionsort4(){
		insertionsort1();
		for (int i = 1; i < A.length; i++){
			static int count = 0;
			if (A[i] != A[i - 1]) count++;
		}
		int B[] = new int[count];
		for (int i = 0; i < A.length; i++){
			
		}
		
		
	}
	public static void main(String[] args){
		System.out.println("Ausgabe vor der Sortierung:");
		ausgabe();
		insertionsort2();
		System.out.println("Ausgabe nach der Sortierung:");
		ausgabe();
	}
}
