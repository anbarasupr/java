package ds.linkedList;

public class FindMiddleNode extends LinkedList {

	public FindMiddleNode(int value) {
		super(value);
	}

	/*
	 * LL: Find Middle Node ( ** Interview Question)
	 * 
	 * Implement a method called findMiddleNode that returns the middle node of the
	 * linked list.
	 * 
	 * If the list has an even number of nodes, the method should return the second
	 * middle node.
	 * 
	 * 
	 * Method signature:
	 * 
	 * public Node findMiddleNode()
	 * 
	 * 
	 * 
	 * Example:
	 * 
	 * LinkedList myList = new LinkedList(1); myList.append(2); myList.append(3);
	 * myList.append(4); myList.append(5); Node middleNode =
	 * myList.findMiddleNode(); System.out.println(middleNode.value); // Output: 3
	 * 
	 * myList.append(6); middleNode = myList.findMiddleNode();
	 * System.out.println(middleNode.value); // Output: 4
	 * 
	 * 
	 * When the linked list has an odd number of nodes, like 1 -> 2 -> 3 -> 4 -> 5,
	 * the function will find the exact middle node. In this case, it will return
	 * the node containing the value 3.
	 * 
	 * When the linked list has an even number of nodes, there will be two middle
	 * nodes. The findMiddleNode function will return the second of these two middle
	 * nodes.
	 * 
	 * For example, if the linked list is 1 -> 2 -> 3 -> 4 -> 5 -> 6, the two middle
	 * nodes are 3 and 4. The function will return the node containing the value 4.
	 * 
	 * 
	 * Note:
	 * 
	 * In this problem, you should use the slow and fast pointer technique (also
	 * known as Floyd's Tortoise and Hare algorithm) to find the middle element of
	 * the linked list efficiently.
	 * 
	 * The key idea is to have two pointers, one that moves twice as fast as the
	 * other. By the time the fast pointer reaches the end of the linked list, the
	 * slow pointer will be at the middle.
	 * 
	 * 
	 * DO NOT use the length attribute in your solution.
	 */

	public Node findMiddleNode() {
		Node slow = head;
		Node fast = head;

		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}

		return slow;
	}

	public Node findMiddleNodeDesc() {
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
		}

		// Return the Node object representing the middle node of the linked list
		return slow;
	}

	public static void main(String[] args) {

		FindMiddleNode myLinkedList = new FindMiddleNode(1);
		myLinkedList.append(2);
		myLinkedList.append(3);
		myLinkedList.append(4);
		myLinkedList.append(5);

		System.out.println("1 -> 2 -> 3 -> 4 -> 5");
		System.out.println("Middle Node: " + myLinkedList.findMiddleNode().value);

		myLinkedList.append(6);

		System.out.println("===========================");
		System.out.println("1 -> 2 -> 3 -> 4 -> 5 -> 6");
		System.out.println("Middle Node: " + myLinkedList.findMiddleNode().value);

		/*
		 * EXPECTED OUTPUT: ---------------- 1 -> 2 -> 3 -> 4 -> 5 Middle Node: 3
		 * =========================== 1 -> 2 -> 3 -> 4 -> 5 -> 6 Middle Node: 4
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
