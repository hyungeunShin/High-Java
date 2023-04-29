package basicCollection;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
 * Stack => LastInFirstOut(LIFO)
 * Queue => FirstInFirstOut(FIFO)
 */

public class StackQueueTest {
	public static void main(String[] args) {
		/* Stack
		 * 1. 자료입력 : push(입력값)
		 * 2. 자료출력 : pop()  => 자료를 꺼내온 후 꺼내온 자료를 Stack에서 삭제
		 *               peek() => 자료를 꺼내도 삭제되지 않는다
		 */
		Stack<String> s = new Stack<>();
		
		s.push("홍길동");
		s.push("일지매");
		s.push("성춘향");
		s.push("변학도");
		
		System.out.println(s);
		
		String data = s.pop();
		System.out.println(data);
		System.out.println(s.pop());
		System.out.println(s);
		
		s.push("강감찬");
		System.out.println(s);
		System.out.println(s.pop());
		
		System.out.println(s.peek());
		System.out.println(s);
		
		System.out.println("===============================================================");
		
		/* Queue
		 * 1. 자료 입력 : offer(입력값)
		 * 2. 자료 출력 : poll() => 자료를 꺼내오고 꺼내온 자료를 Queue에서 삭제
		 *                peek() => 자료를 꺼내도 삭제되지 않는다
		 *                
		 * Queue는 LinkedList를 이용해서 사용가능               
		 */
		
		Queue<String> q = new LinkedList<>();
		
		q.offer("홍길동");
		q.offer("일지매");
		q.offer("성춘향");
		q.offer("변학도");
		
		System.out.println(q);
		
		String d = q.poll();
		System.out.println(d);
		System.out.println(q.poll());
		System.out.println(q);
		
		q.offer("강감찬");
		
		System.out.println(q.poll());
		System.out.println(q);
		
		System.out.println(q.peek());
		System.out.println(q);
	}
}
