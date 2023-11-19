package ds.linkedList;

public class PartitionList extends LinkedList {

	public PartitionList(int value) {
		super(value);
		this.tail = null;
	}

	/*
	 * LL: Partition List ( ** Interview Question)
	 * 
	 * You have a singly linked list that DOES NOT HAVE A TAIL POINTER (which will
	 * make this method simpler to implement).
	 * 
	 * Given a value x you need to rearrange the linked list such that all nodes
	 * with a value less than x come before all nodes with a value greater than or
	 * equal to x.
	 * 
	 * Additionally, the relative order of nodes in both partitions should remain
	 * unchanged.
	 * 
	 * 
	 * Constraints:
	 * 
	 * The solution should traverse the linked list at most once.
	 * 
	 * The solution should not create a new linked list.
	 * 
	 * 
	 * Input:
	 * 
	 * A singly linked list and an integer x.
	 * 
	 * 
	 * Output:
	 * 
	 * The same linked list but rearranged as per the above criteria.
	 * 
	 * 
	 * Function signature:
	 * 
	 * public void partitionList(int x);
	 * 
	 * 
	 * Details:
	 * 
	 * The function partitionList takes an integer x as a parameter and modifies the
	 * current linked list in place according to the specified criteria. If the
	 * linked list is empty (i.e., head is null), the function should return
	 * immediately without making any changes.
	 * 
	 * 
	 * Example 1:
	 * 
	 * Input:
	 * 
	 * Linked List: 3 -> 8 -> 5 -> 10 -> 2 -> 1 x: 5
	 * 
	 * Process:
	 * 
	 * Values less than 5: 3, 2, 1
	 * 
	 * Values greater than or equal to 5: 8, 5, 10
	 * 
	 * Output:
	 * 
	 * Linked List: 3 -> 2 -> 1 -> 8 -> 5 -> 10
	 * 
	 * 
	 * 
	 * Example 2: Input:
	 * 
	 * Linked List: 1 -> 4 -> 3 -> 2 -> 5 -> 2 x: 3
	 * 
	 * Process:
	 * 
	 * Values less than 3: 1, 2, 2
	 * 
	 * Values greater than or equal to 3: 4, 3, 5
	 * 
	 * Output:
	 * 
	 * Linked List: 1 -> 2 -> 2 -> 4 -> 3 -> 5
	 * 
	 * 
	 * 
	 * Tips:
	 * 
	 * While traversing the linked list, maintain two separate chains: one for
	 * values less than x and one for values greater than or equal to x.
	 * 
	 * Use dummy nodes to simplify the handling of the heads of these chains.
	 * 
	 * After processing the entire list, connect the two chains to get the desired
	 * arrangement.
	 * 
	 * 
	 * Note:
	 * 
	 * The solution must maintain the relative order of nodes. For instance, in the
	 * first example, even though 8 appears before 5 in the original list, the
	 * partitioned list must still have 8 before 5 as their relative order remains
	 * unchanged.
	 * 
	 * Note:
	 * 
	 * You must solve the problem WITHOUT MODIFYING THE VALUES in the list's nodes
	 * (i.e., only the nodes' next pointers may be changed.)
	 */

	public void partitionList(int x) {
		if (head == null)
			return;

		Node dummy1 = new Node(0);
		Node dummy2 = new Node(0);
		Node prev1 = dummy1;
		Node prev2 = dummy2;
		Node current = head;

		while (current != null) {
			if (current.value < x) {
				prev1.next = current;
				prev1 = current;
			} else {
				prev2.next = current;
				prev2 = current;
			}
			current = current.next;
		}

		prev2.next = null;
		prev1.next = dummy2.next;

		head = dummy1.next;

		dummy1.next = null;
		dummy2.next = null;
	}

	public void partitionListDesc(int x) {
		// Step 1: Check for an empty list.
		// If the list is empty, there is nothing
		// to partition, so we exit the method.
		if (head == null)
			return;

		// Step 2: Create two dummy nodes.
		// We create dummy nodes to act as the
		// starting points for our two new lists.
		Node dummy1 = new Node(0);
		Node dummy2 = new Node(0);

		// Step 3: Initialize list pointers.
		// We'll use 'prev1' and 'prev2' to keep track
		// of the last nodes in our two new lists.
		Node prev1 = dummy1;
		Node prev2 = dummy2;

		// Step 4: Start at the head node.
		// We initialize 'current' to the head of the list.
		// This node will move through each node in the list.
		Node current = head;

		// Step 5: Begin loop to go through list.
		// We'll continue until we reach the end of the list.
		while (current != null) {

			// Step 6: Partition nodes based on value.
			// We have an if-else block to categorize the current
			// node based on its value in comparison to 'x'.

			// Condition: If the value is less than 'x'
			if (current.value < x) {

				// Step 6.1: Add to first list.
				// We set the 'next' pointer of the node
				// referenced by 'prev1' to the current node.
				// This effectively adds 'current' to the end
				// of the first list (list with smaller values).
				prev1.next = current;

				// Step 6.2: Update 'prev1'.
				// We move 'prev1' so that it now points to
				// 'current'. This prepares 'prev1' for the
				// next addition to the first list.
				prev1 = current;

				// Condition: If the value is greater than or equal to 'x'
			} else {

				// Step 6.3: Add to second list.
				// We set the 'next' pointer of the node
				// referenced by 'prev2' to the current node.
				// This effectively adds 'current' to the end
				// of the second list (list with larger values).
				prev2.next = current;

				// Step 6.4: Update 'prev2'.
				// We move 'prev2' so that it now points to
				// 'current'. This prepares 'prev2' for the
				// next addition to the second list.
				prev2 = current;
			}

		}

		// Step 8: Mark end of the second list.
		// We set the next pointer of the last node
		// in the second list to null.
		prev2.next = null;

		// Step 9: Connect the two lists.
		// We connect the end of the first list
		// to the start of the second list.
		prev1.next = dummy2.next;

		// Step 10: Update the head pointer.
		// The head of the list should now point
		// to the start of the first partitioned list.
		head = dummy1.next;
	}

	public void partitionListV1(int x) {
		if (head == null) {
			return;
		}
		Node left = null, right = null, temp = head, rightHead = null, leftHead = null;

		while (temp != null) {
			if (temp.value < x) {
				if (left == null) {
					left = leftHead = temp;
				} else {
					left.next = temp;
					left = left.next;
				}
			} else {
				if (right == null) {
					right = rightHead = temp;
				} else {
					right.next = temp;
					right = right.next;
				}
			}
			temp = temp.next;
		}
		if (right != null)
			right.next = null;
		if (left != null)
			left.next = rightHead;

		if (rightHead != null) {
			head = rightHead;
		}
		if (leftHead != null) {
			head = leftHead;
		}
	}

	public static void main(String[] args) {

		PartitionList ll = new PartitionList(3);
		ll.append(5);
		ll.append(8);
		ll.append(10);
		ll.append(2);
		ll.append(1);

		// Print the list before partitioning
		System.out.println("LL before partitionList:");
		ll.printList(); // Output: 3 5 8 10 2 1

		// Call the partitionList method with x = 5
		ll.partitionList(3);

		// Print the list after partitioning
		System.out.println("LL after partitionList:");
		ll.printList(); // Output: 3 2 1 5 8 10

		/*
		 * EXPECTED OUTPUT: ---------------- LL before partition_list: 3 5 8 10 2 1 LL
		 * after partition_list: 3 2 1 5 8 10
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
