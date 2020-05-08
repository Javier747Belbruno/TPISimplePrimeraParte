package tpiappsimple;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;

/* This class manages directories and files*/
public class ManagerFilesClass {
  
    public void ProcessFiles(File[] f){
        FileClass fc = new FileClass();
        PersistenceClass pc = new PersistenceClass();
        for (File file : f) {
            
             try {
               HashMap<String,Integer> terms = fc.readFileAndReturnTerms(file.getCanonicalPath());
               String nameDocument = file.getName();
               
                //We got nameDocument,Terms and their frequencies. 
                //We are already to push data to db.
               pc.LoadDataInDB(nameDocument,terms);
               System.out.println("Document processed " + nameDocument + "..." + java.time.LocalDateTime.now()); 
               
               //MoveFile To processedFiles.
               file.renameTo(new File("./processedFiles/" + file.getName()));

               
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
      //CHECK IF EXISTS FILE TO PROCESS.
    public File[] SearchDocuments()throws IOException {
        
        File f = new File("documents");//folder
        
        FilenameFilter textFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".txt");
            }
        };
        File[] files = f.listFiles(textFilter);
        return files;
    }

}
