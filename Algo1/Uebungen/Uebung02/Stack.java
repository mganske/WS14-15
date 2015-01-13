public class Stack{
	
	private int[] stack;
	private int zeiger = 0; 
			
	public Stack (int size){
		stack = new int [size];
	}
	
	public boolean isEmpty(){
		if (zeiger == 0){
			return true;
		}
		else
			return false;
	}
	
	public int top(){
		if (isEmpty()){
			System.out.println("Der Stack ist leer!");
			return -1;
		}
		else 
			return stack[zeiger - 1];
	}
	
	public void push(int in){
		if (zeiger < stack.length){
			stack[zeiger] = in;
			zeiger ++;
		}
		else {
			System.out.println("Overflow!");
		}
	}
	
	public int pop(){
		if (isEmpty()){
			System.out.println("Underflow!");
			return -1;
		}
		else {
			zeiger --;
			return stack[zeiger];
		}
		
		
	}
}