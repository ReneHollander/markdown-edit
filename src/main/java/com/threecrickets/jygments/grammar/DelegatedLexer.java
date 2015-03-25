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

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import com.threecrickets.jygments.ResolutionException;

/**
 * @author Tal Liron
 */
public class DelegatedLexer extends Lexer {
	//
	// Lexer
	//

	@Override
	public Iterable<Token> getTokensUnprocessed(String text) {
		StringBuilder buffered = new StringBuilder();
		List<Token> lngBuffer = new LinkedList<Token>();
		List<Insertion> insertions = new LinkedList<Insertion>();

		Iterable<Token> tokens = languageLexer.getTokensUnprocessed(text);

		for (Token t : tokens) {
			if (t.getType().getName().equals("Other")) {
				if (!lngBuffer.isEmpty()) {
					insertions.add(new Insertion(buffered.length(), lngBuffer));
					lngBuffer = new LinkedList<Token>();
				}
				buffered.append(t.getValue());
			} else
				lngBuffer.add(t);
		}

		if (!lngBuffer.isEmpty())
			insertions.add(new Insertion(buffered.length(), lngBuffer));

		return doInsertions(insertions, rootLexer.getTokensUnprocessed(buffered.toString()));
	}

	// //////////////////////////////////////////////////////////////////////////
	// Protected

	@Override
	protected void addJson(Map<String, Object> json) throws ResolutionException {
		super.addJson(json);
		rootLexer = LexerHelper.getLexer((String) json.get("root_lexer"));
		languageLexer = LexerHelper.getLexer((String) json.get("language_lexer"));
	}

	// //////////////////////////////////////////////////////////////////////////
	// Private

	private Lexer rootLexer;

	private Lexer languageLexer;

	private static class Insertion {
		public int index;

		public List<Token> lngBuffer;

		public Insertion(int index, List<Token> lngBuffer) {
			super();
			this.index = index;
			this.lngBuffer = lngBuffer;
		}
	}

	private Iterable<Token> doInsertions(List<Insertion> insertions, Iterable<Token> tokens) {
		ListIterator<Insertion> li = insertions.listIterator();
		Insertion next_ins = li.hasNext() ? (Insertion) li.next() : null;
		int len = 0;
		LinkedList<Token> rc = new LinkedList<Token>();

		for (Token t : tokens) {
			len += t.getValue().length();
			String s = t.getValue();
			int pos = 0;
			while (next_ins != null && next_ins.index <= len) {
				rc.add(new Token(t.getPos(), t.getType(), s.substring(pos, s.length() + (next_ins.index - len))));
				pos = s.length() + (next_ins.index - len);
				for (Token tt : next_ins.lngBuffer)
					rc.add(tt);
				next_ins = li.hasNext() ? li.next() : null;
			}
			if (pos < s.length())
				rc.add(new Token(t.getPos(), t.getType(), s.substring(pos)));
		}

		// Do remaining tokens
		while (li.hasNext())
			for (Token tt : ((Insertion) li.next()).lngBuffer)
				rc.add(tt);

		return rc;
	}
}
