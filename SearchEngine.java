//Soorya Suresh
//soorya.suresh@stonybrook.edu
//Data Structures

package searchengine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.Spliterator;

/**
 *
 * @author Soorya Suresh
 */
public class SearchEngine {

    public static final String PAGES_FILE = "pages.txt";
    public static final String LINKS_FILE = "links.txt";
    private static WebGraph web;

    /**
     * @param args the command line arguments create a scanner to take in user
     * inputs first get the web to build from files for both pages and links
     * Then the menu is printed out for the user to see from there a while loop
     * is started which only ends once user puts Q AP - takes in url and
     * keywords from users and then takes the addPage(url,keywords) method RP -
     * takes url and then takes removePage(url) and removes the page AL - takes
     * the source and destination that user puts in and
     * addLink(source,destination) to add link RL - takes source and destination
     * and the user puts in removeLink(source,destination) and remove that link
     * P - will print the table but will ask in what order I - is in index order
     * so uses the IndexComparator and prints with table U - is in url order so
     * uses the URLComparator and prints with table R - is in rank order so uses
     * the RankComparator and prints the table S - will search the web for the
     * keywords and print the table in Search method
     */
    public static void main(String[] args) throws IllegalArgumentException, IOException {
        Scanner reader = new Scanner(System.in);
        web.buildFromFiles(PAGES_FILE, LINKS_FILE);
        System.out.println("Loading WebGraph data...");
        System.out.println("Success!");
        System.out.println("Menu:");
        System.out.println("(AP) - Add a new page to the graph.\n"
                + "(RP) - Remove a page from the graph.\n"
                + "(AL) - Add a link between pages in the graph.\n"
                + "(RL) - Remove a link between pages in the graph.\n"
                + "(P) - Print the graph.\n"
                + "(S) - Search for pages with a keyword.\n"
                + "(Q) - Quit.");
        System.out.print("Please select an option: ");
        String input = reader.nextLine();
        while (!input.equals("Q") || !input.equals("q")) {
            if (input.equals("AP") || input.equals("ap")) {
                System.out.print("Enter a URL: ");
                String url = reader.nextLine();
                System.out.println();
                System.out.print("Enter keywords(space-separated): ");
                String keys = reader.nextLine();
                String[] keywords = keys.split(" ");
                ArrayList<String> words = new ArrayList<String>();
                for (int i = 0; i < keywords.length; i++) {
                    words.add(keywords[i]);
                }
                web.addPage(url, words);
                if (input.equals("RP") || input.equals("rp")) {
                    System.out.print("Enter a URL: ");
                    String urll = reader.nextLine();
                    System.out.println();
                    System.out.println(urll + " has been removed from the graph!");
                    web.removePage(urll);

                }
                if (input.equals("AL") || input.equals("al")) {
                    System.out.print("Enter a source URL: ");
                    String source = reader.nextLine();
                    System.out.println();
                    System.out.print("Enter a destination URL: ");
                    String destination = reader.nextLine();
                    System.out.println();
                    System.out.println("Link successfully added from " + source + " to " + destination + "!");
                    web.addLink(source, destination);
                }
                if (input.equals("RL") || input.equals("rl")) {
                    System.out.print("Enter a source URL: ");
                    String source = reader.nextLine();
                    System.out.println();
                    System.out.print("Enter a destination URL: ");
                    String destination = reader.nextLine();
                    System.out.println();
                    System.out.println("Link successfully removed from " + source + " to " + destination + "!");
                    web.removeLink(source, destination);
                }
                if (input.equals("P") || input.equals("p")) {
                    System.out.println("(I) Sort based on index (ASC)\n"
                            + " (U) Sort based on URL (ASC)\n"
                            + " (R) Sort based on rank (DSC)");
                    System.out.print("Please select an option: ");
                    input = reader.nextLine();
                    if (input.equals("I") || input.equals("i")) {
                        web.printBasedOnRank();
                    }
                    if (input.equals("U") || input.equals("u")) {
                        web.printBasedOnURL();
                    }
                    if (input.equals("R") || input.equals("r")) {
                        web.printBasedOnRank();
                    }
                }
                if (input.equals("S") || input.equals("s")) {
                    System.out.print("Search keyword: ");
                    String keyword = reader.nextLine();
                    System.out.println();
                    web.Search(keyword);
                }
                System.out.print("Please select an option: ");
                input = reader.nextLine();

            }

        }
        System.out.println("Goodbye");

    }
}
