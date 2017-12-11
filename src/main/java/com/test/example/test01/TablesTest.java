package com.test.example.test01;

public class TablesTest {
	public static void main(String[] args) {
		for (int i = 1; i <= 9; i++) {
			for (int n = 1; n <= i; n++) {
				//横坐标
				System.out.print(i + " x " + n + " = " + i * n + " ");
			}
			//每纵坐标换行一次
			System.out.println();
		}
	}
}

