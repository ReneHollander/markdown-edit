/*
 * Copyright (C) 2010-2011 Mathias Doenitz
 *
 * Based on peg-markdown (C) 2008-2010 John MacFarlane
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

package org.pegdown.ast;

import java.util.List;

import org.parboiled.common.ImmutableList;
import org.parboiled.common.StringUtils;

public class TextNode extends AbstractNode {
	private final StringBuilder sb;

	public TextNode(String text) {
		this.sb = new StringBuilder(text);
	}

	public String getText() {
		return sb.toString();
	}

	public void append(String text) {
		sb.append(text);
	}

	@Override
	public String toString() {
		return super.toString() + " '" + StringUtils.escape(getText()) + '\'';
	}

	public List<Node> getChildren() {
		return ImmutableList.of();
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}