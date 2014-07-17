package com.cj.nan.koans.surefire;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="testsuite")
public class TestSuiteFile {
	@XmlAttribute
	public String name;
	@XmlAttribute
	public Integer failures, errors, skipped, tests;
	@XmlAttribute
	public BigDecimal time;
	
	@XmlElement(name="testcase")
	public List<TestCase> testCases = new ArrayList<TestCase>();
}
