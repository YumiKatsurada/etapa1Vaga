/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.dao;

import estagio.beans.Vaga;
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
public class VagaDAO {
    private final static String CRIAR_VAGA_SQL = "insert into vaga"
            + " (title, city, state, zipcode, hirer, description, salary, category)"
            + " values (?)";
    
    private final static String CRIAR_VAGAREQ_SQL = "insert into vagarequisito"
            + " (vaga, requisito)"
            + " values (?,?)";


    private final static String BUSCAR_VAGA_SQL = "select"
            + " idVaga, title"
            + " from vaga"
            + " where title=?";
    
    DataSource dataSource;


    public VagaDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    
    public Vaga gravarVaga(Vaga v) throws SQLException, NamingException {
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(CRIAR_VAGA_SQL, Statement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, v.getTitle());
            ps.execute();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                rs.next();
                v.setId(rs.getInt(1));
            }
        }
        return v;
    }


    public Vaga buscarVaga(String t) throws SQLException, NamingException {
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(BUSCAR_VAGA_SQL)) {
            ps.setString(1, t);


            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                Vaga v = new Vaga();
                v.setId(rs.getInt("idVaga"));
                v.setTitle(rs.getString("title"));
                return v;
            }
        }
    }
    
}
