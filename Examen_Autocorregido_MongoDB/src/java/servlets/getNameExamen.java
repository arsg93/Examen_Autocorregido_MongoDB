/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.bson.Document;

/**
 *
 * @author admin
 */
public class getNameExamen extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Connect
        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://backend:frontend@exam-r6hnd.mongodb.net/test");
        MongoClient mongoClient = new MongoClient(uri);
        //Create Database

        MongoDatabase database = mongoClient.getDatabase("Examen");

        //Mete todos los documentos en una lista
        MongoCollection<Document> ex = database.getCollection("Examenes");

        List<Document> examenes = database.getCollection("Examenes").find().into(new ArrayList<>());
        List<String> namesExamenes = new ArrayList<>();
        for (Document examen : examenes) {
            namesExamenes.add(examen.getString("Nombre"));
        }
        mongoClient.close();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(namesExamenes);
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
            MongoCollection<Document> collEx = database.getCollection("Examenes");
            collEx.drop();
            collEx = database.getCollection("Examenes");

            collEx.insertOne(getExamenA());
            collEx.insertOne(getExamenB());
            collEx.insertOne(getExamenC());
            mongoClient.close();

        } catch (Exception e) {

        }

    }

    private Document getExamenA() {
        Document examenA = new Document("Nombre", "Exámen A");

        examenA.put("0", pregunta1ExA());
        examenA.put("1", pregunta2ExA());
        examenA.put("2", pregunta3ExA());
        examenA.put("3", pregunta4ExA());
        examenA.put("4", pregunta5ExA());
        examenA.put("5", pregunta6ExA());
        examenA.put("6", pregunta7ExA());
        examenA.put("7", pregunta8ExA());
        examenA.put("8", pregunta9ExA());
        examenA.put("9", pregunta10ExA());
        return examenA;
    }

    private Document getExamenB() {
        Document examenB = new Document("Nombre", "Exámen B");

        examenB.put("0", pregunta1ExB());
        examenB.put("1", pregunta2ExB());
        examenB.put("2", pregunta3ExB());
        examenB.put("3", pregunta4ExB());
        examenB.put("4", pregunta5ExB());
        examenB.put("5", pregunta6ExB());
        examenB.put("6", pregunta7ExB());
        examenB.put("7", pregunta8ExB());
        examenB.put("8", pregunta9ExB());
        examenB.put("9", pregunta10ExB());

        return examenB;
    }
    
        private Document getExamenC() {
        Document examenC = new Document("Nombre", "Exámen C");

        examenC.put("0", pregunta1ExC());
        examenC.put("1", pregunta2ExC());
        examenC.put("2", pregunta3ExC());
        examenC.put("3", pregunta4ExC());
        examenC.put("4", pregunta5ExC());
        examenC.put("5", pregunta6ExC());
        examenC.put("6", pregunta7ExC());
        examenC.put("7", pregunta8ExC());
        examenC.put("8", pregunta9ExC());
        examenC.put("9", pregunta10ExC());

        return examenC;
    }

    private Document pregunta1ExA() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "radio")
                .append("titulo", "¿Dónde nos podemos encontrar a Link?");
        pregunta.put("correcta", 2);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "En Dragon Ball")
                .append("1", "En el videojuego \"La leyenda de Link\"")
                .append("2", "En el Super Smash Bros")
                .append("3", "En Doraemon");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

    private Document pregunta1ExB() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "radio")
                .append("titulo", "¿Qué es más rápido en la sabana?");
        pregunta.put("correcta", 3);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "Un guepardo")
                .append("1", "Un jaguar")
                .append("2", "El correcaminos")
                .append("3", "Una jirafa en un ferrari");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

    private Document pregunta1ExC() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "radio")
                .append("titulo", "¿Cuál es el país de origen del fútbol?");
        pregunta.put("correcta", 3);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "España")
                .append("1", "Argentina")
                .append("2", "Japón")
                .append("3", "China");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

    private Document pregunta2ExA() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "radio")
                .append("titulo", "¿Cual de estos juegos no existe?");
        pregunta.put("correcta", 1);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "Resident Evil 7")
                .append("1", "Coll of Duty: Ages of Majorca")
                .append("2", "Pokemon Sol")
                .append("3", "Imperium V");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

    private Document pregunta2ExB() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "radio")
                .append("titulo", "¿Qué es lo primero que hace una vaca cuando sale el Sol?");
        pregunta.put("correcta", 2);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "Ir a pastar")
                .append("1", "Dormir")
                .append("2", "Sombra");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

    private Document pregunta2ExC() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "radio")
                .append("titulo", "¿Cuántos mundiales suman Brasil y España?");
        pregunta.put("correcta", 2);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "4")
                .append("1", "5")
                .append("2", "6")
                .append("3", "7");
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

    private Document pregunta3ExB() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "text")
                .append("titulo", "Te la digo, y no la entiendes; te la vuelvo a repetir y por mucho que te la diga, no la sabes escribir. ¿Qué es?");

        pregunta.put("correcta", "tela");

        return pregunta;
    }

    private Document pregunta3ExC() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "text")
                .append("titulo", "¿Cuál jugador inventó la expresión \"jogo bonito\"?");

        pregunta.put("correcta", "Pelé");

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

    private Document pregunta4ExB() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "text")
                .append("titulo", "Si lo nombras, desaparece. Hablamos del...");

        pregunta.put("correcta", "silencio");

        return pregunta;
    }

    private Document pregunta4ExC() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "text")
                .append("titulo", "¿En qué año ganó España el Mundial?");

        pregunta.put("correcta", "2010");

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

    private Document pregunta5ExB() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "checkbox")
                .append("titulo", "Laura, la madre de Lola, tiene 5 hijas. Cuatro de ellas se llaman Lala, Lele, Lili, Lolo. ¿Cuáles son los nombres de las 5 hermanas?");

        //Add repuestas correctas.
        Document correcta = new Document("tam", 5)
                .append("0", 1)
                .append("1", 2)
                .append("2", 3)
                .append("3", 4)
                .append("4", 5);
        pregunta.put("correcta", correcta);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "Laura")
                .append("1", "Lola")
                .append("2", "Lala")
                .append("3", "Lele")
                .append("4", "Lili")
                .append("5", "Lolo")
                .append("6", "Lulu");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

    private Document pregunta5ExC() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "checkbox")
                .append("titulo", "Marca los jugadores que hayan ganado el Balón de Oro");

        //Add repuestas correctas.
        Document correcta = new Document("tam", 3)
                .append("0", 0)
                .append("1", 1)
                .append("2", 3);
        pregunta.put("correcta", correcta);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "Messi")
                .append("1", "Zidane")
                .append("2", "Iniesta")
                .append("3", "Cristian Ronaldo");
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

    private Document pregunta6ExB() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "checkbox")
                .append("titulo", "Valore este examen (sabiendo que una mala valoración podría bajar su nota...)");

        //Add repuestas correctas.
        Document correcta = new Document("tam", 2)
                .append("0", 0)
                .append("1", 1);
        pregunta.put("correcta", correcta);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "Es genial")
                .append("1", "Increíble")
                .append("2", "Malísimo (PISTA: Esta no la marques...)");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

    private Document pregunta6ExC() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "checkbox")
                .append("titulo", "¿Qué equipos juegan en España?");

        //Add repuestas correctas.
        Document correcta = new Document("tam", 2)
                .append("0", 0)
                .append("1", 2);
        pregunta.put("correcta", correcta);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "Real Mallorca")
                .append("1", "Deportivo Alianza")
                .append("2", "Atletico Sevilla")
                .append("3", "Emelec");
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

    private Document pregunta7ExB() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "selectS")
                .append("titulo", "Si explota un avión, ¿por dónde saldrías?");
        pregunta.put("correcta", 2);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "Por la puerta de emergencia")
                .append("1", "Por una ventanilla")
                .append("2", "Por las noticias");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

    private Document pregunta7ExC() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "selectS")
                .append("titulo", "¿Cuál es el mejor entrenador de 2016?");
        pregunta.put("correcta", 2);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "Zidane")
                .append("1", "Luis Enrique")
                .append("2", "Diego Simeone");
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

    private Document pregunta8ExB() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "selectS")
                .append("titulo", " Si hay un incendio en el zoo, ¿quién tiene la culpa?");
        pregunta.put("correcta", 1);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "Los leones")
                .append("1", "Las llamas")
                .append("2", "Los osos polares");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

    private Document pregunta8ExC() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "selectS")
                .append("titulo", "¿En qué año fue fundado el Sevilla FC?");
        pregunta.put("correcta", 0);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "1890")
                .append("1", "1905")
                .append("2", "1910")
                .append("3", "1911");
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

    private Document pregunta9ExB() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "multiple")
                .append("titulo", "¿Cómo se llaman los tres chinos más pobres?");

        //Add repuestas correctas.
        Document correcta = new Document("tam", 3)
                .append("0", 0)
                .append("1", 1)
                .append("2", 3);
        pregunta.put("correcta", correcta);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "Chin lu")
                .append("1", "Chin gas")
                .append("2", "Chin pon")
                .append("3", "Chin agua");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

    private Document pregunta9ExC() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "multiple")
                .append("titulo", "¿Cuáles son los mejores jugadores del mundo?");

        //Add repuestas correctas.
        Document correcta = new Document("tam", 2)
                .append("0", 0)
                .append("1", 1);
        pregunta.put("correcta", correcta);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "Cristiano Ronaldo")
                .append("1", "Messi")
                .append("2", "Álvaro Arbeloa")
                .append("3", "Alex Song");
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

    private Document pregunta10ExB() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "multiple")
                .append("titulo", "Este banco está ocupado, por un padre y un hijo, el padre se llama Juan y el hijo ya te lo he dicho. ¿Quiénes están sentados en el banco?");

        //Add repuestas correctas.
        Document correcta = new Document("tam", 2)
                .append("0", 0)
                .append("1", 3);
        pregunta.put("correcta", correcta);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "Juan")
                .append("1", "El banco está pintado, no hay nadie sentado")
                .append("2", "Una señora alimentando palomas")
                .append("3", "Esteban")
                .append("4", "Carlos");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

    private Document pregunta10ExC() {
        //Create pregunta
        Document pregunta = new Document()
                .append("tipo", "multiple")
                .append("titulo", "¿Cuáles equipos han sido campeones de Europa?");

        //Add repuestas correctas.
        Document correcta = new Document("tam", 3)
                .append("0", 0)
                .append("1", 2)
                .append("2", 3);
        pregunta.put("correcta", correcta);

        //Add respuestas.
        Document respuesta = new Document()
                .append("0", "Real Madrid")
                .append("1", "Arsenal")
                .append("2", "Chelsea")
                .append("3", "AC Milan");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

}

