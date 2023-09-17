package com.golem.userLoad;

import com.golem.serverCell.clients.Clients;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.time.LocalDate;

public class JsonParser {
    private final String file;
    private final Gson gson;
    public JsonParser(String file){
        this.file = file;
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
        this.gson = gsonBuilder.setPrettyPrinting().create();
    }
    public Clients parseLoad () {
        String temp = "";
        try (BufferedInputStream bif = new BufferedInputStream(new FileInputStream(file))) {
            while (bif.available() > 0) {
                temp += (char) bif.read();
            }
//            System.out.println(">>>" + temp);
//            Reader reader = new BufferedReader(new InputStreamReader(bif));
//            System.out.println(gson.fromJson(reader, Clients.class));
//            return null;

            return gson.fromJson(temp, Clients.class);
        } catch (Exception e) {
            System.out.println("Client data information was corrupted:\n" +
                    "  - Reason <<" + e.getMessage() + ">>");
            return new Clients();
        }
    }
    class LocalDateAdapter extends TypeAdapter<LocalDate> {
        @Override
        public void write(final JsonWriter jsonWriter, final LocalDate localDate) throws IOException {
            if (localDate == null) {
                jsonWriter.nullValue();
            } else {
                jsonWriter.value(localDate.toString());
            }
        }

        @Override
        public LocalDate read(final JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            } else {
                return LocalDate.parse(jsonReader.nextString());
            }
        }
    }
}
