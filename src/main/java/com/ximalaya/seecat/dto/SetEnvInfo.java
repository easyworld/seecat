package com.ximalaya.seecat.dto;

public class SetEnvInfo {
	private int runjdwp_address;
	private int jmxremote_port;

	public SetEnvInfo() {
		runjdwp_address = -1;
		jmxremote_port = -1;
	}

	public SetEnvInfo(int runjdwp_address, int jmxremote_port) {
		this.runjdwp_address = runjdwp_address;
		this.jmxremote_port = jmxremote_port;
	}

	public int getRunjdwp_address() {
		return runjdwp_address;
	}

	public void setRunjdwp_address(int runjdwp_address) {
		this.runjdwp_address = runjdwp_address;
	}

	public int getJmxremote_port() {
		return jmxremote_port;
	}

	public void setJmxremote_port(int jmxremote_port) {
		this.jmxremote_port = jmxremote_port;
	}

	@Override
	public String toString() {
		return "SetEnvInfo [runjdwp_address=" + runjdwp_address + ", jmxremote_port=" + jmxremote_port + "]";
	}
}
