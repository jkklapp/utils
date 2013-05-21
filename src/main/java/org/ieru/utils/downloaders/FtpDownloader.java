/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ieru.utils.downloaders;

import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

/**
 *
 * @author jaakko
 */
public class FtpDownloader {

    private String baseUrl;
    private int port;
    private String user;
    private String pass;
    private FTPClient ftpClient;

    public FtpDownloader(String baseUrl, String user, String pass) {
        this.baseUrl = baseUrl;
        port = 21;
        this.user = user;
        this.pass = pass;
    }

    public void openConnection() {
        try {
            ftpClient.connect(baseUrl, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        } catch (SocketException ex) {
            Logger.getLogger(FtpDownloader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FtpDownloader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void changeWorkingDirectory(String dir) {
        try {
            ftpClient.changeWorkingDirectory(dir);
        } catch (IOException ex) {
            Logger.getLogger(FtpDownloader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean retrieveFile(String remoteFile, OutputStream target) {
        try {
            return ftpClient.retrieveFile(remoteFile, target);
        } catch (IOException ex) {
            Logger.getLogger(FtpDownloader.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public String getFileNameFromURL(String url) {
        int slashIndex = url.lastIndexOf('/');
        return url.substring(slashIndex + 1);
    }
}
