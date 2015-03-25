package com.threecrickets.jygments.format;

import com.threecrickets.jygments.grammar.Token;

public abstract class Formatter {

	public static final Formatter HTML_FORMATTER = new HtmlFormatter();

	public abstract String format(Iterable<Token> tokenSource) throws Exception;

}
