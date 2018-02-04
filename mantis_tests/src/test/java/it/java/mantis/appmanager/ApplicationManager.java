package it.java.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  private final Properties properties;
  private WebDriver wd;
  private String browser;
  private RegistrationHelper registrationHelper;
  private FtpHelper FtpHelper;
  private MailHelper mailHelper;
  private JamesHelper JamesHelper;
  private MantisHelper mantisNavigationHelper;
  private DbHelper dbHelper;
  private SoapHelper soap;

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }


  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
  }

  public void stop() {
    if (wd !=null) {
      wd.quit();
    }
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public HttpSession newSession() {
    return new HttpSession(this);
  }

  public RegistrationHelper registration() {
    if (registrationHelper == null) {
      registrationHelper = new RegistrationHelper(this);
    }
    return registrationHelper;
  }

  public MantisHelper mantis() {
    if (mantisNavigationHelper == null) {
      mantisNavigationHelper = new MantisHelper(this);
    }
    return mantisNavigationHelper;
  }

  public MailHelper mail() {
    if (mailHelper == null) {
      mailHelper = new MailHelper(this);
    }
    return mailHelper;
  }

  public JamesHelper james() {
    if (JamesHelper == null) {
      JamesHelper = new JamesHelper(this);
    }
    return JamesHelper;
  }

  public SoapHelper soap() {
    if (soap == null) {
      soap = new SoapHelper(this);
    }
    return soap;
  }

  public FtpHelper ftp() {
    if (FtpHelper == null) {
      FtpHelper = new FtpHelper(this);
    }
    return FtpHelper;
  }

  public WebDriver getDriver() {
    if (wd == null) {
      if (browser.equals(BrowserType.FIREFOX)) {
        wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
      } else if (browser.equals(BrowserType.CHROME)) {
        wd = new ChromeDriver();
      }
      wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
      wd.get(properties.getProperty("web.baseUrl"));

    }
    return wd;
  }

  public DbHelper db() {
    if (dbHelper == null) {
    dbHelper = new DbHelper();
    }
    return dbHelper;
  }


}

