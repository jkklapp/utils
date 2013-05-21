/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ieru.utils.nlp;

//import edu.uwm.nlp.jude.internal.ReferenceAnalyser;
//import edu.uwm.nlp.jude.internal.ReferenceEntity;
import java.util.ArrayList;

/**
 *
 * @author jaakko
 */
public class ReferenceParser {

    private String text;
    /**
     * Builds the reference parser.
     * @param text 
     */
    public ReferenceParser(String text) {
        this.text = text;
    }
    /**
     * Returns references extracted by 'Jude' package as an array list.
     * @return 
     */
//    public ArrayList<String> getReferencesAsArrayList() {
//        ArrayList<String> a = new ArrayList();
//        ReferenceAnalyser ra = new ReferenceAnalyser(text);
//        ArrayList<ReferenceEntity> rList = ra.analyzeReference(false);
//        //AgNLPParser nlp = new AgNLPParser();
//        if (rList != null) {
//            for (ReferenceEntity r : rList) {
//                a.add(r.getFullRef().toString());
//            }
//        }
//        return a;
//    }
    /**
     * Prints references in a human-readable way and with format.
     * @param a 
     */
    public void printReferences(ArrayList<String> a) {
        System.out.println("========================================================================");
        for (String s : a) {
            System.out.println(s);
        }
        System.out.println("========================================================================");
    }
}
