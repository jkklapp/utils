package org.ieru.utils.triples;

import java.util.ArrayList;

public class TripleContainer {

    private ArrayList<Triple> triples;

    public TripleContainer() {
        this.triples = new ArrayList();
    }

    public TripleContainer add(String subject, String predicate, String object) {
        Triple t = new Triple(subject, predicate, object);
        this.triples.add(t);
        return this;
    }

    public int size() {
        return this.triples.size();
    }

    public Triple get(int i) {
        return (Triple) this.triples.get(i);
    }

    public ArrayList<Triple> getTriples() {
        return this.triples;
    }
    public TripleContainer putTriples(TripleContainer t){
        
        for (Triple t1 : t.getTriples())
            this.triples.add(t1);
        return this;
    }
    public void printTriples(){
        if(triples==null || triples.isEmpty()) return;
        for(Triple t : triples){
            System.out.println(t.getSubject()+","+t.getPredicate()+","+t.getObject());
        }
       
    }
    public void printTriples(String text){
        if(triples==null || triples.isEmpty()) return;
        System.out.println(text);
        printTriples();
    }
}
