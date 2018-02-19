import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    private static FlowersStockWebService_Service service = new FlowersStockWebService_Service();
    private static FlowersStockWebService sei = service.getFlowersStockWebServiceImplPort();
    private static Random rand = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                int increaseCount = 10 + rand.nextInt(21);
                sei.increaseFlowersStockSize(increaseCount);
            }
        }, 0, 10, TimeUnit.SECONDS);
    }
}
