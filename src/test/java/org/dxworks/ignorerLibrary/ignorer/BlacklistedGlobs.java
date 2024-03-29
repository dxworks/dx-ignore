package org.dxworks.ignorerLibrary.ignorer;

import org.dxworks.ignorerLibrary.Ignorer;
import org.dxworks.ignorerLibrary.IgnorerBuilder;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.nio.file.Path;

public class BlacklistedGlobs {
    private Ignorer ignorer;
    private final String rootPath = System.getProperty("user.dir");

    @Before
    public void setup() {
        IgnorerBuilder builder = new IgnorerBuilder(
                Path.of(this.rootPath + "/src/test/resources/.globs2"));
        this.ignorer = builder.compile();
    }

    @Test
    public void fromDotIdeaNotAccepted() {
        boolean encodingsXml = this.ignorer.accepts(this.rootPath + "/.idea/encodings.xml");
        assertFalse(encodingsXml);
    }

    @Test
    public void imlNotAccepted() {
        boolean imlFile = this.ignorer.accepts(this.rootPath + "/ignore-library.iml");
        assertFalse(imlFile);
    }

    @Test
    public void javaFilesAccepted() {
        boolean javaFile = this.ignorer.accepts(this.rootPath + "/src/test/java/GlobsReader.java");
        assertTrue(javaFile);
    }

    @Test
    public void acceptGitIgnoreFromDotIdea() {
        boolean gitignore = this.ignorer.accepts(this.rootPath + "/.idea/.gitignore");
        assertTrue(gitignore);
    }
}
