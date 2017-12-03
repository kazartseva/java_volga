package it.java.task2;

public class task2 {
  public static void main(String[] args) {
    Point p1 = new Point(8,21);
    Point p2 = new Point(1,-3);

    System.out.println("Расстояние между точками с координатами (" + p1.x + "," + p2.y + ") и (" + p2.x + "," + p2.y + ") = " + distance(p1, p2));

    System.out.println("Расстояние между точками с координатами (" + p1.x + "," + p2.y + ") и (" + p2.x + "," + p2.y + ") = " + p1.distance(p2));

    System.out.println("Расстояние между точками с координатами (" + p1.x + "," + p2.y + ") и (" + p2.x + "," + p2.y + ") = " + p2.distance(p1));
  }


  public static double distance(Point p1, Point p2) {
  return Math.sqrt(Math.pow((p2.x - p1.x),2) + Math.pow((p2.y - p1.y),2));
  }
}