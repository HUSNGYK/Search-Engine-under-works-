//Soorya Suresh
//111458936
//soorya.suresh@stonybrook.edu
//Data Structures
//CSE 214
//R08 
//Johnny So
package searchengine;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 *
 * @author Soorya Suresh
 */
public class WebGraph {

    public static final int MAX_PAGES = 40;
    private static ArrayList<WebPage> pages;
    private static int[][] edges;

    /**
     * this constructor sets edges max for [40][40] then sets each position in
     * the matrix to -1 sets pages to new ArrayList
     */
    public WebGraph() {
        edges = new int[MAX_PAGES][MAX_PAGES];
        for (int i = 0; i < MAX_PAGES; i++) {
            for (int j = 0; j < MAX_PAGES; j++) {
                edges[i][j] = -1;
            }

        }
        pages = new ArrayList<WebPage>();
    }

    /**
     *
     * @param pagesFile is taken in so the fileinputstream can open that file
     * @param linksFile is taken in so the fileinputstream can open that link
     * @return null
     * @throws IllegalArgumentException in case the pagesFile is null and if
     * linksFile is null
     * @throws FileNotFoundException in case the file isn't found
     * @throws IOException this method builds based off the files the
     * pagesIsFile will addPages to the web based on url and keywords the
     * linkIsFile will set edges[row][col]=1
     */
    public static WebGraph buildFromFiles(String pagesFile, String linksFile) throws IllegalArgumentException, FileNotFoundException, IOException {
        if (pagesFile == null && linksFile == null) {
            throw new IllegalArgumentException();
        } else {
            FileInputStream fis = new FileInputStream(pagesFile);
            InputStreamReader inStream = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(inStream);
            for (String data = reader.readLine(); data != null; data = reader.readLine()) {
                String[] info = data.split(" ");
                ArrayList<String> keywords = new ArrayList<String>();
                for (int i = 1; i < info.length; i++) {
                    keywords.add(info[i]);
                }
                addPage(info[0], keywords);
            }
            FileInputStream lis = new FileInputStream(linksFile);
            InputStreamReader linkStream = new InputStreamReader(lis);
            BufferedReader scanner = new BufferedReader(linkStream);
            for (String data = scanner.readLine(); data != null; data = scanner.readLine()) {
                String[] info = data.split(" ");
                int row = pages.indexOf(info[0]);
                int col = pages.indexOf(info[1]);
                edges[row][col] = 1;
            }
        }
        return null;

    }

    /**
     *
     * @param url used to add that url to the page
     * @param keywords used to add those keywords to the page
     * @throws IllegalArgumentException in case the url doesn't exist this
     * method will add a new webpage and then adjust the links also will update
     * page ranks after
     */
    public static void addPage(String url, ArrayList<String> keywords) throws IllegalArgumentException {
        if (pages.indexOf(url) == -1) {
            throw new IllegalArgumentException();
        } else if (url == null && keywords == null) {
            throw new IllegalArgumentException();
        } else {
            WebPage webpage = new WebPage(url, pages.size(), keywords);
            pages.add(webpage);
            for (int i = 0; i < pages.size(); i++) {
                edges[pages.size() - 1][i] = 0;
                edges[i][pages.size()] = 0;
            }
            updatePageRanks();

        }

    }

    /**
     *
     * @param source the source of the links
     * @param destination the destination of the source
     * @throws IllegalArgumentException in case neither destination or sources
     * exist this method adds link to the source and its destination then
     * updates page ranks
     */
    public void addLink(String source, String destination) throws IllegalArgumentException {
        if (pages.indexOf(destination) != -1 || pages.indexOf(source) != -1) {
            throw new IllegalArgumentException();
        } else {
            int rows = pages.indexOf(source);
            int col = pages.indexOf(destination);
            edges[rows][col] = 1;
            updatePageRanks();
        }
    }

    /**
     *
     * @param url to remove the page at that url if url only then it will
     * removePage change the edges matrix number location set index again after
     * it changes once removes a page updates page ranks after
     */
    public void removePage(String url) {
        if (url != null) {
            pages.remove(pages.indexOf(url));
            for (int i = 0; i < pages.size() + 1; i++) {
                edges[pages.size()][i] = -1;
                edges[i][pages.size()] = -1;
            }
            for (int i = 0; i < pages.size(); i++) {
                pages.get(i).setIndex(i);
            }
            updatePageRanks();
        }

    }

    /**
     *
     * @param source the source of the links
     * @param destination the destination of the source if source and
     * destination are null then only the method works row and col will be set
     * and then edges =0 then after update page ranks
     */
    public void removeLink(String source, String destination) {
        if (source != null && destination != null) {
            int rows = pages.indexOf(source);
            int col = pages.indexOf(destination);
            edges[rows][col] = 0;
            updatePageRanks();

        }
    }

    /**
     * this method updates the page ranks by going through a nested for loop and
     * adding to rank then set rank
     */
    public static void updatePageRanks() {
        int rank = 0;
        for (int col = 0; col < pages.size(); col++) {
            rank = 0;
            for (int rows = 0; rows < pages.size(); rows++) {
                rank += edges[rows][col];
            }
            pages.get(col).setRank(rank);
        }

    }

    /**
     * this method is based on printing through the order of ranks
     */
    public void printBasedOnRank() {
        System.out.format("%-8s%-20s%-15s%-18s%-25s\n", "Index", "URL", "PageRank", "Links", "Keywords");
        System.out.println("--------------------------------------------------------------------------------------------------------");
        Collections.sort(pages, new RankComparator());
        for (int i = 0; i < pages.size(); i++) {
            System.out.format("%-8s%-24s%-11s%-18s%-25s\n", pages.get(i).getIndex(), pages.get(i).getURL(), pages.get(i).getRank(), edges[i][i], pages.get(i).getKeywords());
        }
    }

    /**
     * this method is based on printing through the order of index
     */
    public void printBasedOnIndex() {
        System.out.format("%-8s%-20s%-15s%-18s%-25s\n", "Index", "URL", "PageRank", "Links", "Keywords");
        System.out.println("--------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < pages.size(); i++) {
            System.out.format("%-8s%-24s%-11s%-18s%-25s\n", pages.get(i).getIndex(), pages.get(i).getURL(), pages.get(i).getRank(), edges[i][i], pages.get(i).getKeywords());
        }
    }

    /**
     * this method is based on printing through the order of url
     */
    public void printBasedOnURL() {
        System.out.format("%-8s%-20s%-15s%-18s%-25s\n", "Index", "URL", "PageRank", "Links", "Keywords");
        System.out.println("--------------------------------------------------------------------------------------------------------");
        Collections.sort(pages, new URLComparator());
        for (int i = 0; i < pages.size(); i++) {
            System.out.format("%-8s%-24s%-11s%-18s%-25s\n", pages.get(i).getIndex(), pages.get(i).getURL(), pages.get(i).getRank(), edges[i][i], pages.get(i).getKeywords());
        }
    }

    /**
     *
     * @param keyword will be used to search for those keywords will go through
     * the pages and find the urls and page rank of that keyword then print out
     * the search results in a table
     */
    public void Search(String keyword) {
        System.out.format("%-15s%-15s%-15s\n", "Rank", "PageRank", "URL");
        System.out.println("---------------------------------------------");
        for (int i = 0; i < pages.size(); i++) {
            for (String k : pages.get(i).getKeywords()) {
                if (k == keyword) {
                    System.out.format("%-15s%-15s%-15s\n", i, pages.get(i).getRank(), pages.get(i).getURL());
                }

            }
        }
    }

    /**
     * this method print out the header of table
     */
    public void printTable() {
        System.out.println("Index/tURL/tPageRank/tLinks/tKeywords");
        System.out.println("----------------------------------------------------------------");

    }
}
