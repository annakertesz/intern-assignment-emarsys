import org.junit.Before;
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

    List<Character> finalRoute;

    @Before
    public void setUp() throws Exception {
        RoutePlanner routePlanner = new RoutePlanner(
                new char[][]{{'u'}, {'v', 'w'}, {'w', 'z'}, {'x', 'u'}, {'y', 'v'}, {'z'}});
        finalRoute = routePlanner.findDestinationOrder();
    }

    @Test(expected = IllegalArgumentException.class)
    public void findRouteWithEmptyList() throws Exception {
        RoutePlanner routePlanner = new RoutePlanner(
                new char[][]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void finalRouteWithAnEmptyDestinationItem() throws Exception {
        RoutePlanner routePlanner = new RoutePlanner(
                new char[][]{{'x', 'u'}, {'w'}, {}});
        routePlanner.findDestinationOrder();
    }

    @Test(expected = IllegalArgumentException.class)
    public void finalRouteWithMoreThanOneRelation() throws Exception {
        RoutePlanner routePlanner = new RoutePlanner(
                new char[][]{{'x', 'u', 'z'}, {'w'}, {'s'}});
        routePlanner.findDestinationOrder();
    }

    @Test(expected = IllegalArgumentException.class)
    public void finalRouteWithInvalidRelation() throws Exception {
        RoutePlanner routePlanner = new RoutePlanner(
                new char[][]{{'x', 'a'}, {'w'}, {'z'}});
        routePlanner.findDestinationOrder();
    }

    @Test
    public void finalRouteWithOneDestination() throws Exception {
        RoutePlanner routePlanner = new RoutePlanner(new char[][]{{'x'}});
        List<Character> expected = new ArrayList<>(Arrays.asList('x'));
        assertEquals(expected, routePlanner.findDestinationOrder());
    }

    @Test
    public void finalRouteWithThreeDestinationWithoutRelations() throws Exception {
        RoutePlanner routePlanner = new RoutePlanner(new char[][]{{'x'}, {'y'}, {'z'}});
        assertThat(routePlanner.findDestinationOrder(), containsInAnyOrder('x', 'y', 'z'));
    }

    @Test
    public void finalRouteContainsSameNumOfDestinations() throws Exception {
        assertEquals(finalRoute.size(), 6);
    }


    @Test
    public void finalRouteWithManyDestinationsCheckRelations() throws Exception {
        assertThat(finalRoute, containsInAnyOrder('u', 'v', 'w', 'x', 'y', 'z'));
        assertThat(finalRoute.indexOf('v'), greaterThan(finalRoute.indexOf('w')));
        assertThat(finalRoute.indexOf('w'), greaterThan(finalRoute.indexOf('z')));
        assertThat(finalRoute.indexOf('x'), greaterThan(finalRoute.indexOf('u')));
        assertThat(finalRoute.indexOf('y'), greaterThan(finalRoute.indexOf('v')));
    }

    @Test(expected = IllegalArgumentException.class)
    public void finalRouteWithContradictoryRelations() throws Exception {
        RoutePlanner routePlanner = new RoutePlanner(
                new char[][]{{'x', 'u'}, {'w', 'x'}, {'v'}, {'u', 'y'}, {'z', 'w'}, {'y', 'z'}});
        routePlanner.findDestinationOrder();
    }


}