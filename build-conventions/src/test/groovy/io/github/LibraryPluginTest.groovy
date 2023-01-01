package io.github


import org.gradle.testkit.runner.TaskOutcome

class LibraryPluginTest extends PluginTest {

    def setup() {
        buildFile << """
            plugins {
               id 'io.github.library-conventions'
            }
        """
    }

    def "can declare api dependencies"() {
        given:
        readmeContainingMandatorySectionsExists()
        buildFile << """
            dependencies {
                api 'org.apache.commons:commons-lang3:3.4'
            }
        """

        when:
        def result = runTask('build')

        then:
        result.task(":build").outcome == TaskOutcome.SUCCESS
    }

    def "publishes library with versionin"() {
        given:
        readmeContainingMandatorySectionsExists()
        settingsFile.setText("rootProject.name = 'test-library'")
        buildFile << """
            version = '0.1.0'

            publishing {
                repositories {
                    maven {
                        name 'testRepo'
                        url 'build/'
                    }
                }
            }
        """

        new File(testProjectDir, 'src/main/java/io/github').mkdirs()
        new File(testProjectDir, 'src/main/java/io/github/Util.java') << """
            package io.github;
//
//            public class Util {
//                public static void someUtil() {
//                }
//            }
//          """

        when:
        def result = runTask('publishLibraryPublicationToTestRepoRepository')

        then:
        result.task(":jar").outcome == TaskOutcome.SUCCESS
        result.task(":publishLibraryPublicationToTestRepoRepository").outcome == TaskOutcome.SUCCESS
        new File($projectDirectory, 'build/local-repo/io/github/test-library/0.1.0/test-library-0.1.0.jar').exists()
    }

    def "fails when no README exists"() {
        when:
        def result = runTaskWithFailure('check')

        then:
        result.task(":readmeCheck").outcome == TaskOutcome.FAILED
    }

    def "fails when README does not have an intro section"() {
        given:
        new File(testProjectDir, 'README.md') << """
## Yelp Fusion Java Client
1234
        """

        when:
        def result = runTaskWithFailure('check')

        then:
        result.task(":readmeCheck").outcome == TaskOutcome.FAILED
        result.output.contains('README should contain section: ^## Yelp Fusion Java Client$')
    }

//    def "fails when README does not have example section"() {
//        given:
//        new File(testProjectDir, 'README.md') << """
//## Yelp Fusion Java Client
//
//[![release](https://badgen.net/badge/version/1.0.7/green?icon=github)](https://github.com/stewseo/yelp-fusion-client/tree/version-1.0.7)
//[![Maven Central](https://img.shields.io/maven-central/v/io.github.stewseo/yelp-fusion-client?versionPrefix=1.0.7)](https://search.maven.org/artifact/io.github.stewseo/yelp-fusion-client/1.0.7/jar)
//[![examples](https://badgen.net/badge/docs/examples/cyan?icon=github)](https://stewseo.github.io/yelp-fusion-client/examples)
//
//        """
//
//        when:
//        def result = runTaskWithFailure('check')
//
//        then:
//        result.task(":readmeCheck").outcome == TaskOutcome.FAILED
//        result.output.contains('README should contain: ^## Features')
//    }

    private def readmeContainingMandatorySectionsExists() {
        new File(testProjectDir, 'README.md') << """
## Yelp Fusion Java Client
1234t
    """
    }
}
