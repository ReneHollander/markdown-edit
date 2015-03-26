package at.rene8888.markdownedit.markdown;

import at.rene8888.markdownedit.serializers.FencedCodeBlockSerializer;
import org.pegdown.Extensions;
import org.pegdown.LinkRenderer;
import org.pegdown.PegDownProcessor;
import org.pegdown.VerbatimSerializer;

import java.util.HashMap;
import java.util.Map;

public class Pegdown extends Markdown {

    private PegDownProcessor pegDownProcessor;
    private LinkRenderer linkRenderer;
    private Map<String, VerbatimSerializer> serializers;

    public Pegdown() {
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
