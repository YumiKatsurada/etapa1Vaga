/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.dao;

import estagio.beans.Categoria;
import estagio.beans.Requisito;
import estagio.beans.Vaga;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author yumi
 */
public class VagaDAO {
    private final static String CRIAR_VAGA_SQL = "insert into vaga"
            + " (title, city, state, zipcode, hirer, description, salary, category)"
            + " values (?, ?, ?, ?, ?, ?, ?, ?)";

    private final static String BUSCAR_VAGA_SQL = "select"
            + " v.idVaga, v.title as vaga, v.city, v.state, v.zipcode, v.hirer, v.description, v.salary, category, c.title as categoria"
            + " from vaga v inner join categoria c on v.category = c.idCategoria"
            + " where idVaga=?";
    
    private final static String LISTAR_VAGAS_SQL = "select"
            + " v.idVaga, v.title as vaga, v.city, v.state, v.zipcode, v.hirer, v.description, v.salary, category, c.title as categoria"
            + " from vaga v inner join categoria c on v.category = c.idCategoria";
    
    
    
    DataSource dataSource;


    public VagaDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    
    public Vaga gravarVaga(Vaga v) throws SQLException, NamingException {
        try (Connection con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(CRIAR_VAGA_SQL, Statement.RETURN_GENERATED_KEYS);) {
            //title, city, state, zipcode, hirer, description, salary, category
            ps.setString(1, v.getTitle());
            ps.setString(2, v.getCity());
            ps.setString(3, v.getZipcode());
            ps.setString(4, v.getState());
            ps.setString(5, v.getHirer());
            ps.setString(6, v.getDescription());
            ps.setFloat(7, v.getSalary());
            ps.setInt(8, v.getCategory().getId());
            ps.execute();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                rs.next();
                v.setId(rs.getInt(1));
            }
        }
        
        return v;
    }
    
    

    public Vaga buscarVaga(int id) throws SQLException, NamingException {
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(BUSCAR_VAGA_SQL)) {
            //v.idVaga, v.title, v.city, v.state, v.zipcode, v.hirer, v.description, v.salary, c.title
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                Vaga v = new Vaga();
                Categoria c = new Categoria();
                v.setId(rs.getInt("idVaga"));
                v.setTitle(rs.getString("vaga"));
                v.setCity(rs.getString("city"));
                v.setState(rs.getString("state"));
                v.setZipcode(rs.getString("zipcode"));
                v.setHirer(rs.getString("hider"));
                v.setDescription(rs.getString("description"));
                v.setSalary(rs.getFloat("salary"));
                c.setId(rs.getInt("category"));
                c.setTitle(rs.getString("categoria"));
                v.setCategory(c);
                return v;
            }
        }
    }
    
    
    
    public List<Vaga> listarTodasVagas() throws SQLException, NamingException {
        List<Vaga> ret = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(LISTAR_VAGAS_SQL)) {


            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Vaga v = new Vaga();
                    Categoria c = new Categoria();
                    v.setId(rs.getInt("idVaga"));
                    v.setTitle(rs.getString("vaga"));
                    v.setCity(rs.getString("city"));
                    v.setState(rs.getString("state"));
                    v.setZipcode(rs.getString("zipcode"));
                    v.setHirer(rs.getString("hider"));
                    v.setDescription(rs.getString("description"));
                    v.setSalary(rs.getFloat("salary"));
                    c.setId(rs.getInt("category"));
                    c.setTitle(rs.getString("categoria"));
                    v.setCategory(c);
                    ret.add(v);
                }
            }
        }
        return ret;
    }
}
