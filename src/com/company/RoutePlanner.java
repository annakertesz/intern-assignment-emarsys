package com.company;

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
    }

    public List<Character> createFinalRoute() {
        for (char[] destinationPair : destinations) {
            finalRoute.add(destinationPair[0]);
        }
        for (char[] destinationPair : destinations) {
            if (destinationPair.length == 2) {
                int indexBefore = finalRoute.indexOf(destinationPair[1]);
                finalRoute.remove(destinationPair[0]);
                finalRoute.add(indexBefore, destinationPair[0]);
            }
        }

        return finalRoute;
    }
}
