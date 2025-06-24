package listeners;

import org.testng.ISuite;          // Importăm interfața ce reprezintă o suite de teste
import org.testng.ISuiteListener;  // Importăm interfața suite listener

// Our custom suite listener class, which implements ISuiteListener
public class CustomSuiteListener implements ISuiteListener {

    // Method called before suite execution
    @Override
    public void onStart(ISuite suite) {
        // We log the name of the suite to be executed
        System.out.println("Suite " + suite.getName() + " the execution began");
        // Initializes resources such as global report configuration or settings for all tests
    }

    // Method called after the suite is completed
    @Override
    public void onFinish(ISuite suite) {
        // We log that the suite has ended
        System.out.println("Suite " + suite.getName() + " completed the execution");
        // Performs final cleanup operations and generates the cumulative report for the entire suite
    }
}