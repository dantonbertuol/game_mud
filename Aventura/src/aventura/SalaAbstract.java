package aventura;
import java.util.ArrayList;

abstract public class SalaAbstract implements SalaInterface {
    private String nomeS;
    private String descricao;
    private int[] saidas = new int [4];
    private ArrayList<Integer> itens = new ArrayList();
    private int vNumS;
   
    abstract public void setNomeS(String n);
    
    public void setDescricao(String n){
        descricao=n;
    }
    
    public void setSaidas(int norte, int sul, int leste, int oeste){
        saidas[0]=norte;
        saidas[1]=sul;
        saidas[2]=leste;
        saidas[3]=oeste;
    }
    
    public void setItens(int n){
        itens.add(n);
    }
    
    public void setVNUM(int n){
        vNumS=n;
    }
    
    //métodos get
    public String getNome(){
        return nomeS;
    }
    
    public String getDescricao(){
        return descricao;
    }
    
    public int[] getSaidas(){
        return saidas;
    }
    
    public ArrayList<Integer> getItens(){
        return itens;
    }
    
    public int getVNUM(){
        return vNumS;
    }
}
