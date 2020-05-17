package main.util;

public class HeaderObject {
    private String name;
    private HeaderType type;

    public HeaderObject(String name, HeaderType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public HeaderObject setName(String name) {
        this.name = name;
        return this;
    }

    public HeaderType getType() {
        return type;
    }

    public HeaderObject setType(HeaderType type) {
        this.type = type;
        return this;
    }
}
