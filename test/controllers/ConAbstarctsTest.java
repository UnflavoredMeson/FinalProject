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

public class ConAbstarctsTest extends WithApplication {
	@Before
	public void setUp() {
		start(fakeApplication(inMemoryDatabase(), fakeGlobal()));
		if (Ebean.find(User.class).findRowCount() == 0) {

			@SuppressWarnings("unchecked")
			Map<String, List<Object>> all = (Map<String, List<Object>>) Yaml.load("initial-data.yml");

			// Insert users first
			Ebean.save(all.get("users"));

            // Insert abstracts
            Ebean.save(all.get("abstracts"));

		}
	}
		
		@Test
		public void newAbstract() {
		    Result result = callAction(
		        controllers.routes.ref.ConAbstracts.add(),
		        fakeRequest().withSession("email", "bob@example.com")
		    );
		    assertEquals(200, status(result));
		    ConAbstract conabstract = ConAbstract.find.where()
		        .eq("title", "New Absrtact").findUnique();
		    assertNotNull(conabstract);
		    assertEquals("New Absrtact", conabstract.title);
		    assertEquals("bob@example.com", conabstract.owner.email);
		}
}