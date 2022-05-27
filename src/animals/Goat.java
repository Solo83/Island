package animals;

public class Goat extends Herbivore {

    private static int count; //количество особей вида

    public Goat() {
        super(Population.GOAT.getPicture(), Population.GOAT.getWeight(), Population.GOAT.getSpeed(), Population.GOAT.getMovesForDeath(), Population.GOAT.getMaxPopulationOnCell());
        this.type = Population.GOAT;
        this.id = count;
        count++;
    }

    public int getCount() {
        return id;
    }
}
