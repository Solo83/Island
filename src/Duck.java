import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Duck extends Herbivore{

    private static int count;
    private static final Map<Class<?>, Double> menu = new HashMap<>();

    static {
        menu.put(Plants.class, 100.0);
        menu.put(Caterpillar.class, 90.0);

    }

    public Duck() {
        super(Population.DUCK.getPicture(), Population.DUCK.getWeight(), Population.DUCK.getSpeed(), Population.DUCK.getMovesForDeath(), Population.DUCK.getMaxPopulationOnCell());
        this.type = Population.DUCK;
        this.id = count;
        count++;
    }

    public int getCount() {
        return id;
    }

    @Override
    public void eat(Island.Cell cell) {

      Class<?> willBeEaten = getRandomFood(menu);


   if (willBeEaten.getSimpleName().equalsIgnoreCase("PLANTS")) {
            super.eat(cell);
        } else {

       Animal animalToEat = null;

       for (Animal animal: cell.getAnimals() ) {
           if (animal.getClass().getName().equals(willBeEaten.getName())) {
               animalToEat=animal;
               break;
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

    }

    public Class<?> getRandomFood(Map<Class<?>, Double> menu) {

        double totalProbability = menu.values().stream().mapToDouble(d -> d).sum();

        double x = ThreadLocalRandom.current().nextDouble() * totalProbability;
        Class<?> lastElement = null;

        for (Map.Entry<Class<?>, Double> entry : menu.entrySet()) {
            x -= entry.getValue();
            if (x <= 0) {
                return entry.getKey();
            }
            lastElement = entry.getKey();
        }
        return lastElement;
    }


}
