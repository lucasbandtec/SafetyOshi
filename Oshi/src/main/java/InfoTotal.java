import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;

public class InfoTotal {
    // SystemInfo é a classe principal do oshi, todas as outras classes e metodos estão dentro desta principal
    private SystemInfo systemInfo;
    private HardwareAbstractionLayer hardware;
    private OperatingSystem operatingSystem;
    
    public InfoTotal(){
        systemInfo = new SystemInfo();
        hardware = systemInfo.getHardware();
        operatingSystem = systemInfo.getOperatingSystem();
    }
    
    // Formata o valor de bytes pra giga
    private String formatarValor(long value){
        return FormatUtil.formatBytes(value);
    }
    //Pega quantos Discos tem no computador
    private int numeroDeDiscos(){
        return operatingSystem.getFileSystem().getFileStores().length;
    }
    //Pega o tamanho do disco
    private OSFileStore[] discos(){
        return operatingSystem.getFileSystem().getFileStores();
    }
    //Pega o processador
    public String processador(){
        return hardware.getProcessor().getName();
    }
    //pega memoria Ram
    public String ram(){
        return formatarValor(hardware.getMemory().getTotal());
    }
    
    
    public String[] nomeDosDiscos(){
        int numeroDeDiscos = numeroDeDiscos();
        String[] discos = new String[numeroDeDiscos];
        for(int i = 0; i < numeroDeDiscos; i++){
            discos[i] = discos()[i].getName();
        }
        return discos;
    }
    // pega o tamanho do disco e formata o valor
    public String[] tamanhoDosDiscos(){
        int numeroDeDiscos = numeroDeDiscos();
        String[] discos = new String[numeroDeDiscos];
        for(int i = 0; i < numeroDeDiscos; i++){
            discos[i] = formatarValor(discos()[i].getTotalSpace());
        }
        return discos;
    }
    //pega o armazendo total do computador e formata o valor
    public String armazenamentoTotal(){
        long tamanhoDosDiscos = 0;
        for(int i = 0; i < numeroDeDiscos(); i++){
            tamanhoDosDiscos += discos()[i].getTotalSpace();
        }
        return formatarValor(tamanhoDosDiscos);
    }
    // toString serve para imprimir no console (igual a System.out.println)
    public String toString() {
        String toString = "-------- Dados Gerais ---------" +
                "\n" +
                "Processador: " + processador() +
                "\n" +
                "Memória RAM Total: "+ ram() +
                "\n" +
                "Armazenamento Total: " + armazenamentoTotal() +
                "\n";

        for(int i = 1; i <= numeroDeDiscos(); i++){
            toString += "Disco Local " + i + ": " + nomeDosDiscos()[i - 1] +
                    "\n" +
                    "Tamanho do Disco " + i + ": " + tamanhoDosDiscos()[i - 1] +
                    "\n";
        }

        return toString;
    }
}
