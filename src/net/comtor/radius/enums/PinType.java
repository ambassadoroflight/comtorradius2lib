package net.comtor.radius.enums;

/**
 *
 * @author juandiego@comtor.net
 * @since Jan 30, 2019
 */
public enum PinType {

    LOCAL("LOCAL", "Red Local"),
    FULL("FULL", "Internet");

    private String code;
    private String name;

    private PinType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "PinType{"
                + "code=" + code
                + ", name=" + name
                + '}';
    }

}
