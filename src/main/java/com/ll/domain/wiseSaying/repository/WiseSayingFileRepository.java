package com.ll.domain.wiseSaying.repository;


import com.ll.domain.wiseSaying.entity.WiseSaying;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class WiseSayingFileRepository {

    private final List<WiseSaying> articles = new ArrayList<>();
    private int lastId = 0;

    // 저장할 파일 경로
    private final String dataFilePath = "wise_sayings.txt";

    // 새 게시글 저장 (메모리)
    public int save(WiseSaying wiseSaying) {
        int id = ++lastId;
        articles.add(wiseSaying);
        return id;
    }

    // 모든 게시글 반환 (복사본)
    public List<WiseSaying> findAll() {
        return new ArrayList<>(articles);
    }

    // ID로 게시글 조회
    public WiseSaying findById(int id) {
        return articles.stream()
                .filter(a -> a.id == id)
                .findFirst()
                .orElse(null);
    }

    // 게시글 삭제
    public void delete(WiseSaying article) {
        articles.remove(article);
    }

    // 메모리 데이터 → 파일 저장
    public void saveToFile() {
        StringBuilder sb = new StringBuilder();
        for (WiseSaying ws : articles) {
            sb.append(ws.id).append(";")
                    .append(ws.title).append(";")
                    .append(ws.content).append(";")
                    .append(ws.regDate).append("\n");
        }
        try {
            Files.writeString(Path.of(dataFilePath), sb.toString());
        } catch (IOException e) {
            throw new RuntimeException("파일 저장 실패: " + dataFilePath, e);
        }
    }

    // 파일 → 메모리 데이터 로딩
    public void loadFromFile() {
        articles.clear();
        lastId = 0;

        if (!Files.exists(Path.of(dataFilePath))) {
            return;
        }

        try {
            List<String> lines = Files.readAllLines(Path.of(dataFilePath));
            for (String line : lines) {
                String[] parts = line.split(";", -1);
                if (parts.length < 4) continue;

                int id = Integer.parseInt(parts[0]);
                String title = parts[1];
                String content = parts[2];
                String regDate = parts[3];

                articles.add(new WiseSaying(id, title, content, regDate));
                if (id > lastId) {
                    lastId = id;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("파일 읽기 실패: " + dataFilePath, e);
        }
    }

    // 저장 파일 삭제
    public void clear() {
        articles.clear();
        lastId = 0;
        try {
            Files.deleteIfExists(Path.of(dataFilePath));
        } catch (IOException e) {
            throw new RuntimeException("파일 초기화 실패", e);
        }
    }
}