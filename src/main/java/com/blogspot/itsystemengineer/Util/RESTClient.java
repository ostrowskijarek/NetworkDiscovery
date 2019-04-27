package com.blogspot.itsystemengineer.Util;

public interface RESTClient {
	
	void setUri(String uri);
	void setBody(String body);
	void get();
	void post();
}
