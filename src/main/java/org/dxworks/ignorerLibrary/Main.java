package org.dxworks.ignorerLibrary;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        IgnorerBuilder ignorerBuilder =
                new IgnorerBuilder(Path.of("/home/denisu/IdeaProjects/ignore-library/src/main/resources/.globs"));
        ignorerBuilder.compile();
        Ignorer ignorer = ignorerBuilder.compile();

        System.out.println(ignorer.accept("/home/denisu/IdeaProjects/ignore-library/.idea/.gitignore"));
        System.out.println(ignorer.accept("/home/denisu/IdeaProjects/ignore-library/src/main/java/com/denisfeier/Main.java"));
        System.out.println(ignorer.accept("/home/denisu/IdeaProjects/ignore-library/src/test/java/GlobsReader.java"));
        System.out.println(ignorer.accept("/home/denisu/IdeaProjects/ignore-library/ignore-library.iml"));

        System.out.println(ignorer.accept("/home/denisu/IdeaProjects/ignore-library/.idea/.gitignore"));
    }
}
