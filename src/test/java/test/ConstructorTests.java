package test;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import page.MainPage;

import java.net.MalformedURLException;

    public class ConstructorTests {
        private WebDriver driver;
        private MainPage mainPage;

   @Before
   public void setUpConstructor() throws MalformedURLException {String browser = System.getProperty("browser", "chrome");
       driver = BrowserFactory.createDriver(browser);
       mainPage = new MainPage(driver);
       mainPage.openMainPage();
   }

    @Test
    @DisplayName("Переход к разделу Булки")
    @Description("Скролл страницы до раздела Булки")
    public void goToBunsSection(){
        MainPage mainPage = new MainPage(driver);
        mainPage.checkBunChapter();
    }

    @Test
    @DisplayName("Переход к разделу Соусы")
    @Description("Скролл страницы до раздела Соусы")
    public void goToSaucesSection(){
        MainPage mainPage = new MainPage(driver);
        mainPage.checkSauceChapter();
    }

    @Test
    @DisplayName("Переход к разделу Начинки")
    @Description("Скролл страницы до раздела Начинки")
    public void goToFillingSection(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingChapter();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}


//   @Test
 //   @DisplayName("Go to 'Sauces' section")
//    public void goToSaucesSection() {
//        mainPage.clickSaucesSectionButton();

        // Добавляем явное ожидание для проверки заголовка
//        WebDriverWait wait = new WebDriverWait(driver, 10);
 //       wait.until(ExpectedConditions.visibilityOf(mainPage.saucesSectionButton()));

 //       assertTrue("Переход к разделу 'Соусы' не удался: заголовок 'Соусы' не отображается", mainPage.isSaucesHeaderVisible());
 //   }

 //   @Test
//    @DisplayName("Go to 'Buns' section")
//    public void goToBunsSection() {
//        mainPage.clickSaucesSectionButton();
//        mainPage.clickBunsSectionButton();

        // Добавляем явное ожидание для проверки заголовка
//       WebDriverWait wait = new WebDriverWait(driver, 10);
 //       wait.until(ExpectedConditions.visibilityOf(mainPage.bunsSectionButton()));

 //       assertTrue("Переход к разделу 'Булки' не удался: заголовок 'Булки' не отображается", mainPage.isBunsHeaderVisible());
 //   }

//    @Test
 //   @DisplayName("Go to 'Filling' section")
 //   public void goToFillingSection() {
 //       mainPage.clickFillingsSectionButton();

 //       // Добавляем явное ожидание для проверки заголовка
//        WebDriverWait wait = new WebDriverWait(driver, 10);
 //       wait.until(ExpectedConditions.visibilityOf(mainPage.fillingsSectionButton()));

 //       assertTrue("Переход к разделу 'Начинки' не удался: заголовок 'Начинки' не отображается", mainPage.isFillingsHeaderVisible());
 //   }

//    @After
 //   @Step("Teardown: quit driver")
//    public void tearDown() {
 //       driver.quit();
//    }
//}
