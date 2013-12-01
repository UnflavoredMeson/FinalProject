package models;

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
    
    @Test
    public void createAndRetrieveUser() {
        new User("chuck@gmail.com", "Chuck", "altoona").save();
        User chuck = User.find.where().eq("email", "chuck@gmail.com").findUnique();
        assertNotNull(chuck);
        assertEquals("Chuck", chuck.name);
    }
    
    @Test
    public void tryAuthenticateUser() {
        new User("chuck@gmail.com", "Chuck", "altoona").save();
        
        assertNotNull(User.authenticate("chuck@gmail.com", "altoona"));
        assertNull(User.authenticate("user2@gmail.com", "badpassword"));
        assertNull(User.authenticate("user3@gmail.com", "secret"));
    }
}