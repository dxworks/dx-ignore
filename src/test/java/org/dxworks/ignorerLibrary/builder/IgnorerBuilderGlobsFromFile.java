package org.dxworks.ignorerLibrary.builder;

import org.dxworks.ignorerLibrary.IgnorerBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

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
