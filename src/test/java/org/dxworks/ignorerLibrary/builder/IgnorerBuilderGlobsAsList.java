package org.dxworks.ignorerLibrary.builder;

import org.dxworks.ignorerLibrary.Ignorer;
import org.dxworks.ignorerLibrary.IgnorerBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.util.List;

import static org.dxworks.ignorerLibrary.TestUtils.getGlobsPath;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class IgnorerBuilderGlobsAsList {
	private static IgnorerBuilder builder;

	@BeforeAll
	public static void setup() throws URISyntaxException {
		builder = new IgnorerBuilder(getGlobsPath(".globs"));
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
		Ignorer ignorer = builder.compile();
		assertNotEquals(ignorer, null);
	}
}
