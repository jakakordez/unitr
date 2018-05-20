package net.unitr.unitr.Discussion;

/**
 * Created by Anze on 20/05/2018.
 */

public class ChatMessage {
    private boolean isMine;
    private String content;
    private String color;

    public ChatMessage(String message, boolean mine, String color) {
        content = message;
        isMine = mine;
        this.color = color;
    }

    public String getContent() {
        return content;
    }

    public String getColor() {
        return color;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setIsMine(boolean isMine) {
        this.isMine = isMine;
    }

    public boolean isImage() {
        return false;
    }

}
