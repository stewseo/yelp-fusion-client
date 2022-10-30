package org.example.gradle.internal.conventions.util;

import org.gradle.api.Action;
import org.gradle.api.GradleException;
import org.gradle.api.Project;
import org.gradle.api.file.FileTree;
import org.gradle.api.initialization.IncludedBuild;
import org.gradle.api.invocation.Gradle;
import org.gradle.api.plugins.JavaPluginExtension;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.SourceSetContainer;
import org.gradle.api.tasks.util.PatternFilterable;

import javax.annotation.Nullable;
import java.io.File;
import java.util.Optional;
import java.util.function.Supplier;

public class Util {

    public static boolean getBooleanProperty(String property, boolean defaultValue) {
        String propertyValue = System.getProperty(property);
        if (propertyValue == null) {
            return defaultValue;
        }
        if ("true".equals(propertyValue)) {
            return true;
        } else if ("false".equals(propertyValue)) {
            return false;
        } else {
            throw new GradleException("Sysprop [" + property + "] must be [true] or [false] but was [" + propertyValue + "]");
        }
    }


    public static FileTree getJavaMainSourceResources(Project project, Action<? super PatternFilterable> filter) {
        final Optional<FileTree> mainFileTree = getJavaMainSourceSet(project).map(SourceSet::getResources).map(FileTree::getAsFileTree);
        return mainFileTree.map(files -> files.matching(filter)).orElse(null);
    }

    public static FileTree getJavaTestSourceResources(Project project, Action<? super PatternFilterable> filter) {
        final Optional<FileTree> testFileTree = getJavaTestSourceSet(project).map(SourceSet::getResources).map(FileTree::getAsFileTree);
        return testFileTree.map(files -> files.matching(filter)).orElse(null);
    }

    public static FileTree getJavaTestAndMainSourceResources(Project project, Action<? super PatternFilterable> filter) {
        final Optional<FileTree> testFileTree = getJavaTestSourceSet(project).map(SourceSet::getResources).map(FileTree::getAsFileTree);
        final Optional<FileTree> mainFileTree = getJavaMainSourceSet(project).map(SourceSet::getResources).map(FileTree::getAsFileTree);
        if (testFileTree.isPresent() && mainFileTree.isPresent()) {
            return testFileTree.get().plus(mainFileTree.get()).matching(filter);
        } else if (mainFileTree.isPresent()) {
            return mainFileTree.get().matching(filter);
        } else if (testFileTree.isPresent()) {
            return testFileTree.get().matching(filter);
        }
        return null;
    }

    public static Optional<SourceSet> getJavaTestSourceSet(Project project) {
        return project.getExtensions().findByName("java") == null
                ? Optional.empty()
                : Optional.ofNullable(getJavaSourceSets(project).findByName(SourceSet.TEST_SOURCE_SET_NAME));
    }


    public static Optional<SourceSet> getJavaMainSourceSet(Project project) {
        return isJavaExtensionAvailable(project)
                ? Optional.empty()
                : Optional.ofNullable(getJavaSourceSets(project).findByName(SourceSet.MAIN_SOURCE_SET_NAME));
    }

    private static boolean isJavaExtensionAvailable(Project project) {
        return project.getExtensions().getByType(JavaPluginExtension.class) == null;
    }


    public static Object toStringable(Supplier<String> getter) {
        return new Object() {
            @Override
            public String toString() {
                return getter.get();
            }
        };
    }

    public static SourceSetContainer getJavaSourceSets(Project project) {
        return project.getExtensions().getByType(JavaPluginExtension.class).getSourceSets();
    }

    public static File locateElasticsearchWorkspace(Gradle project) {
        if (project.getParent() == null) {
            // See if any of these included builds is the Elasticsearch project
            for (IncludedBuild includedBuild : project.getIncludedBuilds()) {
                File versionProperties = new File(includedBuild.getProjectDir(), "build-tools-internal/version.properties");
                if (versionProperties.exists()) {
                    return includedBuild.getProjectDir();
                }
            }

            // Otherwise assume this project is the root elasticsearch workspace
            return project.getRootProject().getRootDir();
        } else {
            // We're an included build, so keep looking
            return locateElasticsearchWorkspace(project.getParent());
        }
    }
}