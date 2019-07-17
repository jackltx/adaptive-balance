package enums;


public enum  ServerNameEnum {

    SMALL("provider-small", "provider-small"),

    MEDIUM("provider-medium", "provider-medium"),

    LARGE("provider-large", "provider-large");


    private String code;

    private String desc;

    ServerNameEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static ServerNameEnum getByCode(String code) {
        for (ServerNameEnum serverNameEnum : ServerNameEnum.values()) {
            if (serverNameEnum.code.equalsIgnoreCase(code)) {
                return serverNameEnum;
            }
        }
        return null;
    }

    public static ServerNameEnum getByname(String name) {
        for (ServerNameEnum serverNameEnum : ServerNameEnum.values()) {
            if (serverNameEnum.name().equalsIgnoreCase(name)) {
                return serverNameEnum;
            }
        }
        return null;
    }


}
