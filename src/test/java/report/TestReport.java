package report;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


  //Class that generates detailed reports for tests

public class TestReport {

    private static TestReport instance;
    private final String testTitle;
    private final List<String> logs = new ArrayList<>();
    private final List<String> errors = new ArrayList<>();
    private final List<TestResult> testResults = new ArrayList<>();
    private long startTime;
    private long endTime;
    private boolean status;
    private String pdfPath;

    public TestReport(String testTitle) {
        this.testTitle = testTitle;
        this.startTime = System.currentTimeMillis();
    }

    // Metoda getInstance()
    public static TestReport getInstance(String reportTitle) {
        if (instance == null) {
            instance = new TestReport(reportTitle);
        }
        return instance;
    }

    public void log(String message) {
        logs.add(message);
    }

    public void logError(String error) {
        errors.add(error);
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setExecutionTime() {
        this.endTime = System.currentTimeMillis();
    }

    public long getExecutionTime() {
        return endTime - startTime;
    }

    public String getTestTitle() {
        return testTitle;
    }

    public void addTestResult(TestResult result) {
        testResults.add(result);
    }

    public String getDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Title: ").append(testTitle).append("\n")
                .append("Status: ").append(status ? "Passed" : "Failed").append("\n")
                .append("Execution time: ").append(getExecutionTime()).append("ms\n")
                .append("Steps performed:\n");

        for (String log : logs) {
            details.append("- ").append(log).append("\n");
        }

        if (!errors.isEmpty()) {
            details.append("Errors:\n");
            for (String error : errors) {
                details.append("- ").append(error).append("\n");
            }
        }

        return details.toString();
    }

    public void exportToPDF(String filePath) {
        try {
            PdfWriter writer = new PdfWriter(filePath);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            PdfFont boldFont = PdfFontFactory.createFont(com.itextpdf.io.font.constants.StandardFonts.HELVETICA_BOLD);
            PdfFont normalFont = PdfFontFactory.createFont(com.itextpdf.io.font.constants.StandardFonts.HELVETICA);

            document.add(new Paragraph("Automated Test Report").setFont(boldFont).setFontSize(18));
            document.add(new Paragraph("Report Date: " + java.time.LocalDateTime.now()).setFont(normalFont));
            document.add(new Paragraph("Operating System: " + System.getProperty("os.name")).setFont(normalFont));
            document.add(new Paragraph("Java Version: " + System.getProperty("java.version")).setFont(normalFont));
            document.add(new Paragraph("\n====================\n").setFont(normalFont));

            long totalExecutionTime = 0;

            for (TestResult result : testResults) {
                document.add(new Paragraph("Test Name: " + result.getTestName()).setFont(boldFont));
                document.add(new Paragraph("Status: " + (result.isPassed() ? "PASS" : "FAIL")).setFont(normalFont));
                document.add(new Paragraph("Execution Time: " + result.getExecutionTime() + " ms").setFont(normalFont));

                totalExecutionTime += result.getExecutionTime();

                if (!result.isPassed()) {
                    document.add(new Paragraph("Error Message: " + result.getErrorMessage()).setFontColor(ColorConstants.RED));
                }

                document.add(new Paragraph("\nTest Steps:").setFont(boldFont));
                for (String step : result.getSteps()) {
                    document.add(new Paragraph("- " + step).setFont(normalFont));
                }

                document.add(new Paragraph("\n====================\n").setFont(normalFont));
            }

            document.add(new Paragraph("Total Execution Time: " + totalExecutionTime + " ms").setFont(boldFont));
            document.close();

            this.pdfPath = filePath;
            System.out.println("PDF Report successfully generated: " + filePath);
        } catch (IOException e) {
            System.err.println("Error generating PDF report: " + e.getMessage());
        }
    }

    public void printReport() {
        System.out.println("======= TEST REPORT =======");
        for (TestResult result : testResults) {
            System.out.println("Test Name: " + result.getTestName());
            System.out.println("Status: " + (result.isPassed() ? "PASS" : "FAIL"));
            System.out.println("Execution Time: " + result.getExecutionTime() + " ms");
            if (!result.isPassed()) {
                System.out.println("Error: " + result.getErrorMessage());
            }
            System.out.println("Steps:");
            for (String step : result.getSteps()) {
                System.out.println(" - " + step);
            }
            System.out.println("-----------------------------");
        }
    }

    public void openPDF(String filePath) {
        try {
            File pdfFile = new File(filePath);
            if (pdfFile.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(pdfFile);
                    System.out.println("PDF report opened: " + filePath);
                } else {
                    System.err.println("Desktop not supported. Cannot open PDF.");
                }
            } else {
                System.err.println("PDF file does not exist: " + filePath);
            }
        } catch (IOException e) {
            System.err.println("Error opening PDF: " + e.getMessage());
        }
    }

    public void exportToCSV(String filePath) {
        try (PrintWriter writer = new PrintWriter(new File(filePath))) {
            StringBuilder sb = new StringBuilder();
            sb.append("Test Name,Status,Execution Time (ms),Error Message,Steps\n");

            for (TestResult result : testResults) {
                sb.append(result.getTestName()).append(",");
                sb.append(result.isPassed() ? "PASS" : "FAIL").append(",");
                sb.append(result.getExecutionTime()).append(",");
                sb.append(result.getErrorMessage() != null ? result.getErrorMessage().replace(",", " ") : "").append(",");

                if (result.getSteps() != null && !result.getSteps().isEmpty()) {
                    String stepsCombined = String.join(" | ", result.getSteps()).replace(",", " ");
                    sb.append("\"").append(stepsCombined).append("\"");
                }

                sb.append("\n");
            }

            writer.write(sb.toString());
            System.out.println("CSV Report successfully generated: " + filePath);
        } catch (FileNotFoundException e) {
            System.err.println("Error generating CSV report: " + e.getMessage());
        }
    }

    public String getPDFReport() {
        return pdfPath;
    }
}