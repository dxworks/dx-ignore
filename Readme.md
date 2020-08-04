# Ignorer-library

This is a java library that helps you to
check if a path belongs to a set of restrictions
in a style like gitignore by using the unix globs pattern.

Visit us on [Github](https://github.com/dxworks/ignorer-library).

## Unix globs

Globs are a unix feature that helps you to identify
if a path belongs to a specific pattern similar with regex 
but much more simple.

For some examples of globs patterns, and a brief documentation check:

https://linuxhint.com/bash_globbing_tutorial/

## Usage

The `IgnorerBuilder` helps you to compile your globs.
It can be used with a list of String patterns or file:

### Create Ignorer
```
    List<String> globs = List.of(
                  "**/*.iml", // ignore all files with '.iml' extension
                  "**/.idea/**", // ignore all files from the .idea/ directory
                  "**/target/**", // ignore all files from the target/ directory
                  "!**/.idea/.gitignore"); // ignore all files from the .idea/ directory with the exeption of .gitignore

    IgnorerBuilder builder = new IgnorerBuilder(globs); 
    \\ OR
    IgnorerBuilder builderFromFile = new IgnorerBuilder(Path.of("{project-path}/src/main/resources/.globs"));     

    Ignorer ignorer = builder.compile();
```


The `accept` method of the `ignorer` object will check if your path belongs
or not to those restrictions.


```
    ignorer.accept("{project-path}/src/main/java/com/denisfeier/Main.java"); // true
    ignorer.accept("{project-path}/src/test/java/GlobsReader.java"); // true
    ignorer.accept("{project-path}/ignore-library.iml"); // false
    ignorer.accept("{project-path}/.idea/.gitignore"); // true because of this rule !**/.idea/.gitignore
    ignorer.accept("{project-path}/.idea/.random"); // false
```


### Ignorer File Example

It can be a text file with this globs, for example:

```
# black listed globs
**/*.iml
**/.idea/**
**/target/**

# white listed globs
! **/.idea/.gitignore
```