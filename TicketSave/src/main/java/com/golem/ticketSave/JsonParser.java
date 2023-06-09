package com.golem.ticketSave;

import com.golem.ticketCell.collection.TicketCollection;
import com.google.gson.*;
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
    public void parseSave (TicketCollection ticketCollection) {
        try (BufferedOutputStream bof = new BufferedOutputStream(new FileOutputStream(file))) {
            bof.write((gson.toJson(ticketCollection)).getBytes());
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
