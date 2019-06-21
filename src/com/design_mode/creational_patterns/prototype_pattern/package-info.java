package com.design_mode.creational_patterns.prototype_pattern;

/**
 *  1.5 原型模式
 *
 *    定义：是用于创建重复的对象，同时又能保证性能。这种类型的设计模式属于创建型模式，它提供了一种创建对象的最佳方式。
 *    这种模式是实现了一个原型接口，该接口用于创建当前对象的克隆。当直接创建对象的代价比较大时，则采用这种模式。
 *    例如，一个对象需要在一个高代价的数据库操作之后被创建。我们可以缓存该对象，在下一个请求时返回它的克隆，
 *    在需要的时候更新数据库，以此来减少数据库调用。
 *
 *   关键代码：实现克隆操作，在 JAVA 继承 Cloneable，重写 clone()
 *
 *
 *   浅复制：只复制了指针值，并没有复制指针指向的资源(即没有创建指针指向资源的副本)，复制后原有指针和新指针共享同一块内存。
 *   深复制：不仅复制了指针值，还复制了指针指向的资源。
 */