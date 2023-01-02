package web.common.sourcedemo;

import com.sourcedemo.pages.LoginPage;
import org.testng.annotations.Test;
import web.common.base.BaseClass;

public class LoginTest extends BaseClass {

    @Test
  public void login() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.goTo();
  }
}
