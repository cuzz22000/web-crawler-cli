## Web Crawler CLI

![alt travis](https://travis-ci.org/cuzz22000/web-crawler-cli.svg?branch=master)

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
From the command line the application can be run by invoking two different methods.

The first is to use gradle to run the project with its internal `JavaExec` command. This is invoked as follows.

```bash
$> gradle run --args='-l https://sample.com/ -f output.txt'

Building report for https://sample.com/
processing of https://sample.com/ took 9 seconds
output file located at -> output.txt

```

The second method is to invoke it with the `java -jar` command. During the initial build [Shadow Jar](https://github.com/johnrengelman/shadow) is invoked creating a Fat jar.

```bash
$> java -jar build/libs/web-crawler-cli-0.0.1.jar -l https://sample.com/ -f output.txt

Building report for https://sample.com/
processing of https://sample.com/ took 9 seconds
output file located at -> output.txt

```
### Running in Docker
If you don't want to build locally from source you can use the runnable [Docker](https://www.docker.com/) image. Before doing so you will have to have Docker [installed](https://docs.docker.com/install/).

The following command will execute the latest docker image and write the output to your current directory.

```bash
$> docker run -e "crawl_url=https://sample.com/" -v $(pwd):/app/out cuzz22000/web-crawler-cli
```

### Building Docker
The following is the build command for the docker image. It will install the latest `.jar` file located in you `build/libs` directory. You will have to substitute your docker repositoy name.

```bash
$> docker build -t ${your_repo}/web-crawler-cli:latest .
```

### Future Plans
  - Implement a more robust CLI. Currently the arguments have to be ordered.. not nice!
  - ~~Dockerize!! Runnning the application from a docker container and have it available via HUB would be pretty cool.~~
