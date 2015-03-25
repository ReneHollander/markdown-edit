/*
 * Copyright (C) 2009-2011 Mathias Doenitz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.parboiled.matchervisitors;

import static org.parboiled.common.Preconditions.checkArgNotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.parboiled.matchers.AbstractMatcher;
import org.parboiled.matchers.FirstOfMatcher;
import org.parboiled.matchers.Matcher;
import org.parboiled.matchers.OneOrMoreMatcher;
import org.parboiled.matchers.OptionalMatcher;
import org.parboiled.matchers.SequenceMatcher;
import org.parboiled.matchers.TestMatcher;
import org.parboiled.matchers.TestNotMatcher;
import org.parboiled.matchers.ZeroOrMoreMatcher;

/**
 * A MatcherVisitor that executes a given {@link org.parboiled.matchervisitors.DoWithMatcherVisitor.Action} against a whole matcher hierarchy in a depth-first manner. Potential cycles are detected and not rerun.
 */
public class DoWithMatcherVisitor extends DefaultMatcherVisitor<Void> {

	public interface Action {
		void process(Matcher matcher);
	}

	private final Action action;
	private final Set<Matcher> visited = new HashSet<Matcher>();

	public DoWithMatcherVisitor(Action action) {
		this.action = checkArgNotNull(action, "action");
	}

	@Override
	public Void visit(FirstOfMatcher matcher) {
		if (!visited.contains(matcher)) {
			visited.add(matcher);
			List<Matcher> children = matcher.getChildren();
			for (int i = 0, childrenSize = children.size(); i < childrenSize; i++) {
				Matcher sub = children.get(i);
				sub.accept(this);
			}
			action.process(matcher);
		}
		return null;
	}

	@Override
	public Void visit(SequenceMatcher matcher) {
		if (!visited.contains(matcher)) {
			visited.add(matcher);
			List<Matcher> children = matcher.getChildren();
			for (int i = 0, childrenSize = children.size(); i < childrenSize; i++) {
				Matcher sub = children.get(i);
				sub.accept(this);
			}
			action.process(matcher);
		}
		return null;
	}

	@Override
	public Void visit(OneOrMoreMatcher matcher) {
		if (!visited.contains(matcher)) {
			visited.add(matcher);
			matcher.subMatcher.accept(this);
			action.process(matcher);
		}
		return null;
	}

	@Override
	public Void visit(OptionalMatcher matcher) {
		if (!visited.contains(matcher)) {
			visited.add(matcher);
			matcher.subMatcher.accept(this);
			action.process(matcher);
		}
		return null;
	}

	@Override
	public Void visit(TestMatcher matcher) {
		if (!visited.contains(matcher)) {
			visited.add(matcher);
			matcher.subMatcher.accept(this);
			action.process(matcher);
		}
		return null;
	}

	@Override
	public Void visit(TestNotMatcher matcher) {
		if (!visited.contains(matcher)) {
			visited.add(matcher);
			matcher.subMatcher.accept(this);
			action.process(matcher);
		}
		return null;
	}

	@Override
	public Void visit(ZeroOrMoreMatcher matcher) {
		if (!visited.contains(matcher)) {
			visited.add(matcher);
			matcher.subMatcher.accept(this);
			action.process(matcher);
		}
		return null;
	}

	@Override
	public Void defaultValue(AbstractMatcher matcher) {
		if (!visited.contains(matcher)) {
			visited.add(matcher);
			action.process(matcher);
		}
		return null;
	}
}
