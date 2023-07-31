
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Fatec
 */
public class User {
    
     public static ArrayList<User> users = new ArrayList<>();
    
    private String nome;
    private String senha;
    private String email;
    
    public User(){
        this.setNome("[NEW]");
        this.setSenha("[NEW]");
        this.setEmail("[NEW]");
    }
    
    public User(String nome, String senha, String email){
        this.nome = nome;
        this.senha = senha;
        this.email = email;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
