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

package com.threecrickets.jygments.format;

import java.io.IOException;

import com.threecrickets.jygments.Util;
import com.threecrickets.jygments.grammar.Token;

/**
 * @author Rene Hollander
 */
public class HtmlFormatter extends Formatter {

	@Override
	public String format(Iterable<Token> tokenSource) throws IOException {
		StringBuilder output = new StringBuilder();
		StringBuilder line = new StringBuilder();
		int line_no = 1;
		for (Token token : tokenSource) {
			String[] toks = token.getValue().split("\n", -1);
			for (int i = 0; i < toks.length - 1; i++) {
				format_partial_token(token, toks[i], line);
				format_line(line.toString(), output, line_no++);
				line = new StringBuilder();
			}
			format_partial_token(token, toks[toks.length - 1], line);
		}
		if (line.length() > 0)
			format_line(line.toString(), output, line_no++);
		return output.toString();
	}

	private void format_partial_token(Token token, String part_tok, StringBuilder line) {
		if (token.getType().getShortName().length() > 0) {
			line.append("<span class=\"");
			line.append(token.getType().getShortName());
			line.append("\">");
			line.append(Util.escapeHtml(part_tok));
			line.append("</span>");
		} else
			line.append(Util.escapeHtml(part_tok));
	}

	public void format_line(String line, StringBuilder sb, int line_no) throws IOException {
		sb.append(line);
		sb.append("\n");
	}
}
