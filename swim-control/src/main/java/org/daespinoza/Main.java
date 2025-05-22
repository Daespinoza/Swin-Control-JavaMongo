package org.daespinoza;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.*;
import org.daespinoza.database.MongoDatabaseSingleton;
import org.daespinoza.model.Swimmer;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        MongoDatabaseSingleton db = MongoDatabaseSingleton.getNoSQLInstance();
        List<Swimmer> swimmers = db.getAll();

        System.out.println("Base de datos activa: " + db.getDatabase().getName());

        for (Swimmer swimmer : swimmers) {
            System.out.println(swimmer.toString());
        }

        Swimmer newSwimmer = new Swimmer("Daniel Espinoza", "8888-8888", "daniel001@example.com");
        db.insert(newSwimmer);

        System.out.println("... Nadador registrado.");

        swimmers = db.getAll();
        for (Swimmer swimmer : swimmers) {
            System.out.println(swimmer.toString());
        }

        // mod
        for (Swimmer swimmer : swimmers) {
            if (swimmer.getEmail().equals("daniel001@example.com")) {

                swimmer.setPhone("8999-9999");

                db.update(swimmer);
                System.out.println("... Teléfono actualizado para: " + swimmer.getName());
            }
        }

        swimmers = db.getAll();
        for (Swimmer swimmer : swimmers) {
            System.out.println(swimmer.toString());
        }
    }
}