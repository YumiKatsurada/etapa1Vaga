/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.dao;

import estagio.beans.Categoria;
import estagio.beans.Requisito;
import estagio.beans.Vaga;
import estagio.beans.VagaRequisito;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author yumi
 */
@Path("vaga")
public class VagaDAO {
    private final static String CRIAR_VAGA_SQL = "insert into vaga"
            + " (title, city, state, zipcode, hirer, description, salary, category)"
            + " values (?, ?, ?, ?, ?, ?, ?, ?)";

    private final static String BUSCAR_VAGA_SQL = "select"
            + " v.idVaga, v.title as vaga, v.city, v.state, v.zipcode, v.hider, v.description, v.salary, category, c.title as categoria"
            + " from vaga v inner join categoria c on v.category = c.idCategoria"
            ;
    
    private final static String LISTAR_VAGAS_SQL = "select"
            + " v.idVaga, v.title as vaga, v.city, v.state, v.zipcode, v.hider, v.description, v.salary, category, c.title as categoria"
            + " from vaga v inner join categoria c on v.category = c.idCategoria";
    
    private final static String LISTAR_VAGASREQ_SQL = "select"
            + " r.title as reqTitle"
            + " from vagarequisito vr inner join vaga v on v.idVaga = vr.vaga, vagarequisito vr inner join requisito r on r.idRequisito = vr.requisito"
            + "where vaga=?";
    
    
    
    
    String con = "jdbc:derby://localhost:1527/estagio", user = "yumi", password ="yumi";
    
    
    public Vaga gravarVaga(Vaga v) throws SQLException, NamingException {
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/estagio", "yumi", "yumi");
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
    
    
    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Vaga buscarVaga() throws SQLException, NamingException {
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/estagio", "yumi", "yumi");
            PreparedStatement ps = con.prepareStatement(BUSCAR_VAGA_SQL)) {
            //v.idVaga, v.title, v.city, v.state, v.zipcode, v.hirer, v.description, v.salary, c.title
            //ps.setInt(1, id);

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
    
    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public List<VagaRequisito> listarRequisitoVaga(int vaga) throws SQLException, NamingException {
        List<VagaRequisito> ret = new ArrayList<>();
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/estagio", "yumi", "yumi");
                PreparedStatement ps = con.prepareStatement(LISTAR_VAGASREQ_SQL)) {
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    VagaRequisito vr = new VagaRequisito();
                    Requisito r = new Requisito();
                    r.setTitle(rs.getString("reqTitle"));
                    vr.setRequisito(r);
                    ret.add(vr);
                }
            }
        }
        return ret;
      }
    
    
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vaga> listarTodasVagas() throws SQLException, NamingException {
        List<Vaga> ret = new ArrayList<>();
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/estagio", "yumi", "yumi");
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
