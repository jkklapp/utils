package org.ieru.utils.parsers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.util.PDFTextStripper;

/**
 *
 * @author Jaakko Lappalainen
 *
 */
public class PdfParser {

    private PDDocument pdDoc;
    private String language;
    private String filePath;
    private PDFParser parser;
    private COSDocument cosDoc;
    private String parsedText;
    /**
     * Creates this object
     * @param filePath - The path of the PDF file.
     * @param language - Language of the PDF file.
     * @throws IOException 
     */
    public PdfParser(String filePath, String language) throws IOException {
        this.filePath = filePath;
        this.language = language;
    }
    /**
     * Returns the PDDocument
     * @return 
     */
    public PDDocument getPDDocument() {
        return pdDoc;
    }
    /**
     * Returns the language
     * @return 
     */
    public String getLanguage() {
        return language;
    }
    /**
     * Returns the number of pages in the document.
     * @return 
     */
    public int getDocumentNumberOfPages() {
        return pdDoc.getNumberOfPages();
    }
    /**
     * Returns the PDF metadata information.
     * @return 
     */
    public PDDocumentInformation getPDDocumentInformation() {
        return pdDoc.getDocumentInformation();
    }
    /**
     * Returns the text of the document
     * @return
     * @throws IOException 
     */
    public String getTextFromPDFDocument() throws IOException {
        return getTextFromPage(-1).replace("\n \n", "").replace("  ", "");
    }
    /**
     * Writes some text to a file
     * @param file 
     */
    public void writeText2File(String file) {

        try {
            PrintWriter pw = new PrintWriter(file);
            pw.print(getTextFromPDFDocument());
            pw.close();
        } catch (Exception e) {
            System.err.println("An exception occured in writing '" + file + "' file.");
        }
    }
    /**
     * Returns the text between pages i and j
     * @param i - a number between 1 and the maximum number of pages minus 1.
     * @param j - a number between i and the maximum number of pages.
     * @return
     * @throws IOException 
     */
    private  String getTextFromDocumentByPages(int i, int j) throws IOException {

        try {
            parser = new PDFParser(new FileInputStream(new File(filePath)));
        } catch (Exception e) {
            System.err.println("Unable to open PDF Parser.");
            return null;
        }

        try {
            parser.parse();
            cosDoc = parser.getDocument();
            PDFTextStripper pdfStripper = new PDFTextStripper();
            pdDoc = new PDDocument(cosDoc);
            if (i != -1 && j != -1) {
                pdfStripper.setStartPage(i);
                pdfStripper.setEndPage(j);
            }
            parsedText = pdfStripper.getText(pdDoc);
        } catch(NullPointerException e){
            throw new NullPointerException("Parser: "+ e.toString());
        } 
        catch (Exception e) {
            throw new IOException("Parser: " + e.toString());
        } finally {
            if (pdDoc != null) {
                pdDoc.close();
            }

            if (cosDoc != null) {
                cosDoc.close();
            }
        }
        return parsedText;
    }
    /**
     * Returns the text from the i-th page
     * @param i - a positive number.
     * @return
     * @throws IOException 
     */
    public String getTextFromPage(int i) throws IOException {
        return getTextFromDocumentByPages(i, i);
    }
    /**
     * Closes the PDF Document.
     * @throws IOException 
     */
    public void closePDDoc() throws IOException {
        if (pdDoc != null) {
            pdDoc.close();
        }
    }
}
