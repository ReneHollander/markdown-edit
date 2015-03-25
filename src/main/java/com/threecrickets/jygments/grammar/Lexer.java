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

package com.threecrickets.jygments.grammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.threecrickets.jygments.ResolutionException;
import com.threecrickets.jygments.grammar.def.ChangeStateTokenRuleDef;
import com.threecrickets.jygments.grammar.def.IncludeDef;
import com.threecrickets.jygments.grammar.def.TokenRuleDef;

/**
 * @author Tal Liron
 */
public class Lexer extends Grammar {

	public Lexer() {
		this(false, false, 4, "utf8");
	}

	public Lexer(boolean stripNewlines, boolean stripAll, int tabSize, String encoding) {
		this.stripNewLines = stripNewlines;
		this.stripAll = stripAll;
		this.tabSize = tabSize;
	}

	public boolean isStripNewLines() {
		return stripNewLines;
	}

	public void setStripNewLines(boolean stripNewLines) {
		this.stripNewLines = stripNewLines;
	}

	public boolean isStripAll() {
		return stripAll;
	}

	public void setStripAll(boolean stripAll) {
		this.stripAll = stripAll;
	}

	public int getTabSize() {
		return tabSize;
	}

	public void setTabSize(int tabSize) {
		this.tabSize = tabSize;
	}

	public float analyzeText(String text) {
		return 0;
	}

	public Iterable<Token> getTokens(String text) {
		return getTokens(text, false);
	}

	public Iterable<Token> getTokens(String text, boolean unfiltered) {
		// text = text.replace( "\r\n", "\n" ).replace( "\r", "\n" );
		// if( stripAll )
		// text = text.trim();
		// if( stripNewLines )
		// text = text.replace( "\n", "" );
		if (tabSize > 0) {
			// expand tabs
		}
		if (!text.endsWith("\n"))
			text += "\n";
		Iterable<Token> tokens = getTokensUnprocessed(text);
		if (!unfiltered) {
			// apply filters
		}
		return tokens;
	}

	public Iterable<Token> getTokensUnprocessed(String text) {
		ArrayList<Token> list = new ArrayList<Token>(1);
		list.add(new Token(0, TokenType.Text, text));
		return list;
	}

	protected void addAlias(String alias) {
		aliases.add(alias);
	}

	protected void addFilename(String filename) {
		filenames.add(filename);
	}

	protected void addMimeType(String mimeType) {
		mimeTypes.add(mimeType);
	}

	protected void include(String stateName, String includedStateName) {
		getState(stateName).addDef(new IncludeDef(stateName, includedStateName));
	}

	protected void rule(String stateName, String pattern, int flags, String tokenTypeName) {
		getState(stateName).addDef(new TokenRuleDef(stateName, pattern, flags, tokenTypeName));
	}

	protected void rule(String stateName, String pattern, int flags, String tokenTypeName, String nextStateName) {
		getState(stateName).addDef(new ChangeStateTokenRuleDef(stateName, pattern, flags, new String[] { tokenTypeName }, nextStateName));
	}

	protected void rule(String stateName, String pattern, int flags, String[] tokenTypeNames) {
		getState(stateName).addDef(new TokenRuleDef(stateName, pattern, flags, tokenTypeNames));
	}

	protected void rule(String stateName, String pattern, int flags, String[] tokenTypeNames, String... nextStateNames) {
		getState(stateName).addDef(new ChangeStateTokenRuleDef(stateName, pattern, flags, tokenTypeNames, nextStateNames));
	}

	protected void addJson(Map<String, Object> json) throws ResolutionException {
		@SuppressWarnings("unchecked")
		List<String> filenames = (List<String>) json.get("filenames");
		if (filenames == null)
			return;
		for (String filename : filenames)
			addFilename(filename);
	}

	private boolean stripNewLines;

	private boolean stripAll;

	private int tabSize;

	private final List<String> aliases = new ArrayList<String>();

	private final List<String> filenames = new ArrayList<String>();

	private final List<String> mimeTypes = new ArrayList<String>();
}
