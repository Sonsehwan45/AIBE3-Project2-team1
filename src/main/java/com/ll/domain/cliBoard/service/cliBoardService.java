package com.ll.domain.cliBoard.service;

import com.ll.AppContext;
import com.ll.domain.cliBoard.entity.CliBoard;
import com.ll.domain.cliBoard.repository.cliBoardRepository;
import com.ll.domain.cliBoard.repository.cliBoardFileRepository;

import java.awt.*;
import java.util.List;

public class cliBoardService {
    private final cliBoardRepository cliBoardRepository;
    private final cliBoardFileRepository cliBoardFileRepository;

    public cliBoardService () {
        cliBoardRepository = AppContext.cliBoardRepository;
        cliBoardFileRepository = AppContext.cliBoardFileRepository;
    }

    public CliBoard write (String title, String content) {
        CliBoard cliBoard = new CliBoard(title, content);
        cliBoardRepository.save(cliBoard);

        cliBoardFileRepository.save(cliBoard);
        return cliBoard;
    }


    public List<CliBoard> findForList () {
        return cliBoardRepository.findForList();
    }

    public boolean delete(int id) {
        CliBoard cliBoard = cliBoardRepository.findById(id);

        if (cliBoard == null) return false;

        cliBoardRepository.delete(cliBoard);
        return true;
    }

    public CliBoard findById(int id) {
        return cliBoardRepository.findById(id);
    }

    public void update(CliBoard cliBoard, String modifyTitle, String modifyContent) {
        cliBoard.setContent(modifyContent);
        cliBoard.setTitle(modifyTitle);

        cliBoardFileRepository.save(cliBoard);
        cliBoardRepository.save(cliBoard);
    }

    public void increaseViewCount(int id){
        CliBoard cliboard = findById(id);
        if (cliboard == null) return;

        cliboard.increaseViewCount();

        cliBoardFileRepository.save(cliboard);
        cliBoardRepository.save(cliboard);
    }
}