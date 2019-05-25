import com.profesorfalken.jsensors.JSensors;
import com.profesorfalken.jsensors.model.sensors.Load;
import java.util.Arrays;
import java.util.List;
import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;
import oshi.software.os.OperatingSystem.ProcessSort;
import oshi.util.FormatUtil;

public class InfoLeitura {
    
    private SystemInfo systemInfo;
    private HardwareAbstractionLayer hardware;
    private OperatingSystem operatingSystem;

    public InfoLeitura(){
        systemInfo = new SystemInfo();
        hardware = systemInfo.getHardware();
        operatingSystem = systemInfo.getOperatingSystem();
        
        HardwareAbstractionLayer hal = systemInfo.getHardware();
        
        
        
        }
    
    public String processosT(){
    List<OSProcess> procs = Arrays.asList(operatingSystem.getProcesses(5, ProcessSort.CPU)); 
    
    GlobalMemory memory = hardware.getMemory();
      
                
        System.out.println("   PID  %CPU %MEM       VSZ       RSS Name");
        for (int i = 0; i < procs.size() && i < 10; i++) {
            OSProcess p = procs.get(i);
            System.out.format(" %5d %5.1f %4.1f %9s %9s %s%n", p.getProcessID(),
                    100d * (p.getKernelTime() + p.getUserTime()) / p.getUpTime(),
                    100d * p.getResidentSetSize() / memory.getTotal(), FormatUtil.formatBytes(p.getVirtualSize()),
                    FormatUtil.formatBytes(p.getResidentSetSize()), p.getName());
    }
       return " ";
        
    }
    
    
   
    
   
    
    // Formata de byte pra giga
    public String FormatarValor(long value){
        return FormatUtil.formatBytes(value);
    }
    // Pega a partiçoes do disco
    private int numeroDeParticoesDeDisco(){
        return operatingSystem.getFileSystem().getFileStores().length;
    }
    //pega o processamento atual
    public String processamento(){
        
        List<com.profesorfalken.jsensors.model.components.Cpu> cpus = JSensors.get.components().cpus;
        if(cpus.isEmpty()) return "40.0";
        
        for (final com.profesorfalken.jsensors.model.components.Cpu cpu : cpus) {
            List<Load> loads = cpu.sensors.loads;
            for (final Load load : loads) {
                if(load.name.equals("Load CPU Total"))
                    return String.valueOf(load.value);
            }
        }
        return "40.0";
    }

    
    
    // pega a memoria Ram disponivel
    public long memoriaRamDisponivel(){
        return hardware.getMemory().getAvailable();
    }
    // pega memoria total disponivel
    public long memoriaDisponível() {
        int numeroDeParticoes = numeroDeParticoesDeDisco();
        long memoriaDisponivel = 0;
        for (int i = 0; i < numeroDeParticoes; i++) {
            memoriaDisponivel += operatingSystem.getFileSystem().getFileStores()[i].getUsableSpace();
        }
        return memoriaDisponivel;
    }
    public String toString() {
        return  
                "Processamento Atual: " + processamento() + "%" +
                "\n" +
                "Memória Ram Disponível: "+ FormatarValor(memoriaRamDisponivel()) +
                "\n" +
                "Armazenamento Disponível: "+ FormatarValor(memoriaDisponível()) +
                "\n" +
                processosT();
    }
}
