
import java.io.IOException;
import static java.lang.Thread.sleep;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OSProcess;
import oshi.util.FormatUtil;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author luqui
 */
public class OshiMain {                //throws - Obriga a capturar a execução "InterruptedException
    public static void main(String[] args) throws InterruptedException, IOException {
        InfoTotal info = new InfoTotal();
        InfoLeitura data = new InfoLeitura();
        
        
         
        System.out.println(info);
        
        System.out.println();
        
        testeLog log = new testeLog();
        
        while (true) {
            log.escreveLog(data.toString());
            sleep(20000);
        }
    }
}
