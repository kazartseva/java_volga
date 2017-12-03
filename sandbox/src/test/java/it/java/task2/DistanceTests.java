package it.java.task2;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceTests {
@Test
  public void testDistance () {
    Point p1 = new Point(8,21);
    Point p2 = new Point(1,-3);
    Assert.assertEquals(p1.distance(p2), 25.0);
  }
}
