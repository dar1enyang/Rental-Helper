package com.example.rentalhelper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.apache.commons.net.util.TrustManagerUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AppF {
	public static void main(String[] args) {

		try {
			// 信任所有SSL憑證發行單位
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { TrustManagerUtils.getAcceptAllTrustManager() }, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

			// 詢問 JSON 檔路徑
			// /Users/dar1en/Documents/eclipse-workspace/rental-helper/houses.json
			Scanner scanner = new Scanner(System.in);
			System.out.println("請輸入JSON資料檔路徑: ");
			String filePath = scanner.nextLine();

			// 宣告 GSON 實例
			Gson gson = new Gson();

			// 反序列化回 HashMap<Integer, House>
			Map<Integer, House> houses = null;

			try (FileReader reader = new FileReader(filePath)) {
				System.out.println("開始反序列化...");
				Type type = TypeToken.getParameterized(HashMap.class, Integer.class, House.class).getType();
				houses = gson.fromJson(reader, type);
				System.out.println("反序列化成功, 共有 " + houses.size() + " 筆資料");

			} catch (FileNotFoundException e) {
				System.out.println("檔案不存在, 開始爬蟲...");
				houses = new HashMap<>();
			}

			// 透過 ListPageCrawler 實例來抓回列表頁面當中, 通往各屋詳細頁面連結網址
			ListPageCrawler lpc = new ListPageCrawler(1, 1);
			List<String> detailUrls = lpc.getDetailUrls();

			for (String detailUrl : detailUrls) {
				System.out.println("詳細頁面網址: " + detailUrl);

				DetailPageCrawler dpc = new DetailPageCrawler(detailUrl);
				House house = dpc.getHouse();
				if (house != null) {
					houses.put(house.getPostId(), house);
					System.out.println(house.toString());
				}
			}

			System.out.println("抓取完成");

			// 透過 GSON 序列化成 JSON 檔
			try (FileWriter writer = new FileWriter(filePath)) {
				gson.toJson(houses, writer);
			}
			System.out.println("序列化完成");

		} catch (IOException | NoSuchAlgorithmException | KeyManagementException e) {
			System.out.println("網頁抓取失敗");
			e.printStackTrace();
		}

	}
}
