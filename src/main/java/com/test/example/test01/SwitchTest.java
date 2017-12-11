package com.test.example.test01;

public class SwitchTest {

	public static void main(String[] args) {
		int mouse_acton = Integer.valueOf(args[0]);

		switch (mouse_acton) {
		case 1:
			System.out.println("up");
			break;
		case 2:
			System.out.println("down");
			break;
		case 3:
			System.out.println("left");
			break;
		case 4:
			System.out.println("right");
			break;
		case 5:
			System.out.println("dblclick");
			break;
		default:
			System.out.println("unknown");
		}
	}

}
