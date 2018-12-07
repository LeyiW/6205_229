package zombieWalk;

import java.util.ArrayList;

public class Algorithm {
    /* GA's parameter  */
    private static final double uniformRate = 0.5; //cross over probablity
    private static final double mutationRate = 0.0001; //mutation probablity
    
    
    //The purpose of the kill function is to screen out species with high fitness values.
    // Adopted the roulette method: If the individual score is lower than the AverageFitness, he has a 70% chance of death. Otherwise 30%
    public static Population killFuction(Population pop) {
    	double avgFit = pop.getAverageFitness();
    	for(int i=0;i<pop.size();i++) {
    		if(pop.getIndividual(i).getFitness()<=avgFit) {
    			int point = (int)(Math.random()*100);
    			if (point<70) pop.getIndividual(i).setAlive(false); 	//individual dead
    		}
    		else{
    			int point = (int)(Math.random()*100);
    			if (point<30) pop.getIndividual(i).setAlive(false); 	//individual dead
    		}
    	}
    	return pop;
    }

    public static Population evolve(Population pop) {
        Population newPopulation = new Population(pop.size(), true);
        
        //Traversing the previous generation array If the individual is alive, add him to the new array
        // If the individual dies, randomly select two matings from the live previous generation array to replace the individual.
        for (int i = 0; i < pop.size(); i++) {
        	if(pop.getIndividual(i).isAlive()==true) {
        		newPopulation.saveIndividual(i, pop.getIndividual(i));
        	}else {
        		//Randomly choose two living excellent individuals
        		Individual indiv1 = chooseLiveBreed(pop);
        		Individual indiv2 = chooseLiveBreed(pop);           
        		//cross over
        		Individual newIndiv = crossover(indiv1, indiv2);
        		//Birth child has a probability of mutation
        		mutate(newIndiv);
        		newPopulation.saveIndividual(i, newIndiv);
        	}
          
        } 
        return newPopulation;       
    }    
    
    // randomly choose one
    // If it is not dead, choose it.
    // If it is dead, re-randomly select
    private static Individual chooseLiveBreed(Population pop) {
    	Individual in;
    	while(true) {
    		in = pop.getRandomIndividual();
    		if(in.isAlive())
    			break;
    	}
    	return in;
    }


    // cross over 2 individual : rate = uniformRate
    private static Individual crossover(Individual indiv1, Individual indiv2) {
        Individual newSol = new Individual();
        // choose randomly from two individual
        for (int i = 0; i < indiv1.getLength(); i++) {
            if (Math.random() <= uniformRate) {
                newSol.setGene(i, indiv1.getGene(i));
            } else {
                newSol.setGene(i, indiv2.getGene(i));
            }
        }
        return newSol;
    }

    // mutation: the rate is mutationRate
    private static void mutate(Individual indiv) {
        for (int i = 0; i < indiv.getLength(); i++) {
            if (Math.random() <= mutationRate) {
            	// Generate random 0-5
                byte gene = (byte) Math.floor(Math.random() * 6);
                indiv.setGene(i, gene);
            }
        }
    }
}

