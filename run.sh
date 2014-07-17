#!/bin/sh
cd runner && 
mvn org.apache.maven.plugins:maven-dependency-plugin:2.4:build-classpath compile -Dmdep.outputFile=target/classpath &&
mvn compile &&
cd .. &&
java -cp runner/target/classes:`cat runner/target/classpath`  com.cj.nan.koans.Runner
