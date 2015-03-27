package at.rene8888.markdownedit.markdown.processor;

import at.rene8888.markdownedit.markdown.serializers.FencedCodeBlockSerializer;
import org.pegdown.Extensions;
import org.pegdown.LinkRenderer;
import org.pegdown.PegDownProcessor;
import org.pegdown.VerbatimSerializer;

import java.util.HashMap;
import java.util.Map;

public class PegdownProcessor extends MarkdownProcessor {

    private PegDownProcessor pegDownProcessor;
    private LinkRenderer linkRenderer;
    private Map<String, VerbatimSerializer> serializers;

    public PegdownProcessor() {
        this.pegDownProcessor = new PegDownProcessor(Extensions.ALL - Extensions.ANCHORLINKS);
        this.linkRenderer = new LinkRenderer();
        this.serializers = new HashMap<>();
        this.serializers.put(VerbatimSerializer.DEFAULT, FencedCodeBlockSerializer.INSTANCE);
    }

    public PegDownProcessor getPegDownProcessor() {
        return this.pegDownProcessor;
    }

    public LinkRenderer getLinkRenderer() {
        return this.linkRenderer;
    }

    public Map<String, VerbatimSerializer> getSerializers() {
        return this.serializers;
    }

    @Override
    public String toHtmlString(String markdown) {
        return this.getPegDownProcessor().markdownToHtml(markdown, this.getLinkRenderer(), this.getSerializers());
    }
}
