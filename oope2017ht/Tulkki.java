// kuuluu pakkaukseen oope2017ht
package oope2017ht;
// tuodaan tiedot -kansio sekä omalista- kansio
import oope2017ht.tiedot.*;
import oope2017ht.omalista.*;
 /**
  * Tulkki-luokka. Tulkkiluokka toteuttaa
  * ohjelman tuntema komennot hakemistopuuta käsittelevillä metodeilla. 
  * <p>
  * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2017.
  * <p>
  * @author Sara Kumpulainen 
  * Informaatiotieteiden yksikkö, Tampereen yliopisto.
  */
public class Tulkki  {
   /** Hakemisto-tyyppinen atrribuutti hakemiston juurelle */
   private Hakemisto juuri;
   /** nykyinen hakemisto, Hakemistotyyppinen attribuutti */
   private Hakemisto kayttoHakemisto;
   /**
    * [Tulkin parametriton rakentaja. ]
    * juuresta uusi hakemisto, asetetaan juuri nykyiseen 
    * hakemistoon 
    */
   public Tulkki() {
      juuri = new Hakemisto();
      kayttoHakemisto = juuri;
   }
   /**
    * [juuri juuren getteri]
    * @return [juuri]
    */
   public Hakemisto juuri(){
      return juuri;
   }
   /**
    * [juuri juuren setteri]
    * @param juuri1 [hakemiston juuri]
    */
   public void juuri(Hakemisto juuri1){
      juuri = juuri1;
   }
   /**
    * [kayttoHakemisto nykyisen hakemiston getteri]
    * @return [nykyinen hakemisto]
    */
   public Hakemisto kayttoHakemisto(){
      return kayttoHakemisto;
   }
   /**
    * [kayttoHakemisto nykyisen hakemiston setteri]
    * @param kayttoHakemisto1 [nykyinen hakemisto]
    */
   public void kayttoHakemisto(Hakemisto kayttoHakemisto1){
      kayttoHakemisto = kayttoHakemisto1;
   }
   /**
    * [annaPolku]
    * annaPolku -metodi luo hakemistopolun
    * @param  kayttoHakemisto1 [nykyinen hakemisto]
    * @return polku = "/", jos ylihakemisto on "null".
    * Palautetaan hakemistopolku, jos ylihakemisto ei ole null.
    */
   public String annaPolku(Hakemisto kayttoHakemisto1) {
      // alustetaan polku ensin tyhjäksi
      String polku = "";
      // Jos nykyinen hakemisto on null, ei mennä silmukkaan vaan
      // palautetaan polkuna "/"
      if (kayttoHakemisto1.ylihakemisto() == null) {
         polku = "/";
         return polku;
      }
      else {
        // Jos nykyinen hakemisto ei ole null, voidaan mennä  silmukkaan
         while (kayttoHakemisto1 != null) {
            // poluksi tulee nykyisen hakemiston nimi + "/" ja polku
            polku = kayttoHakemisto1.nimi().toString() + "/" + polku;
            // asetetaan käyttöhakemistoon ylihakemisto
            kayttoHakemisto1 = kayttoHakemisto1.ylihakemisto();
         }
         // palautetaan polku
         return polku;
      } 
   }
   /**
    * [annaPolku -metodi]
    * @return [siirtyy parametrilliseen annaPolku-metodiin]
    * parametrina palautuksessa nykyinen käyttöhakemisto
    */
   public String annaPolku() {
      // Aloitetaan nykyhakemistosta.
      return annaPolku(kayttoHakemisto);
   }
   /**
    * [luoHakemisto luodaan uusi hakemisto]
    * @param  syote [käyttäjän antama syöte]
    * @return       [uusi hakemisto] jos käyttäjän antamalla
    * syöttellä ei löydy hakemistoa. Palautetaan false jos hakemisto löytyy jo.
    */
   public boolean luoHakemisto(String syote) {
      // Jos annetulla nimellä ei löydy hakemistoa, voidaan luoda uusi hakemisto
      if (kayttoHakemisto.hae(syote)== null) {
         // luodaan uusi hakemisto annetulla parametrilla ja nykyisen hakemiston koolla (null)
         Hakemisto uusiHakemisto = new Hakemisto(new StringBuilder(syote), kayttoHakemisto);
         // Palautetaan uusi hakemisto
         return kayttoHakemisto.lisaa(uusiHakemisto);
      }
      // Muutoin palautetaan false.
      else {
        return false;
      }
   }
   /**
    * [luoTiedosto luodaan uusi tiedosto]
    * @param  syote [käyttäjän antama syöte]
    * @param  koko  [käyttäjän antama koko]
    * @return       [uusi tiedosto] jos annetulla parametrilla ei löydy tiedostoa
    * nykyisestä hakemistosta ja annettu koko on suurempi tai
    * yhtäsuuri kuin 0
    * Palautetaan false jos tiedosto löytyy jo hakemistosta
    */
   public boolean luoTiedosto(String syote, int koko) {
      // Luodaan uusi tiedosto jos samalla nimellä ei löydy tiedostoa hakemistosta
      // ja koko on sallittu
      if (kayttoHakemisto.hae(syote)== null && koko >= 0) {
         // Luodaan uusi tiedosto annetuilla parametreilla
         Tiedosto uusiTiedosto = new Tiedosto(new StringBuilder(syote), koko);
         // lisätään uusi tiedosto hakemistoon ja palautetaan
         return kayttoHakemisto.lisaa(uusiTiedosto);
      }
      // Muutoin palautetaan false.
      else {
        return false;
      }
   }
   /**
    * [vaihdaHakemisto metodi vaihtaa hakemiston joko haluttuun hakemistoon tai
    * hakemiston alihakemistoon]
    * @param  syote [käyttäjän antama syöte]
    * @return       [true, jos vaihdettava hakemisto löytyy tai parametri on se on ".."
    * Palautetaan false, jos hakemistoa ei löydy]
    */
    public boolean vaihdaHakemisto(String syote) {
       // Tarkastetaan aluksi ettei syöte ole tyhjö tai ".."
       if (!(syote.equals("") || syote.equals(".."))) {
          // Jos nykyinen hakemisto ei ole tyhjä
          if (kayttoHakemisto.hae(syote)!= null) {
             kayttoHakemisto.hae(syote);
             // Asetetaan väliaikaiseen hakemistoon käyttäjän antama syöte
             Hakemisto temp = (Hakemisto)kayttoHakemisto.hae(syote);
             // siirretään nykyhakemistoon väliaikainen hakemisto
             kayttoHakemisto = temp;
             // Palautyetaan true
             return true;
          }
          // Muutoin palautetaan false
          else {
             return false;
          }
        }
        // Jos syöte on ".."..
        else if (syote.equals("..")) {
           // Tarkastetaan että ylihakemisto johon siirrytään löytyy
           if (kayttoHakemisto.ylihakemisto() != null) {
              // luodaan apuhakemisto johon siirretään ylihakemisto
              Hakemisto nykyinenHakemistonYlihakemisto = kayttoHakemisto.ylihakemisto();
              // Siirrytään nykyiseen hakemistoon jolla on ylihakemiston tiedot
              kayttoHakemisto = nykyinenHakemistonYlihakemisto;
              // palautetaan true
              return true;
           }
           // Muutoin palautetaan false
           else {
              return false;
           }   
        }
        // Muutoin palautetaan false
        else {
           return false;
        }
    }
    /**
     * [vaihdaHakemisto metodi vaihtaa hakemiston juureen
     * pelkällä cd-komennolla]
     */
    public void vaihdaHakemisto() {
       // siirrytään juureen.
       kayttoHakemisto = juuri();
    }
    /**
     * [listaaTiedot käyttäjän antamalla syötteellä]
     * @param  syote [käyttäjän antama syöte]
     * @return       [true jos syötteellä löytyy hakemistosta tietoa.
     * Palautetaan false jos annetulla syötteellä ei löydy mitään hakemistosta]
     */
   public boolean listaaTiedot(String syote) {
      // jos hakemistosta löytyy annetulla syötteellä tietoa
      if (kayttoHakemisto.hae(syote)!= null) {
         // Palautetaan true
         return true;
      }
      // Muutoin palautetaan false.
      else {
        return false;
      }
   }
   /**
    * [listaaRekursiivisesti metodi listaa hakemiston sisällön
    * rekursiivisesti esijärjestyksessä]
    * @param  hakemisto1 [nykyinen hakemisto]
    * @return            [palautetaan merkkijono jossa hakemiston tiedot ]
    */
    private String listaaRekursiivisesti(Hakemisto hakemisto1) {
       // Luodaan lista hakemiston sisällöllä
       OmaLista lista = (OmaLista)hakemisto1.sisalto();
       // Käydään hakemiston sisältö läpi yksi tieto kerrallaan.
       int i = 0;
       while (i < lista.koko()) {
          // Tulostetaan tiedon merkkijonoesitys.
          Tieto tieto = (Tieto)lista.alkio(i);
          System.out.println(annaPolku(hakemisto1) + tieto.toString());
          // Tulostetaan alihakemiston sisältö.
          if (tieto instanceof Hakemisto){
             listaaRekursiivisesti((Hakemisto)tieto);
          }
          i++;
       }
       // Palautetaan merkkijono
       String nimi ="";
       return nimi;          
    }
    /**
     * [listaaRekursiivisesti]
     * @return [palauttaa parametrilliseen listaaRekursiivisesti
     * -metodiin nykyisen hakemiston]
     */
    public String listaaRekursiivisesti() {
        // Aloitetaan nykyhakemistosta.
        return listaaRekursiivisesti(kayttoHakemisto);
     }
     /**
      * [poistaTiedosto poistaa tiedoston varmistamatta käyttäjältä]
      * @param  syote [käyttäjän antama syöte]
      * @return       [true, jos tieto löytyy. False jos tietoa ei löydy]
      */
   public boolean poistaTiedosto(String syote) {
      // Jos annetulla parametrilla lötyy hakemisto tai tiedosto..
      if (kayttoHakemisto.hae(syote)!= null) {
         // Voidaan se poistaa
         kayttoHakemisto.poista(syote);
         // Palautetaan true
         return true;
      }
      // Muutoin palautetaan false
      else {
         return false;
      }
      
   }
   /**
    * [kopioiUudeksiTiedostoksi kopioi tiedoston annetun nimiseksi uudeksi
    * tiedostoksi, jos nimellä löydetään tiedosto nykyhakemistosta ja
    * hakemistossa ei ole vielä uuden nimistä tiedostoa.]
    * @param  syote [käyttäjän antama syöte kopioitava tieto]
    * @param  kopio [uusi kopio]
    * @return       [uusi kopio, jos kopioitava tiedosto löytyy ja kopiota
    * ei löydy sekä kopioitava on tiedosto. Muutoin palautetaan false.]
    */
   public boolean kopioiUudeksiTiedostoksi(String syote, String kopio) {
      // Alustetaan tiedoston koko tyhjäksi
      int koko = 0;
      // Jos jos kopioitava tiedosto löytyy ja kopiota ei löydy sekä kopioitava on tiedosto..
      if (kayttoHakemisto.hae(syote)!= null && kayttoHakemisto.hae(kopio) == null &&
        kayttoHakemisto.hae(syote) instanceof Tiedosto) {
         // Luodaan uusi merkkijono, jonka tietoina annettu kopioitava
         String uusiString = kayttoHakemisto.hae(syote).toString();
         // Katkotaan se, jotta saadaan sen koko
         String[] katkottu = uusiString.split("[ ]");
         String a = katkottu[0];
         String b = katkottu[1];
         // Muutetaan integer-tyyppiseksi
         koko = Integer.parseInt(b);
         // Luodaan uusi tiedosto, jolle annetaan tiedoiksi kopion nimi sekä
         // kopioitavan koko
         Tiedosto uusiTiedosto = new Tiedosto(new StringBuilder(kopio), koko);
         // Palautetaan kopioitu tiedosto
         return kayttoHakemisto.lisaa(uusiTiedosto);
      }
      // Muutoin palauetaan false.
      else {
         return false;
      }
   }
   /**
    * [nimeaUudestaan metodi nimeää tiedoston uudella nimellä]
    * @param  syote [käyttäjän antama aiempi nimi]
    * @param  uusi  [käyttäjän antama uusi nimi]
    * @return       [palautetaan true, jos vanha tiedoston nimi löytyy ja
    * uutta tiedostoa ei löydy. Palautetaan false muutoin.]
    */
   public boolean nimeaUudestaan(String syote, String uusi) {
      // jos vanha tiedoston nimi löytyy ja uutta tiedostoa ei löydy...
      if (kayttoHakemisto.hae(syote)!= null && kayttoHakemisto.hae(uusi) == null) {

         // Muutetaan nimi.
         Tieto muutettava = kayttoHakemisto.hae(syote);
         muutettava.nimi(new StringBuilder(uusi));

         // Päivitetään tiedon paikka poistamalla ja lisäämällä.
         kayttoHakemisto.lisaa(kayttoHakemisto.poista(uusi));
         // Palautetaan tosi
         return true;
      }
      // Muutoin palauetaan false.
      else {
         return false;
      }
   }
    /**
     * [lopetaOhjelma lopettaa ohjelman]
     * @param  syote [käyttäjän antama syöte, eli "exit"]
     * @return       [true, jos syöte on "exit". Muutoin palautetaan false.]
     */
   public boolean lopetaOhjelma(String syote) {
      // Jos syöte on "exit", voidaan palauttaa true.
      if (syote.equals("exit")) {
         return true;
      }
      else {
         return false;
      }
   }
}
