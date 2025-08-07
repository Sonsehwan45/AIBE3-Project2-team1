package com.ll;

import com.ll.domain.wiseSaying.controller.WiseSayingController;
import com.ll.global.rq.Rq;

import java.util.Scanner;

public class App {
    public void run() {
        Scanner sc = new Scanner(System.in);
        AppContext appContext = new AppContext();
        WiseSayingController controller = appContext.getWiseSayingController();

        System.out.println("== 명언 앱 실행 ==");

        while (true) {
            System.out.print("명령어) ");
            String cmd = sc.nextLine().trim();
            Rq rq = new Rq(cmd);

            switch (rq.getAction()) {
                case "write" -> controller.write();
                case "list" -> controller.list();
                case "detail" -> controller.detail(rq.getIntParam(1, -1));
                case "update" -> controller.update(rq.getIntParam(1, -1));
                case "delete" -> controller.delete(rq.getIntParam(1, -1));
                case "exit" -> {
                    System.out.println("프로그램을 종료합니다.");
                    return;
                }
                default -> System.out.println("알 수 없는 명령어입니다.");
            }
        }
    }
}
