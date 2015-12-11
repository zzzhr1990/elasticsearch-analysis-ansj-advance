package org.ansj.lucene5;

import java.io.Reader;
import java.util.Set;
import org.ansj.lucene.util.AnsjTokenizer;
import org.ansj.splitWord.analysis.IndexAnalysis;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Analyzer.TokenStreamComponents;
import org.apache.lucene.analysis.Tokenizer;
import org.nlpcn.commons.lang.tire.domain.Forest;

public class AnsjIndexAnalysis extends Analyzer {

    boolean pstemming;
    public Set filter;

    public AnsjIndexAnalysis(Set filter, boolean pstemming) {
        this.filter = filter;
    }

    public AnsjIndexAnalysis(boolean pstemming) {
        this.pstemming = pstemming;
    }

    public AnsjIndexAnalysis() {
    }


    protected TokenStreamComponents createComponents(String arg0) {
        // TODO Auto-generated method stub
        Tokenizer tokenizer = new AnsjTokenizer(new IndexAnalysis(), this.filter, this.pstemming);
        return new Analyzer.TokenStreamComponents(tokenizer);
    }
}
