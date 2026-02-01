package org.dxworks.utils.ignorer;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.dxworks.utils.ignorer.Ignorer.log;

/**
 * Builder for creating {@link Ignorer} instances from glob patterns.
 *
 * <p>Patterns can be provided as a list of strings or read from a file.
 * Patterns starting with {@code !} are whitelist patterns (exclusions).
 * Lines starting with {@code #} in files are treated as comments.
 *
 * <p>Example:
 * <pre>{@code
 * // From a list
 * Ignorer ignorer = new IgnorerBuilder(List.of("**&#47;*.log", "!**&#47;important.log")).compile();
 *
 * // From a file
 * Ignorer ignorer = new IgnorerBuilder(Path.of(".gitignore")).compile();
 * }</pre>
 */
public class IgnorerBuilder {

	private final List<String> globs;

	private final FileSystem fileSystem = FileSystems.getDefault();

	/**
	 * Creates a builder with the specified glob patterns.
	 *
	 * @param globs list of glob patterns; patterns starting with {@code !} are whitelist patterns
	 */
	public IgnorerBuilder(List<String> globs) {
		this.globs = globs;
	}

	/**
	 * Creates a builder by reading glob patterns from a file.
	 *
	 * <p>The file format supports:
	 * <ul>
	 *   <li>One pattern per line</li>
	 *   <li>Lines starting with {@code #} are comments</li>
	 *   <li>Empty lines are ignored</li>
	 *   <li>Patterns starting with {@code !} are whitelist patterns</li>
	 * </ul>
	 *
	 * @param path path to the file containing glob patterns
	 */
	public IgnorerBuilder(Path path) {
		this.globs = this.getGlobs(path);
	}

	/**
	 * Compiles the glob patterns into an {@link Ignorer}.
	 *
	 * @return a new Ignorer instance
	 */
	public Ignorer compile() {
		List<PathMatcher> blackMatchersGlobs = this.preCompile(true);
		List<PathMatcher> whiteMatchersGlobs = this.preCompile(false);
		return new Ignorer(blackMatchersGlobs, whiteMatchersGlobs);
	}

	private List<PathMatcher> preCompile(boolean isNegative) {
		return this.globs
				.stream()
				.filter(glob -> {
					if (isNegative)
						return !glob.startsWith("!");
					else
						return glob.startsWith("!");
				})
				.map(glob -> {
					if (!isNegative)
						return glob.substring(1);
					else return glob;
				})
				.map(glob -> "glob:" + glob)
				.map(fileSystem::getPathMatcher)
				.collect(Collectors.toList());
	}

	private List<String> getGlobs(Path path) {
		log("Fetch globs from " + path.toAbsolutePath());
		List<String> fileItems = new LinkedList<>();
		try (BufferedReader br = new BufferedReader(new java.io.FileReader(path.toString()))) {
			String globItem;
			while ((globItem = br.readLine()) != null) {
				fileItems.add(globItem);
			}
		} catch (IOException e) {
			log("Error reading globs file: " + e.getMessage());
		}

		List<String> globs = fileItems
				.stream()
				.map(String::trim)
				.map(glob -> glob.replaceAll(" ", ""))
				.filter(line -> !line.isEmpty())
				.filter(line -> !line.startsWith("#"))
				.collect(Collectors.toList());

		log("Globs list: " + globs);

		return globs;
	}

	/**
	 * Returns the list of glob patterns.
	 *
	 * @return the glob patterns
	 */
	public List<String> getGlobs() {
		return globs;
	}
}
