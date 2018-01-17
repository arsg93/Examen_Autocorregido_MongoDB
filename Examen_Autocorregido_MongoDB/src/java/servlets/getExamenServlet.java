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
        //Connect
        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://backend:frontend@exam-r6hnd.mongodb.net/test");
        MongoClient mongoClient = new MongoClient(uri);
        //Create Database
        MongoDatabase database = mongoClient.getDatabase("Examen");

        //Create collection
        MongoCollection<Document> collExA = database.getCollection("Examen A");

        //Mete todos los documentos en una lista
        List<Document> ex = (List<Document>) collExA.find().into(
                new ArrayList<Document>());
        mongoClient.close();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(ex);
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            out.println(json);
        }

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

            //Insert preguntas
            collExA.insertOne(pregunta1ExA());
            collExA.insertOne(pregunta2ExA());
            collExA.insertOne(pregunta3ExA());
            collExA.insertOne(pregunta4ExA());
            collExA.insertOne(pregunta5ExA());
            collExA.insertOne(pregunta6ExA());
            collExA.insertOne(pregunta7ExA());
            collExA.insertOne(pregunta8ExA());
            collExA.insertOne(pregunta9ExA());
            collExA.insertOne(pregunta10ExA());

            mongoClient.close();

        } catch (Exception e) {

        }

    }

    private Document pregunta1ExA() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "radio")
                .append("titulo", "¿Dónde nos podemos encontrar a Link?");

        //Add repuestas correctas.
        Document correcta = new Document()
                .append("0", 2);
        pregunta.put("correcta", correcta);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "En Dragon Ball")
                .append("1", "En el videojuego \"La leyenda de Link\"")
                .append("2", "En el Super Smash Bros")
                .append("3", "En Doraemon");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

    private Document pregunta2ExA() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "radio")
                .append("titulo", "¿Cual de estos juegos no existe?");

        //Add repuestas correctas.
        Document correcta = new Document()
                .append("0", 1);
        pregunta.put("correcta", correcta);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "Resident Evil 7")
                .append("1", "Coll of Duty: Ages of Majorca")
                .append("2", "Pokemon Sol")
                .append("3", "Imperium V");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

    private Document pregunta3ExA() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "text")
                .append("titulo", "¿Qué videojuego clásico arcade se basa en la colocación de bloques de distintas figuras\n"
                        + "        geométricas en la pantalla e ir haciendo líneas horizontales?");

        pregunta.put("correcta", "tetris");

        return pregunta;
    }

    private Document pregunta4ExA() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "text")
                .append("titulo", "Pacman tiene 4 enemigos y esos enemigos son unos....");

        pregunta.put("correcta", "fantasmas");

        return pregunta;
    }

    private Document pregunta5ExA() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "checkbox")
                .append("titulo", "¿Cual es el protagonista del videojuego \"La leyenda de Zelda\" y\n"
                        + "		la persona que tiene que rescatar?");

        //Add repuestas correctas.
        Document correcta = new Document("tam", 2)
                .append("0", 0)
                .append("1", 3);
        pregunta.put("correcta", correcta);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "Link (Protagonista)")
                .append("1", "Link (Persona a rescatar)")
                .append("2", "Zelda (Protagonista)")
                .append("3", "Zelda (Persona a rescatar)");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

    private Document pregunta6ExA() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "checkbox")
                .append("titulo", "¿Cuales son las princesas de Mario?");

        //Add repuestas correctas.
        Document correcta = new Document("tam", 2)
                .append("0", 0)
                .append("1", 2);
        pregunta.put("correcta", correcta);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "Peach")
                .append("1", "Pauline")
                .append("2", "Daisy");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

    private Document pregunta7ExA() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "selectS")
                .append("titulo", "¿Cual es la evolución de Pikachu?");
        pregunta.put("correcta", 2);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "Ryachu")
                .append("1", "Raychu")
                .append("2", "Raichu")
                .append("3", "Riachu");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

    private Document pregunta8ExA() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "selectS")
                .append("titulo", "¿Que famoso tiene una saga de videojuego en honor a su nombre?");
        pregunta.put("correcta", 1);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "Arnold Schwarzenegger")
                .append("1", "Tony Hawk")
                .append("2", "Will Smith")
                .append("3", "Fernando Alonso");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

    private Document pregunta9ExA() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "multiple")
                .append("titulo", "¿Qué juegos son de nintendo?");

        //Add repuestas correctas.
        Document correcta = new Document("tam", 2)
                .append("0", 1)
                .append("1", 2);
        pregunta.put("correcta", correcta);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "Sonic Adventure")
                .append("1", "Super Smash Bros")
                .append("2", "Donkey Kong")
                .append("3", "Golden Axe");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

    private Document pregunta10ExA() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "multiple")
                .append("titulo", "¿En qué juegos aparecen Zombies?");

        //Add repuestas correctas.
        Document correcta = new Document("tam", 3)
                .append("0", 0)
                .append("1", 1)
                .append("2", 3);
        pregunta.put("correcta", correcta);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "Resident Evil")
                .append("1", "Day Z")
                .append("2", "Call of Duty: Modern Warfare 2")
                .append("3", "Left 4 Dead");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

}
