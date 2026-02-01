# dx-ignore

Java library for gitignore-style path matching using Unix globs.

## Build Commands

```bash
# Build and test
./mvnw clean verify

# Build without tests
./mvnw clean package -DskipTests

# Run tests only
./mvnw test
```

## Release Workflow

1. Push to `main` branch triggers snapshot deployment
2. Create and push a tag `v1.2.3` to publish a release

## Architecture

- **Ignorer** - Main class that checks if paths match the configured glob patterns
- **IgnorerBuilder** - Builder class to compile glob patterns from a list or file

## Debug Logging

Set the environment variable `DX_IGNORE_DEBUG=true` to enable debug output.
