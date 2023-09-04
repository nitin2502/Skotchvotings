package Voting;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class extentmanager 
{
	

	    private static ExtentReports extent;

	    public static ExtentReports getInstance() {
	        if (extent == null) {
	            extent = new ExtentReports();
	            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extent.html");
	            sparkReporter.config().setTheme(Theme.STANDARD);
	            extent.attachReporter(sparkReporter);
	        }
	        return extent;
	    }
	}

	

