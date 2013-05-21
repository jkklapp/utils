package org.ieru.utils.downloaders;


import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jaakko
 */
public class HttpDownloader {
     
     
     private String fileUrl(String fAddress, String localFileName, String destinationDir) throws IOException {
        OutputStream outStream = null;

        InputStream is = null;
        if (new File(fAddress).exists()) {
            return localFileName;
        }
        try {
            URL Url;
            byte[] buf;
            int ByteRead, ByteWritten = 0;
            Url = new URL(fAddress);
            outStream = new BufferedOutputStream(new FileOutputStream(destinationDir + "/" + localFileName));

            URLConnection uCon = Url.openConnection();
            is = uCon.getInputStream();
            buf = new byte[1024];
            while ((ByteRead = is.read(buf)) != -1) {
                outStream.write(buf, 0, ByteRead);
                ByteWritten += ByteRead;
            }
        } catch (Exception e) {
            System.err.println("Error downloading " + localFileName);
            new File("temp/" + localFileName).delete();
            return null;
        } finally {
            if (is != null) {
                is.close();
            }
            if (outStream != null) {
                outStream.close();
            }
        }
        return localFileName;
    }
  
    public String rescDownload(String fAddress, String destinationDir) throws IOException{
        int slashIndex = fAddress.lastIndexOf('/');
        int periodIndex = fAddress.lastIndexOf('.');
        String r = null;
        String f = fAddress.substring(slashIndex + 1);
        if (new File(destinationDir+"/" + f).exists()) {
            return f;
        }
        //KeaTest.fileName = f;
        if (periodIndex >= 1 && slashIndex >= 0
                && slashIndex < fAddress.length() - 1) {
            r = fileUrl(fAddress, f, destinationDir);
        } else {
            System.err.println("Error in path or file name.");
        }
        File test = new File(r);
        if(!test.exists()) {
            return null;
        }
        if(!test.canRead()){
            test.delete();
            return null;
        }
        if(test.getTotalSpace()==0){
            test.delete();
            return null;
        }
        if(test.getName().indexOf("index.php?")!=0){
            test.delete();
            return null;
        }
        return r;
    }
}
