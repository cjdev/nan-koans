package com.cj.nan.koans.surefire;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

import org.apache.commons.lang.builder.EqualsBuilder;

public class TestError {
	@XmlAttribute
	public String message, type;
	
	@XmlValue
	public String content;
	

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(obj, this);
	}
}
