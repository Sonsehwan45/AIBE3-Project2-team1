package com.ll.domain.cliBoard.controller;

import com.ll.AppContext;
import com.ll.Rq;
import com.ll.domain.cliBoard.entity.CliBoard;
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


        CliBoard cliBoard = cliBoardService.write(title, content);

        System.out.println(cliBoard);
        System.out.println("=> 게시글이 등록되었습니다.");
        //System.out.printf("%d번이 등록되었습니다.\n", cliBoard.getId());
    }

    public void actionList() {
        System.out.println("번호 / 제목 / 등록일");
        System.out.println("----------------------");
        for (CliBoard cliBoard : cliBoardService.findForList()) {
            System.out.printf("%d / %s / %s\n", cliBoard.getId(), cliBoard.getTitle(), cliBoard.getCreateDate());
        }
    }

    public void actionDetail(Rq rq){
        int id = rq.getParamsAsInt("id", -1);
        if(id == -1){
            System.out.println("id를 정확히 입력해주세요");
            return;
        }
        CliBoard cliBoard = cliBoardService.findById(id);
        System.out.printf("번호: %d\n제목: %s\n내용: %s\n등록일: %s\n", id, cliBoard.getTitle(), cliBoard.getContent(), cliBoard.getCreateDate());

    }

    public void actionDelete(Rq rq) {
        int id = rq.getParamsAsInt("id", -1);
        if(id == -1){
            System.out.println("id를 정확히 입력해주세요");
            return;
        }

        boolean isDeleted = cliBoardService.delete(id);

        if(!isDeleted){
            System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
            return;
        }

        System.out.printf("%d번 게시글이 삭제되었습니다.\n", id);
    }

    public void actionModify(Rq rq) {
        int id = rq.getParamsAsInt("id", -1);
        if(id == -1){
            System.out.println("id를 정확히 입력해주세요");
            return;
        }
        CliBoard cliBoard = cliBoardService.findById(id);
        System.out.printf("제목 (현재: %s):", cliBoard.getTitle());
        String updateTitle = scanner.nextLine();
        System.out.printf("내용 (현재: %s):", cliBoard.getContent());
        String updateContent = scanner.nextLine();
    }
}
