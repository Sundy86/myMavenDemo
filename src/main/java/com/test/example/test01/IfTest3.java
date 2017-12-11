package com.test.example.test01;

public class IfTest3 {

	public static void main(String[] args) {
		int score1 = Integer.valueOf(args[0]);
		int score2 = Integer.valueOf(args[1]);

		if (score1 > 90 && score2 > 90) {
			System.out.println("优秀");
		}

	}

}
