package interfaces;

import org.testng.ITestNGMethod;
import report.TestReport;

/*
  The ITest interface defines the common method for executing tests.
  Any test class must implement this interface.
 */
public interface ITest {
    void run();
    String getName(); // Test name for reporting
    ITestNGMethod getMethod();
    TestReport execute();
}








