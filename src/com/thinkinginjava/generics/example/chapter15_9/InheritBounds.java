package com.thinkinginjava.generics.example.chapter15_9;

import java.awt.*;

/**
 * 可以看到如何在继承的每个层次上添加边界限制。
 */
class HoldItem<T> {
  T item;
  HoldItem(T item) { this.item = item; }
  T getItem() { return item; }
}

class Colored2<T extends HasColor> extends HoldItem<T> {
  Colored2(T item) { super(item); }
  Color color() { return item.getColor(); }
}

class ColoredDimension2<T extends Dimension & HasColor> extends Colored2<T> {
  ColoredDimension2(T item) {  super(item); }
  int getX() { return item.x; }
  int getY() { return item.y; }
  int getZ() { return item.z; }
}

class Solid2<T extends Dimension & HasColor & Weight> extends ColoredDimension2<T> {
  Solid2(T item) {  super(item); }
  int weight() { return item.weight(); }
}

public class InheritBounds {
  public static void main(String[] args) {
    Solid2<Bounded> solid2 =
      new Solid2<>(new Bounded());
    solid2.color();
    solid2.getY();
    solid2.weight();
  }
} ///:~
