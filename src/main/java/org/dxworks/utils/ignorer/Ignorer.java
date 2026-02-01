package org.dxworks.utils.ignorer;

import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.List;

/**
 * Checks if paths match gitignore-style glob patterns.
 *
 * <p>An Ignorer contains blacklist patterns (paths to ignore) and whitelist patterns
 * (exceptions that override the blacklist). Use {@link IgnorerBuilder} to create instances.
 *
 * <p>Example:
 * <pre>{@code
 * Ignorer ignorer = new IgnorerBuilder(List.of("**&#47;*.log", "!**&#47;important.log")).compile();
 * ignorer.accepts("debug.log");     // false (matched by blacklist)
 * ignorer.accepts("important.log"); // true (excluded by whitelist)
 * }</pre>
 */
public class Ignorer {

	private static final boolean DEBUG = Boolean.parseBoolean(System.getenv("DX_IGNORE_DEBUG"));

	private final List<PathMatcher> blackMatchersGlobs;
	private final List<PathMatcher> whiteMatchersGlobs;

	/**
	 * Creates an Ignorer with the specified blacklist and whitelist matchers.
	 *
	 * @param blackMatchersGlobs patterns that match paths to ignore
	 * @param whiteMatchersGlobs patterns that match paths to exclude from ignoring
	 */
	public Ignorer(List<PathMatcher> blackMatchersGlobs, List<PathMatcher> whiteMatchersGlobs) {
		this.blackMatchersGlobs = blackMatchersGlobs;
		this.whiteMatchersGlobs = whiteMatchersGlobs;
	}

	/**
	 * Checks if the given path is accepted (not ignored).
	 *
	 * <p>A path is accepted if it matches a whitelist pattern or does not match
	 * any blacklist pattern.
	 *
	 * @param path the path to check
	 * @return {@code true} if the path is accepted, {@code false} if ignored
	 */
	public boolean accepts(String path) {
		boolean whiteGlobs = match(whiteMatchersGlobs, path);
		boolean blackGlobs = match(blackMatchersGlobs, path);

		return whiteGlobs || !blackGlobs;
	}

	/**
	 * Checks if the given path is accepted (not ignored).
	 *
	 * @param path the path to check
	 * @return {@code true} if the path is accepted, {@code false} if ignored
	 * @see #accepts(String)
	 */
	public boolean accepts(Path path) {
		return accepts(path.toString());
	}

	private boolean match(List<PathMatcher> globsMatcher, String path) {
		return globsMatcher.stream()
				.map(glob -> glob.matches(Path.of(path)))
				.reduce(false, (aBoolean, aBoolean2) -> aBoolean || aBoolean2);
	}

	static void log(String message) {
		if (DEBUG) {
			System.out.println("[dx-ignore] " + message);
		}
	}
}
