package com.ll.domain.cliBoard.repository;

import com.ll.domain.cliBoard.entity.CliBoard;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class cliBoardRepository {
    private final List<CliBoard> cliBoardList;
    private int lastId = 0;

    public cliBoardRepository(List<CliBoard> initialData) {
        this.cliBoardList = new ArrayList<>(initialData);
        this.lastId = cliBoardList.stream()
                .mapToInt(CliBoard::getId)
                .max()
                .orElse(0);
    }

    public CliBoard save (CliBoard cliBoard) {
        if (cliBoard.isNew()) {
            cliBoard.setId(++lastId);
            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = today.format(formatter);
            cliBoard.setCreateDate(formattedDate);
            cliBoardList.add(cliBoard);
            cliBoard.setViewCount(0);
        }

        return cliBoard;
    }

    public List<CliBoard> findForList() {
        return cliBoardList.reversed();
    }

    public void delete(CliBoard cliBoard) {
        cliBoardList.remove(cliBoard);
    }

    public int findIndexById(int id) {
        return IntStream
                .range(0, cliBoardList.size())
                .filter(i -> cliBoardList.get(i).getId() == id)
                .findFirst()
                .orElse(-1);
    }

    public CliBoard findById(int id) {
        int index = findIndexById(id);

        if (index == -1) return null;

        return cliBoardList.get(index);
    }
}