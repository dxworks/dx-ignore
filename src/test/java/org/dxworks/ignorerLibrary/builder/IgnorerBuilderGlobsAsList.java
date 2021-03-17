package org.dxworks.ignorerLibrary.builder;

import org.dxworks.ignorerLibrary.Ignorer;
import org.dxworks.ignorerLibrary.IgnorerBuilder;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
