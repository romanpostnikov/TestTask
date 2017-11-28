package com.blackbee.test;

import java.io.IOException;
import java.util.HashSet;

import static com.blackbee.test.service.ParserService.getOffers;


public class Main {
    public static void main(String[] args) throws IOException {
        String keyword = "duck";
        String link = "https://www.aboutyou.de/suche?term=" + keyword;

        HashSet<String> linksOfOffers = new HashSet<>();
        getOffers(link, linksOfOffers);

    }
}
