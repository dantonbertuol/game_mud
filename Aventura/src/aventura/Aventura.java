package aventura;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;


public class Aventura {
    

    public static void main(String[] args) {
        DButils DB1=new DButils();
        int optionM = 0;
        String S="Norte";
        boolean h=true,j=true,k=true;     
        Scanner entrada = new Scanner(System.in);
        Jogador P1 = new Jogador();
        ArrayList<Item> item1 = new ArrayList();
        ArrayList<Sala> sala1 = new ArrayList();
        Comando C = new Comando(P1, sala1, item1);
        ThreadTwo T2 = new ThreadTwo(sala1,item1);      
        T2.start();
        
        P1.setSala(101);
        
        while (k==true){
        
        try{
            System.out.println("Seja Bem Vindo a Death Clinic");
            System.out.println("1 - Entrar no Jogo");
            System.out.println("2 - Sair");
            optionM=entrada.nextInt();
            if(optionM!=1 && optionM!=2)
                throw new InputMismatchException();
            else
                k=false;
        }
        catch(InputMismatchException a){
            System.out.println("Opcao invalida!");
        }
        }
                  
        switch(optionM){
            case 1:                 
                System.out.println("Nome do jogador: ");
                entrada.nextLine();
                P1.setNomeP(entrada.nextLine());
                
                System.out.println("Senha: ");
                P1.setSenha(entrada.nextLine());
                
                while (h==true){
                    try{
                        System.out.println("Genero do Jogador (M/F)");
                        P1.setSexo(entrada.nextLine());
                       
                        if(!P1.getSexo().equals("M") && !P1.getSexo().equals("m") && !P1.getSexo().equals("F") && !P1.getSexo().equals("f") )
                          throw new InputMismatchException();  
                                                
                        else
                            h=false;
                    }
                    catch(InputMismatchException c){
                        System.out.println("Genero Invalido");                       
                    }
                }               
                
                while (j==true){
                    try{
                        System.out.println("Escolha a profissão: Medico, Enfermeira, Paciente, Secretaria ou Motorista");
                        P1.setProfissao(entrada.nextLine());
                        if(!P1.getProfissao().equals("Medico") && !P1.getProfissao().equals("Enfermeira") && !P1.getProfissao().equals("Paciente") && !P1.getProfissao().equals("Secretaria") && !P1.getProfissao().equals("Motorista") )
                              throw new InputMismatchException();                             
                        else
                                j=false;
                         
                    }
                    catch(InputMismatchException a){
                        System.out.println("Profissão Invalida!!!");                       
                   }
                }
                
                
                while(!S.equals("Sair") && !S.equals("sair")){
                    if(S.equals("Norte") || S.equals("norte") || S.equals("Sul") || S.equals("sul") || S.equals("Leste") || S.equals("leste") || S.equals("Oeste") || S.equals("oeste")){  
                       
                    System.out.println("--------------------------------------------------------------------------");
                    for(int i=0;i<sala1.size();i++){
                        if(P1.getSala()==sala1.get(i).getVNUM()){                    
                            System.out.println(sala1.get(i).getNome());
                            System.out.println(sala1.get(i).getDescricao());
                            for(int m=0;m<sala1.get(i).getItens().size();m++){
                                for(int o=0;o<item1.size();o++){
                                    if(sala1.get(i).getItens().get(m)==item1.get(o).getVNUM())
                                        System.out.println(item1.get(o).getDescricao());
                                }
                            }
                            for(int n=0;n<1;n++){
                                if(sala1.get(i).getSaidas()[0]!=0){
                                    System.out.printf("Norte   ");
                                }
                                if(sala1.get(i).getSaidas()[1]!=0){                       
                                    System.out.printf("Sul   ");
                                }
                                if(sala1.get(i).getSaidas()[2]!=0){                       
                                    System.out.printf("Leste   ");
                                }
                                if(sala1.get(i).getSaidas()[3]!=0){                       
                                    System.out.printf("Oeste   ");
                                }
                            }
                            System.out.println("");
                        }
                    }
                    System.out.println("--------------------------------------------------------------------------");
                    
                    }
                    System.out.print(">");
                    S=entrada.nextLine();               
                    C.Comandos(S);               
                
                }
                break;
            
            case 2:
                System.out.println("Até mais!");
                break;           
        }
                
    }        
}
    

