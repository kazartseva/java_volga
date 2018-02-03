package it.java.mantis.tests;

import it.java.mantis.models.MailMessage;
import it.java.mantis.models.UserData;
import it.java.mantis.models.Users;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTest extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }


  @Test
  public void testChangePassword() throws InterruptedException, IOException {
    String newPassword = "newpassword";
    UserData user = app.db().notAdministartorUsers().stream().iterator().next();
    app.mantis().loginAsAdmin();
    app.mantis().startResetUserPassword(user);
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confermationLink = findConfermationLink(mailMessages, user.getEmail());
    app.mantis().finishResetPassword(confermationLink, user, newPassword);
    assertTrue(app.newSession().login(user.getUsername(), newPassword));
  }

  private String findConfermationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

}
