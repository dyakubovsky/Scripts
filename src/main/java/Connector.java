package main.java;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Connector implements SessionBean {

    private SessionContext ctx;

    public void FillConnectionPool(String username, String password) throws SQLException {
        try {
                Context ctx = new InitialContext();
                DataSource ds = (DataSource) ctx.lookup("jdbc/OracleDS");
                Connection con = ds.getConnection(username, password);
                con.setAutoCommit(false);
                PreparedStatement pstmt = con.prepareStatement("SETECT * FROM TEMP ");
                pstmt.execute();
                con.commit();
                //pstmt.close();
                //con.close()
               //DataSource ds = null;
               //Context ctx = null;
            } catch (NamingException e) {
            e.printStackTrace();
            }
        }
        

    @Override
    public void setSessionContext(SessionContext sessionContext) throws EJBException, RemoteException {
        this.ctx = sessionContext;

    }

    @Override
    public void ejbRemove() throws EJBException, RemoteException {
        System.out.println("ejbRemove");

    }

    @Override
    public void ejbActivate() throws EJBException, RemoteException {
        System.out.println("ejbActivate");

    }

    @Override
    public void ejbPassivate() throws EJBException, RemoteException {
        System.out.println("ejbPassivate");

    }
}
