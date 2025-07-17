package test;

import client.UserClient;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page.ForgotPasswordPage;
import page.LoginPage;
import page.MainPage;
import page.RegisterPage;
import util.UserGenerator;

import java.net.MalformedURLException;

import static org.junit.Assert.assertTrue;

public class LoginTest {

    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private User user;
    private String accessToken;
    private UserClient userClient;

    @Before
    @Step("Setup: Create user via API, open main page")
    public void setUp() throws MalformedURLException {
        String browser = System.getProperty("browser", "chrome");
        driver = BrowserFactory.createDriver(browser);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        user = UserGenerator.generateRandomUser();
        userClient = new UserClient();
        Response response = userClient.createUser(user);
        accessToken = response.path("accessToken");
        mainPage.openMainPage();
    }

    @After
    @Step("Teardown: Delete user via API, quit driver")
    public void tearDown() {
        if (accessToken != null) {
            userClient.deleteUser(accessToken);
        }
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Login via 'Enter Account' button on Main Page")
    @Step("Login via 'Enter Account' button on Main Page")
    public void loginViaEnterAccountButton() {
        mainPage.clickLoginButton();
        loginPage.fillEmailField(user.getEmail())
                .fillPasswordField(user.getPassword())
                .clickLoginButton();
        assertTrue("Кнопка 'Оформить заказ' должна отображаться после успешного входа", mainPage.isCheckoutButtonDisplayed());
    }

    @Test
    @DisplayName("Login via 'Personal Account' button")
    @Step("Login via 'Personal Account' button")
    public void loginViaPersonalAccountButton() {
        mainPage.clickPersonalAccountButton();
        loginPage.fillEmailField(user.getEmail())
                .fillPasswordField(user.getPassword())
                .clickLoginButton();
        assertTrue("Кнопка 'Оформить заказ' должна отображаться после успешного входа", mainPage.isCheckoutButtonDisplayed());
    }

    @Test
    @DisplayName("Login via button in Register Form")
    @Step("Login via button in Register Form")
    public void loginViaButtonInRegisterForm() {
        mainPage.clickLoginButton()
                .clickRegisterLink()
                .clickLoginLink()
                .fillEmailField(user.getEmail())
                .fillPasswordField(user.getPassword())
                .clickLoginButton();
        assertTrue("Кнопка 'Оформить заказ' должна отображаться после успешного входа", mainPage.isCheckoutButtonDisplayed());
    }

    @Test
    @DisplayName("Login via button in Forgot Password Form")
    @Step("Login via button in Forgot Password Form")
    public void loginViaButtonInForgotPasswordForm() {
        mainPage.clickLoginButton()
                .clickForgotPasswordLink()
                .clickLoginLink()
                .fillEmailField(user.getEmail())
                .fillPasswordField(user.getPassword())
                .clickLoginButton();
        assertTrue("Кнопка 'Оформить заказ' должна отображаться после успешного входа", mainPage.isCheckoutButtonDisplayed());
    }
}
