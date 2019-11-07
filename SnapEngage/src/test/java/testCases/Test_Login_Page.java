package testCases;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;


import commonFunctions.CommonFunctions;
import pageObjects.Login_Page_Objects;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Test_Login_Page<HomePage> extends CommonFunctions {

	@Test
	public void verifyLoginPage() throws IOException {
		
		PageFactory.initElements(driver, Login_Page_Objects.class);
		Login_Page_Objects.userName.sendKeys(properties.getProperty("username"));
		Login_Page_Objects.password.sendKeys(properties.getProperty("password"));
		Login_Page_Objects.signInButton.click();
		
		
	
        // Assertion
        String URL = driver.getCurrentUrl();
        String expectedUrl = "https://snapengage-qa.appspot.com/hub";
        Assert.assertEquals(URL, expectedUrl);
        
        if (URL.equalsIgnoreCase(expectedUrl))
        {
            System.out.println("Test case passed.");
        }
        else
        {
            System.out.println("Test case failed.");
        }
        

        // Wait for the home page to load
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='intro-box']")));
        
        //Taking Screenshot using Ashot API
        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(500)).takeScreenshot(driver);
		ImageIO.write(screenshot.getImage(),"PNG",new File("fullscreenshot.png"));
		
		// IMPLEMENTING ANOTHER METHOD TO TAKE SCREENSHOT
		//  takeScreenshot("SnapEngage_HomePage");
        //	File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    //	FileUtils.copyFile(file, new File("/SnapEngage/src/main/java/commonFunctions"+fileName+".jpg"));
		
		
		
	}

				
	
}