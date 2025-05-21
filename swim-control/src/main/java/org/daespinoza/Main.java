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
            System.out.println(swimmer);
        }
    }
}