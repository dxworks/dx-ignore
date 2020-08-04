package org.dxworks.builder;

import org.dxworks.globIgnorer.IgnorerBuilder;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.nio.file.Path;
import java.util.List;

public class IgnorerBuilderGlobsAsList {

    private IgnorerBuilder builder;

    @Before
    public void setup() {
        String rootPath = System.getProperty("user.dir");

        this.builder = new IgnorerBuilder(
                Path.of(rootPath + "/src/test/resources/.globs"));
    }

    @Test
    public void shouldFetchGlobs() {
        List<String> globs = List.of(
                "**/*.iml",
                "**/.idea/**",
                "**/target/**",
                "!**/.idea/.gitignore");

        assertEquals(globs, builder.getGlobs());
    }

    @Test
    public void compileBlobs() {
        Ignorer ignorer = this.builder.compile();
        assertNotEquals(ignorer, null);
    }
}
