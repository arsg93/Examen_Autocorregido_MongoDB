/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.bson.Document;

/**
 *
 * @author admin
 */
public class getExamsNames extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //Connect
            MongoClientURI uri = new MongoClientURI(
                    "mongodb+srv://backend:frontend@exam-r6hnd.mongodb.net/test");
            MongoClient mongoClient = new MongoClient(uri);
            //Create Database
            MongoDatabase database = mongoClient.getDatabase("Examen");
            
                    
            //Create collection
            MongoCollection<Document> collectionA = database.getCollection("Examen A");
            MongoCollection<Document> collectionB = database.getCollection("Examen B");
            MongoCollection<Document> collectionC = database.getCollection("Examen C");
            //Create document
            Document pregunta = new Document("pregunta", "select-1")
                    .append("tipo", "select")
                    .append("titulo", "¿Cuántos centímetros tiene un metro?");

            Document respuesta = new Document("h", 28)
                    .append("0", 10)
                    .append("1", 100)
                    .append("2", 100);
            pregunta.put("respuesta", respuesta);

            //Insert document
            collectionA.insertOne(pregunta);

            //Search document
            Document myDoc = collectionA.find().first();
            

            //Send document
            
            
            response.setContentType("application/json");
            try (PrintWriter out = response.getWriter()) {
                out.println(myDoc.toJson());

            }
        } catch (Exception e) {
            Map<String, String> emess = new HashMap<>();
            emess.put("error", e.toString());

            Gson gson = new GsonBuilder().create();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            PrintWriter pw = response.getWriter();
            pw.println(gson.toJson(emess));

        }
    }

  

}
