package zombieWalkTest;

import static org.junit.Assert.*;

import org.junit.Test;

import zombieWalk.Algorithm;
import zombieWalk.Individual;
import zombieWalk.Population;

public class AlgorithmTest {

	// The goal is to prove that probably killed half of people
	//Enter: 1000 people
	//Detection: if person is dead, death count++; if person is alive, living count++
	//result 0.4<dead/live<0.6
	@Test
	public void testSurvivalFuction() {
		double deadCount =0;
		double surviveCount =0;
		Population population = new Population(1000, false);
		Population newPopulation1 = Algorithm.killFuction(population);
		for(int i=0;i<newPopulation1.size();i++) {
			if(newPopulation1.getIndividual(i).isAlive()==false) {
				deadCount++;
			}else {
				surviveCount++;
			}	
		}
		System.out.println(deadCount);
		System.out.println(surviveCount);
		double result = deadCount/surviveCount;
		assertTrue(result>0.9 && result<1.1 );
		
		
		
		
	}

	@Test
	public void testEvolve() {
		//After the evolution, there is no dead in the array of groups.
		
		int deadCount =0;
		int surviveCount =0;
		Population population = new Population(1000, false);
		Population newPopulation1 = Algorithm.killFuction(population);
		Population newPopulation = Algorithm.evolve(newPopulation1);
		for(int i=0;i<newPopulation.size();i++) {
			if(newPopulation.getIndividual(i).isAlive()==false) {
				deadCount++;
			}else {
				surviveCount++;
			}	
		}
		System.out.println(deadCount);
		assertTrue(deadCount==0);
	}
	
	
	
}
