import play.test.*;
import play.jobs.*;
import models.*;

@OnApplicationStart
public class Bootstrap extends Job {
    public void doJob() {
        // Load default data if the database is empty
        if(User.count() == 0) {
            new User("lola@gmail.com","lolap","lola").save();
	    new User("pedro@gmail.com","pedrop","pedro").save();

	    new Producte("patata",2,5).save();
	    new Producte("ceba figueres",4,20).save();
	    new Producte("rovellons",20,0).save();
            
        }

        
    }
}
