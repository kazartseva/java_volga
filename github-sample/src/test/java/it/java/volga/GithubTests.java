package it.java.volga;

import com.google.common.collect.ImmutableBiMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

  @Test
  public void testCommits() throws IOException {

    Github github = new RtGithub("0050a31bc2988a3794414e4a92c47baed8a97026");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("kazartseva", "java_volga")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableBiMap.Builder<String, String>().build())) {

      System.out.println(new RepoCommit.Smart(commit).message());
    }
  }
}
