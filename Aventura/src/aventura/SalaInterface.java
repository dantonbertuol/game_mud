
package aventura;

import java.util.ArrayList;

public interface SalaInterface {
    
    //métodos set
    abstract public void setNomeS(String n);
    
    abstract public void setDescricao(String n);
    
    abstract public void setSaidas(int norte, int sul, int leste, int oeste);
    
    abstract public void setItens(int n);
    
    abstract public void setVNUM(int n);
    
    //métodos get
    abstract public String getNome();
    
    abstract public String getDescricao();
      
    abstract public int[] getSaidas();
    
    abstract public ArrayList<Integer> getItens();
    
    abstract public int getVNUM();
}
