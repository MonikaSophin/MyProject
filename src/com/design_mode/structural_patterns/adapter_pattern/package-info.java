package com.design_mode.structural_patterns.adapter_pattern;

/**
 *  2.1 适配器模式
 *
 *    定义：是作为两个不兼容的接口之间的桥梁。这种类型的设计模式属于结构型模式，它结合了两个独立接口的功能。
 *    这种模式涉及到一个单一的类，该类负责加入独立的或不兼容的接口功能。
 *         举个真实的例子，读卡器是作为内存卡和笔记本之间的适配器。您将内存卡插入读卡器，再将读卡器插入笔记本，
 *    这样就可以通过笔记本来读取内存卡。
 *
 *    需要被适配的类、接口、对象（我们有的），简称 src（source）
 *    最终需要的输出（我们想要的），简称 dst (destination，即Target)
 *    适配器称之为 Adapter 。
 *    一句话描述适配器模式的感觉： src->Adapter->dst,即src以某种形式（三种形式分别对应三种适配器模式）给到Adapter里，最终转化成了dst。
 *
 *    三种命名方式，是根据 src是以怎样的形式给到Adapter（在Adapter里的形式）来命名的。
 *    类适配器，以类给到，在Adapter里，就是将src当做类，继承，
 *    对象适配器，以对象给到，在Adapter里，将src作为一个对象，持有。
 *    接口适配器，以接口给到，在Adapter里，将src作为一个接口，实现。
 */