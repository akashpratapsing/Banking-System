
package com.banking.system2.util;

import org.apache.commons.codec.digest.DigestUtils;

public class HashUtil {
    public static String sha256(String input) {
        return DigestUtils.sha256Hex(input);
    }
}
