package tpiappsimple;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args)  {
        
        Scanner input = new Scanner(System.in);
        ManagerFilesClass mf = new ManagerFilesClass();
      
        System.out.println("Buscando documentos a cargar en la carpeta documents...");
        try {
            File[] f = mf.SearchDocuments();
            if(f.length>0);
                System.out.println("    Existen " + f.length +" documentos a procesar.");
            
            System.out.println("Desea iniciar Proceso? 0-Si 1-No");
            int number = input.nextInt();
            if(number==0){
                mf.ProcessFiles(f);}
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }  
    }  
}
