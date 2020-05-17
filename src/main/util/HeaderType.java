package main.util;

public enum HeaderType {
    STRING("S"), NUMERIC("N");
    private String value;

    HeaderType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public HeaderType setValue(String value) {
        this.value = value;
        return this;
    }

    public static HeaderType fromValue(String value){
        for(HeaderType type : HeaderType.values()){
            if(type.value.equals(value))
                return type;
        }
        return STRING;
    }
}
