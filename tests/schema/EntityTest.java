package schema;

import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: kmarazo
 * Date: 11/24/14
 * Time: 12:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class EntityTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testGetNumberOfInstances() throws Exception {
        try{
            Entity entity = new Entity("entity", 5);
            assert(5==entity.getNumberOfInstances());

            entity = new Entity("entity", -1);
            assert(-1==entity.getNumberOfInstances());
        }
        catch(Exception e){

        }

    }

    @Test
    public void testToString() throws Exception {

    }

    @Test
    public void testHashCode() throws Exception {

    }

    @Test
    public void testEquals() throws Exception {

    }
}
