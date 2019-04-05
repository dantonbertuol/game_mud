package aventura;

public class Item {
    private String nomeI;
    private String descricaoI;
    private int pesoI;
    private int vNumI;
    
    //métodos set
    public void setNome(String n){
        nomeI=n;
    }
    
    public void setDescricao(String n){
        descricaoI=n;
    }
    
    public void setPeso(int n){
        pesoI=n;
    }
    
    public void setVNUM(int n){
        vNumI=n;
    }
    
    //métodos get
    public String getNome (){
        return nomeI;
    }
    
    public String getDescricao (){
        return descricaoI;
    }
    
    public int getPeso (){
        return pesoI;
    }
    
    public int getVNUM(){
        return vNumI;
    }
    
}
