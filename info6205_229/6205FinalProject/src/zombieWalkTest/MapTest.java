package zombieWalkTest;

import java.awt.Point;

import org.junit.Test;

import junit.framework.Assert;
import zombieWalk.Map;

public class MapTest {
@Test
public void testSetBrains1(){
	Map map=new Map(10, 10);
	int result=map.getTotal();
	Assert.assertEquals(100, result);		
}
@Test
public void testIsInMap(){
	Map map=new Map(10, 10);
	//test:X
	Assert.assertEquals(false, map.isInMap(11, 4));
	Assert.assertEquals(false, map.isInMap(-1, 4));
	Assert.assertEquals(true, map.isInMap(5, 5));

	//test:Y
	Assert.assertEquals(false, map.isInMap(4, 10));
	Assert.assertEquals(false, map.isInMap(4, -1));
	Assert.assertEquals(true, map.isInMap(4, 4));
}

@Test
public void TestHasBrains(){
	Map map=new Map(10,10);
	Assert.assertEquals(false, map.hasBrain(1, 1));
	map.setBrain(100);
	Assert.assertEquals(true, map.hasBrain(1, 1));
}
@Test
public void TestEatBrains(){
	Map map=new Map(10,10);
	Assert.assertEquals(false, map.eatBrain(1, 1));
	map.setBrain(100);
	Assert.assertEquals(true, map.eatBrain(1, 1));
	Assert.assertEquals(true, map.hasBrain(2, 3));
	Assert.assertEquals(false, map.hasBrain(1, 1));

}
@Test
public void TestGetStartPoint(){
	Map map=new Map(10,10);
	Point point =map.getStartPoint();
	Assert.assertEquals(true, map.isInMap(point.x, point.y));
}






	

}

