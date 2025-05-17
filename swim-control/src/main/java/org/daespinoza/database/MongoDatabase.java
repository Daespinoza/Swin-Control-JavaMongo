package org.daespinoza.database;

//import com.mongodb.connection.*;

import java.util.List;

@SuppressWarnings("unused")
public class MongoDatabase implements DatabaseInterface {

    /*
    final private Datastore datastore;
    private static MongoDatabase database;

    private MongoDatabase() {
        final Morphia morphia = new Morphia();
        morphia.mapPackage("models");
        MongoClientURI uri = new MongoClientURI("mongodb+srv://<db_username>:<db_password>@swimcontrol-cluster00.otktrtc.mongodb.net/?retryWrites=true&w=majority&appName=SwimControl-Cluster00");
        MongoClient mongoClient = new MongoClient(uri);
        this.datastore = morphia.createDatastore(mongoClient, "heroku_8prjmqnp");

    }

    public static MongoDatabase getNoSQLInstance() {
        if(database == null) {
            database = new DatabaseNoSQL();
        }
        return database;
    }
    */

    @Override
    public <T> List<T> getAll() {
        return List.of();
    }

}
