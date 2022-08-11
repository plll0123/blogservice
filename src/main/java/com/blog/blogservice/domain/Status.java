package com.blog.blogservice.domain;

public enum Status {
    OPERATION("운영", 1), DELETE_REQ("삭제 요청", 2);

    private static final Status[] STATUS_ARR = values();

    private int code;
    private final String stats;

    Status(String stats, int code){
        this.stats = stats;
        this.code = code;
    }

    public Status changeStatus(){
        code = code % 2 + 1;
        return STATUS_ARR[code - 1];
    }

    public String getStats() {
        return stats;
    }
}
