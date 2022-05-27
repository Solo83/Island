package animals;

public class Mouse extends Herbivore {

    private static int count; //количество особей вида

    public Mouse() {
        super(Population.MOUSE.getPicture(), Population.MOUSE.getWeight(), Population.MOUSE.getSpeed(), Population.MOUSE.getMovesForDeath(), Population.MOUSE.getMaxPopulationOnCell());
        this.type = Population.MOUSE;
        this.id = count;
        count++;
    }

    public int getCount() {
        return id;
    }
}
