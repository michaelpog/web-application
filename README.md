
# Welcome to the Flight Reservation Service Project Guide

#### Clean the Project from Previous Executions
> `mvn clean`

#### Compile and Run Tests
>`mvn test`

#### Build and Package into an Executable jar, Skipping Tests
>`mvn package -Dmaven.test.skip=true`

#### Run the application:
>`java -jar /path/to/jar-with-dependencies.jar /path/to/config.cfg`

For Example:
> `java -jar target/web-application-1.0-SNAPSHOT-jar-with-dependencies.jar src/test/resources/config-example.cfg`

# Contributing to the Project

- All development needs to take place in _**GIT branches**_.
- The ***master*** branch should always contain the latest and most stable code, which is ready to be released.
- **Nobody** is allowed to commit/push code directly to master after the initial commit.
- All code ***merged*** into the *master* branch needs to be **peer reviewed** through a formal     
  **Pull Request**.

## GIT Branch Naming Convention

- Each Git Branch needs to follow the following naming conversion: **[feature/bug-XXXX]**
### Examples:
- A new branch that contains a new feature development and is associated with *Issue 1234* should be called: ***feature-1234***
- A bug fix associated with *"Issue 5678"* should be worked on as part of a ***bug-5678*** GIT branch


Note: The naming convention is specific to this project only, and may different from team to team and from organization to organization


## Pull Requests
- Every change that happened in a separate branch from master needs to go through a ***Pull Request*** **from** the *development branch* **to** *master*.
- If the change contains multiple commits, the commits need to be **squashed** into a single commit before merging to master.
- The squash commit need to start with the **issue number** followed by a **colon**, followed the description of the action this commit does.
### Examples:
"Issue-1234: Add build script to automatically build and test the application"
"Issue-5678: Fix bug in test ABCD..."

- After the Pull Request gets closed, the branch that was merged into master needs to be deleted
 