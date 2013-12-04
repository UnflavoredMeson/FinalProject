package controllers;

import org.junit.*;

import static org.junit.Assert.*;

import java.util.*;

import models.*;
import play.mvc.*;
import play.libs.*;
import play.test.*;
import static play.test.Helpers.*;

import com.avaje.ebean.Ebean;
import com.google.common.collect.ImmutableMap;

public class QuestionsTest extends WithApplication {
	@Before
	public void setUp() {
		start(fakeApplication(inMemoryDatabase(), fakeGlobal()));
		if (Ebean.find(User.class).findRowCount() == 0) {

			@SuppressWarnings("unchecked")
			Map<String, List<Object>> all = (Map<String, List<Object>>) Yaml.load("initial-data.yml");

			// Insert users first
			Ebean.save(all.get("users"));

			// Insert questions
			//Ebean.save(all.get("questions"));

		}
	}
	
	@Test
	public void newQuestion() {
	    Result result = callAction(
	        controllers.routes.ref.Questions.add(),
	        fakeRequest().withSession("email", "chuck@gmail.com")
	    );
	    assertEquals(200, status(result));
	    Question question = Question.find.where()
	        .eq("fullQuestion", "New Question").findUnique();
	    assertNotNull(question);
	    assertEquals("New Question", question.fullQuestion);
	    assertEquals("chuck@gmail.com", question.owner.email);
	}
	
	@Test
	public void renameQuestion() {
	    long id = Question.find.where()
	        .eq("owner.email", "chuck@gmail.com")
	        .eq("fullQuestion", "New Question").findUnique().id;
	    Result result = callAction(
	        controllers.routes.ref.Questions.rename(id),
	        fakeRequest().withSession("email", "chuck@gmail.com")
	            .withFormUrlEncodedBody(ImmutableMap.of("newQuestion", "Is it real?"))
	    );
	    assertEquals(200, status(result));
	    assertEquals("Is it real?", Question.find.byId(id).fullQuestion);
	}

}