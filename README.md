# Composition Assignment

This repository contains a basic composition implementation designed to address an assignment in CS-5800 (Advanced Software Engineering). 
The `driver` package contains a basic program demonstrating a file structure created using the class relationships implemented here. An example of its output can be found in the `output-1.png` and `output-2.png` files.

## Build & Run

To build with Maven, simply navigate to the project root in the command line and run:

```shell
mvn package
```

Alternatively, IDEs such as IntelliJ should be able to build the source files using their standard build utilities.

Once built, the project can be run using the `driver` package:

```shell
java -cp ./target/composition-assignment-1.0-SNAPSHOT.jar driver.Main
```
