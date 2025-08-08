package com.ll.domain.cliBoard.repository;

import com.ll.domain.cliBoard.entity.CliBoard;
import com.ll.util.Util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class cliBoardFileRepository {
    private static final String dataFilePath = "db/cliBoard/data.json";

    public void save(CliBoard cliBoard){
        if (cliBoard.isNew()){
            int newId = getLastId() + 1;
            cliBoard.setId(newId);

            setLastId(newId);
        }

        Map<String, Object> cliBoardMap = cliBoard.toMap(); //cliBoard 객체를 Map으로 변환
        String cliBoardJsonStr = Util.json.toString(cliBoardMap); //map-> JSON 형태로 변환
        Util.file.set(getEntityFilePath(cliBoard.getId()), cliBoardJsonStr); //JSON 파일로 저장
    }

    public Optional<CliBoard> findById(int id){
        String cliBoardJsonStr = Util.file.get(getEntityFilePath(id), "");

        if (cliBoardJsonStr.isBlank()) return Optional.empty();

        Map<String, Object> cliBoardMap = Util.json.toMap(cliBoardJsonStr);

        Optional<CliBoard> opCliBoard = Optional.of(new CliBoard(cliBoardMap));

        return opCliBoard;
    }

    public boolean delete(CliBoard cliBoard) {
        String filePath = getEntityFilePath(cliBoard.getId());
        return Util.file.delete(filePath);
    }

    private void setLastId(int newId) {
        Util.file.set(getLastIdFilePath(), newId);
    }

    private int getLastId() {
        return Util.file.getAsInt(getLastIdFilePath(), 0);
    }

    public String getTableDirPath() {
        return "db/cliBoard";
    }

    public String getEntityFilePath(int id) {
        return getTableDirPath() + "/%d.json".formatted(id);
    }

    private String getLastIdFilePath() {
        return getTableDirPath() + "/lastId.txt";
    }

//    public List<CliBoard> findAll() {
//        List<CliBoard> cliBoardList = new ArrayList<>();
//        File tableDir = new File(getTableDirPath());
//        if (!tableDir.exists() || !tableDir.isDirectory()) {
//            return cliBoardList; // 디렉토리가 없으면 빈 리스트 반환
//        }
//
//        File[] files = tableDir.listFiles((dir, name) -> name.endsWith(".json"));
//        if (files != null) {
//            for (File file : files) {
//                String cliBoardJsonStr = Util.file.get(file.getAbsolutePath(), "");
//                if (!cliBoardJsonStr.isBlank()) {
//                    Map<String, Object> cliBoardMap = Util.json.toMap(cliBoardJsonStr);
//                    cliBoardList.add(new CliBoard(cliBoardMap));
//                }
//            }
//        }
//        return cliBoardList;
//    }


    public void saveAll(List<CliBoard> cliBoards){
        List<Map<String, Object>> mapList = cliBoards.stream()
                .map(CliBoard::toMap)
                .toList();
        String jsonStr = Util.json.toString(mapList);
        Util.file.set(dataFilePath, jsonStr);
    }

    public List<CliBoard> findAll() {
        String jsonStr = Util.file.get(dataFilePath, "[]");

        if(jsonStr.isBlank() || jsonStr.equals("[]")){
            return new ArrayList<>();
        }

        List<Map<String, Object>> mapList = Util.json.toList(jsonStr);

        return mapList.stream()
                .map(CliBoard::new)
                .collect(Collectors.toList());
    }

}
