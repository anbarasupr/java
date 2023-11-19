package ds.linkedList;

import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicates extends LinkedList {

	public RemoveDuplicates(int value) {
		super(value);
		this.tail = null;
	}

	/*
	 * LL: Remove Duplicates ( ** Interview Question)
	 * 
	 * You are given a singly linked list that contains integer values, where some
	 * of these values may be duplicated.
	 * 
	 * 
	 * Note: this linked list class does NOT have a tail which will make this method
	 * easier to implement.
	 * 
	 * 
	 * Your task is to implement a method called removeDuplicates() within the
	 * LinkedList class that removes all duplicate values from the list.
	 * 
	 * Your method should not create a new list, but rather modify the existing list
	 * in-place, preserving the relative order of the nodes.
	 * 
	 * You can implement the removeDuplicates() method in two different ways:
	 * 
	 * 
	 * Using a Set (HashSet) - This approach will have a time complexity of O(n),
	 * where n is the number of nodes in the linked list. You are allowed to use the
	 * provided Set data structure in your implementation.
	 * 
	 * Without using a Set - This approach will have a time complexity of O(n^2),
	 * where n is the number of nodes in the linked list. You are not allowed to use
	 * any additional data structures for this implementation.
	 * 
	 * 
	 * Here is the method signature you need to implement:
	 * 
	 * public void removeDuplicates() { // Your implementation goes here }
	 * 
	 * 
	 * Example:
	 * 
	 * Input:
	 * 
	 * LinkedList: 1 -> 2 -> 3 -> 1 -> 4 -> 2 -> 5
	 * 
	 * Output:
	 * 
	 * LinkedList: 1 -> 2 -> 3 -> 4 -> 5
	 */

	// Solution Using a Set:
	public void removeDuplicates() {
		Set<Integer> values = new HashSet<>();
		Node previous = null;
		Node current = head;
		while (current != null) {
			if (values.contains(current.value)) {
				previous.next = current.next;
				length -= 1;
			} else {
				values.add(current.value);
				previous = current;
			}
			current = current.next;
		}
	}

	// Solution without using a Set:
	public void removeDuplicates2() {
		Node current = head;
		while (current != null) {
			Node runner = current;
			while (runner.next != null) {
				if (runner.next.value == current.value) {
					runner.next = runner.next.next;
					length -= 1;
				} else {
					runner = runner.next;
				}
			}
			current = current.next;
		}
	}

	public void removeDuplicatesV1() {
		HashSet<Integer> set = new HashSet<Integer>();
		Node temp = head;
		Node pre = null;
		while (temp != null) {
			if (!set.contains(temp.value)) {
				set.add(temp.value);
				pre = temp;
			} else {
				pre.next = temp.next;
			}
			temp = temp.next;
		}
	}

	public void removeDuplicatesV2() {
		Node current = head;
		while (current != null) {
			Node temp = current;
			while (temp.next != null) {
				if (current.value == temp.next.value) {
					temp.next = temp.next.next;
					length--;
				} else {
					temp = temp.next;
				}
			}
			current = current.next;
		}

	}

	public static void main(String[] args) {

		RemoveDuplicates myLinkedList = new RemoveDuplicates(1);
		myLinkedList.append(1);
		myLinkedList.append(2);
		myLinkedList.append(2);
		myLinkedList.append(3);
		myLinkedList.append(3);
		myLinkedList.append(3);
		myLinkedList.append(4);

		myLinkedList.removeDuplicates();

		myLinkedList.printList();

		/*
		 * EXPECTED OUTPUT: ---------------- 1 2 3 4
		 * 
		 */

	}

	public void append(int value) {
		Node newNode = new Node(value);
		if (head == null) {
			head = newNode;
		} else {
			Node current = head;
			while (current.next != null) {
				current = current.next;
			}
			current.next = newNode;
		}
		length++;
	}
}
