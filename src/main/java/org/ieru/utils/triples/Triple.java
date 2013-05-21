package org.ieru.utils.triples;

public class Triple {

    private String subject;
    private String predicate;
    private String object;

    Triple(String s, String p, String o) {
        if ((s != null) && (p != null) && (o != null)) {
            this.subject = s;
        }
        this.predicate = p;
        this.object = o;
    }

    public String getSubject() {
        return this.subject;
    }

    public String getPredicate() {
        return this.predicate;
    }

    public String getObject() {
        return this.object;
    }
}
