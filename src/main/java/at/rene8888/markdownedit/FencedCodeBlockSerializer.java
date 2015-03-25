package at.rene8888.markdownedit;

import org.parboiled.common.StringUtils;
import org.pegdown.Printer;
import org.pegdown.VerbatimSerializer;
import org.pegdown.ast.VerbatimNode;

public class FencedCodeBlockSerializer implements VerbatimSerializer {

    public static final FencedCodeBlockSerializer INSTANCE = new FencedCodeBlockSerializer();

    private PygmentsHighlighter highlighter;

    private static final boolean DO_HIGHLIGHT = false;

    @Override
    public void serialize(final VerbatimNode node, final Printer printer) {
        try {

            String language = node.getType();
            String text = node.getText();

            if (DO_HIGHLIGHT) {
                if (!StringUtils.isEmpty(language)) {
                    printer.print(getHighlighter().highlight(language, text));
                } else {
                    printer.println().print("<code><pre>");
                    printer.printEncoded(text);
                    printer.print("</code></pre>");
                }
            } else {
                printer.println().print("<code><pre>");
                printer.printEncoded(text);
                printer.print("</pre></code>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PygmentsHighlighter getHighlighter() {
        if (this.highlighter == null) {
            this.highlighter = new PygmentsHighlighter();
        }
        return this.highlighter;
    }
}
