package ds.linkedList;

import java.util.HashSet;
import java.util.Set;

public class BinaryToDecimal extends LinkedList {

	public BinaryToDecimal(int value) {
		super(value);
		this.tail = null;
	}

	/*
	 * LL: Binary to Decimal ( ** Interview Question)
	 * 
	 * Objective:
	 * 
	 * You have a linked list where each node represents a binary digit (0 or 1).
	 * The goal of the binaryToDecimal function is to convert this binary number,
	 * represented by the linked list, into its decimal equivalent.
	 * 
	 * 
	 * Function Signature:
	 * 
	 * public int binaryToDecimal()
	 * 
	 * 
	 * How Binary to Decimal Conversion Works:
	 * 
	 * In binary-to-decimal conversion, each position of a binary number corresponds
	 * to a specific power of 2, starting from the rightmost digit.
	 * 
	 * The rightmost digit is multiplied by 2^0 (which equals 1).
	 * 
	 * The next digit to the left is multiplied by 2^1 (which equals 2).
	 * 
	 * The digit after that is multiplied by 2^2 (which equals 4). ... and so on.
	 * 
	 * To find the decimal representation:
	 * 
	 * Multiply each binary digit by its corresponding power of 2 value.
	 * 
	 * Sum up all these products.
	 * 
	 * 
	 * Example Execution with Binary 101:
	 * 
	 * Start with num = 0.
	 * 
	 * Process 1 (from the head of the linked list): num = 0 * 2 + 1 = 1
	 * 
	 * Process 0: num = 1 * 2 + 0 = 2
	 * 
	 * Process 1: num = 2 * 2 + 1 = 5
	 * 
	 * Return num, which is 5.
	 * 
	 * 
	 * Steps Involved in the Function:
	 * 
	 * A variable num is initialized to 0, which will store our computed decimal
	 * number.
	 * 
	 * Starting from the head of the linked list (the leftmost binary digit),
	 * iterate through each node until the end.
	 * 
	 * For every node, double the current value of num (this is analogous to
	 * shifting in binary representation). Then, add the binary digit of the current
	 * node.
	 * 
	 * Move to the next node and repeat until you've visited all nodes.
	 * 
	 * Return the value in num, which now represents the decimal value of the binary
	 * number in the linked list.
	 */

	public int binaryToDecimal() {
		int num = 0;
		Node current = head;
		while (current != null) {
			num = num * 2 + current.value;
			current = current.next;
		}
		return num;
	}

	public static void main(String[] args) {

		// ---------------
		// Convert 1011 to 11
		// ---------------
		BinaryToDecimal list1 = new BinaryToDecimal(1);
		list1.append(0);
		list1.append(1);
		list1.append(1);
		System.out.println("Convert 1011 to 11:");
		System.out.println("Input: 1 -> 0 -> 1 -> 1");
		System.out.println("Output: " + list1.binaryToDecimal());
		System.out.println("---------------");

		// ---------------
		// Convert 1100 to 12
		// ---------------
		BinaryToDecimal list2 = new BinaryToDecimal(1);
		list2.append(1);
		list2.append(0);
		list2.append(0);
		System.out.println("Convert 1100 to 12:");
		System.out.println("Input: 1 -> 1 -> 0 -> 0");
		System.out.println("Output: " + list2.binaryToDecimal());
		System.out.println("---------------");

		// ---------------
		// Convert 1 to 1
		// ---------------
		BinaryToDecimal list3 = new BinaryToDecimal(1);
		System.out.println("Convert 1 to 1:");
		System.out.println("Input: 1");
		System.out.println("Output: " + list3.binaryToDecimal());
		System.out.println("---------------");

		// ---------------
		// Convert empty list to 0
		// ---------------
		BinaryToDecimal list4 = new BinaryToDecimal(0);
		list4.makeEmpty();
		System.out.println("Convert empty list to 0:");
		System.out.println("Input: empty");
		System.out.println("Output: " + list4.binaryToDecimal());
		System.out.println("---------------");
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
