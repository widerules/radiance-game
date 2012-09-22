package org.vagosduke.andengine.radiance.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class TraceUtil {

	
	public static String exceptionToString(Exception e) {
		/** Prints stack trace to a custom writer and outputs it to a string */
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return sw.toString();
	}
}
