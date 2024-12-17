package PagePackage;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

import UtilityPackage.ExcelUtil;

public class CourseraProgram 
{
    WebDriver driver;
    ExcelUtil excelUtil;
    
    @FindBy(xpath = "//*[@id='cds-react-aria-34-accordion-header']/div")
    WebElement faqElement;

    @FindBy(xpath = "/html/body/div[2]/div/header/div/div/div[2]/div/div/div/div/div/div[2]/div/div[1]/div/div/button/span/span")
    WebElement goalsButton;

    @FindBy(xpath = "//*[@id='earn-a-degree~menu-item']")
    WebElement earnDegree;

    @FindBy(xpath = "//*[@id='MegamenuWrapperDiv']/div/div/div/nav/div/div/div[2]/div[2]/div/section/div/div[2]/div[1]/ul/li[7]/a")
    WebElement secondItem;
    
    @FindBy(xpath = "//*[@id=\"c-ph-right-nav\"]/ul/li[3]/a")
    WebElement Login;
    
    @FindBy(name = "email")
    WebElement Email;
    
    @FindBy(name = "password")
    WebElement Password;
    
    @FindBy(xpath = "//button[@data-e2e='login-form-submit-button']")
    WebElement LoginButton;
    
    @FindBy(xpath = "//*[@id=\"cds-react-aria-8-product-card-title\"]")
    WebElement RvpClick;
    
    @FindBy(xpath = "/html/body/div[3]/div[2]/div/div[2]/div/div/c-cors_lhc_main/div/div/div[5]/c-cors_community/div/div/div[4]/lightning-button/button")
    WebElement VisitCommunity;
    
    @FindBy(xpath = "/html/body/div[3]/div[1]/div[1]/div/div/div/div[3]/div[4]/div/div[2]/div/div/div/community_user-user-profile-menu/button")
    WebElement LoginCommunity;
    
    @FindBy(xpath = "/html/body/div[3]/div[2]/div/div[2]/div[1]/div/div[1]/div/div/div/ul/li[3]/a/span[2]")
    WebElement Poll;
    
    @FindBy(xpath = "//*[@id=\"right-nav-dropdown-btn\"]/div/div")
    WebElement UserDropdown; 
    
    @FindBy(xpath = "//*[@id=\"profile-link\"]")
    WebElement Profile;
    
    @FindBy(xpath = "//button[contains(@class, 'cds-button-primary')]//span[text()='Upload resume']")
    WebElement AdditionalInformation;
    
    @FindBy(xpath = "//*[@id=\"dialog-content\"]/div/div/div/div[2]/div/div[2]/div/div/button/span")
    WebElement ResumeUpload;
    
//    @FindBy(xpath = "//*[@id=\"cds-react-aria-21\"]/div[3]/div/div/div[1]/button")
//    WebElement CloseButton;
    
    @FindBy(xpath = "//*[@id=\"account-settings-link\"]")
    WebElement Settings;
    
    @FindBy(xpath = "//button[@data-testid='scroll-container']")
    WebElement CloseButton;
       
    @FindBy(xpath = "//*[@id=\"logout-btn\"]")
    WebElement Logout;
    
    
    
  
    public CourseraProgram(WebDriver driver) 
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
   
                
    
    //(1) Mouse Hover
    //the section of mousehover start from explore-->earn a degree---> view all data science degrees click 
    
    public void MouseHoverClick() 
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        Actions act = new Actions(driver);
        act.moveToElement(goalsButton).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='earn-a-degree~menu-item']")));
        earnDegree.click();
        wait.until(ExpectedConditions.elementToBeClickable(secondItem));
        secondItem.click(); 
    }


    //(2)Scroll Down
    //In the all Data Science Degrees i has scroll down to the FAQ section and clicked the frst question and also the link in it.  
    public void ScrollDownClick() 
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", faqElement);
        wait.until(ExpectedConditions.elementToBeClickable(faqElement));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", faqElement);
        
        WebElement panelLink = driver.findElement(By.xpath("//*[@id='cds-react-aria-34-accordion-panel']/div/div/p[1]/a[2]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", panelLink);
        wait.until(ExpectedConditions.elementToBeClickable(panelLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", panelLink);
    }
    
    
    //(3)Window Handle
    //first in the What Is a Computer Science Degree? page we will type Cybersecurity in the search bar 
    //and search and then after loading the page the window is handled back to parent page and so on.

    public void WindowHandleClick() 
    {
        String parentWindow = driver.getWindowHandle();
        System.out.println("Parent window title: " + driver.getTitle());
        driver.findElement(By.xpath("/html/body")).click();  
        Set<String> allWindows = driver.getWindowHandles();
        for (String handle : allWindows)
        {
            if (!handle.equalsIgnoreCase(parentWindow)) 
            {
                driver.switchTo().window(handle);
                driver.findElement(By.xpath("//*[@id=\"search-autocomplete-input\"]")).sendKeys("Cybersecurity" + Keys.RETURN);
                driver.close();
            }
        }
        driver.switchTo().window(parentWindow);
        String expectedUrl = "https://www.coursera.org/";
        if (!driver.getCurrentUrl().equals(expectedUrl)) 
        {
            driver.get(expectedUrl);
        }
    }
    
    
    //(4) Assertion
    // Frst perform the login and then assertion of title verification.

    public void SetLoginValues(String mail, String pwd)
    {
        Login.click();
        Email.sendKeys(mail);
        Password.sendKeys(pwd);
        LoginButton.click();

        String actualTitle = driver.getTitle();
        String expectedTitle = "Coursera | Online Courses & Credentials From Top Educators. Join for Free";
        AssertJUnit.assertEquals(actualTitle, expectedTitle);
        System.out.println("Title verification passed");
    }
    
    
    //(5) Copy and Paste
    // Here after login we need to ckick the recently viewed course and then 
    //redirect to the visit community and then should login and click poll and perform copy and paste.    
    
    public void CopyAndPaste() 
    {
        try 
        {
            RvpClick.click();	
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement helpCenterLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Visit the learner help center")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", helpCenterLink);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", helpCenterLink);

            String parentWindow = driver.getWindowHandle();
            for (String windowHandle : driver.getWindowHandles()) 
            {
                if (!windowHandle.equals(parentWindow)) 
                {
                    driver.switchTo().window(windowHandle);
                    break;
                }
            }

            driver.get("https://www.coursera.support/s/community");
            String expectedTitle = "Coursera Community";
            if (driver.getTitle().contains(expectedTitle)) 
            {
                System.out.println("Page loaded successfully!");
            } 
            else {
                System.err.println("Failed to load the page.");
            }
            LoginCommunity.click();
            Poll.click(); 
            Actions act = new Actions(driver);
            WebElement Question = driver.findElement(By.xpath("//textarea[@placeholder='What would you like to ask?']"));
            Question.sendKeys("Hy Everyone");
            WebElement chooseId = driver.findElement(By.xpath("//input[@class='inputField cuf-pollChoiceField input']"));

            act.keyDown(Question, Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL); 
            act.keyDown(Question, Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL); 
            act.keyDown(chooseId, Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL); 
            act.perform();
            Thread.sleep(2000);
            driver.close();
            driver.switchTo().window(parentWindow);
            driver.get("https://www.coursera.org/");
            System.out.println("Navigated back to home page: " + driver.getCurrentUrl());

        } 
        catch (Exception e)
        {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
    
    // (6) File upload
    // I have to go to the dropdown menu of the home page and then choose prfile, add additional info, file uplaod,
	
	public void FileUpload(String filePath) throws AWTException, InterruptedException 
	{	
        UserDropdown.click();
        Profile.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement AdditionalInformation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"rendered-content\"]/div/div/main/div/div/div[1]/div[3]/span/section[2]/div/button/span")));
        AdditionalInformation.click();
        ResumeUpload.click();
        

        StringSelection strSelection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(strSelection, null);
        Robot robot = new Robot();
        robot.delay(3000);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        Thread.sleep(3000);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
	
	
	//(7)  Data Driven program, after the fileupload we try to add the link and about you
	public void DataDrivenTest(String excelPath) 
	{
	    try 
	    {
	        String parentWindow = driver.getWindowHandle();       
	        excelUtil = new ExcelUtil(excelPath, "Sheet1");
	        int totalRows = excelUtil.getRowCount();
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        for (int i = 1; i < totalRows; i++) 
	        {
	            String link = excelUtil.getCellData(i, 0);
	            String aboutYou = excelUtil.getCellData(i, 1);
	            if (link == null || link.isEmpty() || aboutYou == null || aboutYou.isEmpty())
	            {
	                continue;
	            }
	            WebElement linkField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='website-input-linkedin']")));
	            linkField.clear();
	            linkField.sendKeys(link);    
	            WebElement aboutField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='about']")));
	            aboutField.clear();
	            aboutField.sendKeys(aboutYou);           
	            System.out.println("Row " + i + ": Link = " + link + ", About You = " + aboutYou);
	        }
	        excelUtil.closeWorkbook();
	        driver.switchTo().window(parentWindow);
	        driver.get("https://www.coursera.org/");
	        
	    } 
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	    }
	}
	
	
	//(8) Dropdownhandling select the mahe and deutsch language.
	
	public void DropDownHandling() 
    {
        UserDropdown.click();
        Settings.click();
        
//		Actions act = new Actions(driver); // used for copy paste.
//		WebElement fullname = driver.findElement(By.xpath("//*[@id=\"settings-basic-full-name\"]"));
//		fullname.sendKeys("Hy");
//		WebElement chooseId = driver.findElement(By.xpath("//*[@id=\"settings-basic-email\"]"));
//
//		act.keyDown(fullname, Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL); // select
//		act.keyDown(fullname, Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL); // copy
//		act.keyDown(chooseId, Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL); // paste
//		act.perform();                                                       
		 
        WebElement timezone = driver.findElement(By.xpath("//*[@id=\"settings-basic-timezone\"]"));
        Select timeZoneDetails = new Select(timezone);
        timeZoneDetails.selectByValue("Indian/Mahe");
        
        WebElement language = driver.findElement(By.xpath("//*[@id=\"settings-basic-language\"]"));
        Select languageDetails = new Select(language);
        languageDetails.selectByVisibleText("Deutsch");
	    UserDropdown.click();
	    Logout.click();
    }
	
	
	
	//(9) DragAndDrop of the image in the hone page and drag it to the search box and  search.
	public void DragAndDrop() 
	{
	    WebElement Image = driver.findElement(By.xpath("//*[@id=\"rendered-content\"]/div/main/section[1]/div/div[2]/img"));
	    String imageURL = Image.getAttribute("src");
	    WebElement SearchBar = driver.findElement(By.xpath("//*[@id=\"search-autocomplete-input\"]"));
	    SearchBar.clear();
	    SearchBar.sendKeys(imageURL);
	    SearchBar.sendKeys(Keys.ENTER);
	    driver.get("https://www.coursera.org/");
	}
	
	
	
	//(10) Link Count
	public void LinkCount() 
	{
	    List<WebElement> LinkDetails = driver.findElements(By.tagName("a"));
	    System.out.println("Total number of links = " + LinkDetails.size());
	}
	
	
	
	//(11) Page Source	
	public void PageSource()
	{
		String src = driver.getPageSource();
		if (src.contains("Launch a new career in as little as 6 months")) 
		{
			System.out.println("Content Present");
		} 
		else
		{
			System.out.println("Content not present");
		}
	}
	
	
	//(12) Screenshot
	public void ScreenShot() throws IOException
	{
		File c= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(c, new File("C:\\Users\\ahsan\\OneDrive\\Pictures\\coursera.png"));
		
		WebElement button= driver.findElement(By.xpath("//*[@id=\"MegamenuWrapperDiv\"]/div/div/button/span/span"));
		File buttonImage=button.getScreenshotAs(OutputType.FILE);                         
		FileHandler.copy(buttonImage, new File("C:\\Users\\ahsan\\OneDrive\\Pictures\\Explore.png"));		
	}
}


	