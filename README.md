# GitHub User Activity CLI

A simple command-line tool to fetch and display GitHub user activity. URL: https://roadmap.sh/projects/github-user-activity

## Requirements

- **Java**: Version 14 or higher (ensure `JAVA_HOME` is correctly configured).
- **Maven**: Ensure Maven is installed and accessible from the command line.

## Installation

To set up the project locally, follow these steps:

### 1. Clone the repository: 
  For clone ise command `git clone https://github.com/Jzargoo/GitHub-User-Activity.git`

### 2. Go to repo
  For navigate use `cd GitHub-User-Activity`

### 3. Install stuff for maven
  Make sure Maven is installed before installing. Use `mvn clean install`

## Usage
  `java -jar target/Github-user-activity-1.0-SNAPSHOT.jar <name>`

## Simple example
  `java -jar target/Github-user-activity-1.0-SNAPSHOT.jar kamranahmedse`
  Output:
  ````Example
- Pushed 3 commit(s) to repository kamranahmedse/developer-roadmap
- Created  pull request(s) in repository kamranahmedse/developer-roadmap
- Pushed  commit(s) to repository kamranahmedse/developer-roadmap
- Created  pull request(s) in repository kamranahmedse/developer-roadmap
- Pushed 2 commit(s) to repository kamranahmedse/developer-roadmap
- Created  pull request(s) in repository kamranahmedse/developer-roadmap
- Watched repository Orange-OpenSource/hurl
