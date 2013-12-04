package controllers;

import models.User;

import org.junit.*;

import static org.junit.Assert.*;

import java.util.*;

import play.mvc.*;
import play.libs.*;
import play.test.*;
import static play.test.Helpers.*;

import com.avaje.ebean.Ebean;
import com.google.common.collect.ImmutableMap;

public class LoginTest extends WithApplication {
	@Before
	public void setUp() {
		start(fakeApplication(inMemoryDatabase(), fakeGlobal()));
		if (Ebean.find(User.class).findRowCount() == 0) {

			@SuppressWarnings("unchecked")
			Map<String, List<Object>> all = (Map<String, List<Object>>) Yaml
					.load("initial-data.yml");

			// Insert users first
			Ebean.save(all.get("users"));

			// Insert questions
			Ebean.save(all.get("questions"));

		}
	}

	@Test
	public void authenticateSuccess() {
		Result result = callAction(
				controllers.routes.ref.Application.authenticate(),
				fakeRequest().withFormUrlEncodedBody(
						ImmutableMap.of("email", "chuck@gmail.com", "password",
								"secret")));
		assertEquals(303, status(result));
		assertEquals("chuck@gmail.com", session(result).get("email"));
	}
	
	@Test
	public void authenticateFailure() {
	    Result result = callAction(
	        controllers.routes.ref.Application.authenticate(),
	        fakeRequest().withFormUrlEncodedBody(ImmutableMap.of(
	            "email", "chuck@gmail.com",
	            "password", "badpassword"))
	    );
	    assertEquals(400, status(result));
	    assertNull(session(result).get("email"));
	}
	
	@Test
	public void authenticated() {
	    Result result = callAction(
	        controllers.routes.ref.Application.index(),
	        fakeRequest().withSession("email", "chuck@gmail.com")
	    );
	    assertEquals(200, status(result));
	}
	
	@Test
	public void notAuthenticated() {
	    Result result = callAction(
	        controllers.routes.ref.Application.index(),
	        fakeRequest()
	    );
	    assertEquals(303, status(result));
	    assertEquals("/login", header("Location", result));
	}
	
}
