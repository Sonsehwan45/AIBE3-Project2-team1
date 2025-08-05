package com.ll.domain.cliBoard.service;

import com.ll.AppContext;
import com.ll.domain.cliBoard.entity.cliBoard;
import com.ll.domain.cliBoard.repository.cliBoardRepository;

import java.util.List;

public class cliBoardService {
    private final cliBoardRepository cliBoardRepository;

    public cliBoardService () {
        cliBoardRepository = AppContext.cliBoardRepository;
    }

    public cliBoard write (String content, String author) {
        cliBoard cliBoard = new cliBoard(author, content);
        cliBoardRepository.save(cliBoard);

        return cliBoard;
    }

    public List<cliBoard> findForList () {
        return cliBoardRepository.findForList();
    }

    public boolean delete(int id) {
        cliBoard cliBoard = cliBoardRepository.findById(id);

        if (cliBoard == null) return false;

        cliBoardRepository.delete(cliBoard);

        return true;
    }

    public cliBoard findById(int id) {
        return cliBoardRepository.findById(id);
    }

    public void modify(cliBoard cliBoard, String modifyContent, String modifyTitle) {
        cliBoard.setContent(modifyContent);
        cliBoard.setTitle(modifyTitle);

        cliBoardRepository.save(cliBoard);
    }
}