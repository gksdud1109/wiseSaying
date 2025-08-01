package com.back;

import java.io.*;
import java.util.ArrayList;

public class wiseSayingApp {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final ArrayList<wiseSay> db = new ArrayList<>();
    static final String FOLDER = "db/wiseSaying/";


    void init() throws IOException {
        File folder = new File(FOLDER);
        if (!folder.exists()) folder.mkdir();

        File lastId = new File(FOLDER + "lastId.txt");
        if (!lastId.exists()) {
            BufferedWriter bw = new BufferedWriter(new FileWriter(lastId));
            bw.write("0");
        }
    }

    static int getLastId() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(FOLDER + "lastId.txt"))) {
            return Integer.parseInt(reader.readLine());
        }
    }

    static void saveLastId(int id) throws IOException {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(FOLDER + "lastId.txt"))) {
            bw.write(String.valueOf(id));
        }
    }

    static void saveWiseSay(wiseSay ws) throws IOException {
        String path = FOLDER + ws.id + ".json";
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
            bw.write("{\n");
            bw.write("  \"id\": "+ ws.id + ",\n");
            bw.write("  \"content\": \""+ ws.content +"\",\n");
            bw.write("  \"author\": \""+ ws.author +"\"\n");
            bw.write("}");
        }
    }
}
