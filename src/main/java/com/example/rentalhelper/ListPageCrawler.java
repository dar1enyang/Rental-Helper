package com.example.rentalhelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ListPageCrawler {
	private int regionId;
	private int sectionId;
	private Document doc;

	public ListPageCrawler(int regionId, int sectionId) throws IOException {

		this.regionId = regionId;
		this.sectionId = sectionId;
		String url = "https://rent.591.com.tw/?kind=0&region=" + regionId + "&section=" + sectionId;
		this.doc = Jsoup.connect(url).get();

	}

	public List<String> getDetailUrls() {

		List<String> result = new ArrayList<>();
		List<Element> aElements = doc.select("li.infoContent h3 a");

		for (Element aElement : aElements) {
			String url = aElement.absUrl("href");
			result.add(url);
		}

		return result;
	}
}
