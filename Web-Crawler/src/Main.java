import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.opencsv.CSVWriter;

import java.io.*;
import java.time.Duration;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\Milan\\\\eclipse-workspace\\\\Web-Crawler\\\\src\\\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
        driver.navigate().to("http://www.reifen.com/en/");    
        driver.manage().window().maximize();
         
        // After Chrome is up running with the web site loaded.
         
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='col-12 button-wrapper-bottom']//button[@type='submit'][normalize-space()='Show tyres']")));
        driver.findElement(By.xpath("//div[@class='col-12 button-wrapper-bottom']//button[@type='submit'][normalize-space()='Show tyres']")).click();
        
        // After we have arrived at the page we list Elements
        
        List<WebElement> list = driver.findElements(By.className("tyre-list__item-description-brand"));
        List<WebElement> list2 = driver.findElements(By.xpath("//div[@class='tyre-list']//div[3]//div[3]//a[1]//p[2]"));
        List<WebElement> list3 = driver.findElements(By.className("tyre-price__main"));

  
        // Writer creation to write to CSV file.
        
        File file = new File("Tires.csv");
        
        FileWriter outputfile = new FileWriter(file); 
        
        CSVWriter writer = new CSVWriter(outputfile);
        
        // Header creation.
        
        String[] header = {"Brand", "Profile", "Price" };
        
        writer.writeNext(header); 
        
      
        // Some String to work with iteration.
        
        
        String Brand2 = "";
        String Profile2 = "";
        String Price2 = "";
        
        
        // Listing Elements and getting the text. Storing that in a variable and writing it in CSV file.
    
        
        for (WebElement Brand : list) {
            System.out.println("Brand is: " + Brand.getText());
            
            Brand2 = Brand.getText();
            
            String[] data = { Brand2, "", "" }; 
            writer.writeNext(data);
            writer.flush();
            
            
            for (WebElement Profile : list2) {
                
            	System.out.println("Profile is: " + Profile.getText());
                    
                Profile2 = Profile.getText();
                
                String[] data2 = { "", Profile2, "" }; 
                writer.writeNext(data2);
                writer.flush();
                    
                
               
                for (WebElement Price : list3) {
                    
                	System.out.println("Price is: " + Price.getText());
                    
                	Price2 = Price.getText();
                	
                	String[] data3 = { "", "" , Price2, " Euro"  }; 
                    writer.writeNext(data3);
                    writer.flush();
                            
                    
            }
        }
            
        }
        
        // Freeing memory after the fact.
         
        writer.close();
        driver.close();
        driver.quit();
    }
}
