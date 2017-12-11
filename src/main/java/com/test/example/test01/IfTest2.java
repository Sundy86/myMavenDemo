package com.test.example.test01;

public class IfTest2 {

	public static void main(String[] args) {
		String today = args[0];
		String weather = args[1];
		if("周末".equals(today)){
			if("晴朗".equals(weather)){
				System.out.println("室外游玩");
			}else{
				System.out.println("室内游玩");
			}

		}else{
			System.out.println("去上班");
		}

	}

}
