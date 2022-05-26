import java.util.concurrent.atomic.AtomicInteger;

public class Plants {

    private static int allPlantsCount;

    private String picture = Population.PLANTS.getPicture();

    public Plants () {
        allPlantsCount++;
    }

    public static int getAllPlantsCount() {
        return allPlantsCount;
    }

    public static void decreasePlants() {
        allPlantsCount--;
    }

    @Override
    public String toString() {
        //return  "[" + this.getClass().getSimpleName().toUpperCase() + "]";
        return  picture; // Если будут проблемы с кодировкой, раскомментировать верхний
    }
}
