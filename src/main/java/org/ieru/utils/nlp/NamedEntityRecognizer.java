/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ieru.utils.nlp;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import java.util.ArrayList;
import org.ieru.utils.nlp.TaggedSentences;

/**
 *
 * @author Jaakko Lappalainen
 */
public class NamedEntityRecognizer {

    AbstractSequenceClassifier<CoreLabel> classifier;
    String serializedClassifier;
    /**
     * Builds a NER. It uses Stanford NER.
     * @param sClassifier 
     */
    public NamedEntityRecognizer(String sClassifier) {
        serializedClassifier = sClassifier;
        classifier = CRFClassifier.getClassifierNoExceptions(serializedClassifier);
    }
    /**
     * Gets the desired names, for example 'PERSON' for person names or 
     * 'ORGANIZATION' for organization names.
     * @param text - Text to analyze.
     * @param entity - Entity types as described by Stanford NER.
     * @return 
     */
    public ArrayList<String> recEntity(String text, String entity) {
        String classifiedText = classifier.classifyWithInlineXML(text);
        TaggedSentences sentences = new TaggedSentences(splitTextByTags(classifiedText));
        return sentences.getSentencesFilterByTag(entity);
    }

    private ArrayList<String> splitTextByTags(String text) {
        ArrayList<String> a = new ArrayList();
        ArrayList<Integer> positions = new ArrayList();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '<' || text.charAt(i) == '>') {
                positions.add(i);
            }
        }
        for (int i = 0; i < positions.size(); i++) {
            int j = i + 3;
            if (i > positions.size()) {
                break;
            }
            if (j > positions.size()) {
                j = positions.size() - 1;
            }
            a.add(text.substring(positions.get(i), positions.get(j) + 1));
            i = j;
        }
        return a;
    }
}

