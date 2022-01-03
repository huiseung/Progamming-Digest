package com.example.back.trace;

import java.util.UUID;

public class TraceId {
    private final String id;
    private final int level;

    public TraceId(){
        this.id = createId();
        this.level = 0;
    }

    private TraceId(String id, int level){
        this.id = id;
        this.level = level;
    }

    private String createId(){
        //로그용 ID라 어쩌다 중복이 생겨도 무방
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public TraceId createNextTraceId(){
        return new TraceId(id, level+1);
    }

    public TraceId createPrevTraceId(){
        return new TraceId(id, level-1);
    }

    public boolean isFirstLevel(){
        return level == 0;
    }

    public String getId(){
        return id;
    }

    public int getLevel(){
        return level;
    }
}
