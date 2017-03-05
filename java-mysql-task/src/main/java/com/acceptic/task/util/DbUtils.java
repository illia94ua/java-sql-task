package com.acceptic.task.util;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class DbUtils {

    public static int generateId() {
        return RandomUtils.nextInt(NumberUtils.INTEGER_ZERO, Integer.MAX_VALUE);
    }

}
