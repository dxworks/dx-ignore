package org.dxworks.utils.ignorer;

import org.dxworks.utils.ignorer.ignorer.BlacklistedGlobsTest;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.fail;

public class TestUtils {
	public static Path getGlobsPath(String globsFileName) throws URISyntaxException {
		final URL resource = BlacklistedGlobsTest.class.getClassLoader().getResource(globsFileName);
		if (resource == null)
			fail(globsFileName + " not found in resources");
		return Path.of(resource.toURI());
	}
}
