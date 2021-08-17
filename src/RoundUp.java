import java.util.logging.Logger;
import java.util.logging.Level;

public class RoundUp {
    private static final Logger logger = Logger.getLogger(RoundUp.class.getName());
    public static void main(String[] args) {
        try {
            RoundUpExecutor roundUpExecutor = new RoundUpExecutor();
            roundUpExecutor.execute();
        } catch(Throwable t) {
            logger.log(Level.SEVERE, "Exception occurred", t);
            System.exit(1);
        }
    }
}
