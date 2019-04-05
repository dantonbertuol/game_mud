package aventura;
import java.util.ArrayList;


public class ThreadTwo extends Thread {
    private DButils DB1=new DButils();
    private DButils DB2=new DButils();
    private Sala s1;
    private Item i1;
    private ArrayList<Sala> salas = new ArrayList();
    private ArrayList<Item> itens = new ArrayList();
    
    public ThreadTwo(ArrayList<Sala> c, ArrayList<Item> d){
        salas=c;
        itens=d;
    }
    
    
    
    public void run(){
        DB1.carregar("C:\\Users\\Danton\\Desktop\\itens.obj");
        DB2.carregar("C:\\Users\\Danton\\Desktop\\salas.wld");       
        
        for (int i=0;i<DB2.getObjVnums().length;i++){
            s1=new Sala();
            s1.setVNUM(DB2.getObjVnums()[i]);
            s1.setNomeS(DB2.getItemNomes()[i]);
            s1.setSaidas(DB2.getSalaSaidas()[i][0], DB2.getSalaSaidas()[i][1], DB2.getSalaSaidas()[i][2],DB2.getSalaSaidas()[i][3]);
            s1.setDescricao(DB2.getItemDesc()[i]);           
            for (int k=0;k<DB2.getSalaObjs()[i].length;k++){
                    s1.setItens(DB2.getSalaObjs()[i][k]);            
            }      
            salas.add(s1);       
        }
        
        
        for (int j=0;j<DB1.getObjVnums().length;j++){
            i1=new Item();
            i1.setNome(DB1.getItemNomes()[j]);
            i1.setVNUM(DB1.getObjVnums()[j]);
            i1.setPeso(DB1.getObjPeso()[j]);
            i1.setDescricao(DB1.getItemDesc()[j]);            
            itens.add(i1);
        }      
    }
    
     public ArrayList<Sala> getSalas(){
        return salas;
    }
    
    public ArrayList<Item> getItens(){
        return itens;
    }
}

