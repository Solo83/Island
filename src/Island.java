import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Island {

    private static Cell[][] islandMap;

    public  static Cell[][] getIslandMap() {
        return islandMap;
    }

    public void generateIsland(int width, int height) { // Создаем остров

        islandMap = new Island.Cell[width][height];

        for (int i = 0; i < islandMap.length; i++) {
            for (int j = 0; j < islandMap[i].length; j++) {
                islandMap[i][j] = new Cell(i,j);

            }
        }
    }

    public void settleIsland() {

        for (Cell[] cells : islandMap) {
            for (Cell cell : cells) {
                for (Population population : Population.values()) {
                    if (!population.name().equals("PLANTS")) {
                        for (int k = 0; k < ThreadLocalRandom.current().nextInt(population.getMaxPopulationOnCell()); k++)
                            cell.getAnimals().add((Animal) fillObjectsOnCell(population));
                        }
                }
            }
        }

        for (Cell[] cells : islandMap) {
            for (Cell cell : cells) {
                 Collections.shuffle(cell.getAnimals());
            }
        }
    }

    public void growPlants(Island.Cell cell) {

              for (int k = 0; k < ThreadLocalRandom.current().nextInt(Population.PLANTS.getMaxPopulationOnCell());  k++)
                    cell.getPlants().add((new Plants()));

    }

    public static Object fillObjectsOnCell(Population type) { //Фабрика для заполнения острова объектами

        return switch (type) {

            case WOLF -> new Wolf();
            case SHEEP -> new Sheep();
            case DEER -> new Deer();
            case MOUSE -> new Mouse();
            case GOAT -> new Goat();
            case HORSE -> new Horse();
            case RABBIT -> new Rabbit();
            case FOX -> new Fox();
            case BEAR -> new Bear();
            case EAGLE -> new Eagle();
            case SNAKE -> new Snake();
            case CATERPILLAR -> new Caterpillar();
            case PLANTS -> new Plants();
            case APER -> new Aper();
            case DUCK -> new Duck();

        };
    }

    public void eatAllOnCell(Island.Cell cell) {  //Общий метод для еды

                for (Animal animals : cell.getAnimals()) {
                    if (animals instanceof Herbivore)
                        animals.eat(cell);
                }

                for (Animal animals : cell.getAnimals()) {
                    if (animals instanceof Predator)
                        animals.eat(cell);
                }
    }

    public void moveAll() { //Общий метод для ходьбы

        for (Island.Cell[] cells : islandMap) {
            for (Island.Cell cell : cells) {
                  Animal.move(cell);
            }
        }

        for (Island.Cell[] cells : islandMap) {
            for (Island.Cell cell : cells) {
                for (Animal animal : cell.getAnimals()) {
                    animal.setMoved(false);
                }
            }
        }
    }

    public void islandViewer() { // Статистика
        System.out.println("\nTurn: "  + Turn.getTurnId());

        System.out.printf("\nTOTAL: Animals: %s, Herbivores: %s, Predators: %s, Plants: %s. \n\n", Animal.getAllAnimalCount(), Herbivore.herbivorousCount, Predator.predatorCount, Plants.getAllPlantsCount());

        for (Cell[] cells : islandMap) {
            for (Cell cell : cells) {

                Map<Object, Integer> collect = Stream.of(cell.animals, cell.plants)
                        .flatMap(Collection::stream)
                        .collect(Collectors.toMap(Object::toString, e -> 1, Integer::sum));

                System.out.print(collect);
            }
            System.out.println();
        }
    }

    public void multiplyOnCell(Island.Cell cell) { //размножаемся
         Animal.multiply(cell);
    }



    public class Cell { // Ячейка острова

        private int x;
        private int y;

        private List<Animal> animals = new CopyOnWriteArrayList<>();
        private List<Plants> plants = new CopyOnWriteArrayList<>();

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public List<Animal> getAnimals() {
            return animals;
        }

        public List<Plants> getPlants() {
            return plants;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }


        public int getNumberOfSpeciesOnCell (Object o) {

            Map<Object, Integer> collect = Stream.of(animals)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toMap(Object::toString, e -> 1, Integer::sum));

            return collect.get(o.toString());
        }

    }

}

