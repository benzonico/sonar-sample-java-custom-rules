package org.sonar.samples.java;

import com.sonar.sslr.squid.checks.CheckMessagesVerifierRule;
import org.junit.Rule;
import org.junit.Test;
import org.sonar.java.JavaAstScanner;
import org.sonar.java.model.VisitorsBridge;
import org.sonar.squid.api.SourceFile;

import java.io.File;

/**
 * This class is the test of the ExampleCheck.
 * We test a check by running it against a minimal valid file to raise the issue and
 * verify we raise the correct number of violation with correct messages.
 */
public class ExampleCheckTest {


  @Rule
  public CheckMessagesVerifierRule checkMessagesVerifier = new CheckMessagesVerifierRule();

  @Test
  public void detected() {

    //Parse a known file and use an instance of the check under test to raise issue.
    SourceFile file = JavaAstScanner
        .scanSingleFile(new File("src/test/files/ExampleCheck.java"), new VisitorsBridge(new ExampleCheck()));

    //Verify the messages raised by the check
    checkMessagesVerifier.verify(file.getCheckMessages())
        //Expect an issue at line 9 with a predefined message. This allows test of dynamic messages.
        .next().atLine(9).withMessage("Issue raised on method.");
  }
}
