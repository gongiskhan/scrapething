package org.constantgatherer.webdriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import org.constantgatherer.model.webdriver.Command;
import org.constantgatherer.model.webdriver.CommandType;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * User: ggomes
 * Date: 13-01-2014
 * Time: 21:10
 * Copyright Tango Telecom 2014
 */
@Component
public class CommandExecutor {

    public String executeCommandsAndGetText(List<Command> commands){

        WebDriver driver = new HtmlUnitDriver(){
            @Override
            protected WebClient newWebClient(BrowserVersion version) {
                WebClient webClient = super.newWebClient(version);
                webClient.getOptions().setThrowExceptionOnScriptError(false);
                return webClient;
            }

            @Override
            public void close() {
                try{
                    getWebClient().closeAllWindows();
                }catch (NoSuchWindowException ex){}
                try{
                    super.close();
                }catch (NoSuchWindowException ex){}
            }
        };

        //driver.setJavascriptEnabled(true);
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);

        String text = "";
        for(Command command : commands){
            String result = this.execute(command, driver);
            if(result != null && !result.isEmpty() && command.getCommandType().equals(CommandType.GET_TEXT))
                text = result;
        }
        driver.close();
        driver.quit();
        return text;
    }

    public String execute(Command command, WebDriver driver){
        switch(command.getCommandType()){
            case CLICK:{
                getElement(driver, command.getTarget()).click();
                break;
            }
            case GET_TEXT:{
                return getElement(driver, command.getTarget()).getText();
            }
            case INSERT:{
                getElement(driver, command.getTarget()).sendKeys(command.getValue());
                break;
            }
            case SUBMIT:{
                getElement(driver, command.getTarget()).submit();
                break;
            }
            case OPEN:{
                driver.get(command.getTarget());
                break;
            }
            case WAIT:{
                try {
                    Thread.sleep(Long.parseLong(command.getValue()));
                }catch(Exception ex){
                    try {
                        Thread.sleep(2000);
                    }catch(Exception exx){}
                }
                break;
            }
        }
        return null;
    }

    private WebElement getElement(WebDriver driver, String cssSelector){
        WebElement element = driver.findElement(By.cssSelector(cssSelector));
        Date now = new Date();
        while(!element.isDisplayed() && (new Date().getTime() < (now.getTime()+5000))){
            System.out.println("not displayed");
            element = driver.findElement(By.cssSelector(cssSelector));
        }
        return element;
    }
    /*                JavascriptExecutor jse = (JavascriptExecutor) driver;
                jse.executeScript("setTimeout(function(){},2000);");*/
    /*            @Override
            protected WebClient newWebClient(BrowserVersion version) {
                WebClient webClient = super.newWebClient(version);
                webClient.getOptions().setThrowExceptionOnScriptError(false);
                return webClient;
            }*/

    //DesiredCapabilities capabilities = DesiredCapabilities.chrome();
}
