import com.apssouza.eventsourcing.aggregates.EmailAggregate;
import com.apssouza.eventsourcing.aggregates.EmailState;
import com.apssouza.eventsourcing.commands.EmailCreateCommand;
import com.apssouza.eventsourcing.commands.EmailDeleteCommand;
import com.apssouza.eventsourcing.commands.EmailDeliveryCommand;
import com.apssouza.eventsourcing.commands.EmailSendCommand;
import com.apssouza.infra.AppEvent;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apssouza
 */
public class EmailAggregateTest {
    
    public EmailAggregateTest() {
    }

    @Before
    public void setUp() {
    }

    /**
     * Test of create method, of class EmailAggregate.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        EmailCreateCommand command = null;
        EmailAggregate instance = null;
        EmailAggregate expResult = null;
        EmailAggregate result = instance.create(command);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of send method, of class EmailAggregate.
     */
    @Test
    public void testSend() throws Exception {
        System.out.println("send");
        EmailSendCommand command = null;
        EmailAggregate instance = null;
        EmailAggregate expResult = null;
        EmailAggregate result = instance.send(command);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delivery method, of class EmailAggregate.
     */
    @Test
    public void testDelivery() throws Exception {
        System.out.println("delivery");
        EmailDeliveryCommand command = null;
        EmailAggregate instance = null;
        EmailAggregate expResult = null;
        EmailAggregate result = instance.delivery(command);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class EmailAggregate.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        EmailDeleteCommand command = null;
        EmailAggregate instance = null;
        EmailAggregate expResult = null;
        EmailAggregate result = instance.delete(command);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of from method, of class EmailAggregate.
     */
    @Test
    public void testFrom() {
        System.out.println("from");
        String uuid = "";
        List<AppEvent> history = null;
        EmailAggregate expResult = null;
        EmailAggregate result = EmailAggregate.from(uuid, history);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUncommittedChanges method, of class EmailAggregate.
     */
    @Test
    public void testGetUncommittedChanges() {
        System.out.println("getUncommittedChanges");
        EmailAggregate instance = null;
        List<AppEvent> expResult = null;
        List<AppEvent> result = instance.getUncommittedChanges();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of markChangesAsCommitted method, of class EmailAggregate.
     */
    @Test
    public void testMarkChangesAsCommitted() {
        System.out.println("markChangesAsCommitted");
        EmailAggregate instance = null;
        EmailAggregate expResult = null;
        EmailAggregate result = instance.markChangesAsCommitted();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getState method, of class EmailAggregate.
     */
    @Test
    public void testGetState() {
        System.out.println("getState");
        EmailAggregate instance = null;
        EmailState expResult = null;
//        EmailState result = instance.getState();
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
