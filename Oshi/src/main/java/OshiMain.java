
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
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
    public static void main(String[] args) throws InterruptedException, IOException, SQLException {
        InfoTotal info = new InfoTotal();
        InfoLeitura data = new InfoLeitura();
        
        Database database = new Database();
        
        JdbcTemplate db = database.getConnection();
        
        //System.out.println(db.query("SELECT * FROM LOGS"));
        
        List<Maquina> listaDePcs = db.query("SELECT * FROM MAQUINA", new BeanPropertyRowMapper<Maquina>(Maquina.class));
        
        for(Maquina maquina:listaDePcs){
            db.update("INSERT INTO LOGS VALUES(CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?, ?, ?, ?)",
                data.memoriaDisponível(),
                data.memoriaRamDisponivel(),
                data.processamento(),
                maquina.getIdMaquina());
            
        }
        
        
//         
//        System.out.println(info);
//        
//        System.out.println();
//        
//        testeLog log = new testeLog();
//        
//        while (true) {
//            log.escreveLog(data.toString());
//            sleep(20000);
//        }
    }
}
