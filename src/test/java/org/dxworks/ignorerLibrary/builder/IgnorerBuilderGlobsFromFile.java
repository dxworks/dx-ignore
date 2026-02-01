package org.dxworks.ignorerLibrary.builder;

import org.dxworks.ignorerLibrary.IgnorerBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IgnorerBuilderGlobsFromFile {

	private static IgnorerBuilder builder;

	@BeforeAll
	public static void setup() {
		List<String> globs = List.of(
				"**/*.iml",
				"**/.idea/**",
				"**/target/**",
				"**/src/test/**",
				"!**/.idea/.gitignore");

		builder = new IgnorerBuilder(globs);
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
