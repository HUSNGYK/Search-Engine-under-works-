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
public abstract class IndexComparator implements Comparator {

    /**
     *
     * @param o1 the object used to set to webpage
     * @param o2 the object used to set to webpage
     * @return the webpage with the greater index
     */
    public int compare(Object o1, Object o2) {
        WebPage w1 = (WebPage) o1;
        WebPage w2 = (WebPage) o2;
        if (w1.getIndex() == w2.getIndex()) {
            return 0;
        } else if (w1.getIndex() > w2.getIndex()) {
            return 1;
        }
        return -1;

    }

}
