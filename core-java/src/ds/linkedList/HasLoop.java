package ds.linkedList;

public class HasLoop extends LinkedList {

	public HasLoop(int value) {
		super(value);
	}

	/*
	 * LL: Has Loop ( ** Interview Question)
	 * 
	 * Write a method called hasLoop that is part of the linked list class.
	 * 
	 * The method should be able to detect if there is a cycle or loop present in
	 * the linked list.
	 * 
	 * The method should utilize Floyd's cycle-finding algorithm, also known as the
	 * "tortoise and hare" algorithm, to determine the presence of a loop
	 * efficiently.
	 * 
	 * The method should follow these guidelines:
	 * 
	 * 
	 * Create two pointers, slow and fast, both initially pointing to the head of
	 * the linked list.
	 * 
	 * Traverse the list with the slow pointer moving one step at a time, while the
	 * fast pointer moves two steps at a time.
	 * 
	 * If there is a loop in the list, the fast pointer will eventually meet the
	 * slow pointer. If this occurs, the method should return true.
	 * 
	 * If the fast pointer reaches the end of the list or encounters a null value,
	 * it means there is no loop in the list. In this case, the method should return
	 * false.
	 * 
	 * 
	 * Output:
	 * 
	 * Return true if the linked list has a loop.
	 * 
	 * Return false if the linked list does not have a loop.
	 * 
	 * 
	 * Constraints:
	 * 
	 * You are not allowed to use any additional data structures (such as arrays) or
	 * modify the existing data structure.
	 * 
	 * You can only traverse the linked list once.
	 * 
	 * 
	 * Method signature:
	 * 
	 * public boolean hasLoop()
	 * 
	 * 
	 * Here are a couple of ways that a linked list with a loop might look:
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Note:
	 * 
	 * In this problem, you should use the slow and fast pointer technique (also
	 * known as Floyd's Tortoise and Hare algorithm) to efficiently detect the
	 * presence of a loop in the linked list.
	 */

	public boolean hasLoop() {
		Node slow = head;
		Node fast = head;

		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;

			if (slow == fast) {
				return true;
			}
		}

		return false;
	}

	public boolean hasLoopDesc() {
		// Initialize slow pointer to the head of the linked list
		Node slow = head;

		// Initialize fast pointer to the head of the linked list
		Node fast = head;

		// Traverse the linked list with two pointers: slow and fast
		// slow moves one node at a time, while fast moves two nodes at a time
		while (fast != null && fast.next != null) {
			// Move slow pointer to the next node
			slow = slow.next;

			// Move fast pointer to the next two nodes
			fast = fast.next.next;

			// If slow pointer meets fast pointer, then there is a loop in the linked list
			if (slow == fast) {
				return true;
			}
		}

		// If the loop has not been detected after the traversal, then there is no loop
		// in the linked list
		return false;
	}

	public static void main(String[] args) {

		HasLoop myLinkedList = new HasLoop(1);
		myLinkedList.append(2);
		myLinkedList.append(3);
		myLinkedList.append(4);
		myLinkedList.append(5);

		// create a loop by connecting the tail to the second node
		myLinkedList.getTail().next = myLinkedList.getHead().next;

		System.out.println("Has Loop:");
		System.out.println(myLinkedList.hasLoop());

		/*
		 * EXPECTED OUTPUT: ---------------- Has Loop: true
		 * 
		 */

	}

	public void append(int value) {
		Node newNode = new Node(value);
		if (length == 0) {
			head = newNode;
			tail = newNode;
		} else {
			tail.next = newNode;
			tail = newNode;
		}
		length++;
	}
}
