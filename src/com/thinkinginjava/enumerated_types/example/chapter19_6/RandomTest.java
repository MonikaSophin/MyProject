package com.thinkinginjava.enumerated_types.example.chapter19_6;

import com.thinkinginjava.util.Enums;

/**
 * @author: XueYing.Cao
 * @date: 2019/4/12
 * @description:
 */
enum Activity {
    SITTING, LYING, STANDING, HOPPING,
    RUNNING, DODGING, JUMPING, FALLING, FLYING
}

public class RandomTest {
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++)
            System.out.print(Enums.random(Activity.class) + " ");
    }
}
/**
 * STANDING FLYING RUNNING STANDING RUNNING STANDING LYING DODGING SITTING RUNNING HOPPING HOPPING HOPPING RUNNING STANDING LYING FALLING RUNNING FLYING LYING
 */