/*
 * Copyright (C) 2009-2013 SonarSource SA
 * All rights reserved
 * mailto:contact AT sonarsource DOT com
 */
package org.sonar.samples.java;

import com.google.common.collect.ImmutableList;
import org.sonar.api.BatchExtension;
import org.sonar.api.resources.Java;
import org.sonar.api.rules.AnnotationRuleParser;
import org.sonar.api.rules.Rule;
import org.sonar.api.rules.RuleRepository;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannersFactory;

import java.util.Arrays;
import java.util.List;

/**
 * Defines a custom rules repository
 */
public class JavaExtensionRulesRepository extends RuleRepository implements JavaFileScannersFactory, BatchExtension {

  public static final String REPOSITORY_KEY = "java-extension";

  public JavaExtensionRulesRepository() {
    super(REPOSITORY_KEY, Java.KEY);
  }

  @Override
  public List<Rule> createRules() {
    return new AnnotationRuleParser().parse(REPOSITORY_KEY, checks());
  }

  private static List<Class> checks() {
    return ImmutableList.<Class>of(ExampleCheck.class);
  }

  @Override
  public Iterable<JavaFileScanner> createJavaFileScanners() {
    return Arrays.<JavaFileScanner>asList(new ExampleCheck());
  }

}
