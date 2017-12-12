/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locadoraveiculos;

import static java.lang.Integer.parseInt;
//import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bcosta
 */
public class Dao {

    /**
     *
     * @return
     */
    public ArrayList<Veiculos> todosVeiculos() {

        String strConn = "jdbc:sqlite:/home/bcosta/NetBeansProjects/LocadoraVeiculos/locadora.db";

        Connection con;
        ArrayList <Veiculos> veiculos = new ArrayList();     
       
        try {

            con = DriverManager.getConnection(strConn);

            Statement stm = con.createStatement();

            String sql = "SELECT v.placa, v.marca, v.modelo, v.locando  FROM cliente c, veiculo v WHERE v.cpf = c.cpf ;";
            
            
            ResultSet rs = stm.executeQuery(sql);

          
            while(rs.next()){
                Veiculos veiculo = new Veiculos("", "", "", "");
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setLocando(parseInt(rs.getString("locando")));
                veiculo.setMarca(rs.getString("marca"));
                
                veiculos.add(veiculo);
            }    
            return veiculos;
           
        }catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //stm.close();
        //con.close();
        
        return null;

    }
    
    public ArrayList<Pessoa> todosClientes() {

        String strConn = "jdbc:sqlite:/home/bcosta/NetBeansProjects/LocadoraVeiculos/locadora.db";

        Connection con;
        ArrayList <Pessoa> cliente = new ArrayList();     
       
        try {

            con = DriverManager.getConnection(strConn);

            Statement stm = con.createStatement();

            String sql = "SELECT * FROM cliente ;";
            
            
            ResultSet rs = stm.executeQuery(sql);

          
            while(rs.next()){
                Pessoa clientes = new Pessoa("", "", "", null);
                clientes.setNome(rs.getString("nome"));
                clientes.setLocando(parseInt(rs.getString("locando")));
                
                cliente.add(clientes);
            }    
            return cliente;
           
        }catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //stm.close();
        //con.close();
        
        return null;

    }

    
   public boolean desalocaVeiculo(String cpf) {
       
        String strConn = "jdbc:sqlite:/home/bcosta/NetBeansProjects/LocadoraVeiculos/locadora.db";

        Connection con;
        try {

            con = DriverManager.getConnection(strConn);

            Statement stm = con.createStatement();
            
            //Endereco end = pessoa.getEndereco();

            
            //String sql2 = "SELECT * FROM cliente WHERE cpf =  '" + cPf + "' and locando = '" + 0 + "';";
            System.out.println("executei");
            String sql = "SELECT * FROM cliente c JOIN veiculo v WHERE c.locando = '1' and v.locando = '1' and v.cpf = '"+cpf+"';";
            System.out.println("passei");

            //ResultSet rs2 = stm.executeQuery(sql2);
            ResultSet rs = stm.executeQuery(sql);
            //System.out.println(rs2.getString(1));
            System.out.println("teste");
            
            if (rs.next()) {
                System.out.println("Disponivel");
                String sqlUpdate = "UPDATE cliente SET locando = '"+0+"', placa='"+null+"' WHERE cpf = '"+cpf+"';";
                String sqlUpdate2 = "UPDATE veiculo SET locando = '"+0+"', cpf='"+null+"' WHERE placa = '"+rs.getString(11)+"';";
                int rsD = stm.executeUpdate(sqlUpdate);
                int rsD2 = stm.executeUpdate(sqlUpdate2);
                if((rsD > 0)){
                
                    stm.close();
                    con.close();
                    return true;
                }else{
                    System.out.println("não conseguiu ");
                    return false;
                }
            }

        }catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //stm.close();
        //con.close();
        
        return false;
    }
   
   public boolean alocaVeiculo(String classe, String cpf) {
       
        String strConn = "jdbc:sqlite:/home/bcosta/NetBeansProjects/LocadoraVeiculos/locadora.db";

        Connection con;
        try {

            con = DriverManager.getConnection(strConn);

            Statement stm = con.createStatement();
            
            //Endereco end = pessoa.getEndereco();

            
            //String sql2 = "SELECT * FROM cliente WHERE cpf =  '" + cPf + "' and locando = '" + 0 + "';";
            System.out.println("executei");
            String sql = "SELECT * FROM cliente c JOIN veiculo v WHERE c.locando = '0' and v.locando = '0' and v.classe = '"+classe+"';";
            System.out.println("passei");

            //ResultSet rs2 = stm.executeQuery(sql2);
            ResultSet rs = stm.executeQuery(sql);
            //System.out.println(rs2.getString(1));
            System.out.println(rs.getString(1));
            
            if (rs.next()) {
                System.out.println("Disponivel");
                String sqlUpdate = "UPDATE cliente SET locando = '"+1+"', placa='"+rs.getString(11)+"' WHERE cpf = '"+cpf+"';";
                String sqlUpdate2 = "UPDATE veiculo SET locando = '"+1+"', cpf='"+cpf+"' WHERE placa = '"+rs.getString(11)+"';";
                int rsD = stm.executeUpdate(sqlUpdate);
                int rsD2 = stm.executeUpdate(sqlUpdate2);
                if((rsD > 0)){
                
                    stm.close();
                    con.close();
                    return true;
                }else{
                    System.out.println("não conseguiu ");
                    return false;
                }
            }

        }catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //stm.close();
        //con.close();
        
        return false;
    }
   
   public boolean excluiVeiculo(String placa) {
       
        String strConn = "jdbc:sqlite:/home/bcosta/NetBeansProjects/LocadoraVeiculos/locadora.db";

        Connection con;
        try {

            con = DriverManager.getConnection(strConn);

            Statement stm = con.createStatement();
            
            //Endereco end = pessoa.getEndereco();

            String sql = "SELECT * FROM veiculo WHERE placa =  '" + placa + "' and locando = '" + 0 + "';";

            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                
                String sqlDelete = "DELETE  FROM veiculo WHERE placa = '" + placa + "';";
                int rsD = stm.executeUpdate(sqlDelete);
                if(rsD > 0){
                
                    stm.close();
                    con.close();
                    return true;
                }else{
                    System.out.println("não conseguiu ");
                    return false;
                }
            }

        }catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //stm.close();
        //con.close();
        
        return false;
    }
   
   public boolean excluiPessoa(String cpf) {
       
        String strConn = "jdbc:sqlite:/home/bcosta/NetBeansProjects/LocadoraVeiculos/locadora.db";

        Connection con;
        try {

            con = DriverManager.getConnection(strConn);

            Statement stm = con.createStatement();
            
            //Endereco end = pessoa.getEndereco();

            String sql = "SELECT * FROM cliente WHERE cpf =  '" + cpf + "' and locando = '" + 0 + "';";

            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                
                String sqlDelete = "DELETE  FROM cliente WHERE cpf = '" + cpf + "';";
                int rsD = stm.executeUpdate(sqlDelete);
                if(rsD > 0){
                
                    stm.close();
                    con.close();
                    return true;
                }else{
                    System.out.println("não conseguiu ");
                    return false;
                }
            }

        }catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //stm.close();
        //con.close();
        
        return false;
    }
   
   public boolean cadastraFuncionario(String login, String senha) {
       
        String strConn = "jdbc:sqlite:/home/bcosta/NetBeansProjects/LocadoraVeiculos/locadora.db";

        Connection con;
        try {

            con = DriverManager.getConnection(strConn);

            Statement stm = con.createStatement();
            
            //Endereco end = pessoa.getEndereco();

            String sql = "INSERT INTO funcionario (login , senha) VALUES ('" + login + "', '" + senha+"' )";
               
            int rs = stm.executeUpdate(sql);

            if (rs > 0) {
            
                
                return true;
            }

        }catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //stm.close();
        //con.close();
        
        return false;

    }
   public Pessoa cadastraPessoa(Pessoa pessoa) {
       
        String strConn = "jdbc:sqlite:/home/bcosta/NetBeansProjects/LocadoraVeiculos/locadora.db";

        Connection con;
        try {

            con = DriverManager.getConnection(strConn);

            Statement stm = con.createStatement();
            
            Endereco end = pessoa.getEndereco();

            String sql = "INSERT INTO cliente VALUES ('" + pessoa.getCPF() + "', '" + pessoa.getNome()+ "', '"+ pessoa.getCNH() +"', '" + end.getCidade()  +"', '"+ end.getRua()+"', '"+ end.getNumero()+"', '"+ end.getCEP() +"','"+end.getComplemento()+"','" + 0 +"', '"+0+"' )";
               
            int rs = stm.executeUpdate(sql);

            if (rs > 0) {
            
                Pessoa pessoaCadastrada = pessoa;
                stm.close();
                con.close();
                return pessoaCadastrada;
            }

        }catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //stm.close();
        //con.close();
        
        return null;

    }
   
   public Veiculos cadastra(Veiculos veiculo) {
       
        String strConn = "jdbc:sqlite:/home/bcosta/NetBeansProjects/LocadoraVeiculos/locadora.db";

        Connection con;
        try {

            con = DriverManager.getConnection(strConn);

            Statement stm = con.createStatement();

            String sql = "INSERT INTO veiculo VALUES ('" + veiculo.getPlaca() + "', '"+ veiculo.getModelo() +"', '" + veiculo.getTipo() +"','"+ 0 +"' , '"+ 0 +"', '"+veiculo.getMarca()+"' )";
               
            int rs = stm.executeUpdate(sql);

            if (rs > 0) {
            
                Veiculos veiculoCadastrado = new Veiculos("", "", "", "");
                stm.close();
                con.close();
                return veiculoCadastrado;
            }

        }catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //stm.close();
        //con.close();
        
        return null;

    }
    public Usuario login(Usuario usuario) {

        String strConn = "jdbc:sqlite:/home/bcosta/NetBeansProjects/LocadoraVeiculos/locadora.db";

        Connection con;
        try {

            con = DriverManager.getConnection(strConn);

            Statement stm = con.createStatement();

            String sql = "SELECT * FROM funcionario WHERE senha =  '" + usuario.getSenha() + "' and login = '" + usuario.getLogin() + "';";
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {

                Usuario usuarioLogado = new Usuario();

                usuarioLogado.setLogin(rs.getString("login"));
                stm.close();
                con.close();
                return usuarioLogado;
            }

        }catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //stm.close();
        //con.close();
        
        return null;

    }

    public boolean login(String senha, String login) {

        String strConn = "jdbc:sqlite:/home/bcosta/NetBeansProjects/LocadoraVeiculos/locadora.db";

        Connection con;
        try {
            con = DriverManager.getConnection(strConn);
            Statement stm = con.createStatement();

            String sql = "SELECT * FROM funcionario WHERE senha =  '" + senha + "' and login = '" + login + "';";
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                Usuario usuarioLogado = new Usuario();

                usuarioLogado.setLogin(rs.getString("login"));
                System.out.println("login ok");
                return true;
            } else {
                System.out.println("nada la dentro");

            }

        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }
}
