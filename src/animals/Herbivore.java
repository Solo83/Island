package animals;

import controller.Animal;
import controller.Island;

public abstract class Herbivore extends Animal {


    // public static AtomicInteger herbivorousCount= new AtomicInteger(0);
    public static int herbivorousCount;


    public Herbivore(String picture, double weight, int speed, int movesForDeath, int maxPopulation) {
        super(picture, weight, speed, movesForDeath, maxPopulation);
        herbivorousCount++;

    }

    @Override
    public void decreaseAnimal() {
        herbivorousCount--;
    }

    @Override
    public void eat(Island.Cell cell) {

        if (cell.getPlants().size() > 0) {

            for (Plants plant : cell.getPlants()) {
                cell.getPlants().remove(plant);
                Plants.decreasePlants();
                this.setSaturation(this.type.getMaxSaturation());
                this.setMovesForDead(this.type.getMovesForDeath());
                break;
            }

        } else {
            this.setSaturation(0);
            this.setMovesForDead(getMovesForDead() - 1);
        }

        if (this.getMovesForDead() <= 0) {
            cell.getAnimals().remove(this);
            this.decreaseAnimal();
            setAllAnimalCount(getAllAnimalCount() - 1);
        }
    }

}
