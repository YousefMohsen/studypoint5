/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Yousinho
 */
public class DataGenerator {

    private List<String> fnames = new ArrayList<String>();
    private List<String> lnames = new ArrayList<String>();
    private List<String> streets = new ArrayList<String>();
    private List<String> cities = new ArrayList<String>();

    public DataGenerator() {
        initData();
    }

    public String getData(int amount, String properties) {
        JsonArray names = new JsonArray();
        JsonObject person = new JsonObject();
          Gson gson = new Gson();

        for (int i = 0; i < amount; i++) {
            person = new JsonObject();

            if (properties.contains("fname")) {
                person.addProperty("fname", fnames.get(new Random().nextInt(6)));
            }
            if (properties.contains("lname")) {
                person.addProperty("lname", lnames.get(new Random().nextInt(6)));
            }
            if (properties.contains("street")) {
                person.addProperty("street", streets.get(new Random().nextInt(7)));
            }
            if (properties.contains("city")) {
                person.addProperty("city", cities.get(new Random().nextInt(7)));
            }
//        System.out.println(i+") "+gson.toJson(person));
            names.add(person);

        }
      
        String jsonStr = gson.toJson(names);
      
        return jsonStr;
    }

    private void initData() {
        fnames.add("Messi");
        fnames.add("Thiago");
        fnames.add("Xavi");
        fnames.add("Iniesta");
        fnames.add("Pique");
        fnames.add("Socrates");

        lnames.add("Hansen");
        lnames.add("Gilgamesh");
        lnames.add("Leo");
        lnames.add("Green");
        lnames.add("Lego");
        lnames.add("Skak");

        cities.add("Lyngby");
        cities.add("Copenhagen");
        cities.add("New York");
        cities.add("Ur");
        cities.add("Babylon");
        cities.add("Ashur");
        cities.add("Bejing");

        streets.add("Lyngbyvej");
        streets.add("Copenhagenvej");
        streets.add("New Yorkgade");
        streets.add("Ur-vej");
        streets.add("Babylongade");
        streets.add("Ashurgade");
        streets.add("Bejingvej");

    }

}
