import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by annakertesz on 3/20/17.
 */
public class RoutePlannerTest {


    public List<Character> defineDefaultCase() throws Exception {
        RoutePlanner routePlanner = new RoutePlanner(
                new char[][]{{'u'}, {'v', 'w'}, {'w', 'z'}, {'x', 'u'}, {'y', 'v'}, {'z'}, {'v', 'z'}});
        return routePlanner.findDestinationOrder();
    }

    @Test(expected = IllegalArgumentException.class)
    public void EmptyListThrowsException() throws Exception {
        new RoutePlanner(new char[][]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void routeWithEmptyDestinationItemThrowsException() throws Exception {
        RoutePlanner routePlanner = new RoutePlanner(
                new char[][]{{'x', 'u'}, {'w'}, {}});
        routePlanner.findDestinationOrder();
    }

    @Test(expected = IllegalArgumentException.class)
    public void destinationWithMoreThanTwoElementThrowsException() throws Exception {
        RoutePlanner routePlanner = new RoutePlanner(
                new char[][]{{'x', 'u', 'z'}, {'w'}, {'s'}});
        routePlanner.findDestinationOrder();
    }

    @Test(expected = IllegalArgumentException.class)
    public void nonExistingDestinationThrowsException() throws Exception {
        RoutePlanner routePlanner = new RoutePlanner(
                new char[][]{{'x', 'a'}, {'w'}, {'z'}});
        routePlanner.findDestinationOrder();
    }

    @Test
    public void routeWithOneDestinationWorks() throws Exception {
        RoutePlanner routePlanner = new RoutePlanner(new char[][]{{'x'}});
        List<Character> expected = new ArrayList<>(Arrays.asList('x'));
        assertEquals(expected, routePlanner.findDestinationOrder());
    }

    @Test
    public void routeWithoutRelationsWorks() throws Exception {
        RoutePlanner routePlanner = new RoutePlanner(new char[][]{{'x'}, {'y'}, {'z'}});
        assertThat(routePlanner.findDestinationOrder(), containsInAnyOrder('x', 'y', 'z'));
    }

    @Test
    public void routeContainsGoodNumberOfDestinations() throws Exception {
        List<Character> finalRoute = defineDefaultCase();
        assertEquals(finalRoute.size(), 6);
    }


    @Test
    public void sampleRouteWithManyDestinationsWorks() throws Exception {
        List<Character> finalRoute = defineDefaultCase();
        assertThat(finalRoute, containsInAnyOrder('u', 'v', 'w', 'x', 'y', 'z'));
        assertThat(finalRoute.indexOf('v'), greaterThan(finalRoute.indexOf('w')));
        assertThat(finalRoute.indexOf('w'), greaterThan(finalRoute.indexOf('z')));
        assertThat(finalRoute.indexOf('x'), greaterThan(finalRoute.indexOf('u')));
        assertThat(finalRoute.indexOf('y'), greaterThan(finalRoute.indexOf('v')));
    }

    @Test(expected = IllegalArgumentException.class)
    public void routeWithLoopThrowsException() throws Exception {
        RoutePlanner routePlanner = new RoutePlanner(
                new char[][]{{'x', 'u'}, {'w', 'x'}, {'v'}, {'u', 'y'}, {'z', 'w'}, {'y', 'z'}});
        routePlanner.findDestinationOrder();
    }

    @Test
    public void routeWithBranchingWorks() throws Exception {
        RoutePlanner routePlanner = new RoutePlanner(
                new char[][]{{'x', 'y'}, {'y', 'z'}, {'u', 'y'}, {'z'}});
        List<Character> finalRoute = routePlanner.findDestinationOrder();
        assertThat(finalRoute, containsInAnyOrder('z', 'y', 'x', 'u'));
        assertThat(finalRoute.indexOf('x'), greaterThan(finalRoute.indexOf('y')));
        assertThat(finalRoute.indexOf('y'), greaterThan(finalRoute.indexOf('z')));
        assertThat(finalRoute.indexOf('u'), greaterThan(finalRoute.indexOf('y')));
    }

    @Test
    public void routeWithAForkWorks() throws Exception {
        RoutePlanner routePlanner = new RoutePlanner(
                new char[][]{{'u'}, {'v', 'w'}, {'w', 'z'}, {'x', 'u'}, {'y', 'v'}, {'z'}, {'v', 'z'}});
        List<Character> finalRoute = routePlanner.findDestinationOrder();
        assertThat(finalRoute, containsInAnyOrder('u', 'v', 'w', 'x', 'y', 'z'));
        assertThat(finalRoute.indexOf('v'), greaterThan(finalRoute.indexOf('w')));
        assertThat(finalRoute.indexOf('w'), greaterThan(finalRoute.indexOf('z')));
        assertThat(finalRoute.indexOf('x'), greaterThan(finalRoute.indexOf('u')));
        assertThat(finalRoute.indexOf('y'), greaterThan(finalRoute.indexOf('v')));
    }




}