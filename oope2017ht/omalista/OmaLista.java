// Kuuluu pakkaukseen omalista, joka sijaitsee oope2017ht-pakkauksen sisällä
package oope2017ht.omalista;
// tuodaan apulaiset- kansio, sekä valmiit listat
import apulaiset.*;
import fi.uta.csjola.oope.lista.*;

/**
  * OmaLista-luokka
  * <p>
  * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2017.
  * <p>
  * @author Sara Kumpulainen 
  * Informaatiotieteiden yksikkö, Tampereen yliopisto.
  * OmaLista -luokka perii LinkitettyLista -luokan ja toteuttaa rajapinnan Ooperoiva
  */
public class OmaLista extends LinkitettyLista implements Ooperoiva{
   /*
    * Metodit
    *
    */

   /**
    * [hae Metodi Tutkii onko listalla haettavaa oliota.
    * equals-mielessä vastaava alkio, joita oletetaan olevan korkeintaan yksi kappale.]
    * @param  haettava [Object -tyyppinen haettava tieto]
    * @return [palautetaan viite löydettyyn alkioon, jos haettava olio löytyy
    * ja lista ei ole tyhjä]
    */
   public Object hae(Object haettava) {
      // jos haettava olio ei ole null ja lista ei ole tyhjä..
      if (haettava != null && onkoTyhja() != true) {
         int j = 0;
         // Voidaan käydä listaa läpi ja..
         for(int i = 0; i < koko; i++){
            // verrata olioita. Jos löytyy vastaava..
            if (alkio(i).equals(haettava)) {
               // palautetaan viite löydettyyn alkioon.
               return alkio(i);
            }   
         }   
      }
      // Muutoin palautetaan null.
      else {
         return null;
      }
      return null;
   }

   /**
    * 
    */
   @SuppressWarnings({"unchecked"})
   /**
    * [lisaa Metodi:uusi alkio lisätään listalle siten, että alkio sijoittuu
    * kaikkien itseään pienempien tai yhtä suurien alkioiden
    * jälkeen ja ennen kaikkia itseään suurempia alkioita.]
    * @param  uusi [Object -tyyppinen uusi tieto]
    * @return      [palautetaan true, jos uusi tieto ei ole null-arvoinen ja
    * lisäämminen onnistuu. Muutoin palautetaan false.]
    */
   public boolean lisaa(Object uusi) {
      // Jos olio ei ole null-arvoinen..
      if (uusi != null) {
         // Kiinnitetään nykyinen solmu listan päähän.   
         Solmu nykyinenSolmu = paa();
         // laskuri paikalle
         int laskuri=0;
         // onko lisätty listalle, alustetaan falseksi. 
         boolean lisatty = false;
         // Niin kauan kunnes nykyinen solmu ei ole null ja ei ole lisätty
         while(nykyinenSolmu != null && !lisatty) {
            // Selvitetään suuruusjärjestys Comparable-rajapinnan compareTo-metodilla.
            // otetaab nykyinen solmu Comparable tyypiseksi
            Comparable vertailtava = (Comparable)nykyinenSolmu.alkio();
            // Verrataan olioita
            if (vertailtava.compareTo(uusi) > 0) {
               // Jos vertailtava on suurempi kuin uusi olio,
               // lisätään uusi listaan oikealle paikalle.
               lisaa(laskuri, uusi);
               // on lisätty
               lisatty = true;
            }
            // Jos vertailtava ei ole isompi, siirrytään seuraavaan solmuun.
            nykyinenSolmu = nykyinenSolmu.seuraava();
            // korotetaan (paikan) laskuria
            laskuri++;
         }
         // Jos ei vieläkään lisätty, lisätään uusi olio loppuun.
         if (!lisatty) {
            lisaaLoppuun(uusi);
         }
         // palautetaan tosi
         return true;
      }
      // Jos olio on null, palautetaan false.
      else
         return false;
   }
   // Metodi poistaa listalta annettua oliota equals-mielessä vastaavan alkion,
   // joita oletetaan olevan korkeintaan yksi kappale.
   /**
    * [poista Metodi poistaa listalta annettua oliota equals-mielessä vastaavan alkion,
    * joita oletetaan olevan korkeintaan yksi kappale.]
    * @param  poistettava [Object tyyppinen poistettava tieto]
    * @return [palautetaan poistettu tieto, jos lista ei ole
    * tyhjä ja poistaminen onnistuu]
    */
   public Object poista(Object poistettava) {
      // Jos lista ei ole tyhjä
      if (onkoTyhja() == false) {
         // Voidaan käydä se läpi ja..
         for (int i = 0; i < koko(); i++) {
            // vertailla alkion indeksiä parametrina saatuun poistettavaan
            // Jos vastaavat..
            if (alkio(i).equals(poistettava)) {
               // Sijoitetaan apuolioon ja hyödynnetään LinkitettyLista -luokan
               // poista -metodia.
               Object poistettu = poista(i);
               // palautetaan poistettu olio.
               return poistettu;
            }        
         }
         // Muutoin palautetaan null. 
         return null;
      }
      // Muutoin palautetaan null.
      else {
         return null;
      }
   }
}