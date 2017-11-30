package com.blackbee.test;

import java.util.HashSet;

import static com.blackbee.test.service.ParserService.getOffers;

/**
  Task:
  Extract all offers for the given keyword
  Extract all of the following properties for every offer (if applicable)
  Name
  Brand
  Color
  Price
  Initial Price
  Description
  <p>
  Article ID
  Shipping Costs
  Comment your code where necessary
  Ensure it is thread-safe
  Use as little as possible requests against the target shop
  Do not use any Java Frameworks
  Make use of public available libraries. If there is a lib that does the job – use it.
  Use Maven for dependency management
  Do not make usage of headless browsers or the like
  Your Parser should run on Windows systems and Linux based environments
  Extract the attributes in the format they are presented on the web page
  <p>
  <p>
  Couldn't get the color of an item because the tag
  with color appear only when there is a pointer on color section.
  The size is also not displayed properly, but the query seems to be okay maybe
  something i missed
 */

public class Main {

    public static void main(String[] args) {
        String keyword = "duck";
        String link = "https://www.aboutyou.de/suche?term=" + keyword;

        HashSet<String> linksOfOffers = new HashSet<>();
        getOffers(link, linksOfOffers);
    }
}
