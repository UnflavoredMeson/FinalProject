package models;

import java.util.*;

import javax.persistence.*;

import play.db.ebean.*;

@Entity
public class ConAbstract extends Model {

	@Id
	public Long id;
	public String title;
    public String cite;
    public String link;
    public String url;
    public Date publicationDate;
    public String abstractBody;
	public User owner;

	public ConAbstract(String title, String cite, String link, String url, Date publicationDate, String abstractBody, User owner) {
		this.title = title;
		this.cite = cite;
		this.link = link;
		this.url = url;
		this.publicationDate = publicationDate;
		this.abstractBody = abstractBody;
		this.owner = owner;
	}

	public static Model.Finder<Long, ConAbstract> find = new Finder<Long, ConAbstract>(
			Long.class, ConAbstract.class);

	public static ConAbstract create(String title, String cite, String link, String url, Date publicationDate, String abstractBody, String owner) {
		ConAbstract conabstract = new ConAbstract(title, cite, link, url, publicationDate, abstractBody, User.find.ref(owner));
		conabstract.save();
		return conabstract;
	}

	public static List<ConAbstract> findQuestionsOwnedBy(String owner) {
		return find.where().eq("owner.email", owner).findList();
	}
	public static boolean ownes(Long conabstract, String user) {
	    return find.where()
	        .eq("owner.email", user)
	        .eq("id", conabstract)
	        .findRowCount() > 0;
	}

	public static String rename(Long conAbstractId, String newTitle) {
		ConAbstract conabstract = find.ref(conAbstractId);
		conabstract.title = newTitle;
		conabstract.update();
	    return newTitle;
	}
	
}
