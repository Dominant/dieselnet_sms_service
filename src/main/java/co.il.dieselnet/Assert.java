package co.il.dieselnet;

public class Assert {
    private final static Assert INSTANCE = new Assert();

    public static Assert notNullOrEmpty(String value) throws Exception {
        if (value == null || value.equals("")) {
            throw new Exception("Invalid string value");
        }

        return INSTANCE;
    }
}
