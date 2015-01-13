public class StackMain {

	public static void main(String[] args) {
		
		Stack s = new Stack(5);
		s.pop();
		System.out.println(s.isEmpty());
		s.push(15);
		System.out.println(s.isEmpty());
		s.push(7);
		s.push(6547135);
		s.push(0);
		s.push(256);
		s.push(-15);
		System.out.println(s.top());
		s.pop();
		s.pop();
		s.pop();
		s.pop();
		s.pop();
		System.out.println(s.top());
	}

}
