package org.daespinoza.database;

import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.model.Sorts;
import org.bson.*;
import com.mongodb.client.model.Projections;
import org.bson.conversions.Bson;
import org.daespinoza.AppConfig;

import java.util.ArrayList;
import java.util.List;
import org.daespinoza.model.Swimmer;

public class MongoDatabaseSingleton implements DatabaseInterface {

    private static MongoDatabaseSingleton instance;
    private MongoClient mongoClient;
    private MongoDatabase database;

    private MongoDatabaseSingleton() {
        establishConnection();
    }

    public static MongoDatabaseSingleton getNoSQLInstance() {
        if (instance == null) {
            instance = new MongoDatabaseSingleton();
        }
        return instance;
    }

    private void establishConnection() {
        String connectionString = AppConfig.get("db.url");

        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();

        mongoClient = MongoClients.create(settings);

        try {
            database = mongoClient.getDatabase("Swim-Control");
            database.runCommand(new Document("ping", 1));
            System.out.println("✔ Connected to MongoDB successfully.");
        } catch (MongoException e) {
            e.printStackTrace();
            System.out.println("❌ Error connecting to MongoDB.");
        }
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("🔌 Connection closed.");
        }
    }

    @Override
    public List<Swimmer> getAll() {

        List<Swimmer> swimmers = new ArrayList<>();

        MongoCollection<Document> collection = database.getCollection("swimmers");

        FindIterable<Document> docs = collection.find()
                .sort(Sorts.ascending("Name"));

        for (Document doc : docs) {
            String id = doc.getObjectId("_id").toHexString();
            String name = doc.getString("Name");
            String phone = doc.getString("Phone");
            String email = doc.getString("email");

            swimmers.add(new Swimmer(id, name, phone, email));
        }

        if (swimmers.isEmpty()) {
            System.out.println("No results found.");
        } else {
            System.out.println("Found " + swimmers.size() + " swimmers.");
        }

        return swimmers;
    }

    @Override
    public <T> void insert(T item) {
        if (item instanceof Swimmer) {
            MongoCollection<Document> collection = database.getCollection("swimmers");
            Swimmer swimmer = (Swimmer) item;

            Document doc = new Document("Name", swimmer.getName())
                    .append("Phone", swimmer.getPhone())
                    .append("email", swimmer.getEmail());

            collection.insertOne(doc);
            System.out.println("Swimmer inserted!");
        }
    }

    @Override
    public <T> void update(T item){

    }

    @Override
    public <T> void delete(T item){

    }
}
