package com.ximalaya.seecat.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ConsoleUtil {
	public static String exec(String cmd) {
		Runtime run = Runtime.getRuntime();
		StringBuffer sb = new StringBuffer();
		try {
			Process p = run.exec(cmd);
			BufferedInputStream in = new BufferedInputStream(p.getInputStream());
			BufferedReader inBr = new BufferedReader(new InputStreamReader(in));
			String lineStr;
			while ((lineStr = inBr.readLine()) != null)

				sb.append(lineStr + "\n");

			if (p.waitFor() != 0) {
				if (p.exitValue() == 1)
					System.err.println("command exec failed");
			}
			inBr.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

}
