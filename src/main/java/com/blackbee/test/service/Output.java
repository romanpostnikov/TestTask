package com.blackbee.test.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class Output {
    public static void writeToXml(HashSet<String> linksOfOffers) {
        String intend = "    ";
        try (FileWriter writer = new FileWriter("output.xml", false)) {

            writer.write("<offers>\n");

            for (String link : linksOfOffers) {
                Document doc = Jsoup.connect(link).get();
                writer.write(intend + "<offer>\n");

                Elements brand = doc.select("img[alt]");
                writer.write(intend + intend + "<brand>" + brand.attr("alt") + "</brand>\n");

                Elements name = doc.select("div[class^=name]");
                writer.write(intend + intend + "<name>" + name.text() + "</name>\n");

                Elements beforePrice = doc.select("[class^=beforePrice]");
                writer.write(intend + intend + "<oldprice>" + beforePrice.text() + "</oldprice>\n");

                Elements price = doc.select("span[class^=price]");
                if (price.isEmpty()) {
                    price = doc.select("span[class^=finalPrice]");
                }
                writer.write(intend + intend + "<price>" + price.text() + "</price>\n");



                Elements size = doc.select("div[class^=column] span:not([class^=disabled])");
                writer.write(intend + intend + "<size>" + size.text() + "</size>\n");

                //Elements description = doc.select("div[class^=wrapper]");
                //System.out.println(description.text());

                //Elements color = doc.select("div[class==rc-tooltip-inner]");
                //writer.write(color.text());

                //Elements color = doc.getElementsByClass("rc-tooltip-inner");
                //System.out.println(color.get(0).text());
                writer.write(intend + "</offer>\n");
            }
            writer.write("</offers>");

        } catch (IOException e) {

        }

    }
}
