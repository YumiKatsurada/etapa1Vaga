/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.dao;

import estagio.beans.Categoria;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author yumi
 */
@Path("categoria")
public class CategoriaDAO {
    private final static String CRIAR_CATEGORIA_SQL = "insert into categoria"
            + " (title)"
            + " values (?)";


    private final static String BUSCAR_CATEGORIA_SQL = "select"
            + " idCategoria, title"
            + " from categoria"
            ;
    
    
    
    public Categoria gravarCategoria(Categoria c) throws SQLException, NamingException {
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/estagio", "yumi", "yumi");
                PreparedStatement ps = con.prepareStatement(CRIAR_CATEGORIA_SQL, Statement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, c.getTitle());
            ps.execute();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                rs.next();
                c.setId(rs.getInt(1));
            }
        }
        return c;
    }

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Categoria buscarCategoria() throws SQLException, NamingException {
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/estagio", "yumi", "yumi");
                PreparedStatement ps = con.prepareStatement(BUSCAR_CATEGORIA_SQL)) {
            //ps.setString(1, t);


            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                Categoria c = new Categoria();
                c.setId(rs.getInt("idCategoria"));
                c.setTitle(rs.getString("title"));
                return c;
            }
        }
    }
    
}
