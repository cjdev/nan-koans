package com.cj.nan.koans.surefire;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.lang.builder.EqualsBuilder;

public class TestCase {
	@XmlAttribute
	public String name, classname;
	@XmlAttribute
	public BigDecimal time;
	
	@XmlElement
	public TestError error, failure;
	
	
	
	public TestError theProblem(){
		if(error!=null) return error;
		if(failure!=null) return failure;
		return null;
	}
	
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(obj, this);
	}
	
}
