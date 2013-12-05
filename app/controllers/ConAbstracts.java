package controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static play.data.Form.form;
import play.mvc.*;
import models.*;
import views.html.conabstract.*;

@Security.Authenticated(Secured.class)
public class ConAbstracts extends Controller {
	
	public static Result add() {
		ConAbstract newAbstract = ConAbstract.create(
				"New Abstract", 
				"", 
				"", 
				"", 
				new Date(),
				"", 
				request().username()
	    );
	    return ok(item.render(newAbstract));
	}
	
	public static Result rename(Long conabstract) {
	    if (Secured.ownes(conabstract)) {
	        return ok(
	        		ConAbstract.rename(
	        		conabstract,
	                form().bindFromRequest().get("title")
	            )
	        );
	    } else {
	        return forbidden();
	    }
	}
	
	public static Result delete(Long conabstract) {
	    if(Secured.ownes(conabstract)) {
	    	ConAbstract.find.ref(conabstract).delete();
	        return ok();
	    } else {
	        return forbidden();
	    }
	}
}