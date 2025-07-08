package PageObject;

import Utility.SeleniumMethods;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class DashBoardPage extends SeleniumMethods {
    public WebDriver driver;
    public ExtentTest test;
    String DashBoardicon = "//span[@class='oxd-topbar-header-breadcrumb']//h6";
    String ApplyLeave = "//button[@title='Apply Leave']";
    String AssignLeave = "//a[text()='Assign Leave']";
    String SubmitButton="//button[text()=' Assign ']";
    String UserDropDown = "//li[@class='oxd-userdropdown']";
    String LogOut="//a[text()='Logout']";

    public DashBoardPage(WebDriver driver, ExtentTest test)
    {
        super(driver,test);
        this.driver = driver;
        this.test=test;

    }


    public void verifyDashBoardPage() {
        //String title = getText(driver, By.xpath(DashBoardicon));
        WebElement element=SeleniumMethods.waitForElementVisible(driver, By.xpath(DashBoardicon), 30);
        String title=element.getText();

        System.out.println("Extracted text:" + title);
        if (title.equals("Dashboard")) {
            test.pass("Login verified: Dashboard loaded successfully");
            System.out.println("Login page verified loggedin successfully");
        } else {
            String path = SeleniumMethods.takeScreenshot(driver, "dashboard_fail");
            try {
                test.fail("Login failed: Dashboard not found",
                        MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            } catch (Exception e) {
                test.fail("Screenshot could not be attached due to exception: " + e.getMessage());
            }

        }

    }


    public void applyLeave() {
        SeleniumMethods.click(driver, By.xpath(ApplyLeave));
        SeleniumMethods.click(driver, By.xpath(AssignLeave));
        String emp = SeleniumMethods.getAttribute(driver, By.xpath("//input[@placeholder='Type for hints...']"), "value");
        String leave = SeleniumMethods.getText(driver, By.xpath("//div[@class='oxd-select-text-input']"));
        String from = SeleniumMethods.getAttribute(driver, By.xpath("(//input[@placeholder='yyyy-dd-mm'])[1]"), "value");
        String to = SeleniumMethods.getAttribute(driver, By.xpath("(//input[@placeholder='yyyy-dd-mm'])[2]"), "value");


        if (!emp.isEmpty() && !leave.equals("-- Select --") && !from.isEmpty() && !to.isEmpty()) {
            test.pass("All required leave fields are filled.");
            System.out.println("All required fields are filled.");
        } else {
            String path = SeleniumMethods.takeScreenshot(driver, "missing_leave_fields");
            try {
                test.fail("Some leave fields are missing.",
                        MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            } catch (Exception e) {
                test.fail("Screenshot could not be attached: " + e.getMessage());
            }

        }
        SeleniumMethods.click(driver, By.xpath(SubmitButton));
    }

    public void clickLogOut()
    {
        SeleniumMethods.click(driver,By.xpath(UserDropDown));
        test.pass("Clicked on user dropdown");
        SeleniumMethods.click(driver, By.xpath(LogOut));
        test.pass("Clicked on logout");


    }


}