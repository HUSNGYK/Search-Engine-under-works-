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
public class RankComparator implements Comparator {
    /**
     * 
     * @param o1 the object represents webpage
     * @param o2 the object represents webpage
     * @return the webpage with greater ranks
     */
    public int compare(Object o1, Object o2) {
        WebPage w1 = (WebPage) o1;
        WebPage w2 = (WebPage) o2;
        if (w1.getRank() == w2.getRank()){
            return 0;
        }
        else if (w1.getRank()> w2.getRank()){
            return 1;
        }
        return -1;
        
    }
    
}
