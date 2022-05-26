import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Animal {

    public Population type;
    int id;

    private final String picture;
    private final double weight; // Вес одного животного, кг
    private final int speed; // Скорость перемещения, не более чем, клеток за ход
    private double saturation; //Сытость
    private int movesForDead; //Сколько ходов (тактов) животное может жить после падения шкалы сытости до нуля
    private static int allAnimalCount; // счетчик типа
    private final int maxPopulationOnCell; // Максимальное количество животных этого вида на одной клетке

    private Map<Class<? extends Animal>, Double> menu; //меню

    private boolean isMoved; // Флаг, что сходил

    public Animal(String picture, double weight, int speed, int movesForDead, int maxPopulation, Map<Class<? extends Animal>, Double> menu) { //конструктор с Меню

        this.picture = picture;
        this.weight = weight;
        this.speed = speed;
        this.movesForDead = movesForDead;
        this.maxPopulationOnCell = maxPopulation;
        this.menu = menu;

        allAnimalCount++;

    }

    public Animal(String picture, double weight, int speed, int movesForDead, int maxPopulation) {

        this.picture = picture;
        this.weight = weight;
        this.speed = speed;
        this.movesForDead = movesForDead;
        this.maxPopulationOnCell = maxPopulation;

        allAnimalCount++;

    }


    public double getWeight() {
        return weight;
    }

    public int getSpeed() {
        return speed;
    }

    public double getSaturation() {
        return saturation;
    }

    public int getMovesForDead() {
        return movesForDead;
    }

    public Population getType() {
        return type;
    }

    public static int getAllAnimalCount() {
        return allAnimalCount;
    }


    public static void setAllAnimalCount(int allAnimalCount) {
        Animal.allAnimalCount=allAnimalCount;
    }

    public void setSaturation(double saturation) {
        this.saturation = saturation;
    }

    public void setMoved(boolean moved) {
        isMoved = moved;
    }

    public void setMovesForDead(int movesForDead) {
        this.movesForDead = movesForDead;
    }

    public static void move (Island.Cell cell){ // Ходим по карте

        Island.Cell[][] islandMap = Island.getIslandMap();

                for (Animal animal : cell.getAnimals()) {

                    while (!animal.isMoved && animal.type.getSpeed()!=0) {

                        int speed = ThreadLocalRandom.current().nextInt(animal.getSpeed());
                        int direction = ThreadLocalRandom.current().nextInt(4);

                        if (speed<=0) {
                                animal.isMoved=true;
                            break;
                        }

                        if (direction == 1) { // вверх
                            if (checkBounds(cell.getX() - speed, cell.getY())) {
                                animal.isMoved=true;
                                islandMap[cell.getX() - speed][cell.getY()].getAnimals().add(animal);

                                  if ( islandMap[cell.getX() - speed][cell.getY()].getNumberOfSpeciesOnCell(animal) > animal.maxPopulationOnCell) {
                                      islandMap[cell.getX() - speed][cell.getY()].getAnimals().remove(animal);

                                  } else {cell.getAnimals().remove(animal);}

                        }

                        } else if (direction == 2) { //вниз
                            if (checkBounds(cell.getX() + speed, cell.getY())) {
                                animal.isMoved=true;
                                islandMap[cell.getX() + speed][cell.getY()].getAnimals().add(animal);

                                if ( islandMap[cell.getX() + speed][cell.getY()].getNumberOfSpeciesOnCell(animal) > animal.maxPopulationOnCell) {
                                    islandMap[cell.getX() + speed][cell.getY()].getAnimals().remove(animal);}

                                else {cell.getAnimals().remove(animal);}

                            }

                        } else if (direction == 3) { //влево
                            if (checkBounds(cell.getX(), cell.getY() - speed)) {
                                animal.isMoved=true;
                                islandMap[cell.getX()][cell.getY() - speed].getAnimals().add(animal);

                                if ( islandMap[cell.getX()][cell.getY() - speed].getNumberOfSpeciesOnCell(animal) > animal.maxPopulationOnCell) {
                                    islandMap[cell.getX()][cell.getY() - speed].getAnimals().remove(animal);}

                                else {cell.getAnimals().remove(animal);}

                            }

                        } else  //вправо
                            if (checkBounds(cell.getX(), cell.getY() + speed) && cell.getNumberOfSpeciesOnCell(animal) < animal.maxPopulationOnCell) {
                                animal.isMoved=true;
                                islandMap[cell.getX()][cell.getY() + speed].getAnimals().add(animal);

                                if ( islandMap[cell.getX()][cell.getY() + speed].getNumberOfSpeciesOnCell(animal) > animal.maxPopulationOnCell) {
                                    islandMap[cell.getX()][cell.getY() + speed].getAnimals().remove(animal);}

                                else {cell.getAnimals().remove(animal);}
                            }
                    }
                }
        }

    private static boolean checkBounds ( int x, int y) { // проверяем выход за границы карты

        int height = Island.getIslandMap().length-1;
        int weight = Island.getIslandMap()[0].length-1;
        return (x >= 0 && x <= height && y >= 0 && y <= weight);

    }

    public static void multiply (Island.Cell cell) { // Здесь размножаемся по четному числу особей

        List<Animal> copy = new CopyOnWriteArrayList<>(List.copyOf(cell.getAnimals()));
        List<Animal> burned = new CopyOnWriteArrayList<>();

        for (Animal animal : copy) {

            if (getCountOfSpecies(animal, copy)==animal.maxPopulationOnCell){
                continue;
            }

            if (getCountOfSpecies(animal, copy)>=2) {

                if ((getCountOfSpecies(animal, copy)%2!=0)) {
                    copy.remove(animal);
                    continue;
                }

                for (int i = 0; i <  ThreadLocalRandom.current().nextInt(animal.type.getMaxAmountOfChildrens()+1) ; i++) {

                    Animal burnedAnimal = (Animal)Island.fillObjectsOnCell(animal.getType());

                    burned.add(burnedAnimal);
                }
                copy.remove(animal);
            }
        }

        for (Animal burnedAnimal : burned) {

            if (getCountOfSpecies(burnedAnimal,cell.getAnimals())+getCountOfSpecies(burnedAnimal,burned) > burnedAnimal.maxPopulationOnCell ) {
                burned.remove(burnedAnimal);
                burnedAnimal.decreaseAnimal();
                allAnimalCount--;
            }

           else cell.getAnimals().add(burnedAnimal);
        }
    }

    private static int getCountOfSpecies (Object o, List<Animal> animals ) { // Подсчет числа особей

        Map<Object, Integer> collect = Stream.of(animals)
                .flatMap(Collection::stream)
                .collect(Collectors.toMap(Object::toString, e -> 1, Integer::sum));

        return collect.get(o.toString());
    }

    @Override
    public String toString() {
        //return  "[" + this.getClass().getSimpleName().toUpperCase() + "]";
        return  picture; // Если будут проблемы с кодировкой, раскомментировать верхний
    }

    public abstract void eat(Island.Cell cell);

    public void decreaseAnimal () {
         allAnimalCount--;
    }
}
