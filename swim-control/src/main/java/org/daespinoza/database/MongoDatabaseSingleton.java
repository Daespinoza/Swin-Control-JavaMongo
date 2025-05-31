package org.daespinoza.database;

import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.*;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.daespinoza.AppConfig;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.daespinoza.model.Professor;
import org.daespinoza.model.Swimmer;
import org.daespinoza.model.SwimmingLesson;
import org.daespinoza.model.Transaction;

public class MongoDatabaseSingleton implements DatabaseInterface {

    private static MongoDatabaseSingleton instance;
    private MongoClient mongoClient;
    private MongoDatabase database;

    private List<Swimmer> swimmersCache = new ArrayList<>();
    private List<Professor> professorsCache = new ArrayList<>();
    private List<SwimmingLesson> lessonsCache = new ArrayList<>();
    private List<Transaction> transactionsCache = new ArrayList<>();

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
    public void getAll(){
        swimmersCache = getAllSwimmers();
        professorsCache = getAllProfessors();
        lessonsCache = getAllLessons();
        transactionsCache = getAllTransactions();

        System.out.println("✅ All data was cached:");
        System.out.println("Swimmers: " + swimmersCache.size());
        System.out.println("Professors: " + professorsCache.size());
        System.out.println("Lessons: " + lessonsCache.size());
        System.out.println("Transactions: " + transactionsCache.size());
    }

    public List<Swimmer> getAllSwimmers() {
        List<Swimmer> swimmers = new ArrayList<>();
        MongoCollection<Document> collection = database.getCollection("swimmers");

        for (Document doc : collection.find().sort(Sorts.ascending("Name"))) {
            String id = doc.getObjectId("_id").toHexString();
            String name = doc.getString("Name");
            String phone = doc.getString("Phone");
            String email = doc.getString("email");

            swimmers.add(new Swimmer(id, name, phone, email));
        }

        return swimmers;
    }

    public List<Professor> getAllProfessors() {
        List<Professor> professors = new ArrayList<>();
        MongoCollection<Document> collection = database.getCollection("professors");

        for (Document doc : collection.find().sort(Sorts.ascending("Name"))) {
            String id = doc.getObjectId("_id").toHexString();
            String name = doc.getString("Name");
            String phone = doc.getString("Phone");
            String email = doc.getString("email");

            professors.add(new Professor(id, name, phone, email));
        }

        return professors;
    }

    public List<SwimmingLesson> getAllLessons() {
        List<SwimmingLesson> lessons = new ArrayList<>();
        MongoCollection<Document> collection = database.getCollection("lessons");

        for (Document doc : collection.find().sort(Sorts.ascending("lessonDate"))) {
            String swimmerId = doc.getString("swimmerId");
            LocalDate date = LocalDate.parse(doc.getString("lessonDate"));
            LocalTime time = LocalTime.parse(doc.getString("lessonTime"));
            String paymentType = doc.getString("paymentType");
            String notes = doc.getString("notes");

            SwimmingLesson lesson = new SwimmingLesson(swimmerId, date, time, paymentType, notes);
            lessons.add(lesson);
        }

        return lessons;
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        MongoCollection<Document> collection = database.getCollection("transactions");

        for (Document doc : collection.find().sort(Sorts.ascending("dateIssued"))) {
            double amount = doc.getDouble("amount");
            String typeStr = doc.getString("type");
            String description = doc.getString("description");
            LocalDate dateIssued = LocalDate.parse(doc.getString("dateIssued"));
            LocalDate datePaid = doc.getString("datePaid") != null ? LocalDate.parse(doc.getString("datePaid")) : null;
            String swimmerId = doc.getString("swimmerId");

            Transaction transaction = new Transaction(amount, typeStr.equals("INGRESO") ? 1 : 2, description, dateIssued);
            transaction.setDatePaid(datePaid);
            transaction.setSwimmerId(swimmerId);

            List<String> relatedLessonIds = doc.getList("relatedLessonIds", String.class);
            if (relatedLessonIds != null) transaction.setRelatedLessonIds(relatedLessonIds);

            transactions.add(transaction);
        }

        return transactions;
    }

    public List<Swimmer> getSwimmersCache() {
        return swimmersCache;
    }

    public List<Professor> getProfessorsCache() {
        return professorsCache;
    }

    public List<SwimmingLesson> getLessonsCache() {
        return lessonsCache;
    }

    public List<Transaction> getTransactionsCache() {
        return transactionsCache;
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

        else if (item instanceof Professor) {
            MongoCollection<Document> collection = database.getCollection("professors");
            Professor professor = (Professor) item;

            Document doc = new Document("Name", professor.getName())
                    .append("Phone", professor.getPhone())
                    .append("email", professor.getEmail());

            collection.insertOne(doc);
            System.out.println("Professor inserted!");
        }

        else if (item instanceof SwimmingLesson) {
            MongoCollection<Document> collection = database.getCollection("lessons");
            SwimmingLesson lesson = (SwimmingLesson) item;

            Document doc = new Document("swimmerId", lesson.getId())
                    .append("professorId", lesson.getId())
                    .append("lessonDate", lesson.getLesson_date().toString())
                    .append("price", lesson.getPaid());

            collection.insertOne(doc);
            System.out.println("Lesson inserted!");
        }

        else if (item instanceof Transaction) {
            MongoCollection<Document> collection = database.getCollection("transactions");
            Transaction transaction = (Transaction) item;

            Document doc = new Document("amount", transaction.getAmount())
                    .append("type", transaction.getType())
                    .append("description", transaction.getDescription())
                    .append("dateIssued", transaction.getDateIssued().toString())
                    .append("datePaid", transaction.getDatePaid() != null ? transaction.getDatePaid().toString() : null)
                    .append("swimmerId", transaction.getSwimmerId());

            // Si hay lecciones relacionadas, las agregamos como lista de strings
            if (transaction.getRelatedLessonIds() != null && !transaction.getRelatedLessonIds().isEmpty()) {
                doc.append("relatedLessonIds", transaction.getRelatedLessonIds());
            }

            collection.insertOne(doc);
            System.out.println("Transaction inserted!");
        }

        else {
            System.out.println("⚠ No insert handler for class: " + item.getClass().getSimpleName());
        }
    }

    @Override
    public <T> void update(T item) {
        if (item instanceof Swimmer) {
            MongoCollection<Document> collection = database.getCollection("swimmers");
            Swimmer swimmer = (Swimmer) item;

            Bson filter = Filters.eq("_id", new ObjectId(swimmer.getId()));
            Bson updates = Updates.combine(
                    Updates.set("Name", swimmer.getName()),
                    Updates.set("Phone", swimmer.getPhone()),
                    Updates.set("email", swimmer.getEmail())
            );

            UpdateResult result = collection.updateOne(filter, updates);
            System.out.println("Swimmer updated!");
        }

        else if (item instanceof Professor) {
            MongoCollection<Document> collection = database.getCollection("professors");
            Professor professor = (Professor) item;

            Bson filter = Filters.eq("_id", new ObjectId(professor.getId()));
            Bson updates = Updates.combine(
                    Updates.set("Name", professor.getName()),
                    Updates.set("Phone", professor.getPhone()),
                    Updates.set("email", professor.getEmail())
            );

            UpdateResult result = collection.updateOne(filter, updates);
            System.out.println("Professor updated!");
        }

        else if (item instanceof SwimmingLesson) {
            MongoCollection<Document> collection = database.getCollection("lessons");
            SwimmingLesson lesson = (SwimmingLesson) item;

            Bson filter = Filters.eq("_id", new ObjectId(lesson.getId()));
            Bson updates = Updates.combine(
                    Updates.set("swimmerId", lesson.getSwimmer_id()),
                    Updates.set("lessonDate", lesson.getLesson_date().toString()),
                    Updates.set("lessonTime", lesson.getLesson_time().toString()),
                    Updates.set("status", lesson.getStatus()),
                    Updates.set("paid", lesson.getPaid()),
                    Updates.set("paymentType", lesson.getPaymentType()),
                    Updates.set("notes", lesson.getNotes())
            );

            UpdateResult result = collection.updateOne(filter, updates);
            System.out.println("Lesson updated!");
        }

        else if (item instanceof Transaction) {
            MongoCollection<Document> collection = database.getCollection("transactions");
            Transaction transaction = (Transaction) item;

            Bson filter = Filters.eq("_id", new ObjectId(transaction.getId()));
            List<Bson> updateFields = new ArrayList<>();

            updateFields.add(Updates.set("amount", transaction.getAmount()));
            updateFields.add(Updates.set("type", transaction.getType()));
            updateFields.add(Updates.set("description", transaction.getDescription()));
            updateFields.add(Updates.set("dateIssued", transaction.getDateIssued().toString()));
            updateFields.add(Updates.set("datePaid", transaction.getDatePaid() != null ? transaction.getDatePaid().toString() : null));
            updateFields.add(Updates.set("swimmerId", transaction.getSwimmerId()));
            updateFields.add(Updates.set("relatedLessonIds", transaction.getRelatedLessonIds()));

            Bson updates = Updates.combine(updateFields);
            UpdateResult result = collection.updateOne(filter, updates);
            System.out.println("Transaction updated!");
        }

        else {
            System.out.println("⚠ No update handler for class: " + item.getClass().getSimpleName());
        }
    }


    @Override
    public <T> void delete(T item) {
        if (item == null) {
            System.out.println("⚠ Cannot delete null object.");
            return;
        }

        String id = null;
        String collectionName = null;

        if (item instanceof Swimmer) {
            id = ((Swimmer) item).getId();
            collectionName = "swimmers";
        }
        else if (item instanceof Professor) {
            id = ((Professor) item).getId();
            collectionName = "professors";
        }
        else if (item instanceof SwimmingLesson) {
            id = ((SwimmingLesson) item).getId();
            collectionName = "lessons";
        }
        else if (item instanceof Transaction) {
            id = ((Transaction) item).getId();
            collectionName = "transactions";
        }
        else {
            System.out.println("⚠ No delete handler for class: " + item.getClass().getSimpleName());
            return;
        }

        if (id == null || id.isEmpty()) {
            System.out.println("❌ ID is required to delete " + item.getClass().getSimpleName() + ".");
            return;
        }

        MongoCollection<Document> collection = database.getCollection(collectionName);
        Bson filter = Filters.eq("_id", new ObjectId(id));

        DeleteResult result = collection.deleteOne(filter);

        if (result.getDeletedCount() > 0) {
            System.out.println(item.getClass().getSimpleName() + " deleted successfully.");
        } else {
            System.out.println("❌ No " + item.getClass().getSimpleName().toLowerCase() + " was deleted. Check the ID.");
        }
    }

}
