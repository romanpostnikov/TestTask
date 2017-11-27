package com.blackbee.test;

import com.blackbee.test.service.Output;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;


public class App {
    public static void main(String[] args) throws IOException {
        String keyword = "duck";
        Document doc = Jsoup.connect("https://www.aboutyou.de/suche?term=" + keyword).get();

        Elements search = doc.getElementsByClass("row list-wrapper product-image-list");
        Element searchResultsDiv = search.get(0);

        Elements hrefsOfOffers = searchResultsDiv.select("a[href]");

        HashSet<String> linksOfOffers = new HashSet<>();
        for (Element link : hrefsOfOffers) {
            if (!link.attr("href").equals("")) {
                linksOfOffers.add(link.attr("abs:href"));
            }
        }

        Output.writeToXml(linksOfOffers);


    }
}
