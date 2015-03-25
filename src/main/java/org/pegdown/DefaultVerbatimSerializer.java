package org.pegdown;

import org.parboiled.common.StringUtils;
import org.pegdown.ast.VerbatimNode;

import com.threecrickets.jygments.format.Formatter;
import com.threecrickets.jygments.grammar.Lexer;
import com.threecrickets.jygments.grammar.LexerHelper;

public class DefaultVerbatimSerializer implements VerbatimSerializer {
	public static final DefaultVerbatimSerializer INSTANCE = new DefaultVerbatimSerializer();

	@Override
	public void serialize(final VerbatimNode node, final Printer printer) {
		try {
			printer.println().print("<pre>");

			String language = node.getType();
			String text = node.getText();
			text = text.replace("\t", "    ");

			if (!StringUtils.isEmpty(language)) {
				Lexer l = LexerHelper.getLexer(node.getType());
				if (l != null) {
					String s = Formatter.HTML_FORMATTER.format(l.getTokens(text));
					printer.println().print(s);
				} else {
					printer.println().print("<code class=\"" + language + "\">");
					printer.printEncoded(text);
					printer.print("</code>");
				}
			} else {
				printer.println().print("<code>");
				printer.printEncoded(text);
				printer.print("</code>");
			}
			printer.print("</pre>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
