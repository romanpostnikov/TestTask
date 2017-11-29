package com.blackbee.test.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class ParserService {
    /**
     *
     * @param searchLink
     * This is a link to search goods
     * for a particular keyword
     *
     * @param linksOfOffers
     * A collection created to store links to the goods
     *
     */
    public static void getOffers(String searchLink, HashSet<String> linksOfOffers) throws IOException {

        Document doc = Jsoup.connect(searchLink).get();
        Elements search = doc.getElementsByClass("row list-wrapper product-image-list");

        for (Element link : search.select("a[href]")) {
            if (!link.attr("href").equals("")) {
                if (link.attr("abs:href").contains("https://www.aboutyou.de/suche")) {
                    writeToXml(linksOfOffers);
                    getOffers(link.attr("abs:href"), linksOfOffers);
                } else {
                    linksOfOffers.add(link.attr("abs:href"));
                }
            }
        }
        writeToXml(linksOfOffers);
    }

    /**
     *
     * @param linksOfOffers
     * This parameter is a collection of links with goods
     * that are available for the current keyword
     *
     */
    public static void writeToXml(HashSet<String> linksOfOffers) {
        Document doc;
        Elements brand;
        Elements name;
        Elements oldPrice;
        Elements price;
        Elements size;
        Elements description;
        String indent = "    ";

        try (FileWriter writer = new FileWriter("output.xml", false)) {

            writer.write("<offers>\n");

            for (String link : linksOfOffers) {
                doc = Jsoup.connect(link).get();
                writer.write(indent + "<offer>\n");

                brand = doc.select("img[alt]");
                writer.write(indent + indent + "<brand>" + brand.attr("alt") + "</brand>\n");

                name = doc.select("div[class^=name]");
                writer.write(indent + indent + "<name>" + name.text() + "</name>\n");

                oldPrice = doc.select("[class^=beforePrice]");
                writer.write(indent + indent + "<oldprice>" + oldPrice.text() + "</oldprice>\n");

                price = doc.select("span[class^=price]");
                if (price.isEmpty()) {
                    price = doc.select("span[class^=finalPrice]");
                }
                writer.write(indent + indent + "<price>" + price.text() + "</price>\n");

                size = doc.select("div[class^=column] :not([class^=disabled])");
                writer.write(indent + indent + "<size>" + size.text() + "</size>\n");

                description = doc.select("[class^=attributeWrapper]");
                writer.write(indent + indent + "<description>" + description.text() + "</description>\n");

                writer.write(indent + "</offer>\n");
            }
            writer.write("</offers>");

        } catch (IOException e) {
            System.out.println("The parsing result is not written to the file");
        }

    }
}
