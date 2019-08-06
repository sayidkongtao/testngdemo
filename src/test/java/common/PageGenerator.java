package common;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class PageGenerator {

    public AppiumDriver driver;

    //Constructor
    public PageGenerator(AppiumDriver driver) {
        this.driver = driver;
    }

    private static <T> T instantiatePage(AppiumDriver driver, Class<T> pageClassToProxy) {
        try {
            try {
                Constructor<T> constructor = pageClassToProxy.getConstructor(AppiumDriver.class);
                return constructor.newInstance(driver);
            } catch (NoSuchMethodException e) {
                return pageClassToProxy.newInstance();
            }
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    //JAVA Generics to Create and return a New Page
    public <TPage extends BasePage> TPage GetInstance(Class<TPage> pageClass) {
        try {
            //Initialize the Page with its elements and return it.
            TPage page = instantiatePage(driver, pageClass);
            PageFactory.initElements(new AppiumFieldDecorator(driver), page);
            return page;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}