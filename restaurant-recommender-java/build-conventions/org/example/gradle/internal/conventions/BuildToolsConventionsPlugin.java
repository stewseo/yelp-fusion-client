package org.example.gradle.internal.conventions;

import org.example.gradle.internal.conventions.info.*;
import org.gradle.api.*;

import org.example.gradle.internal.conventions.util.Util;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.tasks.bundling.Jar;
import org.gradle.api.tasks.testing.Test;
import java.io.File;

public class BuildToolsConventionsPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
//        project.getPlugins().apply(LicenseHeadersPrecommitPlugin.class);
        int defaultParallel = ParallelDetector.findDefaultParallel(project);
        project.getTasks().withType(Test.class).configureEach(test -> {
            test.onlyIf((t) -> !Util.getBooleanProperty("tests.fips.enabled", false));
            test.setMaxParallelForks(defaultParallel);
        });
        // we put all our distributable files under distributions
        project.getTasks().withType(Jar.class).configureEach(j ->
                j.getDestinationDirectory().set(new File(project.getBuildDir(), "distributions"))
        );
    }
}
