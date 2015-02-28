package com.gamedev.app.parser.json;

/**
 * Created by MainUser on 28/02/2015.
 */
public class GameEvent {
    private String eventType;
    private String name;

    public GameEvent() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}
