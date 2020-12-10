package com.opeyemi.spring.basicwebapp.model;

public enum MessageType {

    SAY("Say"),
    WHISPER("Whisper"),
    SHOUT("Shout");

    private String label;

    public String getLabel() {
        return label;
    }

    MessageType(String label) {
        this.label = label;
    }

    public static MessageType messageType(String name) {
        for (MessageType tag : MessageType.values()) {
            if (tag.label.equalsIgnoreCase(name.trim()))
                return tag;
        }
        return SAY;
    }
}
