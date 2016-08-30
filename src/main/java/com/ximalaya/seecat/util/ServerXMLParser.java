package com.ximalaya.seecat.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ximalaya.seecat.dto.ConcernedTomcatServerInfo;

public class ServerXMLParser {
	/**
	 * 根据xml字符串获取关注的tomcat服务器属性
	 * 
	 * @param xmlStr
	 * @return
	 */
	private static Logger logger = LoggerFactory.getLogger(ServerXMLParser.class);

	public static ConcernedTomcatServerInfo getConcernedTomcatServerInfo(String xmlStr) {
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(new ByteArrayInputStream(xmlStr.getBytes()));
		} catch (DocumentException e) {
			return new ConcernedTomcatServerInfo();
		}
		Element root = document.getRootElement();
		ConcernedTomcatServerInfo info = new ConcernedTomcatServerInfo();
		if (root.attribute("shutdown").getText().equals("SHUTDOWN")) {
			info.setShutdown_port(parseIntAdv(root.attributeValue("port")));
		}
		for (@SuppressWarnings("rawtypes")
		Iterator i = root.elementIterator("Service"); i.hasNext();) {
			Element service = (Element) i.next();
			if (service.attribute("name").getText().equals("Catalina")) {
				for (@SuppressWarnings("rawtypes")
				Iterator i2 = service.elementIterator("Connector"); i2.hasNext();) {
					Element connector = (Element) i2.next();
					if (connector.attribute("protocol").getText().equalsIgnoreCase("HTTP/1.1")) {
						info.setHttp_port(parseIntAdv(connector.attributeValue("port")));
						info.setHttp_redirect_port(parseIntAdv(connector.attributeValue("redirectPort")));
					} else if (connector.attribute("protocol").getText().equalsIgnoreCase("AJP/1.3")) {
						info.setAjp_port(parseIntAdv(connector.attributeValue("port")));
						info.setAjp_redirect_port(parseIntAdv(connector.attributeValue("redirectPort")));
					}
				}
			}
		}
		return info;
	}

	public static ConcernedTomcatServerInfo getConcernedTomcatServerInfoByPath(String path) {
		String str = readXML(path);
		if (str == null || str.isEmpty()) {
			return new ConcernedTomcatServerInfo();
		}
		return getConcernedTomcatServerInfo(str);
	}

	public static int parseIntAdv(String str) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			logger.info("Number parse error.String:{}", str);
			return -1;
		}
	}

	public static String readXML(String path) {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
			for (String str = br.readLine(); str != null; str = br.readLine()) {
				sb.append(str + "\n");
			}
			br.close();
		} catch (Exception e) {
			logger.info("File Read error. Path:{}", path);
			return Constants.EMPTY_STRING;
		}
		if (sb.length() == 0) {
			logger.info("No content in path:{}", path);
		}
		return sb.toString();
	}
}
