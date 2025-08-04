package com.back;

import java.io.*;
import java.util.ArrayList;

public class wiseSayingFileRepository implements wiseSayingRepository {
    private static final String FOLDER = "db/wiseSaying/";

    public wiseSayingFileRepository() {
        try {
            File folder = new File(FOLDER);
            if (!folder.exists()) folder.mkdirs();

            File lastId = new File(FOLDER + "lastId.txt");
            if (!lastId.exists()) {
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(lastId))) {
                    bw.write("0");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Repository 초기화 중 오류 발생", e);
        }
    }

    @Override
    public wiseSay save(String content, String author) {
        try {
            int newId = getLastId() + 1;
            wiseSay ws = new wiseSay(newId, author, content);
            saveLastId(newId);
            saveWiseSay(ws);
            return ws;
        } catch (IOException e) {
            throw new RuntimeException("명언 저장 실패", e);
        }
    }

    @Override
    public ArrayList<wiseSay> findAll() {
        ArrayList<wiseSay> list = new ArrayList<>();
        File dir = new File(FOLDER);

        File[] files = dir.listFiles((d, name) -> name.endsWith(".json") && !name.equals("lastId.txt"));
        if (files != null) {
            for (File file : files) {
                try {
                    wiseSay ws = getWiseSay(file);
                    if (ws != null) list.add(ws);
                } catch (IOException e) {
                    System.err.println("파일 읽기 실패: " + file.getName());
                }
            }
        }

        // id 내림차순 정렬
        list.sort((a, b) -> b.getId() - a.getId());
        return list;
    }

    @Override
    public wiseSay findById(int id) {
        File file = new File(FOLDER + id + ".json");
        if (!file.exists()) return null;

        try {
            return getWiseSay(file);
        } catch (IOException e) {
            throw new RuntimeException("명언 불러오기 실패", e);
        }
    }

    @Override
    public boolean removeById(int id) {
        File file = new File(FOLDER + id + ".json");
        return file.exists() && file.delete();
    }

    @Override
    public wiseSay update(int id, wiseSay target) {
        File file = new File(FOLDER + id + ".json");
        if (!file.exists()) return null;

        try {
            saveWiseSay(target);
            return target;
        } catch (IOException e) {
            throw new RuntimeException("명언 수정 실패", e);
        }
    }

    private int getLastId() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(FOLDER + "lastId.txt"))) {
            return Integer.parseInt(reader.readLine().trim());
        }
    }

    private void saveLastId(int id) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FOLDER + "lastId.txt"))) {
            bw.write(String.valueOf(id));
        }
    }

    private void saveWiseSay(wiseSay ws) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FOLDER + ws.getId() + ".json"))) {
            bw.write("{\n");
            bw.write("  \"id\": " + ws.getId() + ",\n");
            bw.write("  \"content\": \"" + escape(ws.getContent()) + "\",\n");
            bw.write("  \"author\": \"" + escape(ws.getAuthor()) + "\"\n");
            bw.write("}");
        }
    }

    private wiseSay getWiseSay(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int id = -1;
            String author = "";
            String content = "";
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.startsWith("\"id\"")) {
                    int colonIdx = line.indexOf(":");
                    String value = line.substring(colonIdx + 1).trim();
                    value = value.replace(",", ""); // 쉼표 제거
                    id = Integer.parseInt(value);

                } else if (line.startsWith("\"content\"")) {
                    int colonIdx = line.indexOf(":");
                    String value = line.substring(colonIdx + 1).trim();
                    content = cleanJsonString(value);

                } else if (line.startsWith("\"author\"")) {
                    int colonIdx = line.indexOf(":");
                    String value = line.substring(colonIdx + 1).trim();
                    author = cleanJsonString(value);
                }
            }

            return new wiseSay(id, author, content);
        }
    }

    private String cleanJsonString(String raw) {
        raw = raw.trim();
        // 앞뒤 쌍따옴표 제거
        if (raw.startsWith("\"") && raw.endsWith("\"")) {
            raw = raw.substring(1, raw.length() - 1);
        }
        // 마지막에 쉼표가 있다면 제거
        if (raw.endsWith(",")) {
            raw = raw.substring(0, raw.length() - 1);
        }
        // 이스케이프 문자 처리
        raw = raw.replace("\\\"", "\"").replace("\\\\", "\\");

        return raw;
    }

    private String escape(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}