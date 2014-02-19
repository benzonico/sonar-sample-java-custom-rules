/*
 * Copyright (C) 2009-2013 SonarSource SA
 * All rights reserved
 * mailto:contact AT sonarsource DOT com
 */
package org.sonar.samples.java;

import com.google.common.collect.ImmutableList;
import org.sonar.api.SonarPlugin;

import java.util.List;

/**
 * Entry point of a plugin, in this case, we have only one extension point : a custom rules repository.
 */
public class JavaExtensionPlugin extends SonarPlugin {

  @Override
  public List getExtensions() {
    return ImmutableList.of(JavaExtensionRulesRepository.class);
  }

}
