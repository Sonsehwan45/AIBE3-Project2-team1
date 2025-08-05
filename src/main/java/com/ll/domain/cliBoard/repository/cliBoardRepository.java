package com.ll.domain.cliBoard.repository;

import com.ll.domain.cliBoard.entity.cliBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class cliBoardRepository {
    private final List<cliBoard> cliBoardList = new ArrayList<>();
    private int lastId = 0;

    public cliBoard save (cliBoard cliBoard) {
        if (cliBoard.isNew()) {
            cliBoard.setId(++lastId);
            cliBoardList.add(cliBoard);
        }

        return cliBoard;
    }

    public List<cliBoard> findForList() {
        return cliBoardList.reversed();
    }

    public void delete(cliBoard cliBoard) {
        cliBoardList.remove(cliBoard);
    }

    public int findIndexById(int id) {
        return IntStream
                .range(0, cliBoardList.size())
                .filter(i -> cliBoardList.get(i).getId() == id)
                .findFirst()
                .orElse(-1);
    }

    public cliBoard findById(int id) {
        int index = findIndexById(id);

        if (index == -1) return null;

        return cliBoardList.get(index);
    }
}