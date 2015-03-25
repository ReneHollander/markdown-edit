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

import com.threecrickets.jygments.NestedDef;

/**
 * @author Tal Liron
 */
public class State extends NestedDef<Grammar> {
	//
	// Construction
	//

	public State(String name) {
		super();
		this.name = name;
	}

	public State(State state1, State state2) {
		this(state1.getName() + "+" + state2.getName());
		include(state1);
		include(state2);
	}

	//
	// Attributes
	//

	public String getName() {
		return name;
	}

	public List<Rule> getRules() {
		return rules;
	}

	//
	// Operations
	//

	public void addRule(Rule rule) {
		rules.add(rule);
	}

	public void addRuleAt(int location, Rule rule) {
		rules.add(location, rule);
	}

	public void include(State includedState) {
		rules.addAll(includedState.rules);
	}

	public void includeAt(int location, State includedState) {
		rules.addAll(location, includedState.rules);
	}

	// //////////////////////////////////////////////////////////////////////////
	// Private

	private final String name;

	private final List<Rule> rules = new ArrayList<Rule>();
}
