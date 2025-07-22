package page;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private final WebDriver driver;

    private final String url ="https://stellarburgers.nomoreparties.site/";
    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By personalAccountButton = By.xpath(".//a[@href='/account']");
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    private final By stellarBurgersLogo = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");
    private final By bunsSectionButton = By.xpath(".//span[text()='Булки']");
    private final By saucesSectionButton = By.xpath(".//span[text()='Соусы']");
    private final By fillingsSectionButton = By.xpath(".//span[text()='Начинки']");
    private final By checkoutButton = By.xpath(".//button[text()='Оформить заказ']");
    private By saucesHeader = By.xpath(".//h2[text()='Соусы']");
    private By fillingsHeader = By.xpath(".//h2[text()='Начинки']");
    private By bunsHeader = By.xpath(".//h2[text()='Булки']");
    private final By lastFillingInList = By.xpath(".//p[text()='Сыр с астероидной плесенью']");
    private final By registerLink = By.xpath(".//a[text()='Зарегистрироваться']");
    private final By forgotPasswordLink = By.xpath (".//a[text()='Восстановить пароль']");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Open main page")
    public MainPage openMainPage() {
        driver.get(url);
        return this;
    }
    @Step("Check if checkout button is displayed")
    public boolean isCheckoutButtonDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutButton));
            WebElement checkoutButtonElement = driver.findElement(checkoutButton);
            return checkoutButtonElement.isDisplayed();
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    @Step("Кликаем по кнопке Войти в аккаунт")
    public LoginPage clickLoginButton() {
        driver.findElement(loginButton).click();
        return new LoginPage(driver);
    }

    @Step("Кликаем по кнопке Личный Кабинет")
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

    @Step("Click constructor button")
    public MainPage clickConstructorButton() {
        driver.findElement(constructorButton).click();
        return this;
    }
    @Step("Click Stellar Burgers logo")
    public MainPage clickStellarBurgersLogo() {
        driver.findElement(stellarBurgersLogo).click();
        return this;
    }

    @Step("Click register link")
    public RegisterPage clickRegisterLink() {
        driver.findElement(registerLink).click();
        return new RegisterPage(driver);
    }

    @Step("Click forgot password link")
    public ForgotPasswordPage clickForgotPasswordLink() {
        driver.findElement(forgotPasswordLink).click();
        return new ForgotPasswordPage(driver);
    }


    @Step("Кликаем по разделу Булки")
    public void clickBunChapter() {
        driver.findElement(bunsSectionButton).click();
    }

    @Step("Кликаем о разделу Соусы")
    public void clickSauceChapter() {
        driver.findElement(saucesSectionButton).click();
    }

    @Step("Кликаем о разделу Начинки")
    public void clickFillingChapter() {
        driver.findElement(fillingsSectionButton).click();
    }

    @Step("Проверяем скролла к разделу Булки")
    public void checkBunChapter () {
        scrollListDown();
        String location1 = String.valueOf(driver.findElement(bunsHeader).getLocation());
        clickBunChapter();
        String location2 = String.valueOf(driver.findElement(bunsHeader).getLocation());
        Assert.assertNotEquals(location2, location1);

    }

    @Step("Проверяем скролла к разделу Соусы")
    public void checkSauceChapter() {
        scrollListDown();
        String location1 = String.valueOf(driver.findElement(saucesHeader).getLocation());
        clickSauceChapter();
        String location2 = String.valueOf(driver.findElement(saucesHeader).getLocation());
        Assert.assertNotEquals(location2, location1);
    }

    @Step("Проверяем скролла к разделу Начинки")
    public void checkFillingChapter () {
        String location1 = String.valueOf(driver.findElement(fillingsHeader).getLocation());
        clickFillingChapter();
        String location2 = String.valueOf(driver.findElement(fillingsHeader).getLocation());
        Assert.assertNotEquals(location2, location1);
    }
    public boolean isConstructorPageOpen() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        boolean bunsVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(bunsHeader)).isDisplayed();
        boolean saucesVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(saucesHeader)).isDisplayed();
        boolean fillingsVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(fillingsHeader)).isDisplayed();
        return bunsVisible && saucesVisible && fillingsVisible;
    }
    @Step("Скроллим до нижнего элемента")
    public void scrollListDown() {
        WebElement element = driver.findElement(lastFillingInList);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);

    }
}
