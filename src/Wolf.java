import java.util.HashMap;
import java.util.Map;

public class Wolf extends Predator {

    private static int count; //количество особей вида


    private static final Map<Class<? extends Animal>, Double> menu = new HashMap<>();

    static {
        menu.put(Horse.class, 10.0);
        menu.put(Deer.class, 15.0);
        menu.put(Rabbit.class, 60.0);
        menu.put(Mouse.class, 80.0);
        menu.put(Goat.class, 60.0);
        menu.put(Sheep.class, 70.0);
    }

    public Wolf() {
        super(Population.WOLF.getPicture(), Population.WOLF.getWeight(), Population.WOLF.getSpeed(), Population.WOLF.getMovesForDeath(), Population.WOLF.getMaxPopulationOnCell(),menu);
        this.type = Population.WOLF;
        this.id = count;
        count++;
    }

    public int getCount() {
        return id;
    }
}




