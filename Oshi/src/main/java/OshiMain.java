
import static java.lang.Thread.sleep;

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
    public static void main(String[] args) throws InterruptedException {
        InfoTotal info = new InfoTotal();
        InfoLeitura data = new InfoLeitura();

        System.out.println(info);

        System.out.println();

        while (true) {
            System.out.println(data);
            sleep(7000);
        }
    }
}
