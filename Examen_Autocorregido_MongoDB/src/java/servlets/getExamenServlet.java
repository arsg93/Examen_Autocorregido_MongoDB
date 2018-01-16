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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public class getExamenServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public void init() throws ServletException {
        try {
            //Connect
            MongoClientURI uri = new MongoClientURI(
                    "mongodb+srv://backend:frontend@exam-r6hnd.mongodb.net/test");
            MongoClient mongoClient = new MongoClient(uri);
            //Create Database
            MongoDatabase database = mongoClient.getDatabase("Examen");

            //Create collection
            MongoCollection<Document> collExA = database.getCollection("Examen A");
            collExA.drop();
            collExA = database.getCollection("Examen A");

            //Create document
            Document pregunta = new Document()
                    .append("tipo", "selectS")
                    .append("titulo", "¿Dónde nos podemos encontrar a Link?");

            Document respuesta = new Document("respuesta", 2)
                    .append("option", "En Dragon Ball")
                    .append("option", "En el videojuego \"La leyenda de Link\"")
                    .append("option", "En el Super Smash Bros")
                    .append("option", "En Doraemon");
            pregunta.put("respuesta", respuesta);

            //Insert document
            collExA.insertOne(pregunta);
            mongoClient.close();

        } catch (Exception e) {

        }

    }

}
