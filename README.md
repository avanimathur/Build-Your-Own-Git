# Build-Your-Own-Git

This project is a Java-based implementation of Git, developed as part of the "Build Your Own Git" challenge by [Codecrafters](https://codecrafters.io). This project aims to deepen understanding of Git's internal mechanics by recreating some of its core functionalities from scratch.

## Features

- **Repository Initialization**: Initialize a new Git repository.
- **Commit Creation**: Stage changes and create commits.
- **Repository Cloning**: Clone existing public repositories.
- **Object Management**: Handle Git objects such as blobs, trees, and commits.

## Getting Started

### Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/avanimathur/Git-CodeCrafter.git
   cd Git-CodeCrafter

Sample Commands :

```bash
javac src/main/java/Main.java
```

```bash
javac -d out src/main/java/Main.java
```
```bash
java -cp out src/Main/java/Main.java init
```
```bash
java -cp out src/Main/java/Main.java cat-file -p <commit_hash>
```
```bash
java -cp out src/Main/java/Main.java ls-tree --name-only <commit_hash>
```
