package models;

import java.util.List;

import models.*;

import org.junit.*;

import static org.junit.Assert.*;
import play.test.WithApplication;
import static play.test.Helpers.*;
public class ModelsTest extends WithApplication {
    @Before
    public void setUp() {
        start(fakeApplication(inMemoryDatabase()));
    }
    
//    @Test
//    public void tryAuthenticateUser() {
//        new User("chuck@gmail.com", "Chuck", "secret").save();
//        
//        assertNotNull(User.authenticate("chuck@gmail.com", "secret"));
//        assertNull(User.authenticate("chuck@gmail.com", "badpassword"));
//        assertNull(User.authenticate("susan@gmail.com", "secret"));
//    }
    
    @Test
    public void findQuestionsOwnedBy() {
        //User chuck = new User("chuck@gmail.com", "Chuck", "secret");
        //chuck.save();

        //ConAbstract conabstarct = ConAbstract.create("New", 1, 1, 1, null, "chuck@gmail.com");

        List<ConAbstract> results = ConAbstract.findQuestionsOwnedBy("chuck@gmail.com");
        assertEquals(2, results.size());
        assertEquals("Microwave Absorption in Percolating Metal-insulator Composites", results.get(0).title);
    }
}