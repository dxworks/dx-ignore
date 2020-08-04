package com.denisfeier.builder;


import com.denisfeier.globIgnorer.IgnorerBuilder;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class IgnorerBuilderGlobsFromFile {

    private IgnorerBuilder builder;

    @Before
    public void setup() {
        List<String> globs = List.of(
                "**/*.iml",
                "**/.idea/**",
                "**/target/**",
                "**/src/test/**",
                "!**/.idea/.gitignore");

        this.builder = new IgnorerBuilder(globs);
    }

    @Test
    public void checkGlobsFromList() {
        List<String> globs = List.of(
                "**/*.iml",
                "**/.idea/**",
                "**/target/**",
                "**/src/test/**",
                "!**/.idea/.gitignore");

        assertEquals(globs, builder.getGlobs());
    }
}
