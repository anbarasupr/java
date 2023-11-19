package ds.linkedList;

public class FindKthFromEnd extends LinkedList {

	public FindKthFromEnd(int value) {
		super(value);
	}

	/*
	 * LL: Find Kth Node From End ( ** Interview Question)
	 * 
	 * Implement a method called findKthFromEnd that returns the k-th node from the
	 * end of the list.
	 * 
	 * If the list has fewer than k nodes, the method should return null.
	 * 
	 * Note: This implementation of the Linked List class does not have the length
	 * attribute.
	 * 
	 * Method signature:
	 * 
	 * public Node findKthFromEnd(int k)
	 * 
	 * 
	 * Example:
	 * 
	 * LinkedList myList = new LinkedList(1); myList.append(2); myList.append(3);
	 * myList.append(4); myList.append(5);
	 * 
	 * Node result = myList.findKthFromEnd(2); // Output: Node with value 4
	 * 
	 * result = myList.findKthFromEnd(5); // Output: Node with value 1
	 * 
	 * result = myList.findKthFromEnd(6); // Output: null
	 * 
	 * 
	 * Note:
	 * 
	 * In this problem, you should use the two-pointer technique to efficiently find
	 * the k-th node from the end of the linked list.
	 */

	public Node findKthFromEnd(int k) {
		Node slow = head;
		Node fast = head;

		for (int i = 0; i < k; i++) {
			if (fast == null) {
				return null;
			}
			fast = fast.next;
		}

		while (fast != null) {
			slow = slow.next;
			fast = fast.next;
		}

		return slow;
	}

	public Node findKthFromEndDesc(int k) {
		Node slow = head; // Initialize slow pointer at head
		Node fast = head; // Initialize fast pointer at head

		// Move fast pointer k steps ahead
		for (int i = 0; i < k; i++) {
			if (fast == null) { // If k is out of bounds, return null
				return null;
			}
			fast = fast.next; // Move the fast pointer to the next node
		}

		// Move both pointers until fast reaches the end
		while (fast != null) {
			slow = slow.next; // Move the slow pointer to the next node
			fast = fast.next; // Move the fast pointer to the next node
		}

		return slow; // Return the kth node from the end (slow pointer)
	}

	public Node findKthFromEndV2(int k) {
		Node slow = head; // Initialize slow pointer at head
		Node fast = head; // Initialize fast pointer at head
		int len = 0;
		while (fast != null) {
			len++;
			fast = fast.next;
		}
		int index = len - k;
		for (int i = 0; i < index; i++) {
			slow = slow.next;
		}

		return slow;
	}

	public static void main(String[] args) {

		FindKthFromEnd myLinkedList = new FindKthFromEnd(1);
		myLinkedList.append(2);
		myLinkedList.append(3);
		myLinkedList.append(4);
		myLinkedList.append(5);

		int k = 2;
		int result = myLinkedList.findKthFromEndV2(k).value;

		System.out.println(result); // Output: 4

		/*
		 * EXPECTED OUTPUT: ---------------- 4
		 * 
		 */

	}

	public void append(int value) {
		Node newNode = new Node(value);
		if (head == null) {
			head = newNode;
			tail = newNode;
		} else {
			tail.next = newNode;
			tail = newNode;
		}
	}
}
