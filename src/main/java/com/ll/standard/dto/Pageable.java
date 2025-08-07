package com.ll.standard.dto;


public class Pageable {
    private int page;
    private int size;

    public Pageable(int page, int size) {
        this.page = Math.max(1, page);
        this.size = Math.max(1, size);
    }

    public int getOffset() {
        return (page - 1) * size;
    }

    public int getLimit() {
        return size;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Pageable{page=" + page + ", size=" + size + '}';
    }
}