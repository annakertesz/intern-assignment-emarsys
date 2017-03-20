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

    @Test
    public void finalRouteWithOneDestination() throws Exception {
        RoutePlanner routePlanner = new RoutePlanner(new char[][]{{'x'}});
        List<Character> expected = new ArrayList<>(Arrays.asList('x'));
        assertEquals(expected, routePlanner.createFinalRoute());
    }

    @Test
    public void finalRouteContainsSameNumOfDestinations() throws Exception {
        RoutePlanner routePlanner = new RoutePlanner(new char[][]{{'x'}, {'y', 'x'}, {'z'}});
        assertEquals(routePlanner.createFinalRoute().size(), 3);
    }

    @Test
    public void finalRouteWithThreeDestinationWithoutRelations() throws Exception {
        RoutePlanner routePlanner = new RoutePlanner(new char[][]{{'x'}, {'y'}, {'z'}});
        assertThat(routePlanner.createFinalRoute(), containsInAnyOrder('x', 'y', 'z'));
    }

    @Test
    public void finalRouteWithThreeDestinationWithRelations() throws Exception {
        RoutePlanner routePlanner = new RoutePlanner(new char[][]{{'x'}, {'y', 'x'}, {'z'}});
        List<Character> finalRoute = routePlanner.createFinalRoute();
        assertThat(finalRoute, containsInAnyOrder('x', 'y', 'z'));
        assertThat(finalRoute.indexOf('x'), greaterThan(finalRoute.indexOf('y')) );
    }

    @Test
    public void finalRouteWithManyDestinationsCheckRelations() throws Exception {
        RoutePlanner routePlanner = new RoutePlanner(
                new char[][]{{'u'}, {'v', 'w'}, {'w', 'z'}, {'x', 'u'}, {'y', 'v'}, {'z'}});
        List<Character> finalRoute = routePlanner.createFinalRoute();
        assertThat(finalRoute, containsInAnyOrder('u', 'v', 'w', 'x', 'y', 'z'));
        assertThat(finalRoute.indexOf('v'), greaterThan(finalRoute.indexOf('w')) );
        assertThat(finalRoute.indexOf('w'), greaterThan(finalRoute.indexOf('z')) );
        assertThat(finalRoute.indexOf('x'), greaterThan(finalRoute.indexOf('u')) );
        assertThat(finalRoute.indexOf('y'), greaterThan(finalRoute.indexOf('v')) );
    }


}