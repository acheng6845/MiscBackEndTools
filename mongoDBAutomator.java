/**
 * Created by achen on 2/18/2017.
 */
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.json.simple.JSONArray;
import static com.mongodb.client.model.Filters.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class mongoDBAutomator {
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        try {
            MongoClient client = new MongoClient("localhost", 27017);
            MongoDatabase db = client.getDatabase("monsters");
            System.out.println("Connected to database successfully");

            mongoDBAutomator automator = new mongoDBAutomator();

            automator.populateTypes(db);

            MongoCollection<Document> collection = db.getCollection("units");
            System.out.println("Collection obtained");

            Object obj = parser.parse(new FileReader("C:\\Users\\achen\\IdeaProjects\\PADImageDownloader\\src\\monsters.json"));
            JSONArray jsonArray = (JSONArray) obj;

            List<Document> units = new ArrayList<>();

            for (Object o : jsonArray) {
                JSONObject unit = (JSONObject) o;
                if (collection.find(eq("name", unit.get("name"))).first() == null) {
                    Document document = new Document("name", unit.get("name"))
                            .append("number", unit.get("id"))
                            .append("atk_low", unit.get("atk_min"))
                            .append("atk_high", unit.get("atk_max"))
                            .append("lvl_max", unit.get("max_level"))
                            .append("type", new String[]{(String)unit.get("type"), (String)unit.get("type2")});
                }
            }

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": "+ e.getMessage());
        }
    }

    private void populateTypes(MongoDatabase db) {
        db.createCollection("types");
        MongoCollection<Document> typeCollection = db.getCollection("types");

        List<Document> types = new ArrayList<>();
        
    }
}
