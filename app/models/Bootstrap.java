package models;

import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

@OnApplicationStart
public class Bootstrap
        extends Job {

    @Override
    public void doJob()
    {
        System.out.println("Bootstrap");
        // Check if the database is empty
        if (Election.count() == 0) {
            Fixtures.loadModels("/models/initial-data.yml");
            //new Election(1, "election1").save();
        }
    }

}
