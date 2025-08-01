package com.back;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<wiseSay> db = new ArrayList<>();
        int lastId = 0;

        // 시작
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String nextCmd = sc.nextLine();
            // 종료
            if (nextCmd.equals("종료")) break;
                // 등록
            else if (nextCmd.equals("등록")) {
                System.out.print("명언 : ");
                String content = sc.nextLine();
                System.out.print("작가 : ");
                String author = sc.nextLine();
                lastId++;
                db.add(new wiseSay(lastId, author, content));
                System.out.println(lastId + "번 명언이 등록되었습니다");
            } else if (nextCmd.equals("목록")) {
                System.out.println("번호 / 작가 / 명언\n------------");
                for (int i = db.size() - 1; i >= 0; i--) {
                    wiseSay w = db.get(i);
                    System.out.println(w.id + " / " + w.author + " / " + w.content);
                }
            }
            // 삭제
            else if (nextCmd.startsWith("삭제?id=")) {
                int id = Integer.parseInt(nextCmd.substring((6)));
                boolean found = false;
                for (int i = 0; i < db.size(); i++) {
                    if (db.get(i).id == id) {
                        db.remove(i);
                        System.out.println(id + "번 명언이 삭제되었습니다.");
                        found = true;
                        break;
                    }
                }
                // 삭제하려는 명언이 존재하지 않음.
                if (!found) {
                    System.out.println(id + "번 명언은 존재하지 않습니다.");
                }
            }
            // 수정
            else if (nextCmd.startsWith("수정?id=")) {
                int id = Integer.parseInt(nextCmd.substring((6)));
                wiseSay target = null;
                for (wiseSay w : db) {
                    if (w.id == id) {
                        target = w;
                        break;
                    }
                }
                // 수정하려는 명언이 존재하지 않음.
                if (target == null) System.out.println(id + "번 명언은 존재하지 않습니다.");
                    // 명언 수정
                else {
                    System.out.println("명언(기존) : " + target.content);
                    System.out.print("명언 : ");
                    target.content = sc.nextLine();

                    System.out.println("작가(기존) : " + target.author);
                    System.out.print("작가 : ");
                    target.author = sc.nextLine();
                }
            } else {
                System.out.println("알 수 없는 명령입니다.");
            }
        }
    }
}