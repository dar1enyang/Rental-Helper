package com.example.rentalhelper;

import java.util.Scanner;

public class App {
	public static void main(String[] args) {
		// A
		float areaA = 10f;
		String typeA = "套房";
		int priceA = 12000;
		String ownerA = "王先生";
		String addressA = "台北市文山區木柵路一段101號";
		// B
		float areaB = 8f;
		String typeB = "套房";
		int priceB = 8000;
		String ownerB = "陳先生";
		String addressB = "台北市中山區中山路一段102號";
		// C
		float areaC = 4f;
		String typeC = "雅房";
		int priceC = 6000;
		String ownerC = "林先生";
		String addressC = "台北市大安區仁愛路一段102號";

		// All info
		String infoA = "A listing - " + areaA + "坪 - " + typeA + " - 每月" + priceA + "元 - 屋主: " + ownerA + " - 地址: "
				+ addressA;
		String infoB = "B listing - " + areaB + "坪 - " + typeB + " - 每月" + priceB + "元 - 屋主: " + ownerB + " - 地址: "
				+ addressB;
		String infoC = "C listing - " + areaC + "坪 - " + typeC + " - 每月" + priceC + "元 - 屋主: " + ownerC + " - 地址: "
				+ addressC;

		Scanner sc = new Scanner(System.in);

		mainLoop: while (true) {
			System.out.println("Welcome to this rental helper, please select function:");
			System.out.println("1. List all rental listings");
			System.out.println("2. Rental Inquiry");
			System.out.println("3. 租金試算");
			System.out.println("4. Exit");

			if (sc.hasNextInt()) {
				int option = sc.nextInt();

				switch (option) {
				case 1:
					System.out.println(infoA);
					System.out.println(infoB);
					System.out.println(infoC);
					break;

				case 2: {
					System.out.println("請輸入物件名稱(A, B, C):");
					String id = sc.next();
					switch (id) {
					case "A":
						System.out.println(infoA);
						break;
					case "B":
						System.out.println(infoB);
						break;
					case "C":
						System.out.println(infoC);
						break;
					default:
						System.out.println("很抱歉, 物件不存在!");
						break;
					}
					break;
				}
				case 3: {
					System.out.println("請輸入物件名稱(A, B, C):");
					String id = sc.next();
					int monthlyPrice = 0;
					switch (id) {
					case "A":
						monthlyPrice = priceA;
						break;
					case "B":
						monthlyPrice = priceB;
						break;
					case "C":
						monthlyPrice = priceC;
						break;
					default:
						System.out.println("很抱歉, 物件不存在!");
						continue;
					}
					
					// 接收合約租期
					System.out.println("請輸入合約租期(月):");
					int expectedMonths = sc.nextInt();
					if (expectedMonths < 0) {
						System.out.println("資料無效");
					} else {
						// 接收押金期數
						System.out.println("請輸入押金期數(月):");
						int depositMonths = sc.nextInt();
						if (depositMonths < 0) {
							System.out.println("資料無效");
						} else {
							// 接收實際租期
							System.out.println("請輸入實際租期(月):");
							int actualMonths = sc.nextInt();
							if (actualMonths > expectedMonths || actualMonths < 0) {
								System.out.println("資料無效");
							} else {
								// 進行租金試算
								int total = monthlyPrice * actualMonths + ((actualMonths < expectedMonths) ? (depositMonths * monthlyPrice) : 0);
								float average = total / (float)actualMonths;
								
								System.out.println("租金總額: " + total + "元");
								System.out.println("月平均租金: " + average + "元");
							}
						}
					}
					
					break;
				}
				case 4:
					break mainLoop;

				default:

					break;
				}
			} else {
				sc.next();

			}

		}
	}
}
