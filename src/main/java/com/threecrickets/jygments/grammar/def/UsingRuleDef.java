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

package com.threecrickets.jygments.grammar.def;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import com.threecrickets.jygments.ResolutionException;
import com.threecrickets.jygments.grammar.Grammar;
import com.threecrickets.jygments.grammar.Lexer;
import com.threecrickets.jygments.grammar.LexerHelper;
import com.threecrickets.jygments.grammar.State;
import com.threecrickets.jygments.grammar.UsingRule;

/**
 * @author Tal Liron
 */
public class UsingRuleDef extends StateDef {
	//
	// Construction
	//

	public UsingRuleDef(String stateName, String pattern, String usingLexerName) {
		super(stateName);
		this.pattern = pattern;
		this.usingLexerName = usingLexerName;
	}

	//
	// Attributes
	//

	public String getPattern() {
		return pattern;
	}

	public String getUsingLexerName() {
		return usingLexerName;
	}

	//
	// Def
	//

	@Override
	public boolean resolve(Grammar grammar) throws ResolutionException {
		Pattern pattern;
		try {
			pattern = Pattern.compile(this.pattern, Pattern.MULTILINE | Pattern.DOTALL);
		} catch (PatternSyntaxException x) {
			throw new ResolutionException("RegEx syntax error: " + this.pattern, x);
		}

		Lexer usingLexer = LexerHelper.getLexer(usingLexerName);
		UsingRule rule = new UsingRule(pattern, usingLexer);
		State state = grammar.getState(stateName);
		state.addRule(rule);

		resolved = true;
		return true;
	}

	//
	// Object
	//

	@Override
	public String toString() {
		return super.toString() + ", " + pattern + ", " + usingLexerName;
	}

	// //////////////////////////////////////////////////////////////////////////
	// Protected

	// //////////////////////////////////////////////////////////////////////////
	// Private

	private final String pattern;

	private final String usingLexerName;
}
