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
public class VagaRequisitoDAO {
    
    private final static String CRIAR_VAGAREQ_SQL = "insert into vagarequisito"
            + " (vaga, requisito)"
            + " values (?,?)";
    
    private final static String LISTAR_VAGASREQ_SQL = "select"
            + " r.title as reqTitle"
            + " from vagarequisito vr inner join vaga v on v.idVaga = vr.vaga, vagarequisito vr inner join requisito r on r.idRequisito = vr.requisito"
            + "where vaga=?";
    
    
        
    DataSource dataSource;


    public VagaRequisitoDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    
        public VagaRequisito gravarVagaRequisito(VagaRequisito vr) throws SQLException, NamingException {
            try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(CRIAR_VAGAREQ_SQL, Statement.RETURN_GENERATED_KEYS);) {
                ps.setInt(1, vr.getVaga().getId());
                ps.setInt(2, vr.getRequisito().getId());
                ps.execute();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    rs.next();
                }
            }
            
          return vr;
        }
        
        public List<VagaRequisito> listarRequisitoVaga(int vaga) throws SQLException, NamingException {
        List<VagaRequisito> ret = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
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
    
}
  
 
    

