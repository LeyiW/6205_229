package zombieWalk;

public class Individual {

    //There are 3^5 kinds enviroments around zombies in total. The positions that can be observed are a total of five up, down, left and right and current grids. 
	//There are five in total, each of which has a ground thorn, a brain, and no brain.
    private static int length = 243;
    /*zombies has 6 action:
     * 0 :up    
     * 1 : left   4 : eat
     * 2 : down   5 : not move  
     * 3 : right    
    */
    private static byte actionNum = 6;

    private byte genes[] = null;
    private int fitness = Integer.MIN_VALUE;
    private boolean isAlive=true; 	//shows the individual is alive or not, if false, it will be replaced by new children
    
    
    

    public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public Individual() {
        genes = new byte[length];       
    }

    public void generateGenes(){        
        for (int i = 0; i < length; i++) {
            byte gene = (byte) Math.floor(Math.random() * actionNum);
            genes[i] = gene;
        }
    }

    public int getFitness() {
        if (fitness == Integer.MIN_VALUE) {
            fitness = FitnessCalc.getFitnessPall(this);
        }
        return fitness;
    }


    public int getLength() {
        return length;
    }

    public byte getGene(int index) {
        return genes[index];
    }

    public void setGene(int index, byte gene) {
        this.genes[index] = gene;
        fitness = Integer.MIN_VALUE;
    }

    //Status code conversion: 5 decimal digits, 
    //the first representative, 
    //the second representative, 
    //the third representative right, 
    //the fourth delegate, 
    //the fifth delegate left
    public byte getActionCode(State state) {        
        int stateCode = (int) (state.getMiddle() * Math.pow(3, 4) + state.getUp() * Math.pow(3, 3) + state.getRight() * Math.pow(3, 2) + state.getDown() * 3 + state.getLeft());
        return genes[stateCode];
    }

    @Override
    public String toString() {  
        StringBuffer bf = new StringBuffer();
        for (int i = 0; i < length; i++) {
            bf.append(genes[i]);
        }
        return bf.toString();
    }

    
}
