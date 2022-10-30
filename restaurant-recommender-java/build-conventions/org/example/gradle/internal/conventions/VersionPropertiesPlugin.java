package org.example.gradle.internal.conventions;

import org.example.gradle.internal.conventions.util.*;
import org.gradle.api.*;
import java.io.*;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.provider.Provider;

public class VersionPropertiesPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        File workspaceDir = Util.locateElasticsearchWorkspace(project.getGradle());

        // Register the service if not done yet
        File infoPath = new File(workspaceDir, "build-tools-internal");
        Provider<VersionPropertiesBuildService> serviceProvider = project.getGradle().getSharedServices()
                .registerIfAbsent("versions", VersionPropertiesBuildService.class, spec -> {
                    spec.getParameters().getInfoPath().set(infoPath);
                });
        project.getExtensions().add("versions", serviceProvider.get().getProperties());
    }
}