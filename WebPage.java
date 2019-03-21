//Soorya Suresh
//soorya.suresh@stonybrook.edu
//Data Structures

package searchengine;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Soorya Suresh
 */
public class WebPage {

    private String url;
    private int index;
    private int rank;
    private ArrayList<String> keywords;

    /**
     *
     * @param url will take in the url
     * @param index will take the index
     * @param keywords will take the keywords this constructor sets it the this
     * value
     */
    public WebPage(String url, int index, ArrayList<String> keywords) {//change this up as you go alone
        this.url = url;
        this.index = index;
        this.rank = rank;
        this.keywords = keywords;

    }

    /**
     *
     * @param rank and set rank to this
     */
    public void setRank(int rank) {
        this.rank = rank;
    }

    /**
     *
     * @return the index
     */
    public int getIndex() {
        return this.index;
    }

    /**
     *
     * @param index and set index to this
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     *
     * @return the rank
     */
    public int getRank() {
        return this.rank;
    }

    /**
     *
     * @return the url
     */
    public String getURL() {
        return this.url;
    }

    /**
     *
     * @return the keywords
     */
    public ArrayList<String> getKeywords() {
        return this.keywords;
    }

    /**
     *
     * @return a String of the index,url,rank,keywords in organized format
     */
    public String toString() {
        System.out.printf("%-15s%-15s%-15s&-15s\n", index, url, rank, keywords);
        return " ";
    }

}
