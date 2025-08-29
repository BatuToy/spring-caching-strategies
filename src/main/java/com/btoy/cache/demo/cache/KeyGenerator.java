package com.btoy.cache.demo.cache;

/*
 * @created 29/08/2025 ~~ 12:49
 * author: batu
 */

public final class KeyGenerator {

    private static final String V = "v1";

    private KeyGenerator() {
        throw new UnsupportedOperationException("Utility class!");
    }

    public static String generateKey(String nameSpace, String id) {
        return String.format("%s:%s:%s", V, nameSpace, id);
    }
}
