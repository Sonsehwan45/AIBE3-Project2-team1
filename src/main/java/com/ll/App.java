package com.ll;


import com.ll.domain.cliBoard.controller.cliBoardController;
import com.ll.domain.system.controller.SystemController;

import java.util.Scanner;

public class App {
    private final Scanner scanner;

    public App() {
        this.scanner = AppContext.scanner;
    }
    void run(){
        SystemController systemController = new SystemController();
        cliBoardController cliBoardController = new cliBoardController();

        System.out.println("== CLI 게시판 앱 ==");

        while(true){
            System.out.print("명령 : ");
            String cmd = scanner.nextLine().trim();
            Rq rq = new Rq(cmd);

            switch(rq.getActionName()){
                case "등록" -> cliBoardController.actionWrite();
                case "목록" -> cliBoardController.actionList();
                case "삭제" -> cliBoardController.actionDelete(rq);
                case "수정" -> cliBoardController.actionModify(rq);
                case "종료" -> {
                    systemController.actionExit();
                    return;
                }
            }
        }
    }
}
