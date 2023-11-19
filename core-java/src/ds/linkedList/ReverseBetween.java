package ds.linkedList;

import java.util.HashSet;
import java.util.Set;

public class ReverseBetween extends LinkedList {

	public ReverseBetween(int value) {
		super(value);
		this.tail = null;
	}

	/*
	 * LL: Reverse Between ( ** Interview Question)
	 * 
	 * In the LinkedList class, implement a method called reverseBetween that
	 * reverses the nodes of the list between indexes startIndex and endIndex
	 * (inclusive).
	 * 
	 * It's important to note that you should only rearrange the nodes themselves,
	 * not just their values.
	 * 
	 * Note: The Linked List does NOT have a tail which will make the implementation
	 * easier.
	 * 
	 * Assumption: You can assume that startIndex and endIndex are not out of
	 * bounds.
	 * 
	 * The method should have the following signature:
	 * 
	 * 
	 * public void reverseBetween(int m, int n) { // Your implementation here }
	 * 
	 * 
	 * The method should not return any value, and it should modify the original
	 * linked list.
	 * 
	 * The positions startIndex and endIndex are 0-indexed.
	 * 
	 * 
	 * Example:
	 * 
	 * Given the following linked list:
	 * 
	 * 
	 * 1 -> 2 -> 3 -> 4 -> 5
	 * 
	 * 
	 * Calling reverseBetween(1, 3) should result in the following modified linked
	 * list:
	 * 
	 * 
	 * 1 -> 4 -> 3 -> 2 -> 5
	 * 
	 * 
	 * I highly recommend that you draw the Linked List out on a piece of paper so
	 * you can visualize the steps.
	 * 
	 * Notes:
	 * 
	 * The method should not duplicate any of the existing nodes, only rearranging
	 * the existing nodes in the list.
	 * 
	 * However, the creation of a limited number of new nodes is allowed (e.g.,
	 * dummy nodes to facilitate the partitioning process).
	 * 
	 * The method should not use any extra data structures such as arrays or lists.
	 */

	public void reverseBetween(int startIndex, int endIndex) {
		if (head == null)
			return;

		Node dummyNode = new Node(0);
		dummyNode.next = head;
		Node previousNode = dummyNode;

		for (int i = 0; i < startIndex; i++) {
			previousNode = previousNode.next;
		}

		Node currentNode = previousNode.next;

		for (int i = 0; i < endIndex - startIndex; i++) {
			Node nodeToMove = currentNode.next;
			currentNode.next = nodeToMove.next;
			nodeToMove.next = previousNode.next;
			previousNode.next = nodeToMove;
		}

		head = dummyNode.next;
	}

	public void reverseBetweenV1(int m, int n) {

		Node start = head;
		Node startBefore = null;

		for (int i = 0; i < m; i++) {
			startBefore = start;
			start = start.next;
		}

		System.out.println("start:" + start.value);
		System.out.println("startBefore:" + startBefore.value);
		System.out.println("head:" + head.value);

		Node before = null;
		Node after = null;
		Node temp = start;
		for (int i = m; i <= n; i++) {
			// System.out.println("swap:" + temp.value);

			after = temp.next;
			before = temp.next;

			before = temp;
			System.out.println("before:" + before.value + ", temp:" + temp.value);

			temp = after;
		}

		System.out.println("before:" + before.value + ", next:" + before.next.value);
		System.out.println("after:" + after.value);

		startBefore.next = before;
		start.next = after;

		System.out.println("head:" + head.value);

	}

	public static void main(String[] args) {

		ReverseBetween myLinkedList = new ReverseBetween(1);
		myLinkedList.append(2);
		myLinkedList.append(3);
		myLinkedList.append(4);
		myLinkedList.append(5);

		System.out.println("Original linked list: ");
		myLinkedList.printList();

		// Reverse a sublist within the linked list
		myLinkedList.reverseBetween(1, 3);
		System.out.println("\nReversed sublist (1, 3): ");
		myLinkedList.printList();

		// Reverse another sublist within the linked list
//		myLinkedList.reverseBetween(0, 4);
//		System.out.println("\nReversed entire linked list: ");
//		myLinkedList.printList();
//
//		// Reverse a sublist of length 1 within the linked list
//		myLinkedList.reverseBetween(2, 2);
//		System.out.println("\nReversed sublist of length 1 (2, 2): ");
//		myLinkedList.printList();
//
//		// Reverse an empty linked list
//		ReverseBetween emptyList = new ReverseBetween(0);
//		emptyList.makeEmpty();
//		emptyList.reverseBetween(0, 0);
//		System.out.println("\nReversed empty linked list: ");
//		emptyList.printList();

		/*
		 * EXPECTED OUTPUT: ---------------- Original linked list: 1 2 3 4 5
		 * 
		 * Reversed sublist (1, 3): 1 4 3 2 5
		 * 
		 * Reversed entire linked list: 5 2 3 4 1
		 * 
		 * Reversed sublist of length 1 (2, 2): 5 2 3 4 1
		 * 
		 * Reversed empty linked list:
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
