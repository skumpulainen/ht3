// Tieto -luokka kuuluu pakkaukseen tiedot.
package oope2017ht.tiedot;

/**
  * Hakemisto-luokka
  * <p>
  * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2017.
  * <p>
  * @author Sara Kumpulainen 
  * Informaatiotieteiden yksikkö, Tampereen yliopisto.
  * Tieto -luokka toteuttaa rajapinnan
  * Kiinnitetään geneeriseksi tyypiksi "Tieto"
  */
public abstract class Tieto implements Comparable<Tieto> {
   // atrribuutit: Nimi ja onko sallittu merkki
   /**
    * StrinBuilder -tyyppinen nimi attribuutti
    */
   private StringBuilder nimi;
   /**
    * Boolean tyyppinen atrribuutti, tarkastetaan onko merkki sallittu.
    */
   private boolean oikeaMerkki;
   /**
    * [Tieto Parametrillinen rakentaja, jolla parametrina tiedoston nimi]
    * @param  uusiNimi                 [parametrina nimi]
    * @throws IllegalArgumentException [jos löytyy virheitä]
    */
   public Tieto (StringBuilder uusiNimi) throws IllegalArgumentException{
      // kutsutaan oikeaMerkki -operaatiota, nimi parametrina
      // tarkastaa onko nimessä sallittuja merkkejä
      boolean inputOK = oikeaMerkki(uusiNimi.toString());
      // Jos ei kiellettyjä merkkejä, kutsutaan asetusmetodia
      if (inputOK == true) {
         nimi(uusiNimi);
      }
      // muutoin tulostetaan virheilmoitus
      else {
         throw new IllegalArgumentException();
      }
      
   }
   /**
    * [Tieto parametriton rakentaja joka asettaa nimen StringBuilder-tyyppiseksi]
    */
   public Tieto(){   
      nimi = new StringBuilder();
   }
   /**
    * [oikeaMerkki Operaatio tarkastaa, onko tiedoston nimessä kiellettyjä merkkejä]
    * @param  uusiNimi [parametrina nimi]
    * @return [palautetaan true, jos kiellettyjä merkkejä ei löydy. Muutoin palautetaan false.]
    */
   public boolean oikeaMerkki(String uusiNimi) {
      // jos nimeä ei ole, palautetaan false.
      if (uusiNimi == null) {
         return false;
      }
      int laskuri = 0;
      // käydään läpi nimeä ja tarkastetaan merkki indeksistä
      for (int i = 0; i < uusiNimi.length(); i++) {
         // sijoitetaan indeksi apumuuttujaan
         char apu = uusiNimi.charAt(i);
         // Jos merkki ei ole kirjain, numero tai alaviiva...
         if ((!Character.isLetter(apu) && !Character.isDigit(apu)) && (apu != '_' )) {
            // Jos merkki on piste ja nimi ei ole yhden merkin pituinen..
            if (apu == '.' && uusiNimi.length() != 1) {
               // voidaan laskea piste, sillä niitä saa olla vain yksi nimessä
               laskuri++;
               // jos vastaan tulee toinen piste, palautetaan false,
               if (laskuri > 1) {
                  return false;
               }
            }
            // palautetaan false, jos nimi on yhden merkin pituinen
            else {
               return false;
            }
         }
      }
      // Jos takastus menee läpi, palautetaan tosi rakentajaan.
      return true;    
   }
   /**
    * [Tieto kopiorakentaja Tiedolle]
    * @param  e [parametrina Tieto -tyyppinen parametri]
    */
   public Tieto(Tieto e) {
      nimi = new StringBuilder(e.nimi());
   }
   /*
   Aksessorit 

   */
   /**
    * [nimi nimen setteri]
    * @param  uusiNimi                 [parametrina nimi]
    * @throws IllegalArgumentException [jos löytyy virheitä]
    */
   public void nimi(StringBuilder uusiNimi) throws IllegalArgumentException {
       nimi = uusiNimi;
   }
   /**
    * [nimi nimen getteri]
    * @return [palauttaa nimen]
    */
   public StringBuilder nimi() {
      return nimi;
   }
   /**
    * [toString Muuttaa nimen String-tyyppiseksi]
    * @return [palauttaa nimen String -tyyppisenä]
    */
   @Override 
   public String toString() {
      return nimi.toString();
   }
   /**
    * [equals    metodi vertaa luokan nimeä parametrina saatuun nimeen.
    * Jos tapahtuu virhe, palautetaan false. ]
    * @param  olio [Object -tyyppinen olio]
    * @return      [paluuarvona true jos tiedot vastaavat toisiaan. Muutoin false.]
    */
   @Override
   public boolean equals(Object olio) {
     try {
        // Apuoliot String-tyyppisenä
        String apu = nimi.toString();
        // Tyyppimuunnos oliolle
        String apu1 = ((Tieto)olio).nimi().toString();
        // palautetaan tulos ja verrataan
        return apu.equals(apu1);
       
     }
     // Napataan virheet
     catch (Exception e){
        return false;
     }      
   }
   /**
    * [compareTo Metodi vertaa olioden(nimien) järjestystä]
    * @param  t [parametrina Tieto -tyyppinen olio]
    * @return   [palautetaan -1, jos apu on pienempi kuin apu1. Palautetaan 0 jos apu on yhtäsuuri kuin apu1.
    * Palautetaan 1, jos apu on suurempi kuin apu1 ]
    */
   @Override
   public int compareTo(Tieto t) {
      // Apuoliot vertailuun String tyyppisenä
      String apu = nimi.toString();
      String apu1 = t.nimi.toString();
      // Palautetaan tulos ja verrataan
      return apu.compareTo(apu1);
   }
}

