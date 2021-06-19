package com.example.restservice;

import java.util.Objects;

public class InputSnippet {
	String name;
	Integer expires_in;
	String snippet;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getExpiresin() {
		return expires_in;
	}
	public void setExpiresin(Integer expiresin) {
		this.expires_in = expiresin;
	}
	public String getSnippet() {
		return snippet;
	}
	public void setSnippet(String snippet) {
		this.snippet = snippet;
	}
	public InputSnippet(String name, Integer expiresin, String snippet) {
		super();
		this.name = name;
		this.expires_in = expiresin;
		this.snippet = snippet;
	}
	public InputSnippet() {
	}
	@Override
	public String toString() {
		return "InputSnippet [name=" + name + ", expiresin=" + expires_in + ", snippet=" + snippet + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(expires_in, name, snippet);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InputSnippet other = (InputSnippet) obj;
		return Objects.equals(expires_in, other.expires_in) && Objects.equals(name, other.name)
				&& Objects.equals(snippet, other.snippet);
	}
	

}
