package zombieWalkTest;


import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;
import zombieWalk.State;

public class StateTest {
		byte a=1;
		byte b=2;
		byte c=3;
		byte d=4;
		byte e=5;
		State state=new State(a,b,c,d,e);
	
@Test
public void getfunctionTest(){
	Assert.assertEquals(a, state.getMiddle());
	Assert.assertEquals(b, state.getUp());
	Assert.assertEquals(c, state.getRight());
	Assert.assertEquals(d, state.getDown());
	Assert.assertEquals(e, state.getLeft());
}
}
