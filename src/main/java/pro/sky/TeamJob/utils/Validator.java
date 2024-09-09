package pro.sky.TeamJob.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Validator {

    public static boolean isValidUUID(String uuid) {
        if (uuid == null) {
            return false;
        }
        if (uuid.length() != 36) {
            return false;
        }
        if (uuid.charAt(8) != '-' || uuid.charAt(13) != '-' || uuid.charAt(18) != '-' || uuid.charAt(23) != '-') {
            return false;
        }
        for (int i = 0; i < uuid.length(); i++) {
            if (i == 8 || i == 13 || i == 18 || i == 23) {
                continue;
            }
            if (!isHexChar(uuid.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static boolean isHexChar(char c) {
        return (c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F');
    }

}
