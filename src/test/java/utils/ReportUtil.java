package utils;

import org.testng.internal.TestResult;
import java.util.ArrayList;
import java.util.List;

public class ReportUtil {
    private static ReportUtil instance;
    private List<TestResult> testResults = new ArrayList<>();
    private List<String> reportMessages = new ArrayList<>(); // Store report messages

    private ReportUtil() {}

    public static ReportUtil getInstance() {
        if (instance == null) {
            instance = new ReportUtil();
        }
        return instance;
    }

    public void addTestResult(TestResult result) {
        testResults.add(result);
    }

    public List<String> getReportMessages() {
        return reportMessages;
    }

    public void logReportMessage(String message) {
        reportMessages.add(message);
    }
}
