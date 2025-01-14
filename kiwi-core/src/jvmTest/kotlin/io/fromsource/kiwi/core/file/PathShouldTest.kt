package io.fromsource.kiwi.core.file

import io.fromsource.kiwi.core.should
import kotlin.test.Test
import java.io.File

class PathShouldTest {

    @Test
    fun `should be able to verify if path exist`() {
        File("src/jvmTest/resources/sample.txt").toPath().should().exist()
    }

    @Test
    fun `should fail if path does not exist`() {
        val path = File("src/jvmTest/resources/non_existent.txt").toPath()

        runCatching {
            path.should().exist()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("Path 'src/jvmTest/resources/non_existent.txt' does not exist")
    }

    @Test
    fun `should be able to verify if path points to a directory`() {
        File("src/jvmTest/resources").toPath().should().beADirectory()
    }

    @Test
    fun `should fail if path does not point to a directory`() {
        val path = File("src/jvmTest/resources/sample.txt").toPath()

        runCatching {
            path.should().beADirectory()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("Path 'src/jvmTest/resources/sample.txt' is not a directory")
    }

    @Test
    fun `should be able to verify if path points to a file`() {
        File("src/jvmTest/resources/sample.txt").toPath().should().beAFile()
    }

    @Test
    fun `should fail if path does not point to a file`() {
        val path = File("src/jvmTest/resources").toPath()

        runCatching {
            path.should().beAFile()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("Path 'src/jvmTest/resources' is not a file")
    }

    @Test
    internal fun `should be able to verify that path is absolute`() {
        val path = File("src/jvmTest/resources").toPath().toAbsolutePath()
        path.should().beAbsolute()
    }

    @Test
    fun `should fail if path is not absolute`() {
        val path = File("src/jvmTest/resources").toPath()
        runCatching {
            path.should().beAbsolute()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("Path 'src/jvmTest/resources' is not absolute")
    }

    @Test
    fun `should be able to verify parent directory`() {
        val path = File("src/jvmTest/resources/sample.txt").toPath()

        path.should().haveParentDirectory("src/jvmTest/resources")
    }

    @Test
    fun `should fail if parent directory does not match`() {
        val path = File("src").toPath()
        val actualParentPath = File(path.toUri()).parent
        runCatching {
            path.should().haveParentDirectory("src/failure")
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("Parent directory '$actualParentPath' does not match value '$actualParentPath/src/failure'")
    }
}