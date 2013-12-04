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
    public void findQuestionsInvolving() {
        User chuck = new User("chuck@gmail.com", "Chuck", "secret");
        chuck.save();

        Question question = Question.create("New", 1, 1, 1, null, "chuck@gmail.com");

        List<Question> results = Question.findQuestionsOwnedBy("chuck@gmail.com");
        assertEquals(1, results.size());
        assertEquals("New", results.get(0).fullQuestion);
    }
}