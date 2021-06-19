package com.example.restservice;

import java.util.Objects;

public class OutputSnippet {
	String url;
	String name;
	String expires_at;
	String snippet;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExpires_at() {
		return expires_at;
	}
	public void setExpires_at(String expires_at) {
		this.expires_at = expires_at;
	}
	public String getSnippet() {
		return snippet;
	}
	public void setSnippet(String snippet) {
		this.snippet = snippet;
	}
	public OutputSnippet(String url, String name, String expires_at, String snippet) {
		super();
		this.url = url;
		this.name = name;
		this.expires_at = expires_at;
		this.snippet = snippet;
	}
	public OutputSnippet() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		return Objects.hash(expires_at, name, snippet, url);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OutputSnippet other = (OutputSnippet) obj;
		return Objects.equals(expires_at, other.expires_at) && Objects.equals(name, other.name)
				&& Objects.equals(snippet, other.snippet) && Objects.equals(url, other.url);
	}
	@Override
	public String toString() {
		return "OutputSnippet [url=" + url + ", name=" + name + ", expires_at=" + expires_at + ", snippet=" + snippet
				+ "]";
	}

}
