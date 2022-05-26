
public enum Population {
    WOLF("\uD83D\uDC3A",30,8.0,3,5,50.0,3),
    SHEEP("\uD83D\uDC11",140,15.0,3,2,70.0,2),
    PLANTS ("\uD83C\uDF3F",200,0.0,0,0,0,0),
    HORSE ("\uD83D\uDC0E",20,60.0,4,5,400.0,2),
    DEER ("\uD83E\uDD8C",20,50.0,4,7,300.0,2),
    RABBIT ("\uD83D\uDC07",150,0.45,2,15,2.0,5),
    MOUSE ("\uD83D\uDC01",500,0.01,1,10,0.05,12),
    GOAT ("\uD83D\uDC10",140,10.0,3,10,60.0,2),
    SNAKE("\uD83D\uDC0D",30,3.0,1,5,15.0,10),
    FOX("\uD83E\uDD8A",30,2.0,2,5,2.0,3),
    BEAR ("\uD83D\uDC3B",5,80.0,2,5,500.0,2),
    EAGLE ("\uD83E\uDD85", 20,1.0,3,5,6.0,2),
    CATERPILLAR("\uD83D\uDC1B",100,0,0,3,0.01,20),
    DUCK ("\uD83E\uDD86",200,0.15,4,10,1,10),

    APER ("\uD83D\uDC17",50,50,2,10,400,3),

    ;

    private String picture; // аватар Unicode
    private int maxPopulationOnCell; //Максимальное количество животных этого вида на одной клетке
    private double maxSaturation; //Сколько килограммов пищи нужно животному для полного насыщения
    private int speed;// Скорость перемещения, не более чем, клеток за ход

    private int movesForDeath; //Сколько ходов (тактов) животное может жить после падения шкалы сытости до нуля
    private double weight; // Вес одного животного, кг
    private int move; //Сколько ходов (тактов) животное может жить после падения шкалы сытости до нуля
    private int maxAmountOfChildrens; //Число особей вида для размножения

    Population(String picture, int maxPopulationOnCell, double maxSaturation, int speed, int movesForDeath, double weight,int maxAmountOfChildrens) {
        this.picture = picture;
        this.maxPopulationOnCell = maxPopulationOnCell;
        this.maxSaturation =  maxSaturation;
        this.speed = speed;
        this.weight = weight;
        this.movesForDeath = movesForDeath;
        this.maxAmountOfChildrens = maxAmountOfChildrens;

    }

    public int getMaxPopulationOnCell() {
        return maxPopulationOnCell;
    }
    public double getMaxSaturation() {
        return maxSaturation;
    }
    public String getPicture() { return picture; }
    public int getSpeed() {return speed;}
    public double getWeight() {return weight;}
    public int getMovesForDeath() {
        return movesForDeath;
    }
    public int getMaxAmountOfChildrens() {
        return maxAmountOfChildrens;
    }
}
