import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

 
public class DButils 
{
    private boolean formato_obj = false;
    private ArrayList<Integer> vnums;
    private ArrayList<String> nomes;
    private ArrayList<String> descs;
    private ArrayList<Integer> obj_pesos;
    private ArrayList<int[]> sala_saidas;
    private ArrayList<int[]> sala_lista_obj;
            
    public boolean carregar(String nome_arq)
    {
        // Verificar se as variáveis de instância já contém dados
        if (vnums != null       || nomes != null        || descs != null || 
            obj_pesos != null   || sala_saidas != null  || sala_lista_obj != null)
        {
            System.out.println("DButils::carregar: !!! Esta instância já foi carregada !!!");
            return false;
        }
        
        // Detectar formato do arquivo baseado na extensão dele
        if (nome_arq.substring(nome_arq.length()-4, nome_arq.length()).equals(".obj"))
            formato_obj = true;
                   
        // Alocar memória para as variáveis de instância
        vnums = new ArrayList<Integer>();
        nomes = new ArrayList<String>();
        descs = new ArrayList<String>();
        obj_pesos = new ArrayList<Integer>();
        sala_saidas = new ArrayList<int[]>();
        sala_lista_obj = new ArrayList<int[]>();
        
        // Verificar se arquivo existe no disco rígido
        File arquivo = new File(nome_arq);
        if (!arquivo.exists())
        {
            System.out.println("DButils::carregar: !!! Arquivo não existe !!!");
            return false;
        }
                
        // Alocar memória para um objeto Scanner para ler dados do arquivo
        Scanner entrada;
        try 
        {
            entrada = new Scanner(arquivo);
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
            return false;
        }        
        
        /* Loop que faz o parsing das informações do arquivo
         * e armazena os dados em variáveis próprias da classe
         * para serem recuperadas depois através dos métodos GET.
         */
        
        int num_linha = 0;
        while (entrada.hasNext()) 
        {
            String line = entrada.nextLine();
            
            // sai do loop quando encontrar o caractere $ 
            if (line.charAt(0) == '$') // indica final do arquivo
                break;
            
            // linha 0: vnum do item
            if ( (num_linha == 0) && (line.charAt(0) == '#') )
            {
                //System.out.println("dbg: " + line.substring(1));
                vnums.add(Integer.parseInt(line.substring(1)));
                num_linha++;
                continue;
            }
            
            // linha 1: nome do item
            if (num_linha == 1)
            {
                if (line.endsWith("~"))
                    nomes.add(line.substring(0, line.length()-1));
                else
                    nomes.add(line);
            }
            
            // linha 2: descrição do item
            if (num_linha == 2)
            {
                if (line.endsWith("~"))
                    descs.add(line.substring(0, line.length()-1));
                else
                    descs.add(line);
            }
                                    
            if (line.charAt(line.length()-1) == '~')
            {
                num_linha++;
                continue;
            }            

            // linha 3: se for sala, lista das saidas; senão, peso do objeto
            if (num_linha == 3)
            {
                if (!formato_obj)
                {
                    // Separar os números por espaços
                    String[] palavras = line.split("\\s+");
                    
                    // Montar array com os vnums das salas
                    int array[] = new int[palavras.length];
                    for (int i = 0; i < palavras.length; i++)
                        array[i] = Integer.parseInt(palavras[i]);

                    sala_saidas.add(array);            
                    num_linha++;
                    continue; 
                }
                else       
                {   
                    obj_pesos.add(Integer.parseInt(line)); 
                    
                    // fim dos dados de um arquivo .obj 
                    num_linha = 0; // reiniciar a contagem de linhas
                    continue; 
                }
                                
            }
            
            // linha 4: lista de objetos (somente .wld)
            if (num_linha == 4)
            {
                // Separar os números por espaços
                String[] palavras = line.split("\\s+");

                // Montar array com os vnums dos objetos
                int array[] = new int[palavras.length];
                for (int i = 0; i < palavras.length; i++)
                    array[i] = Integer.parseInt(palavras[i]);

                sala_lista_obj.add(array);                  
                
                // fim dos dados de um arquivo .wld
                num_linha = 0; // reiniciar a contagem de linhas
                continue;
            }
        }

        entrada.close();
        
        return true;
    }
    
    public int[] getObjVnums()
    {
        return arrayList_2_intArray(vnums);
    }
    
    public String[] getItemNomes()
    {
        return arrayList_2_strArray(nomes);
    }

    public String[] getItemDesc()
    {
        return arrayList_2_strArray(descs);
    }   
    
    public int[][] getSalaSaidas()
    {
        return arrayList_2_intArrayInt(sala_saidas);
    } 
        
    public int[][] getSalaObjs()
    {
        return arrayList_2_intArrayInt(sala_lista_obj);
    } 
    
    public int[] getObjPeso()
    {        
        if (!formato_obj)
        {
            // Arquivos que não são .obj não possuem esta informação
            return null;
        }
        
        return arrayList_2_intArray(obj_pesos);
    }

    private int[] arrayList_2_intArray(ArrayList<Integer> al)
    {
        int[] intArray = new int[al.size()];
        for (int i = 0; i < intArray.length; i++)
        {
            intArray[i] = al.get(i).intValue();
        }        
        return intArray;
    }
    
    private int[][] arrayList_2_intArrayInt(ArrayList<int[]> al)
    {   
        int dim1_tam = al.size();
        int dim2_tam = 0;
        for (int i = 0; i < dim1_tam; i++)
        {
            if (al.get(i).length > dim2_tam)
                dim2_tam = al.get(i).length;
        }                
                
        int[][] result = new int[dim1_tam][dim2_tam];
        
        for (int i = 0; i < dim1_tam; i++)
            for (int j = 0; j < dim2_tam; j++)
                result[i][j] = 0;
        
        for (int i = 0; i < dim1_tam; i++)
            for (int j = 0; j < al.get(i).length; j++)
                result[i][j] = al.get(i)[j];
                
        return result;
    }
    
    private String[] arrayList_2_strArray(ArrayList<String> al)
    {
        String[] strArray = al.toArray(new String[al.size()]);        
        return strArray;        
    }
}
