package org.ansj.lucene5;

import java.io.Reader;
import java.util.Set;
import org.ansj.lucene.util.AnsjTokenizer;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Analyzer.TokenStreamComponents;
import org.apache.lucene.analysis.Tokenizer;
import org.nlpcn.commons.lang.tire.domain.Forest;

public class AnsjAnalysis extends Analyzer {

    boolean pstemming;
    public Set filter;

    public AnsjAnalysis(Set filter, boolean pstemming) {
        this.filter = filter;
        this.pstemming = pstemming;
    }

    public AnsjAnalysis(boolean pstemming) {
        this.pstemming = pstemming;
    }

    public AnsjAnalysis() {
    }

    protected TokenStreamComponents createComponents(String arg0) {
        // TODO Auto-generated method stub
        Tokenizer tokenizer = new AnsjTokenizer(new ToAnalysis(), this.filter, this.pstemming);
        return new Analyzer.TokenStreamComponents(tokenizer);
    }
}
