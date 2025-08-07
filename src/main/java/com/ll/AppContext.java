package com.ll;

import com.ll.domain.cliBoard.controller.cliBoardController;
import com.ll.domain.cliBoard.repository.cliBoardFileRepository;
import com.ll.domain.cliBoard.repository.cliBoardRepository;
import com.ll.domain.cliBoard.service.cliBoardService;
import com.ll.domain.system.controller.SystemController;

import java.util.Scanner;

public class AppContext {
    public static Scanner scanner;
    public static SystemController systemController;
    public static cliBoardController cliBoardController;
    public static cliBoardService cliBoardService;
    public static cliBoardRepository cliBoardRepository;
    public static cliBoardFileRepository cliBoardFileRepository;

    public static void renew (Scanner _scanner) {
        scanner = _scanner;
        systemController = new SystemController();
        cliBoardFileRepository = new cliBoardFileRepository();
        cliBoardRepository = new cliBoardRepository(cliBoardFileRepository.findAll()); //파일 읽어오기
        cliBoardService = new cliBoardService();
        cliBoardController = new cliBoardController();
    }

    public static void renew () {
        renew(new Scanner(System.in));
    }
}
