package com.cj.nan.koans;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import com.cj.nan.koans.impl.CommandRunner;
import com.cj.nan.koans.impl.CommandRunnerImpl;
import com.cj.nan.koans.surefire.TestCase;
import com.cj.nan.koans.surefire.TestError;
import com.cj.nan.koans.surefire.TestSuiteFile;

public class Runner {
	public static void main(String[] args) throws Exception {
		
		File where = new File(System.getProperty("user.dir"));
		
		quitIfMavenIsNotPresent(where);
		
		Map<String, Long> timestamps = new HashMap<String, Long>();

		Map<String, List<TestSuiteFile>> testResults = new HashMap<String, List<TestSuiteFile>>();
		
		TestCase previousError = null;
		
		while(true){
			List<String> changes = monitorChanges(where, timestamps);
			if(!changes.isEmpty()){


				Set<File> changedProjects = findSubdirsThatChanged(where, changes);

				for(File projectPath : changedProjects){
					System.out.println("Running Tests in " + projectPath);
					
					List<TestSuiteFile> results = runMavenTests(projectPath);
					System.out.println("Found " + results.size() + " suites");
					testResults.put(projectPath.getName(), results);
					
				}
			}
			
			TestCase test = firstError(testResults);
			if(test!=null && !test.equals(previousError)){
				TestError problem = test.theProblem();
				System.out.println("#################################################################");
				System.out.println("## FIX THIS: " + test.name + "(" + test.classname + ")");
				System.out.println(indent("##     ",  problem.message));
				System.out.println(indent("##     ",  problem.content));
				System.out.println("#################################################################");
			}
			
			previousError = test;

			Thread.sleep(1000);
		}
	}


	private static void quitIfMavenIsNotPresent(File where) {
		String versionInfo;
		try {
			CommandRunner shell = new CommandRunnerImpl(System.out, System.err, where);
			versionInfo = shell.run(new ByteArrayInputStream(new byte[]{}), "mvn", "--version");
			if(!versionInfo.startsWith("Apache Maven ")){
				System.out.println("This program requires genuine apache maven, but it looks like you are not using the real thing; when I ran \"mvn --version\", your system said:\n" + 
							indent("    ", versionInfo));
				System.exit(1);
			}
		} catch (Exception e) {
			System.out.println("This program requires genuine apache maven.  However, when I tried to run \"mvn --version\", there was an error:\n" + 
							indent("    ", e.getMessage()));
			System.exit(1);
		}
	}
	
	
	private static String indent(String indentation, String text){
		if(text==null){
			return indentation + "null";
		}else{
			try {
				BufferedReader reader = new BufferedReader(new StringReader(text));
				StringBuilder result = new StringBuilder();
				for(String line = reader.readLine();line!=null;line = reader.readLine()){
					result.append(indentation);
					result.append(line);
					result.append('\n');
				}
				return result.toString();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private static TestCase firstError(Map<String, List<TestSuiteFile>> resultsByModule) {
		for(List<TestSuiteFile> results : resultsByModule.values()){
			for(TestSuiteFile suite : results){
				for(TestCase test : suite.testCases){
					if(test.theProblem()!=null){
						return test;
					}
				}
			}
		}
		return null;
	}

	private static List<TestSuiteFile> runMavenTests(File projectPath)
			throws JAXBException {
		try {
			CommandRunner shell = new CommandRunnerImpl(System.out, System.err, projectPath);
			String output = shell.run( "mvn", "test"); 
			//						System.out.println("Results: " + output);

		} catch (Exception e) {
			//							System.out.println(e.getMessage());
		}
		List<TestSuiteFile> results = new ArrayList<TestSuiteFile>();
		
		File surefireReportsDir = new File(projectPath, "target/surefire-reports");
		if(surefireReportsDir.isDirectory()){
			for (File next : surefireReportsDir.listFiles()){
				if(next.getName().endsWith(".xml")){
					JAXBContext jaxb = JAXBContext.newInstance(TestSuiteFile.class);
					TestSuiteFile suiteInfo = (TestSuiteFile) jaxb.createUnmarshaller().unmarshal(next);
					results.add(suiteInfo);
				}
			}
		}
		return results;
	}

	private static Set<File> findSubdirsThatChanged(File where, List<String> changes) {
		Set<File> modules = new HashSet<File>();

		for(File child : where.listFiles()){
			for(String change : changes){
//				System.out.println("Changed: " + change);
				if(change.startsWith(child.getAbsolutePath()) && child.isDirectory()){
					modules.add(child);
				}
			}
		}

		return modules;
	}

	private static List<String> monitorChanges(File where, Map<String, Long> timestamps) {

		List<String> changes = new ArrayList<String>();

		if(!where.getName().equals("target") && !where.getName().startsWith(".")){
			final String path = where.getAbsolutePath();
			final Long previous = timestamps.get(path);
			final Long current = where.lastModified();

			if(where.exists()){
				timestamps.put(path, current);
			}else{
				timestamps.remove(path);
			} 

			boolean somethingChanged = !current.equals(previous); 

			if(somethingChanged) { 
//				System.out.println(path + "( " + current + " vs " + previous + ")");
				changes.add(path);
			}

			if(where.isDirectory()){
				for(File next : where.listFiles()){
					changes.addAll(monitorChanges(next, timestamps));
				}
			}
		}

		return changes;
	}



}
