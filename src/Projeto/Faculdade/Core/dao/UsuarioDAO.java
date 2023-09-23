/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Projeto.Faculdade.Core.dao;

import Projeto.Faculdade.Core.entity.Usuario;
import Projeto.Faculdade.Core.dao.conexao.ConexaoJDBC;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author jgsco
 */
public class UsuarioDAO {
    public void adicionarUsuario(Usuario usuario){
        
        String sql = "INSERT INTO USUARIO (NOME, LOGIN, SENHA, EMAIL) VALUES(?,?,?,?)";
      
      PreparedStatement ps; 
      try{
       ps = ConexaoJDBC.getConexao().prepareStatement(sql);
       ps.setString(1, usuario.getNome());
       ps.setString(2, usuario.getLogin());
       ps.setString(3, usuario.getSenha());
       ps.setString(4, usuario.getEmail());
       
       ps.execute();
      
      }catch(SQLException e){
        e.printStackTrace();
    }
     
        JOptionPane.showMessageDialog(null, "Usuario Cadastrado com sucesso");
    } 
    
    public Usuario buscarUsuario(String CampoLogin, String CampoSenha){
    
        String sql = "SELECT ID, NOME, LOGIN, SENHA, EMAIL FROM USUARIO WHERE LOGIN LIKE ? AND SENHA LIKE  ?";
        
        PreparedStatement ps;
        ResultSet rs;
        
        try{
            ps = ConexaoJDBC.getConexao().prepareStatement(sql);
            
            
            ps.setString(1, CampoLogin);
            ps.setString(2, CampoSenha);
            
            
            
            
            rs = ps.executeQuery();
            Usuario usuario = new Usuario();
            if(rs.next()){
            
            usuario.setId(rs.getLong("ID"));
            usuario.setNome(rs.getString("NOME"));
            usuario.setLogin(rs.getString("LOGIN"));
            usuario.setSenha(rs.getString("SENHA"));
            usuario.setEmail(rs.getString("EMAIL"));
            }
            JOptionPane.showMessageDialog(null, "Acesso Autorizado");
            return usuario;
        } catch(SQLException e){
        e.fillInStackTrace();
        }
         JOptionPane.showMessageDialog(null, "Acesso Negado");
     return null;
    
    }
}
