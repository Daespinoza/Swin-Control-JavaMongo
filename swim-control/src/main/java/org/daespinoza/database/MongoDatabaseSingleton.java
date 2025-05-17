package org.daespinoza.database;

import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.model.Sorts;
import org.bson.*;
import com.mongodb.client.model.Projections;
import org.bson.conversions.Bson;
import org.daespinoza.AppConfig;

import java.util.List;

public class MongoDatabaseSingleton implements DatabaseInterface {

    final private MongoDatabase database;
    private static MongoDatabaseSingleton instance;

    private MongoDatabaseSingleton() {
        MongoDatabase database = null;
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(AppConfig.get("db.url")))
                .serverApi(serverApi)
                .build();
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            try {
                // Send a ping to confirm a successful connection
                database = mongoClient.getDatabase("admin");
                database.runCommand(new Document("ping", 1));
                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
            } catch (MongoException e) {
                //noinspection CallToPrintStackTrace
                e.printStackTrace();
            }
            finally {
                this.database = database;
            }
        }
    }

    public static MongoDatabaseSingleton getNoSQLInstance() {
        if(instance == null) {
            instance = new MongoDatabaseSingleton();
        }
        return instance;
    }

    @Override
    public <T> List<T> getAll() {

        String message = "Null";

        MongoCollection<Document> collection = database.getCollection("swimmers");

        Bson projectionFields = Projections.fields(
                Projections.include("Name", "Phone", "email"),
                Projections.excludeId());

        FindIterable<Document> docs = collection.find()
                .projection(projectionFields)
                .sort(Sorts.ascending("Name"));


        Document doc = collection.find()
                .projection(projectionFields)
                .sort(Sorts.ascending("Name"))
                .first();

        // @TODO: move this for main
        if (doc == null) {
            System.out.println("No results found.");
        } else {
            System.out.println("Find !! ");
        }

        return doc.values();
    }
}
