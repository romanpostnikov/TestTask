package com.blackbee.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        String keyword = "ducks";
        Document doc = Jsoup.connect("https://www.aboutyou.de/suche?term=" + keyword).get();

        Elements search = doc.getElementsByClass("row list-wrapper product-image-list");
        Element searchDiv = search.get(0);

        //Elements links = searchDiv.getElementsByTag("a");
        Elements links = searchDiv.select("a[href]");
        System.out.println(links);




       /* Document doc = Jsoup.connect("https://www.aboutyou.de/p/save-the-duck/steppjacke-mit-kunstdaunen-3727286").get();
        Elements info = doc.getElementsByClass("rc-tooltip-inner");


        try(FileWriter writer = new FileWriter("output.xml", false))
        {
            // запись всей строки
            //String text = "tu carne";
            writer.write(info.toString());
            // запись по символам
            writer.append('\n');
            writer.append('E');

            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }*/
    }
}
