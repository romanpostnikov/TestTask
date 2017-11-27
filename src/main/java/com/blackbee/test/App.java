package com.blackbee.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        /*String html = "<html><head><title>First parse</title></head>"
                + "<body><p>Parsed HTML into a doc.</p></body></html>";
        Document doc = Jsoup.parse(html);
        System.out.println(doc.toString());

        String html1 = "<div><p>Lorem ipsum.</p>";
        Document doc1 = Jsoup.parseBodyFragment(html);
        Element body = doc.body();
        System.out.println(body.toString());

        Document doc2 = Jsoup.connect("https://www.aboutyou.de/").get();
        String title = doc2.title();
        System.out.println(title);



        File input = new File("input.html");
        Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");

        Elements links = doc.select("a[href]"); // a with href
        Elements pngs = doc.select("img[src$=.png]");
        // img with src ending .png

        Element masthead = doc.select("div.masthead").first();
        // div with class=masthead

        Elements resultLinks = doc.select("h3.r > a"); // direct a after h3*/

        Document doc = Jsoup.connect("https://www.aboutyou.de/suche?term=ducks&category=20201").get();


        Element content = doc.getElementById("content");
        Elements links = doc.getElementsByTag("a");
        for (Element link : links) {
            String linkHref = link.attr("href");
            String linkText = link.text();
        }
        Elements linkies = doc.select("a[href]"); // a with href
        Elements pngs = doc.select("img[src$=.png]");
        // img with src ending .png

        Element masthead = doc.select("div.masthead").first();
        // div with class=masthead

        Elements resultLinks = doc.select("h3.r > a"); // direct a after h3

       // System.out.println(linkies.toString() + "\n");
        System.out.println(pngs.toString() + "\n");
        //System.out.println(masthead.toString() + "\n\n");
        System.out.println(resultLinks.toString() + "\n");

        File output = new File("output.xml");

    }
}