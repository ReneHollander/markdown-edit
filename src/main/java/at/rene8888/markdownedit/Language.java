package at.rene8888.markdownedit;

import java.util.Arrays;

/**
 * Created by rene on 3/25/15.
 */
public class Language {

    protected String name;
    protected String[] aliases;
    protected String[] filenamepatterns;
    protected String[] mimetypes;

    public String getName() {
        return name;
    }

    public String[] getAliases() {
        return aliases;
    }

    public String[] getFilenamepatterns() {
        return filenamepatterns;
    }

    public String[] getMimetypes() {
        return mimetypes;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Language{");
        sb.append("name='").append(name).append('\'');
        sb.append(", aliases=").append(aliases == null ? "null" : Arrays.asList(aliases).toString());
        sb.append(", filenamepatterns=").append(filenamepatterns == null ? "null" : Arrays.asList(filenamepatterns).toString());
        sb.append(", mimetypes=").append(mimetypes == null ? "null" : Arrays.asList(mimetypes).toString());
        sb.append('}');
        return sb.toString();
    }
}
