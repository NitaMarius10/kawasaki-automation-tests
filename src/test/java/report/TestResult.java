package report;

import java.util.ArrayList;
import java.util.List;

public class TestResult {
    private String testName;
    private boolean passed;
    private String errorMessage;
    private long executionTime;
    private List<String> steps;
    private String status;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }


    public TestResult(String testName) {
        this.testName = testName;
        this.steps = new ArrayList<>();
    }

    // Getter and setter for testName
    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    // Getter and setter for passed
    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    // Getter and setter for errorMessage
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    // Getter and setter for executionTime
    public long getExecutionTime() {
        return executionTime;
    }

    public void addExecutionTime(long duration) {
        this.executionTime = duration;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    // Method to add a step to the list
    public void addStep(String step) {
        this.steps.add(step);
    }

    // Getter for the step list
    public List<String> getSteps() {
        return steps;
    }
}

