public class Rabbit extends Herbivore {

    private static int count; //количество особей вида

    public Rabbit() {
        super(Population.RABBIT.getPicture(), Population.RABBIT.getWeight(), Population.RABBIT.getSpeed(), Population.RABBIT.getMovesForDeath(), Population.RABBIT.getMaxPopulationOnCell());
        this.type = Population.RABBIT;
        this.id = count;
        count++;
    }

    public int getCount() {
        return id;
    }
}
