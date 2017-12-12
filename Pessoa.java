/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locadoraveiculos;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author bcosta
 */
public class Pessoa {
    
    private String Nome;
    private String CPF;
    private String CNH;
    private Endereco endereco;
    private int locando ;

    public int getLocando() {
        return locando;
    }

    public void setLocando(int locando) {
        this.locando = locando;
    }
    

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
  
        this.Nome = Nome;
    }

    public String getCPF() {
        return CPF;
    }

    public boolean setCPF(String CPF) {
        
        CPF = CPF.toUpperCase();
        Pattern pattern = Pattern.compile("\\d{11,11}");
        Matcher matcher = pattern.matcher(CPF);
        if(matcher.find()) {
            this.CPF = CPF;
            return true;
            
        }
        else{
            System.out.println("erro cpf");
            return false;
        }

    }

    public String getCNH() {
        return CNH;
    }

    public boolean setCNH(String CNH) {
        
        CNH = CNH.toUpperCase();
        Pattern pattern = Pattern.compile("\\d{11,11}");
        Matcher matcher = pattern.matcher(CNH);
        if(matcher.find()) {
            this.CNH = CNH;
            return true;
            
        }
        else{
            System.out.println("erro cnh");
            return false;
        }
    }

    public Pessoa(String Nome, String CPF, String CNH, Endereco end) {
        this.Nome = Nome;
        this.CPF = CPF;
        this.CNH = CNH;
        endereco = end;
    }
    
}
