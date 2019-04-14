import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;

public class InfoLeitura {
    
    private SystemInfo systemInfo;
    private HardwareAbstractionLayer hardware;
    private OperatingSystem operatingSystem;

    public InfoLeitura(){
        systemInfo = new SystemInfo();
        hardware = systemInfo.getHardware();
        operatingSystem = systemInfo.getOperatingSystem();
    }
    // Formata de byte pra giga
    private String FormatarValor(long value){
        return FormatUtil.formatBytes(value);
    }
    // Pega a partiçoes do disco
    private int numeroDeParticoesDeDisco(){
        return operatingSystem.getFileSystem().getFileStores().length;
    }
    //pega o processamento atual
    public double processamento(){
        return hardware.getProcessor().getSystemCpuLoad();
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
                "Processamento Atual: " + (processamento() * 100) + "%" +
                "\n" +
                "Memória Ram Disponível: "+ FormatarValor(memoriaRamDisponivel()) +
                "\n" +
                "Armazenamento Disponível: "+ FormatarValor(memoriaDisponível()) +
                "\n";
    }
}
