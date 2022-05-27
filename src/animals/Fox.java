package animals;

import controller.Animal;

import java.util.HashMap;
import java.util.Map;

public class Fox extends Predator {

    private static int count; //?????????? ?????? ????

    private static final Map<Class<? extends Animal>, Double> menu = new HashMap<>();

    static {
        menu.put(Rabbit.class, 70.0);
        menu.put(Mouse.class, 90.0);
    }

    public Fox() {
        super(Population.FOX.getPicture(), Population.FOX.getWeight(), Population.FOX.getSpeed(), Population.FOX.getMovesForDeath(), Population.FOX.getMaxPopulationOnCell(), menu);
        this.type = Population.FOX;
        this.id = count;
        count++;
    }

    public int getCount() {
        return id;
    }

}