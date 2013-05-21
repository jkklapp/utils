/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ieru.utils.nlp;

import java.util.ArrayList;

/**
 *
 * @author Jaakko Lappalainen
 */
public class TaggedSentences{
    
    ArrayList<TaggedSentence> sentences;
    /**
     * Builds this object.
     * @param a 
     */
    public TaggedSentences(ArrayList<String> a){
        sentences = new ArrayList();
        for(String s : a)
            sentences.add(new TaggedSentence(s));
    }
    /**
     * From a set with sentences extracted from text and surrounded by tags, gets the ones
     * with the desired tag. The tags are described in Stanfor NER software package. Here it is only used 
     * the tags 'PERSON' and 'ORGANIZATION'.
     * @param tag
     * @return 
     */
    public ArrayList<String> getSentencesFilterByTag(String tag){
        if(tag == null) return null;
        ArrayList<String> r = new ArrayList();
        for(TaggedSentence t : sentences){
            if(t.tag == null) return null;
            if(t.tag.equals(tag)){
                String st = t.getTextContent();
                if(!r.contains(st))
                    r.add(t.getTextContent());
            }
        }
        return r;
    }
    
}
class TaggedSentence{
    OpenerLabel opener;
    CloserLabel closer;
    String tag;
    String rawText;
    
    TaggedSentence(String rawText){
        this.rawText=rawText;
        this.opener = new OpenerLabel(rawText);
        
        this.tag=opener.name;
        this.closer=new CloserLabel(rawText);
        
            
    }
    
    public String getTextContent(){
        int last = rawText.lastIndexOf('<');
        int first = rawText.indexOf('>')+1;
        return rawText.substring(first,last); 
    }
}

class OpenerLabel extends Label{
    String name;
    OpenerLabel(String rawText){
        beginTextPosition=rawText.indexOf('<');
        endTextPosition=rawText.indexOf('>');
        if(beginTextPosition >= 0 && endTextPosition > 0)
            name=rawText.substring(beginTextPosition+1, endTextPosition);
    }
}

class CloserLabel extends Label{
    CloserLabel(String rawText){
        beginTextPosition=rawText.indexOf('/')-1;
        endTextPosition=rawText.lastIndexOf('>');
    }
}
class Label{
    int beginTextPosition, endTextPosition;
}
