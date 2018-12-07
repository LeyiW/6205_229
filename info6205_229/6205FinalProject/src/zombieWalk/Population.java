package zombieWalk;

public class Population {

    private Individual[] individuals;
    
    

    public Individual[] getIndividuals() {
		return individuals;
	}

	public void setIndividuals(Individual[] individuals) {
		this.individuals = individuals;
	}

	public Population(int size, boolean keepclean) {
        individuals = new Individual[size];
        if (!keepclean) {
            for (int i = 0; i < individuals.length; i++) {
                Individual ind = new Individual();
                ind.generateGenes();
                individuals[i] = ind;
            }
        }
    }

    public void saveIndividual(int index, Individual ind) {
        individuals[index] = ind;
    }

    public Individual getIndividual(int index) {
        return individuals[index];
    }

    public Individual getRandomIndividual() {
    	int index = (int) (Math.random() * this.size());
    	Individual individual = individuals[index];
    	return individual;
    }
    
    public Individual getFittest() {
        Individual fittest = individuals[0];
        // Loop through all the individuals and,  find fittest
        for (int i = 1; i < size(); i++) {
            if (fittest.getFitness() <= getIndividual(i).getFitness()) {
                fittest = getIndividual(i);
            }
        }
        return fittest;
    }

    public Individual getLeastFittest() {
        Individual ind = individuals[0];
        for (int i = 1; i < size(); i++) {
            if (ind.getFitness() > getIndividual(i).getFitness()) {
                ind = getIndividual(i);
            }
        }
        return ind;
    }

    public double getAverageFitness() {
        double sum = 0;
        for (int i = 0; i < size(); i++) {
            sum += individuals[i].getFitness();
        }
        return sum / size();
    }

    public int size() {
        return individuals.length;
    }

    @Override
    public String toString(){
        StringBuffer bf = new StringBuffer();
        bf.append("Population size: " + size() + "\n");
        bf.append("Max Fitnewss: " + getFittest().getFitness() + "\n");
        bf.append("Least Fitness: " + getLeastFittest().getFitness() + "\n");
        bf.append("Average Fitness: " + getAverageFitness() + "\n");   
        bf.append("gene is: " + getFittest() + "\n");
        return bf.toString();
    }

}
