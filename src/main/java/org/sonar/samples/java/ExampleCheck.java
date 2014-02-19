/*
 * Copyright (C) 2009-2013 SonarSource SA
 * All rights reserved
 * mailto:contact AT sonarsource DOT com
 */
package org.sonar.samples.java;

import org.sonar.api.rule.RuleKey;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.MethodTree;

/**
 * This class is an example of how to implement your own rules based on strongly typed AST.
 * The (stupid) rule implemented by this check is to raise a Minor issue for each method.
 * The class has the annotation @Rule which allows to specify the rule key and the default priority for the rule.
 */
@Rule(key = ExampleCheck.KEY, priority = Priority.MINOR, name = "Example", description = "Example")
/**
 * The class extends BaseTreeVisitor : the visitor for the Java Strongly Typed AST, this class is therefore a visitor.
 * The logic of the rule will be implemented in the overriding of its methods.
 * It also implements the JavaFileScanner interface in order to be injected with the JavaFileScannerContext to attach issues to this context.
 */
public class ExampleCheck extends BaseTreeVisitor implements JavaFileScanner {

  public static final String KEY = "example";
  private final RuleKey RULE_KEY = RuleKey.of(JavaExtensionRulesRepository.REPOSITORY_KEY, KEY);

  /**
   * Private field to store the context : this is the object used to create issues.
   */
  private JavaFileScannerContext context;

  /**
   * Implementation of the method of the JavaFileScanner interface.
   * @param context Object used to attach issues to source file.
   */
  @Override
  public void scanFile(JavaFileScannerContext context) {
    this.context = context;
    //The call to the scan method on the root of the tree triggers the visit of the strongly typed AST by this visitor.
    scan(context.getTree());
  }


  /**
   * Overriding of the visitor method.
   * This is where the logic of the rule will be implemented.
   * @param tree the Strongly Typed AST of the visited method.
   */
  @Override
  public void visitMethod(MethodTree tree) {
    //All code placed before the call to the overriden method will be executed before visiting the node.

    //Adding the issue by attaching it with the tree and the rule that raised the issue and a message.
    context.addIssue(tree, RULE_KEY, "Method.");
    //The call to the super implementation allows to continue the visit of the AST.
    //Be careful to always call this method to visit every node of the tree.
    super.visitMethod(tree);

    //All code after the call to the overriden method will be executed as leaving the node.
  }

}
