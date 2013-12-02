package models;

import java.util.ArrayList;
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
    
    @Test
    public void findProjectsInvolving() {
        new User("chuck@gmail.com", "Bob", "secret").save();
        new User("susan@gmail.com", "Jane", "secret").save();
        
        List<String> answers = new ArrayList<String>();
        answers.add("A");
        answers.add("B");
        answers.add("C");
        
        Question.create("Here is a simple question for you?", 1, 1, 60, null, "chuck@gmail.com");
        Question.create("Non multiple choice example", 2, 2, 25, answers, "susan@gmail.com");

        List<Question> results = Question.findInvolving("chuck@gmail.com");
        assertEquals(1, results.size());
        assertEquals("Here is a simple question for you?", results.get(0).fullQuestion);
    }
}