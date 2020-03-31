## Web Crawler CLI

Web Crawler CLI is a simple command line interface implementing the [Frog Front Web Crawler Library](https://github.com/Frog-Front/web-crawler).

### Building
The project is built using [gradle](https://gradle.org/). Once [installed](https://gradle.org/install/) building the project is done with the following command.

```bash
$> gradle
...
BUILD SUCCESSFUL in 17s
6 actionable tasks: 6 executed
```


### Running
The application can be run by invoking two different methods.

The first is to use gradle to run the project with its internal `JavaExec` command. This is invoked as follows.

```bash
$> gradle run --args='-l https://wiprodigital.com/ -f output.txt'
```

The second method is to invoke it with the `java -jar` command. During the initial build [Shadow Jar](https://github.com/johnrengelman/shadow) is invoked creating a Fat jar.

```bash
$> java -jar build/libs/web-crawler-cli-0.0.1.jar -l https://wiprodigital.com/ -f output.txt

```

### Future Plans
  - Implement a more robust CLI. Currently the arguments have to be ordered.. not nice!
  - Dockerize!! Runnning the application from a docker container and have it available via HUB would be pretty cool.
