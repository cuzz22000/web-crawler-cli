package org.cjwilson.webcrawler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import com.frogfront.webcrawler.DomainWebCrawler;
import com.frogfront.webcrawler.ReportingLocationProvider;
import com.frogfront.webcrawler.RobotsTxtParser;
import com.frogfront.webcrawler.api.LocationProvider;
import com.frogfront.webcrawler.api.RobotsTxt;
import com.frogfront.webcrawler.api.WebCrawler;
import com.google.common.base.Stopwatch;

public class App {

	public static void main(String[] args) throws IOException {

		if (args.length == 0 || args[0].equals("--help") || args[0].equals("-h")) {
			System.out.println(usage());
		} else if (args[0].equals("--loc") || args[0].equals("-l") && args[2].equals("--file")
				|| args[2].equals("-f")) {
			System.out.println("Building report for " + args[1]);

			Stopwatch stopwatch = Stopwatch.createStarted();
			File outFile = new File(args[3]);
			FileOutputStream fileOutputStream = new FileOutputStream(outFile);
			LocationProvider locationProvider = new ReportingLocationProvider(fileOutputStream);
			URL robostsTxtUrl = new URL(args[1].endsWith("/") ? args[1] + "robots.txt" : args[1] + "/robots.txt");
			RobotsTxt robotsTxt = new RobotsTxtParser(robostsTxtUrl);
			WebCrawler webCrawler = new DomainWebCrawler().useRobotstxt(robotsTxt)
					.useLocationProvider(locationProvider);
			webCrawler.crawlUrl(new URL(args[1]));
			fileOutputStream.close();
			stopwatch.stop();
			long millis = stopwatch.elapsed(TimeUnit.SECONDS);

			System.out.println("processing of " + args[1] + " took " + millis + " seconds");
			System.out.println("output file located at -> " + outFile.getAbsolutePath());
		}
	}

	private static String usage() {
		return new StringBuilder("Usage:\n").append("\t-h , --help : this munu\n")
				.append("\t-l , --loc : url location of site\n").append("\t-f , --file: output file location\n")
				.toString();
	}
}
