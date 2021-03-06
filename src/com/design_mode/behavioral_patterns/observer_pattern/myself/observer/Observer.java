package com.design_mode.behavioral_patterns.observer_pattern.myself.observer;

import com.design_mode.behavioral_patterns.observer_pattern.model.Message;

/**
 * 观察者接口
 *    更新来自主题的信息
 */
public interface Observer {
    void update(Message message);
}
