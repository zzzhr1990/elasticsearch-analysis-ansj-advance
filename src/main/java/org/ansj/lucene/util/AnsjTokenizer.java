package org.ansj.lucene.util;

import java.io.IOException;
import java.io.Reader;
import java.util.Set;
import org.ansj.domain.Term;
import org.ansj.domain.TermNatures;
import org.ansj.splitWord.Analysis;
import org.ansj.util.AnsjReader;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;

public class AnsjTokenizer extends Tokenizer {

    private final CharTermAttribute termAtt = (CharTermAttribute) addAttribute(CharTermAttribute.class);

    private final OffsetAttribute offsetAtt = (OffsetAttribute) addAttribute(OffsetAttribute.class);

    private final PositionIncrementAttribute positionAttr = (PositionIncrementAttribute) addAttribute(PositionIncrementAttribute.class);

    protected Analysis ta = null;

    private Set filter;
    private boolean pstemming;
    private final PorterStemmer stemmer = new PorterStemmer();

    public AnsjTokenizer(Analysis ta, Set filter, boolean pstemming) {
        // super(input);
        this.ta = ta;
        this.filter = filter;
        this.pstemming = pstemming;
    }

    public boolean incrementToken() throws IOException {
        clearAttributes();
        int position = 0;
        Term term;
        String name;
        int length = 0;
        boolean flag = true;
        do {
            term = this.ta.next();
            if (term == null) {
                break;
            }
            name = term.getName();
            length = name.length();
            if ((this.pstemming) && (term.termNatures() == TermNatures.EN)) {
                name = this.stemmer.stem(name);
                term.setName(name);
            }

            if ((this.filter == null) || (!this.filter.contains(name))) {

                position++;
                flag = false;
            }
        } while (flag);

        if (term != null) {
            this.positionAttr.setPositionIncrement(position);
            this.termAtt.setEmpty().append(term.getName());
            this.offsetAtt.setOffset(term.getOffe(), term.getOffe() + length);
            return true;
        }
        return false;
    }

    public void reset() throws IOException {
        super.reset();
        this.ta.resetContent(new AnsjReader(this.input));
    }
}
