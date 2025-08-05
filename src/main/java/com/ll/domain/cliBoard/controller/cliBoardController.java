package com.ll.domain.cliBoard.controller;

import com.ll.AppContext;
import com.ll.Rq;
import com.ll.domain.cliBoard.entity.cliBoard;
import com.ll.domain.cliBoard.service.cliBoardService;

import java.util.Scanner;

public class cliBoardController {
    private final Scanner scanner;
    private final cliBoardService cliBoardService;

    public cliBoardController() {
        this.scanner = AppContext.scanner;
        this.cliBoardService = AppContext.cliBoardService;
    }

    public void actionWrite() {
        System.out.print("제목 : ");
        String title = scanner.nextLine();

        System.out.print("내용 : ");
        String content = scanner.nextLine();

        cliBoard cliBoard = cliBoardService.write(title, content);

        System.out.println(cliBoard);
        System.out.printf("%d번 명언이 등록되었습니다.\n", cliBoard.getId());
    }

    public void actionList() {
    }

    public void actionDelete(Rq rq) {
    }

    public void actionModify(Rq rq) {
    }
}
