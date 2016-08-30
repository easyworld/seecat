package com.ximalaya.seecat.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ximalaya.seecat.dto.SetEnvInfo;

public class SetEnvParser {
	public static SetEnvInfo parse(String path) {
		String str = ServerXMLParser.readXML(path);
		SetEnvInfo result = new SetEnvInfo();
		Pattern p = Pattern.compile("-Xrunjdwp:[,=_\\w]+address=(\\d+)");
		Matcher m = p.matcher(str);
		if (m.find()) {
			result.setRunjdwp_address(ServerXMLParser.parseIntAdv(m.group(1)));
		}
		p = Pattern.compile("-Dcom.sun.management.jmxremote.port=(\\d+)");
		m = p.matcher(str);
		if (m.find()) {
			result.setJmxremote_port(ServerXMLParser.parseIntAdv(m.group(1)));
		}
		return result;
	}

}
