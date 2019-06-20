package com.design_mode.d_factory_pattern.abstract_factory;

import com.design_mode.d_factory_pattern.model.Food;

/**
 * @author: XueYing.Cao
 * @date: 2019/6/20
 * @description:
 */
public class Test {
    public static void main(String[] args) {
        //抽象工厂
        AbstractFactory factory = new ChocolateFactory();
        Food food = factory.produce();
        System.out.println(food.getName());
    }
}
/**output:
 * chocolate
 */