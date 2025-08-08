package com.ll;

// 커맨드 명령어 요청 유틸 클래스
public class Rq {
    private final String actionName; // 객체가 만들어진 뒤 그 속성이 바뀌지 않아야 하는 경우에 final
    private final int id;

    public Rq(String cmd) {
        String[] bits = cmd.split(" ");
        actionName = bits[0];
        id = bits.length>1 ? Integer.parseInt(bits[1]) : 0;
        // write, list는 뒤에는 숫자를 쓰지않고 입력한다...
    }

    public String getActionName() {
        return actionName;
    }

    public int getId() {
        return id;
    }
}
