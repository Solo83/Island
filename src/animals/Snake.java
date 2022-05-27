package animals;

import controller.Animal;

import java.util.HashMap;
import java.util.Map;

public class Snake extends Predator {

    private static int count; //?????????? ?????? ????

    private static final Map<Class<? extends Animal>, Double> menu = new HashMap<>();

    static {
        menu.put(Eagle.class, 15.0);
        menu.put(Rabbit.class, 20.0);
        menu.put(Mouse.class, 40.0);
    }

    public Snake() {
        super(Population.SNAKE.getPicture(), Population.SNAKE.getWeight(), Population.SNAKE.getSpeed(), Population.SNAKE.getMovesForDeath(), Population.SNAKE.getMaxPopulationOnCell(), menu);
        this.type = Population.SNAKE;
        this.id = count;
        count++;
    }

    public int getCount() {
        return id;
    }

}
