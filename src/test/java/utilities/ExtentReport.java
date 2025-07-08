package utilities;
import java.awt.Desktop;
import java.io.FileNotFoundException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import baseClass.base;

public class ExtentReport extends base implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    String repName;

    public void onStart(ITestContext testContext) {
        // *SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        // Date dt = new Date();
        // String currentdatetimeStamp = df.format(dt);

        //String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // time stamp
       // repName = "Test-Report-" + timeStamp + ".html";
    	sparkReporter = new ExtentSparkReporter("C:\\Users\\bhanu\\eclipse-myworkspace\\OrangeHRM\\Report\\myreport.html");


        sparkReporter.config().setDocumentTitle("HRMOrange Automation"); // Title of report
        sparkReporter.config().setReportName("HRMOrange Functional Testing");  // Name of the report
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "HRMOrange");
        extent.setSystemInfo("Module", "Bhanu");
        extent.setSystemInfo("Sub Module", "Customers");
    }
    public void onTestSuccess(ITestResult result)
    {
    	test=extent.createTest(result.getTestClass().getName());
    //test.assignCategory(null)
    
    test.log(Status.PASS, "Test Case Passed Is :"+result.getName());

}
    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());

        test.log(Status.FAIL, "Test Case Failed: " + result.getName());
        test.log(Status.FAIL, "Error: " + result.getThrowable().getMessage());

        // Capture screenshot and attach
        String imgPath = new base().captureScreen(result.getName());

        test.addScreenCaptureFromPath(imgPath); // ✅ Add screenshot to Extent Report
    }




    public void onTestSkipped(ITestResult result)
    {
    	test=extent.createTest(result.getName());
    
    test.log(Status.SKIP ,"Test Case Skipped Is :"+result.getName());
   // test.log(Status.FAIL, "Test Case Failed Is :"+result.getThrowable());

}
    
    public void onFinish(ITestContext context) {
        extent.flush(); // 💾 Save the report to disk

        // (Optional) Automatically open the report in the browser
        try {
            Desktop.getDesktop().browse(new java.io.File("C:\\Users\\bhanu\\eclipse-myworkspace\\OrangeHRM\\Report\\myreport.html").toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

    


