package dev.knalis.xsao.utils.binds;

public class BindUtil {
    private static BindUtil instance;
    public static BindUtil getInstance() {
        if (instance == null) {
            instance = new BindUtil();
        }
        return instance;
    }
}
