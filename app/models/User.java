package models;

import javax.persistence.*;

import play.db.ebean.*;

import org.mindrot.jbcrypt.BCrypt;

@Entity
public class User extends Model {

    @Id
    public String email;
    public String name;
    public String passwordHash;
    
    public User(String email, String name, String passwordHash) {
      this.email = email;
      this.name = name;
      this.passwordHash = passwordHash;
    }

    public static Finder<String,User> find = new Finder<String,User>(
        String.class, User.class
    ); 
    
    public static User authenticate(String email, String password) {
        return find.where().eq("email", email)
            .eq("passwordHash", password).findUnique();
    }
    
//    public static User authenticate(String email, String password) {
//    	User user = User.find.where().eq("email", email).findUnique();
//    	
//    	if (user == null){
//    		return null;
//    	} else if (checkPassword(password, user.passwordHash)) {
//        	return find.where().eq("email", email).findUnique();
//        } else {
//        	return null;
//        }
//    }
//    
//    public static String createPassword(String clearString) {
//        return BCrypt.hashpw(clearString, BCrypt.gensalt());
//    }
//    
//    public static boolean checkPassword(String candidate, String encryptedPassword) {
//        if (candidate == null) {
//            return false;
//        }
//        if (encryptedPassword == null) {
//            return false;
//        }
//        return BCrypt.checkpw(candidate, encryptedPassword);
//    }
}