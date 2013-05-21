/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ieru.utils.rdb;

/**
 *
 * @author jaakko
 */
public abstract class Connector {
    public abstract void openConnection();
    public abstract void closeConnection();
}
