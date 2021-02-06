package dev.hottek.data;

import java.util.LinkedList;

public class DialogData {
    int result;
    LinkedList<String> data;

    public DialogData(int result, LinkedList<String> data) {
        this.result = result;
        this.data = data;
    }

    public int getResult() {
        return result;
    }

    public LinkedList<String> getData() {
        return data;
    }
}
