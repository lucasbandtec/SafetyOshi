/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.util.logging.Handler;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;
/**
 *
 * @author Aluno
 */
public class testeLog {
    
    private Logger LOGGER;
    
    public testeLog(String erro) throws IOException {   
        escreveLog(erro);
    }
    
    public testeLog(){
        
    }
    
    public void escreveLog(String erro) throws IOException{
        LOGGER = Logger.getLogger(testeLog.class.getSimpleName());
        Handler fileHandler = null;
        fileHandler = new FileHandler("./logDiferenciado.txt",true);
        SimpleFormatter simple = new SimpleFormatter();
        fileHandler.setFormatter(simple);
        fileHandler.setEncoding("utf-8");
        
        
        LOGGER.setUseParentHandlers(false);
        
        LOGGER.addHandler(fileHandler);
        
        fileHandler.setLevel(Level.ALL);
        LOGGER.setLevel(Level.ALL);
        
        
        
        LOGGER.info(erro);
        
    }
}
