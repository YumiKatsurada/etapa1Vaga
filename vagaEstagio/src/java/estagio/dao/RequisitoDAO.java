/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.dao;

import estagio.beans.Requisito;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author yumi
 */
public class RequisitoDAO {
    private final static String CRIAR_REQUISITO_SQL = "insert into requisito"
            + " (title)"
            + " values (?)";


    private final static String BUSCAR_REQUISITO_SQL = "select"
            + " idRequisito, title"
            + " from requisito"
            + " where title=?";
    
    DataSource dataSource;


    public RequisitoDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public Requisito gravarRequisito(Requisito r) throws SQLException, NamingException {
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(CRIAR_REQUISITO_SQL, Statement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, r.getTitle());
            ps.execute();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                rs.next();
                r.setId(rs.getInt(1));
            }
        }
        return r;
    }


    public Requisito buscarRequisito(String t) throws SQLException, NamingException {
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(BUSCAR_REQUISITO_SQL)) {
            ps.setString(1, t);


            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                Requisito r = new Requisito();
                r.setId(rs.getInt("idRequisito"));
                r.setTitle(rs.getString("title"));
                return r;
            }
        }
    }
    
}
