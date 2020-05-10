package tpiappsimple;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    
    static Scanner input = new Scanner(System.in);
    static InvertedIndexClass ii = new InvertedIndexClass();
    public static void main(String[] args)  {


        boolean exit = false;
        int option; //Guardaremos la opcion del usuario

        while(!exit){
            
           System.out.println("1. Realizar una consulta");
           System.out.println("2. Indexar nuevos documentos");
           System.out.println("3. Salir");
            
           System.out.println("Escribe una de las opciones");
           option = input.nextInt();
            
           switch(option){
               case 1:
                   Query();
                   break;
               case 2:
                   LoadDocumentsAndIndex();
                   break;
                case 3:
                   exit=true;
                   break;
                default:
                   System.out.println("Solo números entre 1 y 3");
           }
            
       }
        
     
    }
    
    public static void Query(){
        try {
        Scanner inputQuery = new Scanner(System.in).useDelimiter("\\n");
        Scanner inputR = new Scanner(System.in);
        QueryClass q = new QueryClass();
        System.out.println("Ingrese Consulta");
        String query = inputQuery.next();
        
        System.out.println("Ingrese Cantidad de Documentos a Recuperar");
        Integer r = inputR.nextInt();
        
        //Ask if HashTable vocabulary is empty, fill it   
        if(ii.IsEmpty())
            ii.doFillVocabulary();
        
        q.processQuery(query,r);
        //show Documents.
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }
    
     
    public static void LoadDocumentsAndIndex(){
        ManagerFilesClass mf = new ManagerFilesClass();
        System.out.println("Buscando documentos a cargar en la carpeta /documents y actualizar el inverted index");
        try {
            File[] f = mf.SearchDocuments();
            if(f.length>0);
                System.out.println("    Existen " + f.length +" documentos a procesar.");
            
            System.out.println("Desea iniciar Proceso? 0-Si 1-No");
            int number = input.nextInt();
            if(number==0){
                mf.ProcessFiles(f);}
            //Then Update HashTable vocabulary.
                ii.doFillVocabulary();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
