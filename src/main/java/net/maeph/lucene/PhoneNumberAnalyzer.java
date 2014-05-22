package net.maeph.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.util.Version;

import java.io.Reader;

/**
 * Created by mephi_000 on 22.05.14.
 */
public class PhoneNumberAnalyzer extends Analyzer {
    private Version matchVersion;

    public PhoneNumberAnalyzer(Version matchVersion) {
        this.matchVersion = matchVersion;
    }
    @Override
    protected TokenStreamComponents createComponents(String fieldName, Reader reader) {
        final Tokenizer source = new StandardTokenizer(matchVersion, reader);
        TokenStream sink = new StandardFilter(matchVersion, source);
        sink = new PhoneNumberFilter(sink);
        return new TokenStreamComponents(source, sink);
    }
}
