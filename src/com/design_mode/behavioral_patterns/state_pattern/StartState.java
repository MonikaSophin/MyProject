package com.design_mode.behavioral_patterns.state_pattern;

/**
 * @author: XueYing.Cao
 * @date: 2019/7/2
 * @description:
 * 步骤3.
 * 实现State接口。
 */
public class StartState implements State {

    @Override
    public void doAction(Context context) {
        System.out.println("Player is in start state");
        context.setState(this);
    }

    @Override
    public String toString() {
        return "Start State";
    }
}
