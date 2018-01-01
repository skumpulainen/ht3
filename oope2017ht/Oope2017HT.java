// Kuuluu pakkaukseen oope2017ht
package oope2017ht;
// tuodaan KayttoLiittyma -luokka
import oope2017ht.KayttoLiittyma;

/**
  * Oope2017HT-luokka, joka toimii ohjelman ajoluokkana. 
  * <p>
  * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2017.
  * <p>
  * @author Sara Kumpulainen 
  * Informaatiotieteiden yksikkö, Tampereen yliopisto.
  */
public class Oope2017HT {
   /**
    * 
    * @param args [pääsilmukka]
    * 
    */
   public static void main(String[] args) {
      // Käynnistetään ohjelma
      System.out.println("Welcome to SOS.");
      // Luodaan oliot jotka kutsuvat käyttöliittymän tulkiteKomennot-operaatiota
      KayttoLiittyma kayttoLiittyma = new KayttoLiittyma();
      kayttoLiittyma.tulkitseKomennot();
        
    }
}
