package com.matthew.essexstudent.activities.scrapers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WhatsOnScraper {

    //Creating ArrayLists
    private static List<String> eventLinks = new ArrayList<String>();
    private static List<String> eventTitles = new ArrayList<String>();
    private static List<String> eventInfos = new ArrayList<String>();
    private static List<String> eventImages = new ArrayList<String>();
    private static List<String> eventDescs = new ArrayList<String>();
    private static List<Boolean> imageChecks = new ArrayList<Boolean>();
    private static List<String> tempImages = new ArrayList<String>();

    private Document connection;
    private boolean established;

    //Attempting Initial Connection
    public void connect(){
        established = false;
        try {
            connection = Jsoup.connect("https://www.essexstudent.com/whatson/").get();
            established = true;
        } catch (IOException ex) {
            System.out.println("Connection to https://www.essexstudent.com/whatson/ failed.");
        }
    }

    //Retrieving links for all events from http://www.essexstudent.com/whatson/
    public void getLinks() {
        Elements rawLink = connection.select("a.msl_event_name");

        for(Element e: rawLink) {
            String link = "https://www.essexstudent.com"+e.attr("href");
            eventLinks.add(link);
        }
    }

    //Connecting to individual event pages
    public Document getEvent(int x) {
        String link = eventLinks.get(x);
        try {
            return Jsoup.connect(link).get();
        } catch (Exception ex) {
            if (ex instanceof NullPointerException) {
                //do nothing
                return null;
            } else {
                System.out.println("Connection Error: Unable to connect to "+link);
                return null;
            }
        }
    }

    //Retrieving the event image from each event
    public void getImages(int itemNumber) {
        String url = "https://www.essexstudent.com";
        Elements span;
        try {
            for (int n=0;n<15;n++) {
                itemNumber++;

                if ((itemNumber % 2) == 0) {
                    span = connection.select("div.event_item.item" + itemNumber
                            + ".itemEven > dl > dt > a > span.msl_event_image");
                } else {
                    span = connection.select("div.event_item.item" + itemNumber
                            + ".itemOdd > dl > dt > a > span.msl_event_image");
                }

                checkImages(span);
            }

            imageFinder(url);

            saveImage(url);

        } catch (Exception ex) {
            if (ex instanceof NullPointerException) {
                //do nothing
            } else {
                System.out.println("Error Retrieving Image");
            }
        }
    }

    //Checks if the image for each event exists
    private void checkImages(Elements span) {
        String check = span.toString();
        if (check.matches(".*\\w.*")) {
            imageChecks.add(true);
        } else {
            imageChecks.add(false);
        }
    }

    //Scraping each event image and storing it in a temporary list
    private void imageFinder(String url) {
        Elements finder = connection.select("span.msl_event_image");
        for (Element e: finder) {
            Elements imageTag = e.select("img");
            String allImages = imageTag.attr("src");
            tempImages.add(url+allImages);
        }
    }

    //Saving images to the final list
    private void saveImage(String url) {
        int tempInt = 0;
        for (int n=0;n<imageChecks.size();n++) {
            if (imageChecks.get(n)) {
                eventImages.add(tempImages.get(tempInt));
                tempInt++;
            } else {
                String link = eventLinks.get(n);
                try {
                    Document eventPage = Jsoup.connect(link).get();
                    Elements defaultImage = eventPage.select("#ctl00_ctl22_imgBanner");
                    String imageURL = defaultImage.attr("src");
                    eventImages.add(url+imageURL);
                } catch (IOException ex) {
                    System.out.println("Connection Error: Unable to connect to "+link);
                }
            }
        }
    }

    //Retrieve the Titles for each event
    public void getTitles(Document connection) {
        try {
            Elements rawTitle = connection.select("h1.header");

            for(Element e: rawTitle) {
                String title = e.text();
                eventTitles.add(title);
            }
        } catch (Exception ex) {
            System.out.println("Error Retrieving Title");
        }
    }

    //Retrieve the information about each event - date, time and location
    public void getInfo(Document connection) {
        try {
            Elements rawInfo = connection.select("h2");
            String info = "";

            for(Element e: rawInfo) {
                info += e.text()+" ";
                eventInfos.add(info);
            }
        } catch (Exception ex) {
            System.out.println("Error Retrieving Info");
        }
    }

    //Retrieve the description of each event
    public void getDesc(Document connection) {
        try {
            Elements rawDesc = connection.select("h3,p");
            String desc = "";

            for(Element e: rawDesc) {
                String check = e.text();
                if (check.equals("Tickets")) {
                    //Do nothing
                } else if (check.matches(".*\\w.*")) {
                    desc += e.text()+" ";
                }
            }

            desc = desc.replace("Powered by MSL","");

            //Removing whitespaces - checking for ASCII characters
            if (desc.matches(".*\\w.*")) {
                eventDescs.add(desc);
            } else {
                desc = "More details for this event coming soon!, Watch this space!";
                eventDescs.add(desc);
            }
        } catch (Exception ex) {
            System.out.println("Error Retrieving Description");
        }
    }

    public List<String> returnLinks() {
        return eventLinks;
    }

    public List<String> returnImages() {
        return eventImages;
    }

    public List<String> returnTitles() {
        return eventTitles;
    }

    public List<String> returnInfos() {
        return eventInfos;
    }

    public List<String> returnDescs() {
        return eventDescs;
    }

    public boolean returnConnected() { return established; }

    //Display the eventLinks array
    public void displayLinks() {
        for(String link:eventLinks) {
            System.out.println("Event Link: "+link);
        }
    }

    //Display the eventTitles array
    public void displayTitles() {
        for(String title:eventTitles) {
            System.out.println("Event Title: "+title);
        }
    }

    //Display the eventImages array
    public void displayImages() {
        for(String image:eventImages) {
            System.out.println("Event Image: "+image);
        }
    }

    //Display the eventInfos array
    public void displayInfos() {
        for(String info:eventInfos) {
            System.out.println("Event Info: "+info);
        }
    }

    //Display the eventDescs array
    public void displayDescs() {
        for(String desc:eventDescs) {
            System.out.println("Event Desc: "+desc);
        }
    }
}

