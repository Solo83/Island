public class Sheep extends Herbivore {

    private static int count; //количество особей вида

    public Sheep() {
        super(Population.SHEEP.getPicture(), Population.SHEEP.getWeight(), Population.SHEEP.getSpeed(), Population.SHEEP.getMovesForDeath(), Population.SHEEP.getMaxPopulationOnCell());
        this.type = Population.SHEEP;
        this.id = count;
        count++;
    }

    public int getCount() {
        return id;
    }


}
