package controllers;

import java.util.*;

import static play.data.Form.form;
import play.mvc.*;
import views.html.question.*;
import models.*;

@Security.Authenticated(Secured.class)
public class Questions extends Controller {
	
	public static Result add() {
		
	    Question newQuestion = Question.create(
	        "New Question",
	        1,
	        60,
	        1,
	        null,
	        request().username()
	    );
	    return ok(item.render(newQuestion));
	}
	
	public static Result rename(Long question) {
	        return ok(
	            Question.changeQuestion(
	            		question,
	                form().bindFromRequest().get("newQuestion")
	            )
	        );
	}
	
	public static Result delete(Long question) {
	        Question.find.ref(question).delete();
	        return ok();
	    }
	
}