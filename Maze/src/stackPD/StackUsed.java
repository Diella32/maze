package stackPD;

public interface StackUsed<E> {

	int size();
	boolean isEmpty();
	void push(E e);
	E top();
	E pop(); 
}
