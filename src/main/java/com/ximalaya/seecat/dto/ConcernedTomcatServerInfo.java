package com.ximalaya.seecat.dto;

public class ConcernedTomcatServerInfo {
	private int shutdown_port;
	private int http_port;
	private int http_redirect_port;
	private int ajp_port;
	private int ajp_redirect_port;

	public ConcernedTomcatServerInfo() {
		this.shutdown_port = -1;
		this.http_port = -1;
		this.http_redirect_port = -1;
		this.ajp_port = -1;
		this.ajp_redirect_port = -1;
	}

	public int getShutdown_port() {
		return shutdown_port;
	}

	public void setShutdown_port(int shutdown_port) {
		this.shutdown_port = shutdown_port;
	}

	public int getHttp_port() {
		return http_port;
	}

	public void setHttp_port(int http_port) {
		this.http_port = http_port;
	}

	public int getHttp_redirect_port() {
		return http_redirect_port;
	}

	public void setHttp_redirect_port(int http_redirect_port) {
		this.http_redirect_port = http_redirect_port;
	}

	public int getAjp_port() {
		return ajp_port;
	}

	public void setAjp_port(int ajp_port) {
		this.ajp_port = ajp_port;
	}

	public int getAjp_redirect_port() {
		return ajp_redirect_port;
	}

	public void setAjp_redirect_port(int ajp_redirect_port) {
		this.ajp_redirect_port = ajp_redirect_port;
	}

	@Override
	public String toString() {
		return "ConcernedTomcatServerInfo [shutdown_port=" + shutdown_port + ", http_port=" + http_port
				+ ", http_redirect_port=" + http_redirect_port + ", ajp_port=" + ajp_port + ", ajp_redirect_port="
				+ ajp_redirect_port + "]";
	}
}
