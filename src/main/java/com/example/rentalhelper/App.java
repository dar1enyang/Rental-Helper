package com.example.rentalhelper;

import java.util.Scanner;

public class App {
	public static void printGuide() {
		System.out.println("Welcome to this rental helper, please select function:");
		System.out.println("1. List all rental listings");
		System.out.println("2. Rental Inquiry");
		System.out.println("3. 租金試算");
		System.out.println("4. Exit");
	}

	public static void printHouseQuery() {
		System.out.println("請輸入物件名稱(A, B, C):");
	}

	public static void printHouseNotFound() {
		System.out.println("很抱歉, 物件不存在!");
	}

	public static void printInvalidData() {
		System.out.println("資料無效!");
	}

	public static String getHouseInfo(int id, float area, String type, int price, String owner, String address) {
		return id + "物件 - " + area + "坪 - " + type + " - 每月" + price + "元 - 屋主: " + owner + " - 地址: " + address;
	}

	public static void main(String[] args) {

		float[] areas = new float[] { 10f, 8f, 4f };
		String[] types = new String[] { "套房", "套房", "雅房" };
		int[] prices = new int[] { 12000, 8000, 6000 };
		String[] owners = new String[] { "王先生", "陳先生", "林先生" };
		String[] addresses = new String[] { "台北市文山區木柵路一段101號", "台北市中山區中山路一段102號", "台北市大安區仁愛路一段102號" };

		Scanner sc = new Scanner(System.in);

		mainLoop: while (true) {

			printGuide();

			if (sc.hasNextInt()) {
				int option = sc.nextInt();

				switch (option) {
				case 1:
					for (int i = 0; i < areas.length; i++) {
						System.out.println(getHouseInfo(i, areas[i], types[i], prices[i], owners[i], addresses[i]));
					}

					break;

				case 2: {
					printHouseQuery();
					int index = sc.nextInt();

					if (!(index >= 0 && index < areas.length)) {
						printHouseNotFound();
						continue;
					}

					System.out.println(getHouseInfo(index, areas[index], types[index], prices[index], owners[index],
							addresses[index]));

					break;
				}

				case 3: {
					printHouseQuery();
					int index = sc.nextInt();
					if (!(index >= 0 && index < areas.length)) {
						printHouseNotFound();
						continue;
					}
					int monthlyPrice = prices[index];

					// 接收合約租期
					System.out.println("請輸入合約租期(月):");
					int expectedMonths = sc.nextInt();
					if (expectedMonths < 0) {
						printInvalidData();
						continue;
					}
					// 接收押金期數
					System.out.println("請輸入押金期數(月):");
					int depositMonths = sc.nextInt();
					if (depositMonths < 0) {
						printInvalidData();
						continue;
					}
					// 接收實際租期
					System.out.println("請輸入實際租期(月):");
					int actualMonths = sc.nextInt();
					if (actualMonths > expectedMonths || actualMonths < 0) {
						printInvalidData();
						continue;
					}
					// 進行租金試算
					int total = monthlyPrice * actualMonths
							+ ((actualMonths < expectedMonths) ? (depositMonths * monthlyPrice) : 0);
					float average = total / (float) actualMonths;

					System.out.println("租金總額: " + total + "元");
					System.out.println("月平均租金: " + average + "元");

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
