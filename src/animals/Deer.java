package animals;

public class Deer extends Herbivore {

    private static int count; //количество особей вида

    public Deer() {
        super(Population.DEER.getPicture(), Population.DEER.getWeight(), Population.DEER.getSpeed(), Population.DEER.getMovesForDeath(), Population.DEER.getMaxPopulationOnCell());
        this.type = Population.DEER;
        this.id = count;
        count++;
    }

    public int getCount() {
        return id;
    }


}
