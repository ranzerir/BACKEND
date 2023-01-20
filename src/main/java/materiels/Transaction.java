/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package materiels;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import connectivity.Connexion;
import gestion.Convertisseur;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import model.Client;

/**
 *
 * @author Mendrika
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class Transaction {
    private String id;
    private String time;
    private double montant;
    private Enchere enchere;
    private Client cli;

    public String getId() {
        return id;
    }
    public static Transaction get_last(int id) throws Exception{
        Statement stmt = null;
        String sql = null;
        Connexion connexion = null;
        ResultSet rs = null;
        ArrayList list =null;
        Transaction cc=null;
        try {
            list = new ArrayList();
            connexion = new Connexion();
            stmt = connexion.getConnect().createStatement();
            sql = "SELECT*FROM Transaction where idEnchere="+id;
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                cc=new Transaction();
                Client c=new Client();
                Enchere pal=new Enchere();
                c.setId(rs.getInt(3));
                pal.setId(rs.getInt(4)+"");
                cc.setCli(c);
                cc.setEnchere(pal);
                cc.setMontant(rs.getDouble(2));
            }
        }
        finally{
            stmt.close();
            connexion.getConnect().close();
        }
         return cc;

    }
    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }
    public void save() throws Exception{
    Statement stmt=null;
        Connection con=new Connexion().getConnect();
        try{
            String sql="Insert into Transaction(time,montant,idEnchere,idClient) values('"+LocalDate.now().toString()+" 00:00:00',"+this.getMontant()+","+this.getEnchere().getId()+","+this.getCli().getId()+")";
            stmt=con.createStatement();
            System.out.println(sql);
            if(Convertisseur.get_date(Enchere.findById(this.getCli().getId()).getDate_fin()).isBefore(LocalDateTime.now())){            
                if(Transaction.get_last(Integer.parseInt(this.getEnchere().getId()))==null && Enchere.findById(this.getCli().getId()).getPrix_depart()>=this.getMontant()){            
                    stmt.executeUpdate(sql);            
                }
                else{
                    if(Integer.parseInt(Enchere.findById(this.getCli().getId()).getId())!=this.getCli().getId()){
                        if(Transaction.get_last(Integer.parseInt(this.getEnchere().getId())).getMontant()<this.getMontant()){
                            stmt.executeUpdate(sql);            
                        }
                    }
                }
            }
        
            
        }
        finally{
            if(con!=null){
                con.close();
            }
            if(stmt!=null){
                stmt.close();
            }
        }
    }
    public void setTime(String time) {
        this.time = time;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Enchere getEnchere() {
        return enchere;
    }

    public void setEnchere(Enchere enchere) {
        this.enchere = enchere;
    }

    public Client getCli() {
        return cli;
    }

    public void setCli(Client cli) {
        this.cli = cli;
    }
    
    
}
