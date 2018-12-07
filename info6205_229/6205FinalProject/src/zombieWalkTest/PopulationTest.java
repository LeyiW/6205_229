package zombieWalkTest;


import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;
import zombieWalk.FitnessCalc;
import zombieWalk.Individual;

public class PopulationTest {
@Before
public void setUp(){
	Individual ind=new Individual();
	byte b=00;
	ind.setAlive(true);
	ind.setGene(2, b);
}
@Test
public void testSetIndividuals(){
	Individual ind=new Individual();
	byte b=00;
	ind.setAlive(true);
	ind.setGene(2, b);
	FitnessCalc fit=new FitnessCalc();
	int f=fit.getFitnessPall(ind);
	Assert.assertEquals(-977, f);
}
}

