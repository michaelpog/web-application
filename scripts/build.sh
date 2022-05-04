#!/bin/bash

MAVEN="/usr/local/bin/mvn"

# Clean Previous Artifacts
$MAVEN clean

# Run Tests
echo "Running Tests"
$MAVEN test

# Obtain Exit code
EXIT_CODE=$?

if [ "$EXIT_CODE" -ne 0 ]; then
  echo "Tests failed. Exiting..."
  exit 1
fi

echo "Tests passed. Continuing to package the application"

# Build and 'package' the application
$MAVEN package -Dmaven.test.skip=true
EXIT_CODE=$?

if [ "$EXIT_CODE" -ne 0 ]; then
  echo "Build failed. Exiting..."
  exit 1
fi

echo "The application was built and packaged successfully."
