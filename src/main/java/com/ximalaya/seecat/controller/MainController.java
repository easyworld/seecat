package com.ximalaya.seecat.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.ximalaya.seecat.dto.ConcernedTomcatServerInfo;
import com.ximalaya.seecat.dto.SetEnvInfo;
import com.ximalaya.seecat.util.ConsoleUtil;
import com.ximalaya.seecat.util.Constants;
import com.ximalaya.seecat.util.ServerXMLParser;
import com.ximalaya.seecat.util.SetEnvParser;

@Controller
public class MainController {

	private static Logger logger = LoggerFactory.getLogger(MainController.class);

	@RequestMapping(value = "getRunningTomcat")
	@ResponseBody
	public String getRunningTomcat() {
		String cmdResult = ConsoleUtil.exec(Constants.CMD);
		if (cmdResult == null || cmdResult.isEmpty()) {
			logger.info("cmd result is empyt, cmd:{}", Constants.CMD);
			return Constants.EMPTY_STRING;
		}
		String[] tomcatList = cmdResult.split("\n");
		Map<String, Object> result = new HashMap<String, Object>();
		for (String str : tomcatList) {
			Map<String, Object> map = new HashMap<String, Object>();
			ConcernedTomcatServerInfo serverInfo = ServerXMLParser
					.getConcernedTomcatServerInfoByPath(str + "/conf/server.xml");
			map.put("server", serverInfo);
			SetEnvInfo envInfo = SetEnvParser.parse(str + "/bin/setenv.sh");
			map.put("env", envInfo);
			result.put(str, map);
		}
		return JSONArray.toJSONString(result);
	}

}
