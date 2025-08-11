package org.daespinoza;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.*;
import org.bson.conversions.Bson;
import org.daespinoza.database.MongoDatabaseSingleton;
import org.daespinoza.model.Professor;
import org.daespinoza.model.Swimmer;
import org.daespinoza.model.SwimmingLesson;
import org.daespinoza.model.Transaction;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;
import static com.mongodb.client.model.Updates.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        MongoDatabaseSingleton dbSingleton = MongoDatabaseSingleton.getNoSQLInstance();
        MongoDatabase database = dbSingleton.getDatabase();

        MongoCollection<Document> professorsCollection = database.getCollection("professors");
        MongoCollection<Document> swimmersCollection = database.getCollection("swimmers");
        MongoCollection<Document> swimmingLessonsCollection = database.getCollection("swimming_lessons");
        MongoCollection<Document> transactionsCollection = database.getCollection("transactions");

        testProfessorCRUD(professorsCollection);
        testSwimmerCRUD(swimmersCollection);
        testSwimmingLessonCRUD(swimmingLessonsCollection);
        testTransactionCRUD(transactionsCollection);
        imprimirDatos(dbSingleton);
    }

    private static void testProfessorCRUD(MongoCollection<Document> collection) {
        // 1️⃣ Crear 5 profesores
        for (int i = 1; i <= 5; i++) {
            Professor prof = new Professor(
                    "Profesor " + i,
                    "555-000" + i,
                    "prof" + i + "@correo.com",
                    "Notas del profesor " + i
            );

            Document doc = new Document("name", prof.getName())
                    .append("phone", prof.getPhone())
                    .append("email", prof.getEmail())
                    .append("notes", prof.getNotes());

            collection.insertOne(doc);
        }
        System.out.println("✅ Profesores insertados.\n");

        // 2️⃣ Leer todos
        System.out.println("📚 Lista de profesores:");
        for (Document doc : collection.find()) {
            System.out.println(doc.toJson());
        }
        System.out.println();

        // 3️⃣ Modificar uno
        String nombreOriginal = "Profesor 3";
        collection.updateOne(eq("name", nombreOriginal), set("name", "Profesor Modificado"));
        System.out.println("✏️ Profesor modificado.\n");

        // 4️⃣ Leer de nuevo
        System.out.println("📚 Lista después de modificar:");
        for (Document doc : collection.find()) {
            System.out.println(doc.toJson());
        }
        System.out.println();

        // 5️⃣ Borrar uno
        String nombreBorrar = "Profesor Modificado";
        collection.deleteOne(eq("name", nombreBorrar));
        System.out.println("🗑️ Profesor borrado.\n");

        // 6️⃣ Lista final
        System.out.println("📚 Lista final:");
        for (Document doc : collection.find()) {
            System.out.println(doc.toJson());
        }
    }

    private static void testSwimmerCRUD(MongoCollection<Document> collection) {
        Random random = new Random();

        // 1️⃣ Crear 5 nadadores
        for (int i = 1; i <= 5; i++) {
            Swimmer swimmer = new Swimmer(
                    "Nadador " + i,
                    "666-000" + i,
                    "nadador" + i + "@correo.com",
                    "Notas del nadador " + i
            );

            Document doc = new Document("name", swimmer.getName())
                    .append("phone", swimmer.getPhone())
                    .append("email", swimmer.getEmail())
                    .append("notes", swimmer.getNotes())
                    .append("reg_date", swimmer.getReg_date().toString());

            collection.insertOne(doc);
        }
        System.out.println("✅ Nadadores insertados.\n");

        // 2️⃣ Leer todos
        System.out.println("📚 Lista inicial de nadadores:");
        for (Document doc : collection.find()) {
            System.out.println(doc.toJson());
        }
        System.out.println();

        // 3️⃣ Modificar uno aleatorio
        int randomIndex = random.nextInt(5) + 1; // entre 1 y 5
        String originalName = "Nadador " + randomIndex;
        String newName = originalName + " (Actualizado)";
        collection.updateOne(eq("name", originalName), set("name", newName));
        System.out.println("✏️ Modificado: " + originalName + " -> " + newName + "\n");

        // 4️⃣ Leer después de modificar
        System.out.println("📚 Lista después de modificar:");
        for (Document doc : collection.find()) {
            System.out.println(doc.toJson());
        }
        System.out.println();

        // 5️⃣ Borrar otro aleatorio
        int deleteIndex;
        do {
            deleteIndex = random.nextInt(5) + 1;
        } while (deleteIndex == randomIndex); // evitar borrar el que acabamos de modificar

        String deleteName = "Nadador " + deleteIndex;
        collection.deleteOne(eq("name", deleteName));
        System.out.println("🗑️ Borrado: " + deleteName + "\n");

        // 6️⃣ Lista final
        System.out.println("📚 Lista final:");
        for (Document doc : collection.find()) {
            System.out.println(doc.toJson());
        }
    }

    private static void testSwimmingLessonCRUD(MongoCollection<Document> collection) {
        Random random = new Random();

        // 1️⃣ Crear 5 lecciones de natación
        for (int i = 1; i <= 5; i++) {
            SwimmingLesson lesson = new SwimmingLesson(
                    "swimmer_" + i,
                    LocalDate.now().plusDays(i),
                    LocalTime.of(10 + i, 0),
                    (i % 2 == 0) ? "Tarjeta" : "Efectivo",
                    "Notas de la lección " + i
            );

            Document doc = new Document("swimmer_id", lesson.getSwimmer_id())
                    .append("lesson_date", lesson.getLesson_date().toString())
                    .append("lesson_time", lesson.getLesson_time().toString())
                    .append("status", lesson.getStatus())
                    .append("paid", lesson.getPaid())
                    .append("paymentType", lesson.getPaymentType())
                    .append("notes", lesson.getNotes());

            collection.insertOne(doc);
        }
        System.out.println("✅ Lecciones insertadas.\n");

        // 2️⃣ Leer todas
        System.out.println("📚 Lista inicial de lecciones:");
        for (Document doc : collection.find()) {
            System.out.println(doc.toJson());
        }
        System.out.println();

        // 3️⃣ Modificar una lección aleatoria
        int randomIndex = random.nextInt(5) + 1; // entre 1 y 5
        String originalSwimmerId = "swimmer_" + randomIndex;
        String newStatus = "COMPLETADA";
        boolean newPaidStatus = true;

        collection.updateOne(eq("swimmer_id", originalSwimmerId),
                combine(
                        set("status", newStatus),
                        set("paid", newPaidStatus),
                        set("notes", "Actualizado automáticamente")
                )
        );
        System.out.println("✏️ Modificada lección de: " + originalSwimmerId + "\n");

        // 4️⃣ Lista después de modificar
        System.out.println("📚 Lista después de modificar:");
        for (Document doc : collection.find()) {
            System.out.println(doc.toJson());
        }
        System.out.println();

        // 5️⃣ Borrar otra lección aleatoria (que no sea la modificada)
        int deleteIndex;
        do {
            deleteIndex = random.nextInt(5) + 1;
        } while (deleteIndex == randomIndex);

        String deleteSwimmerId = "swimmer_" + deleteIndex;
        collection.deleteOne(eq("swimmer_id", deleteSwimmerId));
        System.out.println("🗑️ Borrada lección de: " + deleteSwimmerId + "\n");

        // 6️⃣ Lista final
        System.out.println("📚 Lista final:");
        for (Document doc : collection.find()) {
            System.out.println(doc.toJson());
        }
    }

    private static void testTransactionCRUD(MongoCollection<Document> collection) {

        System.out.println("\n=== CREAR Transactions ===");
        List<Transaction> transactionsToInsert = List.of(
                new Transaction(150.0, 1, "Pago mensualidad", LocalDate.now()),
                new Transaction(75.5, 2, "Compra material", LocalDate.now()),
                new Transaction(200.0, 1, "Pago clase privada", LocalDate.now())
        );

        List<Document> docs = new ArrayList<>();
        for (Transaction t : transactionsToInsert) {
            Document doc = new Document("type", t.getType())
                    .append("amount", t.getAmount())
                    .append("description", t.getDescription())
                    .append("relatedLessonIds", t.getRelatedLessonIds())
                    .append("dateIssued", t.getDateIssued().toString())
                    .append("datePaid", t.getDatePaid() != null ? t.getDatePaid().toString() : null)
                    .append("swimmerId", t.getSwimmerId());
            docs.add(doc);
        }
        collection.insertMany(docs);
        System.out.println("Insertados " + docs.size() + " registros.");

        System.out.println("\n=== LEER Transactions ===");
        List<Document> allDocs = collection.find().into(new ArrayList<>());
        for (Document d : allDocs) {
            System.out.println(d.toJson());
        }

        if (!allDocs.isEmpty()) {
            System.out.println("\n=== MODIFICAR Transaction ===");
            Document randomDoc = allDocs.get(new Random().nextInt(allDocs.size()));

            Bson filter = eq("_id", randomDoc.getObjectId("_id"));
            Bson updates = combine(
                    set("description", "Actualizado: " + randomDoc.getString("description")),
                    set("amount", randomDoc.getDouble("amount") + 50)
            );

            collection.updateOne(filter, updates);
            System.out.println("Registro actualizado con _id: " + randomDoc.getObjectId("_id"));

            System.out.println("\n=== LEER después de actualización ===");
            allDocs = collection.find().into(new ArrayList<>());
            for (Document d : allDocs) {
                System.out.println(d.toJson());
            }

            System.out.println("\n=== BORRAR Transaction ===");
            Document docToDelete = allDocs.get(new Random().nextInt(allDocs.size()));
            collection.deleteOne(eq("_id", docToDelete.getObjectId("_id")));
            System.out.println("Borrado registro con _id: " + docToDelete.getObjectId("_id"));

            System.out.println("\n=== LEER final ===");
            allDocs = collection.find().into(new ArrayList<>());
            for (Document d : allDocs) {
                System.out.println(d.toJson());
            }
        }
    }

    private static void imprimirDatos(MongoDatabaseSingleton db) {
        System.out.println("\nProfesores:");
        for (Professor p : db.getAllProfessors()) {
            System.out.println(p);
        }

        System.out.println("\nNadadores:");
        for (Swimmer s : db.getAllSwimmers()) {
            System.out.println(s);
        }

        System.out.println("\nLecciones:");
        for (SwimmingLesson l : db.getAllLessons()) {
            System.out.println(l);
        }

        System.out.println("\nTransacciones:");
        for (Transaction t : db.getAllTransactions()) {
            System.out.println(t);
        }
    }
}