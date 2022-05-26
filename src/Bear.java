import java.util.HashMap;
import java.util.Map;

public class Bear extends Predator{

    private static int count; //?????????? ?????? ????

    private static final Map<Class<? extends Animal>, Double> menu = new HashMap<>();

    static {
        menu.put(Snake.class, 80.0);
        menu.put(Horse.class, 40.0);
        menu.put(Deer.class, 80.0);
        menu.put(Rabbit.class, 80.0);
        menu.put(Mouse.class, 90.0);
        menu.put(Goat.class, 70.0);
        menu.put(Sheep.class, 70.0);
    }

    public Bear() {
        super(Population.BEAR.getPicture(), Population.BEAR.getWeight(), Population.BEAR.getSpeed(), Population.BEAR.getMovesForDeath(), Population.BEAR.getMaxPopulationOnCell(), menu);
        this.type=Population.BEAR;
        this.id = count;
        count++;
    }

    public int getCount() {
        return id;
    }

}