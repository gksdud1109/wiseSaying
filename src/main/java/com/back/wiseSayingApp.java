package com.back;

import java.util.Scanner;

public class wiseSayingApp {
    static final Scanner sc = new Scanner(System.in);
    private wiseSayingController controller = new wiseSayingController(sc);
    public void run(){
        // 시작
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String nextCmd = sc.nextLine().trim();
            // 종료
            if (nextCmd.equals("종료"))
                break;
                // 등록
            else if (nextCmd.equals("등록"))
                controller.register();
                // 목록
            else if (nextCmd.equals("목록"))
                controller.list();
                // 삭제
            else if (nextCmd.startsWith("삭제?id=")) {
                int id = Integer.parseInt(nextCmd.substring((6)));
                controller.remove(id);
            }
            // 수정
            else if (nextCmd.startsWith("수정?id=")) {
                int id = Integer.parseInt(nextCmd.substring((6)));
                controller.update(id);
            }
            else System.out.println("알 수 없는 명령입니다.");

        }
    }
}
/*
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

    static wiseSay getWiseSay(File file) throws IOException {
        try(BufferedReader reader = new BufferedReader((new FileReader((file))))){
            String line;
        }
    }
 */