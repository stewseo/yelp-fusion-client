package io.github.stewseo


import org.gradle.testkit.runner.TaskOutcome

class JavaConventionPluginTest extends PluginTest {

    def setup() {
        buildFile << """
            plugins {
                id 'io.github.stewseo.java-conventions'
            }
        """
    }

    def "fails on checkstyle error"() {
        given:
        new File(testProjectDir, 'src/main/java/io/github/stewseo').mkdirs()
        new File(testProjectDir, 'src/main/java/io/github/stewseo/Foo.java') << """
            package io.github.stewseo;

            import java.util.*;

            class Foo {
                void bar() {
                }
            }
        """

        when:
        def result = runTaskWithFailure('build')

        then:
        result.task(":checkstyleMain").outcome == TaskOutcome.FAILED
        result.output.contains('Checkstyle rule violations were found.')
        result.output.contains('Checkstyle violations by severity: [error:1]')
    }

    def "fails on checkstyle warning"() {
        given:
        new File(testProjectDir, 'src/main/java/io/github/stewseo').mkdirs()
        new File(testProjectDir, 'src/main/java/io/github/stewseo/Foo.java') << """
            package io.github.stewseo;

            class Foo {
                final static public String FOO = "BAR";

                void bar() {
                }
            }
        """

        when:
        def result = runTaskWithFailure('build')

        then:
        result.task(":checkstyleMain").outcome == TaskOutcome.FAILED
        result.output.contains('Checkstyle rule violations were found.')
        result.output.contains('Checkstyle violations by severity: [warning:1]')
    }

    def "fails on spotbugs error"() {
        given:
        new File(testProjectDir, 'src/main/java/io/github/stewseo').mkdirs()
        new File(testProjectDir, 'src/main/java/io/github/stewseo/Foo.java') << """
            package io.github.stewseo;

            class Foo {
                void bar() {
                    String s = null;
                    s.hashCode();
                }
            }
        """

        when:
        def result = runTaskWithFailure('build')

        then:
        result.task(":spotbugsMain").outcome == TaskOutcome.FAILED
    }

    def "warns on deprecated API usage"() {
        given:
        new File(testProjectDir, 'src/main/java/io/github/stewseo').mkdirs()
        new File(testProjectDir, 'src/main/java/io/github/stewseo/Foo.java') << """
            package io.github.stewseo;

            public class Foo {
                @Deprecated
                public void deprecatedMethod() {}
            }
        """

        new File(testProjectDir, 'src/main/java/io/github/stewseo/Bar.java') << """
            package io.github.stewseo;


            public class Bar {
                public void bar() {
                    new Foo().deprecatedMethod();
                }
            }
        """

        when:
        def result = runTask('build')

        then:
        result.task(":build").outcome == TaskOutcome.SUCCESS
        result.output.contains('warning: [deprecation] deprecatedMethod()')
    }
}
