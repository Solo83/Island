public class Caterpillar extends Herbivore {

    private static int count; //?????????? ?????? ????

    public Caterpillar() {
        super(Population.CATERPILLAR.getPicture(), Population.CATERPILLAR.getWeight(), Population.CATERPILLAR.getSpeed(), Population.CATERPILLAR.getMovesForDeath(), Population.CATERPILLAR.getMaxPopulationOnCell());
        this.type = Population.CATERPILLAR;
        this.id = count;
        count++;
    }

    public int getCount() {
        return id;
    }


}
