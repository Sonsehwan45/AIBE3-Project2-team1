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
            System.out.print("명령어 : ");
            String cmd = scanner.nextLine().trim();
            Rq rq = new Rq(cmd);

            switch(rq.getActionName()){
                case "write" -> cliBoardController.actionWrite();
                case "list" -> cliBoardController.actionList();
                case "detail" -> cliBoardController.actionDetail(rq);
                case "delete" -> cliBoardController.actionDelete(rq);
                case "update" -> cliBoardController.actionModify(rq);
                case "exit" -> {
                    systemController.actionExit();
                    return;
                }
            }
        }
    }
}
