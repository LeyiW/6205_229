package zombieWalk;

public class Main {
    public static void main(String[] args) {
        Population population = new Population(1000, false);
        System.out.println(population);
        long count = 1;
        while (true){ 
        	Population newPopulation1 = Algorithm.killFuction(population);
            Population newPopulation = Algorithm.evolve(newPopulation1);
            if (count>=200 && count % 5 == 0) {
                System.out.println("The " + count + "'s evolve");
                System.out.println(newPopulation);  
            }else if(count<200){
            	System.out.println("The " + count + "'s evolve");
                System.out.println(newPopulation); 
            }
            population = newPopulation;
            count++;            
        }
    }       
}
