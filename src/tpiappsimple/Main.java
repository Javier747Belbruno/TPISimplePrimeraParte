package tpiappsimple;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;




public class Main {
    
    static Scanner input = new Scanner(System.in);
    
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
                   System.out.println("Has seleccionado la opcion 1");
                
                   
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
        
        System.out.println("Ingrese Consulta");
        String query = input.next();
        //Ask if HashTable vocabulary is not empty.     ELSE LOAD VOCABULARY.
        //  Then CleanQuery/DivideQuery/DeleteStopsWordsQuery
        
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
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }  
    }
    
}
