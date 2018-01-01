// Kuuluu pakkaukseen tiedot joka on oope2017ht -pakkauksen sisällä
package oope2017ht.tiedot;

/**
  * Tiedosto -luokka joka perii Tieto -luokan.
  * <p>
  * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2017.
  * <p>
  * @author Sara Kumpulainen 
  * Informaatiotieteiden yksikkö, Tampereen yliopisto.
  */
public class Tiedosto extends Tieto {
   /**
    * attribuutti koolle
    */
   private int koko;
   /**
    * [Tiedosto Parametrillinen rakentaja, joka saa parametreina nimen sekä koon.]
    * @param  uusiNimi                 [parametrina tiedoston nimi]
    * @param  uusiKoko                 [parametrina tiedoston koko]
    * @throws IllegalArgumentException [jos löytyy virheitä, heitetään poikkeus]
    */
   public Tiedosto (StringBuilder uusiNimi, int uusiKoko) throws IllegalArgumentException{
     // Kutsutaan yliluokkaa nimen saamiseksi 
     super(uusiNimi);
     // Jos koko on sallittu, voidaan kutsua asetusmetodia
     if (uusiKoko >= 0){
        koko(uusiKoko);
     }
     // Muutoin heitetään poikkeus
     else {
        throw new IllegalArgumentException();
     }
   }
   /**
    * [Tiedosto tiedoston parametrillinen rakentaja, jossa kutsutaan yliluokan nimeä.]
    * @param  uusiNimi [tiedoston nimi parametrina]
    */
   public Tiedosto (StringBuilder uusiNimi) {
     // Kutsutaan yliluokkaa nimen saamiseksi 
     super(uusiNimi);
   }

   /**
    * [Tiedosto tiedoston Kopiorakentaja]
    * @param  e [Tiedosto -tyyppinen nimi]
    */
   public Tiedosto(Tiedosto e) {
      // Kutsutaan yliluokan kopiorakentajaa.
      super(e);
      // kopioidaan tiedosto
      koko(e.koko());
   }
   /* 

   Aksessorit

   */

   /**
    * [koko koon setteri]
    * @param  uusiKoko                 [parametrina tiedoston koko]
    * @throws IllegalArgumentException [jos löytyy virheitä, heitetään poikkeus]
    */
   public void koko (int uusiKoko) throws IllegalArgumentException {
   // Jos koko on sallittu, asetetaan koko
      if (uusiKoko >= 0){
         koko=uusiKoko;
      }
      // Muutoin heitetään poikkeus
      else {
         throw new IllegalArgumentException();
      }
   }
   /**
    * [koko koon getteri]
    * @return [palautetaan koko]
    */
   public int koko() {
      return koko;
   }

   /**
    * [toString Metodi palauttaa yluokasta nimen sekä Tiedosto-luokasta koon.]
    * @return [palautetaan yliluokasta nimi ja tästä luokasta koon]
    */
   @Override
   public String toString() {
      return super.toString() + " " + koko;
   }
}