package domein;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VerzoekLogger {

    //TODO attrib
    private final ArrayBlockingQueue<String> logQueue = new ArrayBlockingQueue<>(1000);

    public void log(String string) {
        try {
            logQueue.put(string);
        } catch (InterruptedException ex) {
            Logger.getLogger(VerzoekLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String haalLogOp() {
        try {
            return logQueue.take();
        } catch (InterruptedException ex) {
            Logger.getLogger(VerzoekLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
