package droidPD;

import java.util.EmptyStackException;


import stackPD.StackUsed;

public class LinkedStackMaze<E> implements StackUsed<E> {

	private SinglyLinkedListMaze<E> SinglyLinkedList;
	public LinkedStackMaze() {
		SinglyLinkedList = new SinglyLinkedListMaze<E>();
	}
	public int size() {
		return SinglyLinkedList.size(); 
	}
	public boolean isEmpty() {
		return SinglyLinkedList.isEmpty();
	}

	public void push(E element) {
		SinglyLinkedList.addFirst (element);
	}

	public E top() {
		if(isEmpty()) {
			throw new EmptyStackException();
		
		}
		return SinglyLinkedList.first();
	}

	public E pop() {
		if(isEmpty()) {
			throw new EmptyStackException();
		
		}
		return SinglyLinkedList.removeFirst();
	}

}
