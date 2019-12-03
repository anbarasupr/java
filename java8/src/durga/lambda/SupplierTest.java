package durga.lambda;

import java.util.*;
import java.util.Iterator;
import java.util.function.*;

import common.Employee;
import common.Student;

public class SupplierTest {

	public static void main(String[] args) {
		// program to get random password
		// length should be 8 characters
		// 2,4,6,8 placesonly digits
		// 1,3,5,7 only Capital Uppercase characters, @,#,$
		String symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZ#$@";
		Supplier<Integer> ds = () -> (int) (Math.random() * 10);
		Supplier<Character> cs = () -> symbols.charAt((int) (Math.random() * 29));
		System.out.println("random: " + Math.random());

		Integer digit = ds.get();
		Consumer<Object> c = (obj) -> System.out.println(obj);
		c.accept(digit);
		c.accept(cs.get());
		
		
		String pwd="";
		for (int i = 0; i < 8; i++) {
			if(i%2==0) {
				pwd+=ds.get();
			}else {
				pwd+=cs.get();
			}
		}
		c.accept("Random Password: "+pwd);
		
		
		
	}

}
