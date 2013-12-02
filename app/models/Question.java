package models;

import java.util.*;

import javax.persistence.*;

import play.db.ebean.*;
import play.db.ebean.Model.Finder;

@Entity
public class Question extends Model {

    @Id
    public Long id;
    public String fullQuestion;
    public int difficultyLevel;
    public int avgTime;
    public int type;
    public List<String> possibleAnswers = new ArrayList<String>(); 
    @ManyToOne
    public User owner;

    public Question(String fullQuestion, int difficultyLevel, int avgTime, int type, List<String> possibleAnswers, User owner) {
        this.fullQuestion = fullQuestion;
        this.difficultyLevel = difficultyLevel;
        this.avgTime = avgTime;
        this.type = type;
        this.possibleAnswers = possibleAnswers;
        this.owner = owner;
    }

    public static Model.Finder<Long,Question> find = new Finder(Long.class, Question.class);

    public static Question create(String fullQuestion, int difficultyLevel, int avgTime, int type, List<String> possibleAnswers, String owner) {
    	Question question = new Question(fullQuestion, difficultyLevel, avgTime, type, possibleAnswers, User.find.ref(owner));
    	question.save();
        return question;
    }

    public static List<Question> findInvolving(String user) {
        return find.where()
            .eq("owner.email", user)
            .findList();
    }
}