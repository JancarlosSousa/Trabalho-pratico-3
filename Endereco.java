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
public class Endereco {
    private String UF;

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String Cidade) {
        this.Cidade = Cidade;
    }

    public String getRua() {
        return Rua;
    }

    public void setRua(String Rua) {
        this.Rua = Rua;
    }

    public int getNumero() {
        return Numero;
    }

    public boolean setNumero(String Numero) {
        int n;
        
        Pattern pattern = Pattern.compile("\\d{1,10}");
        Matcher matcher = pattern.matcher(Numero);
        if(matcher.find() == true) {
            n = Integer.parseInt(Numero);
            this.Numero = n;
            return true;
            
        }
        else{
            System.out.println("erro Numero");
            return false;
        }
    }

    public String getCEP() {
        return CEP;
    }

    /**
     *
     * @param CEP
     * @return
     */
    public boolean setCEP(String CEP) {
      
        Pattern pattern = Pattern.compile("\\d{5,5}-\\d{3,3}");
        Matcher matcher = pattern.matcher(CEP);
        if(matcher.find()) {
            this.CEP = CEP;
            return true;
            
        }
        else{
            System.out.println("erro CEP");
            return false;
        }
    }

    public String getComplemento() {
        return Complemento;
    }

    public void setComplemento(String Complemento) {
        this.Complemento = Complemento;
    }

    public Endereco(String UF, String Cidade, String Rua, int Numero, String CEP, String Complemento) {
        this.UF = UF;
        this.Cidade = Cidade;
        this.Rua = Rua;
        this.Numero = Numero;
        this.CEP = CEP;
        this.Complemento = Complemento;
    }
    private String Cidade;
    private String Rua;
    private int Numero;
    private String CEP;
    private String Complemento;
    
}
