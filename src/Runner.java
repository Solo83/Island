import java.util.concurrent.*;

public class Runner {

    public static void main(String[] args) {

        Island newIsland = new Island();
        newIsland.generateIsland(20, 20);
        newIsland.settleIsland();

     ScheduledExecutorService service = Executors.newScheduledThreadPool(2);

             service.scheduleAtFixedRate(new Turn(newIsland),0,2,TimeUnit.SECONDS);

    }


}


