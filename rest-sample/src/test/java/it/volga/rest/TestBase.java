package it.volga.rest;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;

public class TestBase {

  @BeforeMethod
  public void init() {
    RestAssured.authentication = RestAssured.basic("28accbe43ea112d9feb328d2c00b3eed", "");

  }

  public void skipIfNotFixed(int issueId) {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  private boolean isIssueOpen(int issueId) {
    String path = "http://demo.bugify.com/api/issues/" + issueId + ".json";
    String json = RestAssured.get(path).asString();
    JsonElement parsed = new JsonParser().parse(json);
    String issueStatus = parsed.getAsJsonObject().get("issues")
            .getAsJsonArray().get(0).getAsJsonObject()
            .get("state_name").getAsString();
    if (issueStatus.equals("Closed")) {
      return false;
    }
    return true;
  }
}
