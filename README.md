# dx-ignore

[![Build](https://github.com/dxworks/dx-ignore/actions/workflows/build.yml/badge.svg)](https://github.com/dxworks/dx-ignore/actions/workflows/build.yml)
[![Maven Central](https://img.shields.io/maven-central/v/org.dxworks.utils/dx-ignore)](https://central.sonatype.com/artifact/org.dxworks.utils/dx-ignore)

A Java library for gitignore-style path matching using Unix globs.

## Installation

### Maven

```xml
<dependency>
    <groupId>org.dxworks.utils</groupId>
    <artifactId>dx-ignore</artifactId>
    <version>1.1.0</version>
</dependency>
```

### Gradle

```groovy
implementation 'org.dxworks.utils:dx-ignore:1.1.0'
```

## Unix Globs

Globs are a Unix feature for pattern matching paths, similar to regex but simpler.

For examples and documentation: https://linuxhint.com/bash_globbing_tutorial/

## Usage

The `IgnorerBuilder` compiles glob patterns from a list of strings or a file.

### Create Ignorer

```java
List<String> globs = List.of(
    "**/*.iml",           // ignore all files with '.iml' extension
    "**/.idea/**",        // ignore all files in .idea/ directories
    "**/target/**",       // ignore all files in target/ directories
    "!**/.idea/.gitignore" // except .gitignore in .idea/
);

IgnorerBuilder builder = new IgnorerBuilder(globs);
// OR from a file
IgnorerBuilder builderFromFile = new IgnorerBuilder(Path.of("path/to/.globs"));

Ignorer ignorer = builder.compile();
```

### Check Paths

The `accepts` method returns `true` if the path is allowed (not ignored):

```java
ignorer.accepts("src/main/java/com/example/Main.java"); // true
ignorer.accepts("src/test/java/GlobsReader.java");      // true
ignorer.accepts("project.iml");                          // false (matched by **/*.iml)
ignorer.accepts(".idea/.gitignore");                     // true (excluded by !**/.idea/.gitignore)
ignorer.accepts(".idea/random.xml");                     // false (matched by **/.idea/**)
```

### Globs File Format

```
# Comments start with #
**/*.iml
**/.idea/**
**/target/**

# Exclusions start with !
!**/.idea/.gitignore
```

## Debug Logging

Set `DX_IGNORE_DEBUG=true` environment variable to enable debug output.

## License

Apache-2.0
