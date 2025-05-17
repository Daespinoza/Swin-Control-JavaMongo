package org.daespinoza;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.*;
import org.daespinoza.database.MongoDatabaseSingleton;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        MongoDatabaseSingleton instance = MongoDatabaseSingleton.getNoSQLInstance();
        instance.getAll().forEach(System.out::println);

    }
}