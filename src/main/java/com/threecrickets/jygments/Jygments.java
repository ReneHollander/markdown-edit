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

import com.threecrickets.jygments.format.Formatter;
import com.threecrickets.jygments.grammar.Lexer;
import com.threecrickets.jygments.grammar.Token;

/**
 * @author Tal Liron
 */
public abstract class Jygments {
	//
	// Static operations
	//

	public static Iterable<Token> lex(String code, Lexer lexer) {
		return lexer.getTokens(code);
	}

	public static String format(Iterable<Token> tokens, Formatter formatter) throws Exception {
		return formatter.format(tokens);
	}

	public static String highlight(String code, Lexer lexer, Formatter formatter) throws Exception {
		return format(lex(code, lexer), formatter);
	}

	// //////////////////////////////////////////////////////////////////////////
	// Private

	private Jygments() {
	}
}
