package animals;

public class Horse extends Herbivore {

    private static int count; //количество особей вида

    public Horse() {
        super(Population.HORSE.getPicture(), Population.HORSE.getWeight(), Population.HORSE.getSpeed(), Population.HORSE.getMovesForDeath(), Population.HORSE.getMaxPopulationOnCell());
        this.type = Population.HORSE;
        this.id = count;
        count++;
    }

    public int getCount() {
        return id;
    }

}
