package org.sonar.samples.java;

import com.sonar.sslr.squid.checks.CheckMessagesVerifierRule;
import org.junit.Rule;
import org.junit.Test;
import org.sonar.java.JavaAstScanner;
import org.sonar.java.model.VisitorsBridge;
import org.sonar.squid.api.SourceFile;

import java.io.File;

public class ExampleCheckTest {


  @Rule
  public CheckMessagesVerifierRule checkMessagesVerifier = new CheckMessagesVerifierRule();

  @Test
  public void detected() {
    SourceFile file = JavaAstScanner
        .scanSingleFile(new File("src/test/files/ExampleCheck.java"), new VisitorsBridge(new ExampleCheck()));

    checkMessagesVerifier.verify(file.getCheckMessages())
        .next().atLine(9).withMessage("Issue raised on method.");
  }
}
