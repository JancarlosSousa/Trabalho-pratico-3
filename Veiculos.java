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
public class Veiculos {
    
    private String Marca;
    private String Modelo;
    private String Placa;
    private String Tipo;
    private int Locando;

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public boolean isDisponibilidade() {
        return Disponibilidade;
    }

    public void setDisponibilidade(boolean Disponibilidade) {
        this.Disponibilidade = Disponibilidade;
    }
    private boolean Disponibilidade = true;
    
    //métodos construtor
    public Veiculos(String marca, String modelo, String placa, String tipo){
    
        setMarca(marca);
        setModelo(modelo);
        setPlaca(placa);
        setTipo(tipo);
 
    }
  
    //métodos set
    public void setMarca(String marca){
        Marca = marca;
    }
    public void setModelo(String modelo){
        Modelo = modelo;
    }
    public boolean setPlaca(String placa){
        placa = placa.toUpperCase();
        Pattern pattern = Pattern.compile("[A-Z]{3,3}\\d{4,4}");
        Matcher matcher = pattern.matcher(placa);
        if(matcher.find()) {
            Placa = placa;
            return true;
            
        }
        else{
            System.out.println("erro placa");
            return false;
        }
    }
   
    
    public void setDisponibilidade(){
        Disponibilidade = !Disponibilidade;
    }
    
    //métodos get
    public String getMarca(){
        return Marca;
    }
    public String getModelo(){
        return Modelo;
    }
    public String getPlaca(){
        
        return Placa;
    }
    
    
    //métodos 
    public String disponibilidadeVeiculo(){
        return (Disponibilidade ? "Sim":"Não");
    }
    //métodos de impressão
    @Override
    public String toString() {
	return String.format("\n\tDados do Veículos\nMarca: %s\nModelo: %s\nPlaca: %s\nDisponivel: %s\n"   , Marca, Modelo, Placa, disponibilidadeVeiculo());
    }

    void setLocando(int locando) {
        Locando = locando;
        
    }

    /**
     * @return the Locando
     */
    public int getLocando() {
        return Locando;
    }

    
}
