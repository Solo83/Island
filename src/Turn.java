import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.atomic.AtomicInteger;

public class Turn implements Runnable{

    private static AtomicInteger id = new AtomicInteger(0);
    private final Island island;

    public Turn(Island island) {
        this.island=island;

    }

    @Override
    public void run() {

        id.getAndIncrement();

        Island.Cell[][] islandMap = Island.getIslandMap();

        for (int i = 0; i < islandMap.length; i++) {
            for (int j = 0; j < islandMap.length; j++) {
                island.eatAllOnCell(islandMap[i][j]);
                island.multiplyOnCell(islandMap[i][j]);
                island.growPlants(islandMap[i][j]);
            }
        }

        island.moveAll();

        island.islandViewer();
    }

    public static AtomicInteger getTurnId() {
        return id;
    }
}
