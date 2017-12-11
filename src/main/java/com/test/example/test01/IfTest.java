package com.test.example.test01;

public class IfTest {
	public static void main(String[] args) {
		int score = Integer.valueOf(args[0]);
		if(score>90){
			System.out.println("奖励iphone7s");
		}else if(score>70){
			System.out.println("奖励红米");
		}else {
			System.out.println("俯卧撑伺候");
		}
	}

}
