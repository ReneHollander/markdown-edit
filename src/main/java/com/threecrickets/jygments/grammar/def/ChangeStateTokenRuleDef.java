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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.threecrickets.jygments.ResolutionException;
import com.threecrickets.jygments.grammar.Grammar;
import com.threecrickets.jygments.grammar.Rule;
import com.threecrickets.jygments.grammar.State;
import com.threecrickets.jygments.grammar.TokenRule;
import com.threecrickets.jygments.grammar.TokenType;

/**
 * @author Tal Liron
 */
public class ChangeStateTokenRuleDef extends TokenRuleDef {
	//
	// Construction
	//

	public ChangeStateTokenRuleDef(String stateName, String pattern, int flags, List<String> tokenTypeNames, List<String> nextStateNames) {
		super(stateName, pattern, flags, tokenTypeNames);
		this.nextStateNames = nextStateNames;
	}

	public ChangeStateTokenRuleDef(String stateName, String pattern, int flags, String[] tokenTypeNames, String... nextStateNames) {
		super(stateName, pattern, flags, tokenTypeNames);
		ArrayList<String> list = new ArrayList<String>(nextStateNames.length);
		for (String nextStateName : nextStateNames)
			list.add(nextStateName);
		this.nextStateNames = list;
	}

	//
	// Def
	//

	@Override
	public boolean resolve(Grammar grammar) throws ResolutionException {
		if (grammar.resolveStates(nextStateNames) != null)
			return super.resolve(grammar);
		else {
			if (placeHolder == null) {
				placeHolder = new Rule();
				State state = grammar.getState(stateName);
				state.addRule(placeHolder);
			}
			return false;
		}
	}

	//
	// TokenRuleDef
	//

	@Override
	protected TokenRule createTokenRule(Pattern pattern, List<TokenType> tokenTypes, Grammar grammar) throws ResolutionException {
		return new TokenRule(pattern, tokenTypes, grammar.resolveStates(nextStateNames));
	}

	// //////////////////////////////////////////////////////////////////////////
	// Private

	private final List<String> nextStateNames;
}
