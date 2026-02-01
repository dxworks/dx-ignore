package org.dxworks.utils.ignorer.builder;

import org.dxworks.utils.ignorer.IgnorerBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IgnorerBuilderGlobsFromFileTest {

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
