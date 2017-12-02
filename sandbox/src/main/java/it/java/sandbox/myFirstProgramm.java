package it.java.sandbox;

public class myFirstProgramm {
  public static void main(String[] args) {
    hello("world");
    hello("user");
    hello("Maria");

    Square s = new Square(5);
       System.out.println("Площадь квардрата со стороной " + s.l + " = " + s.area());


    Rectangle r = new Rectangle(3, 5);

        System.out.println("Площадь прямоугольника с сторонами " + r.a + " и " + r.b + " = " + r.area());
  }


  public static void hello(String somebody) {
    System.out.println("Hello, " + somebody + "!");
  }






}

