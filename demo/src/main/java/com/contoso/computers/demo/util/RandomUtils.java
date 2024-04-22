package com.contoso.computers.demo.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public final class RandomUtils {

    public static String randomFromArray(String[] array, Random random){
        return array[random.nextInt(array.length)];
    }

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz, Random random){
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    public static Date randomDate(int startOffsetField, int startOffset, int endFieldOffset, int endOffset, Random random) {
        Calendar cal = Calendar.getInstance();
        cal.add(startOffsetField, startOffset);
        long start = cal.getTime().getTime();

        cal = Calendar.getInstance();
        cal.add(endFieldOffset, endOffset);
        long end = cal.getTime().getTime();

        long time = random.nextLong(start, end);

        return new Date(time);
    }
}
