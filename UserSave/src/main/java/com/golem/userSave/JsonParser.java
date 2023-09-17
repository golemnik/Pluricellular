package com.golem.userSave;

import com.golem.serverCell.clients.Clients;
import com.golem.serverCell.clients.RegClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

public class JsonParser {
    private final String file;
    private final Gson gson;
    public JsonParser(String file){
        this.file = file;
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
        this.gson = gsonBuilder.setPrettyPrinting().create();
    }
    public void parseSave (Clients clients) {
        try (BufferedOutputStream bof = new BufferedOutputStream(new FileOutputStream(file))) {
            bof.write((gson.toJson(clients)).getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
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
