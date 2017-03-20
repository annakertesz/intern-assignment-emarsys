import java.util.ArrayList;
import java.util.List;

/**
 * Created by annakertesz on 3/20/17.
 */
public class RoutePlanner {

    char[][] destinations;
    List<Character> finalRoute = new ArrayList<Character>();

    public RoutePlanner(char[][] destinations) {

        this.destinations = destinations;
        for (char[] destinationPair : destinations) {
            finalRoute.add(destinationPair[0]);
        }
    }

    public List<Character> createFinalRoute() {
        System.out.println("unordered destinations: " + finalRoute);
        for (char[] destinationPair : destinations) {

//            System.out.println("item: " + destinationPair[0] + " " + destinationPair[1]);
            if (destinationPair.length == 2) {

                int indexToPlace = finalRoute.indexOf(Character.valueOf(destinationPair[0]));
                System.out.println("I remove " + destinationPair[1] + " and place to index " + indexToPlace);
                if (indexToPlace <= finalRoute.indexOf(Character.valueOf(destinationPair[1]))) {
                    finalRoute.remove(Character.valueOf(destinationPair[1]));
                    finalRoute.add(indexToPlace, destinationPair[1]);
                }
                System.out.println(finalRoute + "\n");

            }
        }

//        for (char[] destinationPair : destinations) {
//            System.out.println(destinationPair);
//            if (destinationPair.length == 2) {
//                int indexBefore = finalRoute.indexOf(destinationPair[0]);
//                finalRoute.remove(Character.valueOf(destinationPair[1]));
//                System.out.println("I removed " + Character.valueOf(destinationPair[1]));
//                finalRoute.add(indexBefore, destinationPair[1]);
//            }
//            System.out.println(finalRoute + "\n");
//        }
//        System.out.println("");
//        System.out.println("final:");
//        System.out.println(finalRoute);
        return finalRoute;
    }
}
