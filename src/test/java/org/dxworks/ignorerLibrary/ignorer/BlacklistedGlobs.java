package org.dxworks.ignorerLibrary.ignorer;

import org.dxworks.ignorerLibrary.Ignorer;
import org.dxworks.ignorerLibrary.IgnorerBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.dxworks.ignorerLibrary.TestUtils.getGlobsPath;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BlacklistedGlobs {
	private static Ignorer ignorer;

	@BeforeAll
	public static void setup() throws URISyntaxException {
		ignorer = new IgnorerBuilder(getGlobsPath(".globs2")).compile();
	}

	@Test
	public void fromDotIdeaNotAccepted() {
		boolean encodingsXml = ignorer.accepts("/.idea/encodings.xml");
		assertFalse(encodingsXml);
	}

	@Test
	public void imlNotAccepted() {
		boolean imlFile = ignorer.accepts("/ignore-library.iml");
		assertFalse(imlFile);
	}

	@Test
	public void javaFilesAccepted() {
		boolean javaFile = ignorer.accepts("/src/test/java/GlobsReader.java");
		assertTrue(javaFile);
	}

	@Test
	public void acceptGitIgnoreFromDotIdea() {
		boolean gitignore = ignorer.accepts("/.idea/.gitignore");
		assertTrue(gitignore);
	}
}
