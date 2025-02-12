# Build-Your-Own-Git

This project is a Java-based implementation of Git, developed as part of the ["Build Your Own Git" Challenge](https://codecrafters.io/challenges/git) by Codecrafters. This project aims to deepen understanding of Git's internal mechanics by recreating some of its core functionalities from scratch.

## Features

- **Repository Initialization**: Initialize a new Git repository.
- **Commit Creation**: Stage changes and create commits.
- **Object Management**: Handle Git objects such as blobs, trees, and commits.

## Getting Started

### Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/avanimathur/Git-CodeCrafter.git
   cd Git-CodeCrafter

2. **Compile The Program**:
   ```bash
   javac src/main/java/Main.java
   ```

   ```bash
   javac -d out src/main/java/Main.java
   ```

Sample Commands :
```bash
java -cp out src/Main/java/Main.java init
```
```bash
java -cp out src/Main/java/Main.java cat-file -p <SHA>
```
```bash
java -cp out src/Main/java/Main.java ls-tree --name-only <SHA>
```
