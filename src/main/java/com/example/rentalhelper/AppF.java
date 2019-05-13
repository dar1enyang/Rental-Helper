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
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class AppF {
	public static void main(String[] args) {

		try {
			// 信任所有SSL憑證發行單位
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { TrustManagerUtils.getAcceptAllTrustManager() }, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

			// 引導 Morphia 讀取標記
			Morphia morphia = new Morphia();
			morphia.mapPackage("com.example.rentalhelper");

			// 建立 MongoDB 網路連線
			MongoClientURI uri = new MongoClientURI(
					"mongodb://rental-helper:rental-helper123@cluster0-shard-00-00-d4cqk.mongodb.net:27017,cluster0-shard-00-01-d4cqk.mongodb.net:27017,cluster0-shard-00-02-d4cqk.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true");
			MongoClient mongoClient = new MongoClient(uri);
			Datastore datastore = morphia.createDatastore(mongoClient, "rental_helper");
			
			// 透過 ListPageCrawler 實例來抓回列表頁面當中, 通往各屋詳細頁面連結網址
			ListPageCrawler lpc = new ListPageCrawler(1, 1);
			List<String> detailUrls = lpc.getDetailUrls();

			// 走訪各詳細頁面
			for (String detailUrl : detailUrls) {
				System.out.println("詳細頁面網址: " + detailUrl);

				// 透過 DetailPageCrawler 實例來抓取各屋詳細資料
				DetailPageCrawler dpc = new DetailPageCrawler(detailUrl);
				House house = dpc.getHouse();

				// 如果成功才會加入資料庫
				if (house != null) {

					// 查詢資料庫: 是否已經有符合的 postId 的租屋資料
					List<House> existed = datastore.createQuery(House.class).field("postId").equal(house.getPostId())
							.asList();
					if (existed.size() == 0) {
						datastore.save(house);
						System.out.println("資料已寫入");
					} else {
						System.out.println("資料已存在");
					}
					System.out.println(house.toString());
				}
			}

			System.out.println("抓取完成");

		} catch (IOException | NoSuchAlgorithmException | KeyManagementException e) {
			System.out.println("網頁抓取失敗");
			e.printStackTrace();
		}

	}
}
