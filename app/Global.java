import play.*;
import play.libs.*;

import com.avaje.ebean.Ebean;

import models.*;

import java.util.*;

public class Global extends GlobalSettings {
    public static void insert(Application app) {
        if(Ebean.find(User.class).findRowCount() == 0) {
            
            @SuppressWarnings("unchecked")
							Map<String,List<Object>> all = (Map<String,List<Object>>)Yaml.load("initial-data.yml");

            // Insert users first
            Ebean.save(all.get("users"));

            // Insert projects
            Ebean.save(all.get("questions"));
        }
    }
}