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

package org.pegdown.plugins;

import org.parboiled.Rule;

/**
 * A parser that provides block parser rules for pegdown. A pegdown plugin should implement this, along with {@link org.parboiled.BaseParser} or {@link org.pegdown.Parser} if it wants to use the pegdown utilities.
 *
 * This interface is intended for use with {@link org.pegdown.plugins.PegDownPlugins.Builder#withPlugin(Class, Object...)}, in order for Java plugins to easily be registered with a parser.
 */
public interface BlockPluginParser {
	Rule[] blockPluginRules();
}
