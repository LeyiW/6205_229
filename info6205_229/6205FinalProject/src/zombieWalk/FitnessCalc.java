package zombieWalk;

import java.awt.Point;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FitnessCalc {
	/* Action result description:
	 * Hit the wall: -5 points
	 * Eat brains: 10 points
	 * Eat short: -1 point
	 * Other: 0 points
	*/
    //simtimes zombie run
    private static int DefaultSimTimes = 1000;
    //simsteps
    private static int simSteps = 200;
    private static int cores = 4;

    public static int getFitness(Individual ind) {
        return getFitness(ind, DefaultSimTimes);
    }

    public static int getFitness(Individual ind, int simTimes) {
        int fitness = 0;        
        MapMgr mgr = MapMgr.getInstance();  
        for (int i = 0; i < simTimes; i++) {
            Map map = mgr.getMap(i);	//getmap[]
            Point point = map.getStartPoint(); 	//get start point 
            for (int j = 0; j < simSteps; j++) {
                State state = map.getState(point);
                byte actionCode = ind.getActionCode(state);
                fitness += action(point, map, actionCode);
            }                               
        }       
        return fitness / simTimes;
    }

    public static int getFitnessPall(Individual ind) {
        int fitness = 0;        
        if (DefaultSimTimes < 100) {
            fitness = getFitness(ind);
        } else {                            
            FutureTask<Integer>[] tasks = new FutureTask[cores];            
            for (int i = 0; i < cores; i++) {
                FitnessPall pall = null;
                if (i == 0) {
                    pall = new FitnessPall(ind, (DefaultSimTimes / cores) + DefaultSimTimes % cores);
                } else {
                    pall = new FitnessPall(ind, DefaultSimTimes / cores);   
                }               
                tasks[i] = new FutureTask<Integer>(pall);
                Thread thread = new Thread(tasks[i]);
                thread.start();
            }       
            for (int i = 0; i < cores; i++) {
                try {
                    fitness += tasks[i].get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
            fitness = fitness / cores;
        }
        return fitness;
    }


    private static int action(Point point, Map map, int actionCode) {
        int sorce = 0;
        switch (actionCode) {
        //down
        case 0:
            if (map.isInMap(point.x, point.y - 1)) {
                sorce = 0;
                point.y = point.y - 1;
            } else {
                sorce = -5;
            }           
            break;
        //left
        case 1:
            if (map.isInMap(point.x - 1, point.y)) {
                sorce = 0;
                point.x = point.x - 1;
            } else {
                sorce = -5;
            }
            break;
        //up
        case 2:
            if (map.isInMap(point.x, point.y + 1)) {
                sorce = 0;
                point.y = point.y + 1;
            } else {
                sorce = -5;
            }
            break;
        //right
        case 3: 
            if (map.isInMap(point.x + 1, point.y)) {
                sorce = 0;
                point.x = point.x + 1;
            } else {
                sorce = -5;
            }
            break;
        
            
         //eat brains
        case 4:
            if (map.eatBrain(point.x, point.y)) {
                sorce = 10;             
            } else {
                sorce = -1;
            }
            break;
            
        //don't move
        case 5: 
            sorce = 0;
            break;
        }
        return sorce;
    }


}

class FitnessPall implements Callable<Integer> {
    private int simTimes;
    private Individual ind;
    public FitnessPall(Individual ind, int simTimes) {
        this.ind = ind;
        this.simTimes = simTimes;       
    }

    @Override
    public Integer call() throws Exception {
        return FitnessCalc.getFitness(ind, simTimes);       
    }   
}