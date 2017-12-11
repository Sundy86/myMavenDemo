package com.test.example.test01;

public class LoopTest {

	public static void main(String[] args) {
		//输出10到20之间数字
		
		// for
		for (int x = 10; x < 20; x++) {
			System.out.println("for value of x : " + x);
		}

		// while
		int x = 10;
		while (x < 20) {
			System.out.println("while value of x : " + x);
			x++;
		}

		// do while
		int y = 10;
		do {
			System.out.println(" do while value of y : " + y);
			y++;
		} while (y < 20);

		

	}
}
