package org.dxworks.ignorerLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.List;

public class Ignorer {

    private final List<PathMatcher> blackMatchersGlobs;
    private final List<PathMatcher> whiteMatchersGlobs;

    private final Logger logger = LoggerFactory.getLogger(Ignorer.class);

    public Ignorer(List<PathMatcher> blackMatchersGlobs, List<PathMatcher> whiteMatchersGlobs) {
        this.blackMatchersGlobs = blackMatchersGlobs;
        this.whiteMatchersGlobs = whiteMatchersGlobs;
    }

    public boolean accepts(String path) {
        boolean whiteGlobs = this.match(this.whiteMatchersGlobs, path);
        boolean blackGlobs = this.match(this.blackMatchersGlobs, path);

        this.logger.info("Black Globs res: {}", blackGlobs);
        this.logger.info("White Globs res: {}", whiteGlobs);
        this.logger.info("Match Path {} with globs", path);

        return whiteGlobs || !blackGlobs;
    }

    private boolean match(List<PathMatcher> globsMatcher, String path) {
        return globsMatcher.stream()
                .map(glob -> glob.matches(Path.of(path)))
                .reduce(false, (aBoolean, aBoolean2) -> aBoolean || aBoolean2);
    }
}
