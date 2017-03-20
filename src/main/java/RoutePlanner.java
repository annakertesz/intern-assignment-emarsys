import java.util.ArrayList;
import java.util.List;

/**
 * Created by annakertesz on 3/20/17.
 */
public class RoutePlanner {

    char[][] destinationList;
    List<Character> finalRoute;


    public RoutePlanner(char[][] destinationList) throws IllegalArgumentException {
        if (destinationList.length < 1) throw new IllegalArgumentException("You have to give at least one destination");
        this.destinationList = destinationList;
        this.finalRoute = new ArrayList<Character>();
    }


    public List<Character> findDestinationOrder() throws IllegalArgumentException {
        createUnorderedList();
        for (char[] destinationPair : destinationList) {
            if (destinationPair.length == 2) {
                moveDestination(destinationPair);
            }
        }
        if (detectContradiction()) throw new IllegalArgumentException("There is contradiction in route plan");
        return finalRoute;
    }


    private void createUnorderedList() throws IllegalArgumentException {
        for (char[] destinationPair : destinationList) {
            if (destinationPair.length > 2)
                throw new IllegalArgumentException("The destination pair can contain maximum 2 elements");
            else if (destinationPair.length < 1) throw new IllegalArgumentException("Empty destination!");
            if (!finalRoute.contains(destinationPair[0])) finalRoute.add(destinationPair[0]);
        }
    }

    private void moveDestination(char[] destinationPair) {
        // Based on the examples I assumed that every destination must be at least once on the left side.
        if (!finalRoute.contains(Character.valueOf(destinationPair[1]))) throw new IllegalArgumentException("Relation is invalid!");
        int indexToPlace = finalRoute.indexOf(Character.valueOf(destinationPair[0]));
        if (indexToPlace <= finalRoute.indexOf(Character.valueOf(destinationPair[1]))) {
            finalRoute.remove(Character.valueOf(destinationPair[1]));
            finalRoute.add(indexToPlace, destinationPair[1]);
        }
    }

    private boolean detectContradiction() {
        for (char[] destinationPair : destinationList) {
            if (destinationPair.length == 2) {
                Character firstValue = Character.valueOf(destinationPair[0]);
                Character secondValue = Character.valueOf(destinationPair[1]);
                if (finalRoute.indexOf(firstValue) < finalRoute.indexOf(secondValue)) return true;

            }
        }
        return false;
    }

}
