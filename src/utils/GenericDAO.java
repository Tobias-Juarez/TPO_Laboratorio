package utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericDAO<T> {
    final Class<T> clase;
    protected File archivo;

    public GenericDAO(Class<T> clase, String file) throws Exception {
        this.clase = clase;
        this.archivo = new File(System.getProperty("user.dir") + file);
        if (!archivo.exists()) {
            archivo.createNewFile();
        }
    }
    public ArrayList<T> getAll() throws Exception {
        ArrayList<T> list = new ArrayList<T>();
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);

        try {
            String cadena = b.readLine();
            if (cadena == null){
                this.saveAll(list);
                cadena = "[]";
            }
            JsonParser parser = new JsonParser();
            JsonArray gsonArr = parser.parse(cadena).getAsJsonArray();
            Gson g = new Gson();
            for (JsonElement obj : gsonArr) {
                list.add(g.fromJson(obj, clase));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public void saveAll(List<T> list) throws Exception {
        Gson g = new Gson();
        String texto = g.toJson(list);
        FileWriter fw = new FileWriter(archivo);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(texto);
        bw.close();
    }
}
