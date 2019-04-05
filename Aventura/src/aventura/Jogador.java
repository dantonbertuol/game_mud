package aventura;
import java.util.ArrayList;

public class Jogador {
    private String nomeP;
    private String senha;
    private String sexo;
    private String profissao;
    private ArrayList<Item> itens = new ArrayList();
    private int maxPeso=100000;
    private int sala;
    private int mochila;
    
    //métodos set
    public void setNomeP(String n){
        nomeP=n;
    }
    
    public void setSenha(String n){
        senha=n;
    }
    
    public void setSexo(String n){
        sexo=n;
    }
    
    public void setProfissao(String n){
        profissao=n;
    }
    
    public void setItens(Item n){
        itens.add(n);
    }
    
    public void setSala(int n){
        sala=n;
    }
    
    public void setMochila(int n){
        mochila=n+mochila;
    }
    
    //métodos get
    public String getNomeP(){
        return nomeP;
    }
    
    public String getSenha(){
        return senha;
    }
    
    public String getSexo(){
        return sexo;
    }
    
    public String getProfissao(){
        return profissao;
    }
    
    public ArrayList<Item> getItens(){
        return itens;
    }
    
    public int getMaxPeso(){
        return maxPeso;
    }
     
    public int getSala(){
        return sala;
    }
      
    public int getMochila(){
        return mochila;
    }
    
}
