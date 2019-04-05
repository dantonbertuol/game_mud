package aventura;
import java.util.ArrayList;
import java.util.Scanner;


public class Comando {
    private String nomeC;
    private String nomeCS;
    private Jogador C1 = new Jogador();
    private DButils DB=new DButils();
    private int i;
    private boolean h;
    private ArrayList<Sala> salas = new ArrayList();
    private ArrayList<Item> itens = new ArrayList();
    Scanner entrada = new Scanner(System.in);
    
    
public Comando (Jogador a, ArrayList<Sala> s, ArrayList<Item> i){
    C1=a;
    salas=s;
    itens=i;
}
 
    public void Comandos(String c){
        nomeC=c;       
        
        if (nomeC.equals("Norte") || nomeC.equals("norte")){
            for (i=0;i<salas.size();i++){
                if(C1.getSala()==salas.get(i).getVNUM()){                
                    if(salas.get(i).getSaidas()[0]!=0){
                        C1.setSala(salas.get(i).getSaidas()[0]);                        
                    }
                else
                    System.out.println("Nao ha saidas para o norte!");
                    break;
                }           
            }                        
        }
        
        if (nomeC.equals("Sul") || nomeC.equals("sul")){
            for (i=0;i<salas.size();i++){
                if(C1.getSala()==salas.get(i).getVNUM()){                
                    if(salas.get(i).getSaidas()[1]!=0){
                        C1.setSala(salas.get(i).getSaidas()[1]);                       
                    }
                else
                    System.out.println("Nao ha saidas para o sul!");
                    break;
                }           
            }
        }
        
        if (nomeC.equals("Leste") || nomeC.equals("leste")){
            for (i=0;i<salas.size();i++){
                if(C1.getSala()==salas.get(i).getVNUM()){                
                    if(salas.get(i).getSaidas()[2]!=0){
                        C1.setSala(salas.get(i).getSaidas()[2]);                       
                    }
                else
                    System.out.println("Nao ha saidas para o leste!");
                    break;
                }           
            }
        }
        
        if (nomeC.equals("Oeste") || nomeC.equals("oeste")){
            for (i=0;i<salas.size();i++){
                if(C1.getSala()==salas.get(i).getVNUM()){                
                    if(salas.get(i).getSaidas()[3]!=0){
                        C1.setSala(salas.get(i).getSaidas()[3]);                       
                    }
                else
                    System.out.println("Nao ha saidas para o oeste!");
                    break;
                }           
            }
        }
        
        if (nomeC.equals("olhar") || nomeC.equals("Olhar")){
            for(int i=0;i<salas.size();i++){
                if(C1.getSala()==salas.get(i).getVNUM()){                    
                    System.out.println(salas.get(i).getNome());
                    System.out.println(salas.get(i).getDescricao());
                    for(int m=0;m<salas.get(i).getItens().size();m++){
                        for(int o=0;o<itens.size();o++){
                            if(salas.get(i).getItens().get(m)==itens.get(o).getVNUM())
                                System.out.println(itens.get(o).getDescricao());
                                }
                    }
                    for(int n=0;n<1;n++){
                        if(salas.get(i).getSaidas()[0]!=0){
                            System.out.printf("Norte   ");
                        }
                        if(salas.get(i).getSaidas()[1]!=0){                       
                            System.out.printf("Sul   ");
                        }
                        if(salas.get(i).getSaidas()[2]!=0){                       
                            System.out.printf("Leste   ");
                        }
                        if(salas.get(i).getSaidas()[3]!=0){                       
                            System.out.printf("Oeste   ");
                        }
                    }
                        System.out.println("");
                }
            }
        }
        
        if (nomeC.length()>=6 &&(nomeC.substring(0, 6).equals("olhar ") || nomeC.substring(0,6).equals("Olhar "))){
            for (int k=0;k<salas.size();k++){
                if(salas.get(k).getVNUM()==C1.getSala()){
                    for(int m=0;m<salas.get(k).getItens().size();m++){
                        for(int o=0;o<itens.size();o++){
                            if(salas.get(k).getItens().get(m)==itens.get(o).getVNUM() && nomeC.substring(6).equals(itens.get(o).getNome())){
                                System.out.println(itens.get(o).getNome());
                                System.out.println(itens.get(o).getDescricao());
                            }
                            
                        }
                    }
                }
            }
            for(int j=0;j<C1.getItens().size();j++){
                if(C1.getItens().get(j).getNome().equals(nomeC.substring(6))){
                    System.out.println(C1.getItens().get(j).getNome());
                    System.out.println(C1.getItens().get(j).getDescricao());
                }               
            }         
        }
        
        if(nomeC.equals("inv") || nomeC.equals("Inv")){                      
                if(C1.getItens().isEmpty()){
                    System.out.println("Nenhum item");
                }
                else
                for(i=0;i<C1.getItens().size();i++){
                    System.out.println(C1.getItens().get(i).getNome());                   
                }
                System.out.println("Peso: "+C1.getMochila());
        }
        
        if (nomeC.length()>=6 && (nomeC.substring(0,6).equals("pegar ") || nomeC.substring(0,6).equals("Pegar "))){
            for(int k=0;k<salas.size();k++){
                if(salas.get(k).getVNUM()==C1.getSala()){
                    for(int m=0;m<salas.get(k).getItens().size();m++){
                        for(int o=0;o<itens.size();o++){
                            try{
                            if(salas.get(k).getItens().get(m)==itens.get(o).getVNUM() && nomeC.substring(6).equals(itens.get(o).getNome())){
                                if((C1.getMochila()+itens.get(o).getPeso())>100.000){
                                    C1.setItens(itens.get(o));
                                    C1.setMochila(itens.get(o).getPeso());                                                                      
                                    salas.get(k).getItens().remove(m);
                                }
                                else
                                    System.out.println("Espaço insuficiente");                         
                            }
                            }
                            catch(RuntimeException a){                                
                            }
                        }                            
                    }
                }
            }             
        }                              
        
        if (nomeC.length()>=7 && (nomeC.substring(0,7).equals("largar ") || nomeC.substring(0,7).equals("Largar "))){           
            for(int j=0;j<C1.getItens().size();j++){
                if(C1.getItens().get(j).getNome().equals(nomeC.substring(7))){
                    for(int k=0;k<salas.size();k++){
                        if(salas.get(k).getVNUM()==C1.getSala()){
                            salas.get(k).setItens(C1.getItens().get(j).getVNUM());
                            C1.setMochila(-(C1.getItens().get(j).getPeso()));
                            C1.getItens().remove(j);                                             
                        }
                    }
                }
            }           
        }
        
        if(nomeC.equals("Sair") || nomeC.equals("sair")){
            return;
        }
  
    }
}
    


        

    

