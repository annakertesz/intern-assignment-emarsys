import java.util.ArrayList;
import java.util.List;

/**
 * Created by annakertesz on 3/20/17.
 */
public class RoutePlanner {

    char[][] destinations;
    List<Character> finalRoute;

    public RoutePlanner(char[][] destinations) throws IllegalArgumentException{

        if (destinations.length < 1) throw new IllegalArgumentException("You have to give at least one destination");
        this.destinations = destinations;
        this.finalRoute = new ArrayList<Character>();

    }

    public List<Character> createFinalRoute() throws IllegalArgumentException {
        addItemsToFinalRoute(destinations);
        for (char[] destinationPair : destinations) {
            if (destinationPair.length == 2) {
                moveDestination(destinationPair);
            }
        }
        return finalRoute;
    }

    private void addItemsToFinalRoute(char[][] destinations) throws IllegalArgumentException{
        for (char[] destinationPair : destinations) {
            if (destinationPair.length > 2) throw new IllegalArgumentException("The destination can contains maximum 1 relation");
            if (destinationPair.length < 1) throw new IllegalArgumentException("Empty destination!");
            finalRoute.add(destinationPair[0]);
        }
        if (detectContradiction()) throw new IllegalArgumentException("There is contradiction in route plan");
    }

    private void moveDestination(char[] destinationPair) {
        if (!finalRoute.contains(Character.valueOf(destinationPair[1]))) throw new IllegalArgumentException("Relation is invalid!");
        int indexToPlace = finalRoute.indexOf(Character.valueOf(destinationPair[0]));
        if (indexToPlace <= finalRoute.indexOf(Character.valueOf(destinationPair[1]))) {
            finalRoute.remove(Character.valueOf(destinationPair[1]));
            finalRoute.add(indexToPlace, destinationPair[1]);

        }
        System.out.println(finalRoute);
    }

    private boolean detectContradiction() {
        for (char[] destinationPair : destinations) {
            if (destinationPair.length == 2) {
                Character firstValue = Character.valueOf(destinationPair[0]);
                Character secondValue = Character.valueOf(destinationPair[1]);
                if (finalRoute.indexOf(firstValue) < finalRoute.indexOf(secondValue)) return true;
            }
        }
        return false;
    }
}
