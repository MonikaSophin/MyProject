package com.design_mode.creational_patterns.abstract_factory_pattern;

import com.design_mode.creational_patterns.abstract_factory_pattern.model.Food;
import com.design_mode.creational_patterns.abstract_factory_pattern.model.Milk;

/**
 * @author: XueYing.Cao
 * @date: 2019/6/20
 * @description:
 */
public class MilkFactory implements AbstractFactory {
    @Override
    public Food produce() {
        return new Milk();
    }
}
