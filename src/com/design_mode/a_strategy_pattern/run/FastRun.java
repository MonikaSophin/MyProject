package com.design_mode.a_strategy_pattern.run;

/**
 * 是一个‘猫跑’的算法族
 */
public class FastRun implements IRun{
    @Override
    public void run(){
        System.out.println("快速跑--");
    }
}
