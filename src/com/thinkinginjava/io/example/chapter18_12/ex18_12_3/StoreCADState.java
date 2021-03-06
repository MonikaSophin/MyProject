package com.thinkinginjava.io.example.chapter18_12.ex18_12_3;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author: XueYing.Cao
 * @date: 2019/3/21
 * @description:
 */
abstract class Shape implements Serializable {
    public static final int RED = 1, BLUE = 2, GREEN = 3;
    private int xPos, yPos, dimension;
    private static Random rand = new Random(47);
    private static int counter = 0;
    public abstract void setColor(int newColor);
    public abstract int getColor();
    public Shape(int xPos, int yPos, int dimension) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.dimension = dimension;
    }
    @Override
    public String toString() {
        return String.format("%s color[%s] xPos[%s] yPos[%s] dim[%s]\n",
                getClass(), getColor(), xPos, yPos, dimension);
    }
    public static Shape randomFactory() {
        int xVal = rand.nextInt(100);
        int yVal = rand.nextInt(100);
        int dim = rand.nextInt(100);
        switch (counter++ % 3) {
            default:
            case 0: return new Circle(xVal, yVal, dim);
            case 1: return new Square(xVal, yVal, dim);
            case 2: return new Line(xVal, yVal, dim);
        }
    }
}

class Circle extends Shape {
    private static int color = RED;
    public Circle(int xVal, int yVal, int dim) {
        super(xVal, yVal, dim);
    }
    public void setColor(int newColor) { color = newColor; }
    public int getColor() { return color; }
}

class Square extends Shape {
    private static int color;
    public Square(int xVal, int yVal, int dim) {
        super(xVal, yVal, dim);
        color = RED;
    }
    public void setColor(int newColor) { color = newColor; }
    public int getColor() { return color; }
}

class Line extends Shape {
    private static int color = RED;
    public static void
    serializeStaticState(ObjectOutputStream os)
            throws IOException { os.writeInt(color); }
    public static void
    deserializeStaticState(ObjectInputStream os)
            throws IOException { color = os.readInt(); }
    public Line(int xVal, int yVal, int dim) {
        super(xVal, yVal, dim);
    }
    public void setColor(int newColor) { color = newColor; }
    public int getColor() { return color; }
}

public class StoreCADState {
    static String filePath = "src\\com\\thinkinginjava\\io\\example\\chapter18_12\\file\\StoreCADState.out";
    public static void main(String[] args) throws IOException {
        List<Class<? extends Shape>> shapeTypes = new ArrayList<>();
        shapeTypes.add(Circle.class);
        shapeTypes.add(Square.class);
        shapeTypes.add(Line.class);

        List<Shape> shapes = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            shapes.add(Shape.randomFactory());
        for (int i = 0; i < 10; i++)
            shapes.get(i).setColor(Shape.GREEN);

        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(filePath));
        out.writeObject(shapeTypes);

        Line.serializeStaticState(out);
        out.writeObject(shapes);
        System.out.println(shapes);
    }
}
/**output:
 * [class com.thinkinginjava.io.example.chapter18_12.ex18_12_3.Circle color[1] xPos[58] yPos[55] dim[93]
 * , class com.thinkinginjava.io.example.chapter18_12.ex18_12_3.Square color[0] xPos[61] yPos[61] dim[29]
 * , class com.thinkinginjava.io.example.chapter18_12.ex18_12_3.Line color[3] xPos[68] yPos[0] dim[22]
 * , class com.thinkinginjava.io.example.chapter18_12.ex18_12_3.Circle color[1] xPos[7] yPos[88] dim[28]
 * , class com.thinkinginjava.io.example.chapter18_12.ex18_12_3.Square color[0] xPos[51] yPos[89] dim[9]
 * , class com.thinkinginjava.io.example.chapter18_12.ex18_12_3.Line color[3] xPos[78] yPos[98] dim[61]
 * , class com.thinkinginjava.io.example.chapter18_12.ex18_12_3.Circle color[1] xPos[20] yPos[58] dim[16]
 * , class com.thinkinginjava.io.example.chapter18_12.ex18_12_3.Square color[0] xPos[40] yPos[11] dim[22]
 * , class com.thinkinginjava.io.example.chapter18_12.ex18_12_3.Line color[3] xPos[4] yPos[83] dim[6]
 * , class com.thinkinginjava.io.example.chapter18_12.ex18_12_3.Circle color[1] xPos[75] yPos[10] dim[42]
 * ]
 */