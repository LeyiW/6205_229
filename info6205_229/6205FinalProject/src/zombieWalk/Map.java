package zombieWalk;

import java.awt.Point;

public class Map implements Cloneable{

	//x is the x-axis of map
	//y is the y-axis of map
    private int x = -1;
    private int y = -1;
    private int total = -1;
    private byte[][] mapGrid = null;
    
    

    public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Map(int x, int y) {
        this.x = x;
        this.y = y;
        mapGrid = new byte[x][y];
        total = x * y;
    }

    public void setBrain(int num) {
        //check num 
        if (num > total) {
            num = total;
        }
        for (int i = 0; i < num; i++) {
            int address, xp, yp;
            do{
                address = (int) Math.floor((Math.random() * total)); //Generate random number 0 - (total-1)          
                xp = address / y;
                yp = address % y;   
            } while (mapGrid[xp][yp] != 0);
            mapGrid[xp][yp] = 1;            
        }

    }

    public boolean isInMap(int x, int y) {      
        if (x < 0 || x >= this.x) return false;
        if (y < 0 || y >= this.y) return false;     
        return true;
    }

    public boolean hasBrain(int x, int y) {
        boolean ret = mapGrid[x][y] == 0 ? false : true;
        return ret;
    }

    public boolean eatBrain(int x, int y) {
        if(hasBrain(x, y)) {
            mapGrid[x][y] = 0;
            return true;
        }
        return false;
    }

    public Point getStartPoint() {              
        int x = (int) Math.floor(Math.random() * this.x);
        int y = (int) Math.floor(Math.random() * this.y);       
        return new Point(x, y);
    }

    public State getState(Point p) {        
        byte middle = stateOfPoint(p);
        byte up = stateOfPoint(new Point(p.x, p.y - 1));
        byte right = stateOfPoint(new Point(p.x + 1, p.y));
        byte down = stateOfPoint(new Point(p.x, p.y + 1));
        byte left = stateOfPoint(new Point(p.x - 1, p.y));
        return new State(middle, up, right, down, left);
    }

    //0 is spike, 1 is has Brain, 2 is nothing
    private byte stateOfPoint(Point p) {
        byte ret;

        if (!isInMap(p.x, p.y)) ret = 0;            
        else if (mapGrid[p.x][p.y] == 0) ret =  2;
        else ret = 1;

        return ret;
    }


    @Override
    public Map clone() throws CloneNotSupportedException {
        Map m = (Map) super.clone();
        byte[][] mapGrid = new byte[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                mapGrid[i][j] = this.mapGrid[i][j];
            }
        }
        m.mapGrid = mapGrid;
        return m;       
    }

    public void print() {
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                System.out.print(mapGrid[j][i]);
            }
            System.out.println();
        }
    }

    

}

