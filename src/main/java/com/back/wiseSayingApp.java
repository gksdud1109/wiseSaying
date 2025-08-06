package com.back;

import java.util.Scanner;

public class wiseSayingApp {

    public void run(){
        Scanner sc = new Scanner(System.in);
        wiseSayingRepository repository = null;
        repository = new wiseSayingFileRepository();
        wiseSayingService service = new wiseSayingService(repository);
        wiseSayingController controller = new wiseSayingController(sc, service);

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
                int id = Integer.parseInt(nextCmd.substring("삭제?id=".length()));
                controller.remove(id);
            }
            // 수정
            else if (nextCmd.startsWith("수정?id=")) {
                int id = Integer.parseInt(nextCmd.substring("수정?id=".length()));
                controller.update(id);
            }
            else if (nextCmd.equals("빌드")){
                boolean result = controller.dataBuild();
                if(result) System.out.println("data.json 파일의 내용이 갱신되었습니다.");
                else System.out.println("data.json 파일 갱신 실패ㅠㅠ");
            }
            else System.out.println("알 수 없는 명령입니다.");
        }
    }
}