package org.ieru.utils.jena;


import com.hp.hpl.jena.db.DBConnection;
import com.hp.hpl.jena.db.IDBConnection;
import com.hp.hpl.jena.graph.GraphMaker;
import com.hp.hpl.jena.rdf.model.*;
import java.io.BufferedWriter;
import java.sql.SQLException;
import org.ieru.utils.triples.Triple;

public class Connector {

    Model model;
    ModelMaker maker;
    GraphMaker gMaker;
    private String category;
    
    private static String DB_URL;
    private static String DB_USER;
    private static String DB_PASSWD;
    private static String DB_TYPE;

    public Connector(String category, String host, String db_name, String user, String pass, String type) throws SQLException, ClassNotFoundException{
        this.category = category;
        setCustomParameters(host, db_name, user, pass, type);
        openConnection();
    }
    public Model getModel(){
        return model;
    }
    public void setCustomParameters(String host, String db_name, String user, String pass, String type) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        DB_URL = "jdbc:mysql://"+host+"/"+db_name;
        DB_USER = user;
        DB_PASSWD = pass;
        DB_TYPE = type;
        openConnection();
    }
    private void openConnection() throws SQLException{
        //Class.forName(modelRDBdriver);
        IDBConnection conn = new DBConnection(DB_URL, DB_USER, DB_PASSWD, DB_TYPE);
        //conn.getDriver().unlockDB();
        //conn.cleanDB();
        maker = ModelFactory.createModelRDBMaker(conn);
        this.model = maker.createDefaultModel();
        conn.close();
    }
    public void store(String s, String p, String o) throws SQLException, ClassNotFoundException {
        if (this.model == null) {
            return;
        }
        System.err.println("Adding stmt: "+s+", "+p+", "+o);
        model.add(model.createResource(s), model.createProperty(p), o);
        //Statement stmt = model.createLiteralStatement(model.createResource(s), model.createProperty(p), model.createResource(o));
        //stmt.createReifiedStatement(category);
        //ReifiedStatement rstmt= model.createReifiedStatement(stmt);
        //Statement stmt2 = model.createLiteralStatement(rstmt, model.createProperty("belongsTo"), model.createResource(category));
        //model.add(stmt);
        //model.add(stmt2);
    }
    public void store(Triple t) throws SQLException, ClassNotFoundException{
        store(t.getSubject(),t.getPredicate(), t.getObject());
    }
    public void getGraph(){
        gMaker = maker.getGraphMaker();
        //gMaker.
    }
    public void printModelAsRDF(BufferedWriter out){
        model.write( out, "RDF/XML-ABBREV" );
    }
}
