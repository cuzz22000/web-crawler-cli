package org.cjwilson.webcrawler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import com.frogfront.webcrawler.DomainWebCrawler;
import com.frogfront.webcrawler.ReportingLocationProvider;
import com.frogfront.webcrawler.RobotsTxtParser;
import com.frogfront.webcrawler.api.LocationProvider;
import com.frogfront.webcrawler.api.RobotsTxt;
import com.frogfront.webcrawler.api.WebCrawler;
import com.google.common.base.Stopwatch;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

public class App implements Callable<Integer> {

	@Parameters(index = "0", description = "The base domain url to crawl ex. http://example.com")
	private URL url;

	@Option(names = { "-f", "--file" }, description = "The file to output report")
	private File outFile = new File("output.txt");

	public static void main(String[] args) throws IOException {
		int exitCode = new CommandLine(new App()).execute(args);
		System.exit(exitCode);
	}

	@Override
	public Integer call() {
		System.out.println("Building report for " + this.url.toString());
		try {
			Stopwatch stopwatch = Stopwatch.createStarted();
			FileOutputStream fileOutputStream = new FileOutputStream(outFile);
			LocationProvider locationProvider = new ReportingLocationProvider(fileOutputStream);
			URL robostsTxtUrl = new URL(this.url.toString().endsWith("/") ? new URL(url.toString()) + "robots.txt"
					: new URL(url.toString()) + "/robots.txt");
			RobotsTxt robotsTxt = new RobotsTxtParser(robostsTxtUrl);
			WebCrawler webCrawler = new DomainWebCrawler().useRobotstxt(robotsTxt)
					.useLocationProvider(locationProvider);
			webCrawler.crawlUrl(this.url);
			fileOutputStream.close();
			stopwatch.stop();
			long millis = stopwatch.elapsed(TimeUnit.SECONDS);

			System.out.println("processing of " + this.url.toString() + " took " + millis + " seconds");
			System.out.println("output file located at -> " + outFile.getAbsolutePath());
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return 1;
		}
		return 0;
	}
}
