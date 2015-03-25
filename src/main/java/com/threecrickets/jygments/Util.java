/**
 * Copyright 2010-2014 Three Crickets LLC.
 * <p>
 * The contents of this file are subject to the terms of a BSD license. See
 * attached license.txt.
 * <p>
 * Alternatively, you can obtain a royalty free commercial license with less
 * limitations, transferable or non-transferable, directly from Three Crickets
 * at http://threecrickets.com/
 */

package com.threecrickets.jygments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Tal Liron
 */
public class Util {
	public static String literalRegEx(String expression) {
		return "\\Q" + expression + "\\E";
	}

	public static String replace(String string, String occurence, String replacement) {
		return string.replaceAll(literalRegEx(occurence), replacement);
	}

	public static String streamToString(InputStream stream) throws IOException {
		StringBuilder builder = new StringBuilder();
		String line;

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
			while ((line = reader.readLine()) != null)
				builder.append(line).append("\n");
		} finally {
			stream.close();
		}

		return builder.toString();
	}

	public static String rejsonToJson(InputStream stream) throws IOException {
		String rejson = streamToString(stream);
		String json = rejsonToJson(rejson, true);
		json = rejsonToJson(json, false);
		return json;
	}

	public static String rejsonToJson(String rejson, boolean doubleQuote) {
		Matcher matcher = doubleQuote ? DOUBLE_QUOTED_STRING.matcher(rejson) : SINGLE_QUOTED_STRING.matcher(rejson);
		StringBuilder json = new StringBuilder();
		int start = 0, end = 0, lastEnd = 0;
		while (matcher.find()) {
			lastEnd = end;
			start = matcher.start();
			end = matcher.end();
			if ((start > 0) && (rejson.charAt(start - 1) == 'r')) {
				// Convert Python-style r"" string to Java-compatible pattern
				String string = rejson.substring(start + 1, end - 1);
				json.append(rejson.substring(lastEnd, start - 1));
				json.append('\"');
				json.append(pythonRegExToJavaPattern(string, doubleQuote));
				json.append('\"');
			}
			/*
			 * else if( !doubleQuote ) { // From single quote to double quote String string = rejson.substring( start + 1, end - 1 ); json.append( rejson.substring( lastEnd, start - 1 ) ); json.append( '\"' ); json.append( string.replaceAll( "\"", "\\\\\"" ) ); json.append( '\"' ); }
			 */
			else {
				// As is
				json.append(rejson.substring(lastEnd, end));
			}
		}
		json.append(rejson.substring(end));
		// System.out.println( json );
		return json.toString();
	}

	public static String pythonRegExToJavaPattern(String pattern, boolean doubleQuote) {
		pattern = pattern.replaceAll("\\\\", "\\\\\\\\");
		pattern = pattern.replaceAll("\\{", "\\\\\\\\{");
		pattern = pattern.replaceAll("\\}", "\\\\\\\\}");
		if (!doubleQuote)
			pattern = pattern.replaceAll("\"", "\\\\\"");
		// System.out.println( pattern );
		return pattern;
	}

	public static String escapeHtml(String text) {
		text = text.replace("&", "&amp;");
		text = text.replace("<", "&lt;");
		text = text.replace(">", "&gt;");
		text = text.replace("\"", "&quot;");
		text = text.replace("'", "&#39;");
		return text;
	}

	public static String asHtml(String text) {
		text = escapeHtml(text);
		text = text.replace(" ", "&nbsp;");
		return text;
	}

	private static final Pattern DOUBLE_QUOTED_STRING = Pattern.compile("\"(?>\\\\.|.)*?\"");

	private static final Pattern SINGLE_QUOTED_STRING = Pattern.compile("'(?>\\\\.|.)*?'");
}
