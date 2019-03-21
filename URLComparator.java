//Soorya Suresh
//111458936
//soorya.suresh@stonybrook.edu
//Data Structures
//CSE 214
//R08 
//Johnny So
package searchengine;

import java.util.Comparator;

/**
 *
 * @author Soorya Suresh
 */
public class URLComparator implements Comparator {

    /**
     *
     * @param o1 the object that represents webpage
     * @param o2 the object that represents webpage
     * @return the webpage with the compareTo of url
     */
    public int compare(Object o1, Object o2) {
        WebPage w1 = (WebPage) o1;
        WebPage w2 = (WebPage) o2;
        return (w1.getURL().compareTo(w2.getURL()));

    }

}
