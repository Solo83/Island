import java.util.HashMap;
import java.util.Map;

public class Eagle extends Predator{

    private static int count; //?????????? ?????? ????

    private static final Map<Class<? extends Animal>, Double> menu = new HashMap<>();

    static {
        menu.put(Fox.class, 10.0);
        menu.put(Rabbit.class, 90.0);
        menu.put(Mouse.class, 90.0);
    }

    public Eagle() {
        super(Population.EAGLE.getPicture(), Population.EAGLE.getWeight(), Population.EAGLE.getSpeed(), Population.EAGLE.getMovesForDeath(), Population.EAGLE.getMaxPopulationOnCell(), menu);
        this.type=Population.EAGLE;
        this.id = count;
        count++;
    }

    public int getCount() {
        return id;
    }

}