package it.java.mantis.appmanager;

import it.java.mantis.models.UserData;
import org.openqa.selenium.By;

public class MantisHelper extends BaseHelper {
  public MantisHelper(ApplicationManager app) {
    super(app);
  }


  public void loginAsAdmin() {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), app.getProperty("web.loginAdmin"));
    click(By.cssSelector("input[value='Login']"));
    type(By.name("password"), app.getProperty("web.loginPassword"));
    click(By.cssSelector("input[value='Login']"));
  }

  public void startResetUserPassword(UserData user) {
    click(By.cssSelector("a[href='/mantisbt-2.10.0/manage_overview_page.php']"));
    click(By.cssSelector("a[href='/mantisbt-2.10.0/manage_user_page.php']"));
    type(By.cssSelector("input[id='username']"), user.getEmail());
    click(By.cssSelector("input[value='Manage User']"));
    click(By.cssSelector("input[value='Reset Password']"));
  }

  public void finishResetPassword(String confermationLink, UserData user, String newPassword) {
    wd.get(confermationLink);
    type(By.cssSelector("input[id='realname']"), user.getUsername());
    type(By.cssSelector("input[id='password']"), newPassword);
    type(By.cssSelector("input[id='password-confirm']"), newPassword);
    click(By.cssSelector("button[type='submit']"));

  }
}
