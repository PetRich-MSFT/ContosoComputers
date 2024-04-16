package com.contoso.computers.demo.util;

import java.util.Random;

public final class RandomUtils {

    public static String randomFromArray(String[] array, Random random){
        return array[random.nextInt(array.length)];
    }

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz, Random random){
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
}
