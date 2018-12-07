package zombieWalk;

public class MapMgr {

    private static int x = 10;
    private static int y = 10;
    private static int beanNum = 50;
    private static int mapNum = 100;

    private static MapMgr manager = null;       
    private Map[] maps = null;

    private MapMgr() {
        maps = new Map[mapNum];
        for (int i = 0; i < mapNum; i++) {
            Map map = new Map(x, y);
            map.setBrain(beanNum);
            maps[i] = map;
        }
    }

    synchronized public static MapMgr getInstance() {
        if (manager == null) manager = new MapMgr();
        return manager;
    }

    public Map getMap(int index) {
        Map map = null;
        index = index % mapNum;
        try {
            map = maps[index].clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return map;     
    }

}
