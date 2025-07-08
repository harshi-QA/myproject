package Test;

import BaseTest.BrowserOpen;
import PageObject.DashBoardPage;
import PageObject.LaunchPage;
import Utility.SeleniumMethods;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import Utility.ExcelUtils;

import static Utility.SeleniumMethods.extent;

public class LoginTest extends BrowserOpen {
    public static void main(String[] args) throws InterruptedException {

            BrowserOpen bp = new BrowserOpen();
            WebDriver driver = bp.openBrowser();
            ExtentReports extent = SeleniumMethods.getExtentReport();

            String path = "testdata/LoginData.xlsx";
            String sheet = "Sheet1";

            ExtentTest test = extent.createTest("Login Test");

            String username = ExcelUtils.getCellData(path, sheet, 1, 0).trim();
            String password = ExcelUtils.getCellData(path, sheet, 1, 1).trim();


            LaunchPage lp = new LaunchPage(driver, test);
            lp.clickLogin(username, password);

            DashBoardPage dp = new DashBoardPage(driver, test);
            dp.verifyDashBoardPage();
            dp.applyLeave();
            dp.clickLogOut();
            extent.flush();


        }



}