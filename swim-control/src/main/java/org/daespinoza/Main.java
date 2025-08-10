package org.daespinoza;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.*;
import org.daespinoza.database.MongoDatabaseSingleton;
import org.daespinoza.model.Professor;
import org.daespinoza.model.Swimmer;
import org.daespinoza.model.SwimmingLesson;
import org.daespinoza.model.Transaction;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        MongoDatabaseSingleton db = MongoDatabaseSingleton.getNoSQLInstance();

        System.out.println("Base de datos activa: " + db.getDatabase().getName());

        insertarDatosPrueba(db);

    }

    private static void insertarDatosPrueba(MongoDatabaseSingleton db) {
        Random rnd = new Random();

        // Crear y guardar 5 Profesores
        for (int i = 1; i <= 5; i++) {
            Professor prof = new Professor("Profesor " + i, "555-000" + i, "prof" + i + "@mail.com", "Notas prof " + i);
            db.insert(prof);
        }

        // Crear y guardar 5 Nadadores
        for (int i = 1; i <= 5; i++) {
            Swimmer swimmer = new Swimmer("Nadador " + i, "555-100" + i, "swimmer" + i + "@mail.com", "Notas nadador " + i);
            db.insert(swimmer);
        }

        // Para crear SwimmingLessons y Transactions necesitamos IDs de nadadores,
        // entonces primero recargamos la lista de nadadores con IDs asignados por Mongo
        List<Swimmer> nadadoresGuardados = db.getAllSwimmers();

        // Crear y guardar 5 Lecciones de Natación (asignando random un nadador)
        for (int i = 1; i <= 5; i++) {
            Swimmer nadador = nadadoresGuardados.get(rnd.nextInt(nadadoresGuardados.size()));
            LocalDate fecha = LocalDate.of(2025, rnd.nextInt(5) + 2, rnd.nextInt(28) + 1); // Feb-Jun 2025
            LocalTime hora = LocalTime.of(rnd.nextInt(8) + 8, 0); // Horas entre 8AM y 3PM
            SwimmingLesson lesson = new SwimmingLesson(nadador.getId(), fecha, hora, "Tarjeta", "Lección prueba " + i);
            db.insert(lesson);
        }

        // Crear y guardar 5 Transacciones (random con nadadores y tipos INGRESO o EGRESO)
        for (int i = 1; i <= 5; i++) {
            Swimmer nadador = nadadoresGuardados.get(rnd.nextInt(nadadoresGuardados.size()));
            LocalDate fecha = LocalDate.of(2025, rnd.nextInt(5) + 2, rnd.nextInt(28) + 1);
            int tipo = rnd.nextBoolean() ? 1 : 2; // 1 = INGRESO, 2 = EGRESO
            Transaction trans = new Transaction(
                    rnd.nextDouble() * 100 + 20, // monto aleatorio entre 20 y 120
                    tipo,
                    "Transacción prueba " + i,
                    fecha
            );
            trans.setSwimmerId(nadador.getId());
            db.insert(trans);
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