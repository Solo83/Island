import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Predator extends Animal {

    private final Map<Class<? extends Animal>, Double> menu; //меню

    public static int predatorCount;

    public Predator(String picture, double weight, int speed, int movesForDeath, int maxPopulation, Map<Class<? extends Animal>, Double> menu) {
        super(picture, weight, speed, movesForDeath, maxPopulation);
        this.menu=menu;
        predatorCount++;

    }

    @Override
    public void decreaseAnimal() {
        predatorCount--;
    }

    public void eat(Island.Cell cell) {

        Animal animalToEat = null;

        Class<? extends Animal> willBeEaten = getRandomFood(this.menu); //Получаем тип еды из меню

        for (Animal animal: cell.getAnimals() ) {
            if (animal.getClass().getName().equals(willBeEaten.getName())) {
                animalToEat=animal;
            }
        }

        if (animalToEat!=null) { // Если есть, что поесть

            cell.getAnimals().remove(animalToEat);
            this.setSaturation(this.getSaturation() + animalToEat.getWeight());
            this.setMovesForDead(this.type.getMovesForDeath()); // Установим кол-во шагов голодным в первоначальное состояние
            animalToEat.decreaseAnimal();
            setAllAnimalCount(getAllAnimalCount()-1);
        }

        else {  // Если нет еды
            this.setSaturation(0);
            this.setMovesForDead(getMovesForDead()-1);}

        if (this.getMovesForDead()<=0) {
            cell.getAnimals().remove(this);
            this.decreaseAnimal();
            setAllAnimalCount(getAllAnimalCount()-1);
        }

    }

    private Class<? extends Animal> getRandomFood(Map<Class<? extends Animal>, Double> menu) { // получить тип для поедания

        double totalProbability = menu.values().stream().mapToDouble(d -> d).sum();
        double x = ThreadLocalRandom.current().nextDouble() * totalProbability;
        Class<? extends Animal> lastElement = null;

        for (Map.Entry<Class<? extends Animal>, Double> entry : menu.entrySet()) {
            x -= entry.getValue();
            if (x <= 0) {
                return entry.getKey();
            }
            lastElement = entry.getKey();
        }
        return lastElement;
    }


}
