package w2smsp;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class W2smsmethods 
{
public WebDriver driver;
public WebDriverWait wait;
public String launch(String l,String d,String c) throws Exception
{
	if(l.equalsIgnoreCase("chrome")) 
	{
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\Harish\\Downloads\\chromedriver_win32\\chromedriver.exe");
	driver=new ChromeDriver();
	}
	else if(l.equalsIgnoreCase("firefox"))
	{
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Harish\\Downloads\\geckodriver-v0.23.0-win64\\geckodriver.exe");
		driver=new FirefoxDriver();
	}
	else
	{
		OperaOptions oo=new OperaOptions();
		oo.setBinary("C:\\Users\\Harish\\AppData\\Local\\Programs\\Opera\\launcher.exe");
		System.setProperty("webdriver.oper.driver","C:\\Users\\Harish\\Downloads\\operadriver_win64\\operadriver.exe");
		driver=new OperaDriver(oo);
	}
	driver.get(d);
	wait=new WebDriverWait(driver,20);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("mobileNo")));
	return "done";
		
	}
public String fill(String l,String d,String c) throws Exception
{
	driver.findElement(By.xpath(l)).sendKeys(d);
	return "done";
}
public String click(String l,String d,String c) throws Exception
{
	driver.findElement(By.xpath(l)).click();
	Thread.sleep(5000);
	return "done";
}
public String validatelogin(String l,String d,String c) throws Exception
{
	try
	{
		if(c.equalsIgnoreCase("all_valid") && driver.findElement(By.xpath("//*[text()='Send SMS']")).isDisplayed())
		{
			return "login with valid data passed";
		}
		else if(c.equalsIgnoreCase("mbno_blank") && driver.findElement(By.xpath("//*[text()='Enter your mobile number']")).isDisplayed())
		{
			return "blank mbno test passed";
		}
		else if(c.equalsIgnoreCase("mbno_wrongsize") && driver.findElement(By.xpath("//*[text()='Enter valid mobile number']")).isDisplayed())
		{
			return "wrong size mbno test passed";
		}
		else if(c.equalsIgnoreCase("mbno_invalid") && driver.findElement(By.xpath("//*[text()='Your mobile number is not register with us.']")).isDisplayed())
		{
			return "blank pwd test passed";
		}
		else if(c.equalsIgnoreCase("pwd_blank") && driver.findElement(By.xpath("//*[text()='Enter password'][2]']")).isDisplayed())
		{
			return "blank pwd test passed";
		}
		else if(c.equalsIgnoreCase("pwd_invalid") && driver.findElement(By.xpath("//*[text()='Incorrect number or password! Try Again.']")).isDisplayed())
		{
			return "wrong pwd test passed";
		}
		else
		{
			SimpleDateFormat sf=new SimpleDateFormat("dd-MM-yy-hh-mm-ss");
			Date dt=new Date();
			String fname=sf.format(dt)+".png";
			File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			File dest=new File(fname);
			FileHandler.copy(src,dest);
			return "login test failed and see "+fname;
		}
		
		
	}
	catch (Exception ex)
	{
		return(ex.getMessage());
	}
}
public String closesite(String l,String d,String c) throws Exception
{
	driver.quit();
	return("done");
}
}
