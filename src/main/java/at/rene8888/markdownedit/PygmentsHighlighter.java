package at.rene8888.markdownedit;

import org.python.core.*;
import org.python.util.PythonInterpreter;

import java.util.ArrayList;

public class PygmentsHighlighter {

    private PythonInterpreter interpreter;

    private PyObject html_formatter;
    private PyFunction get_lexer_func;
    private PyFunction get_all_lexers_func;
    private PyFunction highlight_func;

    public PygmentsHighlighter() {
        this.interpreter = new PythonInterpreter();
        this.interpreter.exec(this.interpreter.compile("from pygments import highlight"));
        this.interpreter.exec(this.interpreter.compile("from pygments.lexers import get_lexer_by_name"));
        this.interpreter.exec(this.interpreter.compile("from pygments.lexers import get_all_lexers"));
        this.interpreter.exec(this.interpreter.compile("from pygments.formatters import HtmlFormatter"));

        PyObject formatterClass = this.interpreter.get("HtmlFormatter");
        this.html_formatter = formatterClass.__call__();
        this.highlight_func = (PyFunction) this.interpreter.get("highlight");
        this.get_lexer_func = (PyFunction) this.interpreter.get("get_lexer_by_name");
        this.get_all_lexers_func = (PyFunction) this.interpreter.get("get_all_lexers");
    }

    public String highlight(String lang, String code) {
        try {
            PyObject lexer = this.get_lexer_func.__call__(new PyString(lang));
            PyObject formattedCode = this.highlight_func.__call__(new PyString(code), lexer, this.html_formatter);
            return formattedCode.asString();
        } catch (Exception e) {
            return "<code class=\"" + lang + "\"><pre>" + code + "</pre></code>";
        }
    }

    public Language[] getSupportedLanguages() {
        ArrayList<Language> languages = new ArrayList<>();
        PyIterator lexeriter = (PyIterator) this.get_all_lexers_func.__call__();
        for (Object p : lexeriter) {
            PyTuple pt = (PyTuple) p;
            Language l = new Language();
            l.name = (String) pt.get(0);
            l.aliases = (String[]) ((PyTuple) pt.get(1)).toArray(new String[((PyTuple) pt.get(1)).size()]);
            l.filenamepatterns = (String[]) ((PyTuple) pt.get(2)).toArray(new String[((PyTuple) pt.get(2)).size()]);
            l.mimetypes = (String[]) ((PyTuple) pt.get(3)).toArray(new String[((PyTuple) pt.get(3)).size()]);
            languages.add(l);
        }
        return languages.toArray(new Language[languages.size()]);
    }

}
