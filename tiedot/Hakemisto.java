// Kuuluu pakkaukseen tiedot, joka on oope2017ht-pakkauksen sisällä
package oope2017ht.tiedot;

// tuodaan OmaLista-luokka sekä apulaiset- kansio, sekä valmiit listat
import fi.uta.csjola.oope.lista.*;
import apulaiset.*;
import oope2017ht.omalista.OmaLista;

/**
  * Hakemisto-luokka, joka toteuttaa rajapinnan Komennettava.
  * Geneerisenä tyyppinä Tieto. Perii luokan Tieto.
  * <p>
  * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2017.
  * <p>
  * @author Sara Kumpulainen 
  * Informaatiotieteiden yksikkö, Tampereen yliopisto.
  * 
  */

public class Hakemisto extends Tieto implements Komennettava<Tieto>  {
   // Atribuutit
   /**
    * OmaLista -tyyppinen attribuutti, joka viittaa hakemiston sisältämiin tiedostoihin
    */
   private OmaLista lista;
   /**
    * Hakemisto -tyyppinen attribuutti, joka viittaa hakemiston ylihakemistoon 
    */
   private Hakemisto ylihakemisto;

   /**
    * [Hakemisto Parametrillinen rakentaja, jonka parametrina nimi sekä Hakemisto tyyppinen alihakemisto]
    * @param  uusiNimi                 [parametrina nimi]
    * @param  alihakemisto             [parametrina alihakemisto]
    * @throws IllegalArgumentException [jos löytyy virheitä]
    */
   public Hakemisto (StringBuilder uusiNimi, Hakemisto alihakemisto) throws IllegalArgumentException{
      // kutsutaan yliluokkaa
      super(uusiNimi);
      // kutsutaan oikeaMerkki -operaatiota, nimi parametrina
      // tarkastaa onko nimessä sallittuja merkkejä
      boolean inputOK = oikeaMerkki(uusiNimi.toString());
      // Jos ei kiellettyjä merkkejä, kutsutaan aksessoria
      if (inputOK == true) {
         lista = new OmaLista();
         ylihakemisto = alihakemisto;
      }
      // Muutoin heitetään poikkeus.
      else {
         throw new IllegalArgumentException();
      }
   }
   /**
    * [Hakemisto hakemiston parametriton rakentaja. Asettaa ylihakemiston
    * null-arvoiseksi ja luo uuden OmaLista -tyyppisen listan]
    */
   public Hakemisto(){
      
     lista = new OmaLista();
     ylihakemisto = null;
   }
   
   /**
    * [oikeaMerkki Operaatio tarkastaa, onko tiedoston nimessä kiellettyjä merkkejä]
    * @param  uusiNimi [parametrina nimi]
    * @return [palautetaan true, jos kiellettyjä merkkejä ei löydy. Muutoin palautetaan false. ]
    */
   public boolean oikeaMerkki(String uusiNimi ) {
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
   /*
   
   Aksessorit

   */
  
   /**
    * [lista getteri listalle]
    * @return [lista]
    */
   public OmaLista lista() {
      return lista;
   }
   /**
    * [lista setteri listalle]
    * @param lista1 [Omalista -tyyppinen lista]
    */
   public void lista (OmaLista lista1) {
      lista = lista1;
   }
   /**
    * [ylihakemisto ylihakemiston getteri]
    * @return [paluuarvona ylihakemisto]
    */
   public Hakemisto ylihakemisto() {
      return ylihakemisto;
   }
   /**
    * [ylihakemisto setteri ylihakemistolle]
    * @param alihakemisto [Hakemisto -tyyppinen alihakemisto]
    */
   public void ylihakemisto (Hakemisto alihakemisto) {
      ylihakemisto = alihakemisto;
   }

   /**
    * [hae Hakee hakemistosta tiedostoa tai alihakemistoa.
    * Hyödyntää OmaLista-luokan hae-operaatiota.]
    * @param  uusiNimi [parametrina nimi]
    * @return [palauttaa null, jos nimeä ei löydy. Palauttaa muutoin viitteen löydettyyn olioon.]
    */
   @Override
   public Tieto hae(String uusiNimi) {
      // Jos nimi null, palautetaan null.
      if (uusiNimi == null) {
         return null;
      }
      // Muutoin luodaan Hakemisto-olio, nimen hakua varten
      // Parametrina nimi StringBuilder -tyyppinen nimi
      Hakemisto apu = new Hakemisto(new StringBuilder(uusiNimi), null);
      // palautetaan viite löydettyyn olioon
      return (Tieto)lista.hae(apu);
   }

   /**
    * [sisalto antaa viitteen hakemiston sisällön säilövään listaan.]
    * @return [paluuarvona lista]
    */
   @Override
   public LinkitettyLista sisalto() {
      // palautetaan viite hakemisto-olion osaolioon
      return lista;
   }

   /**
    * [lisaa Lisää hakemistoon tiedoston tai alihakemiston.
    * Hyödyntää OmaLista-luokan lisaa-operaatiota.]
    * @param  lisattava [Tieto -tyyppinen lisättävä tieto]
    * @return [palautetaan lisättävä tieto jos sitä ei jo löydy. Muutoin palautetaan false.]
    */
   @Override
   public boolean lisaa(Tieto lisattava) {
      // Jos lisättävä tieto on null
      if (lista.hae(lisattava)== null) {
         // voidaan lisätä tiedosto
         return lista.lisaa(lisattava);  
     }
     // muutoin palautetaan false;
     else {
        return false;
     }   
   }

   /**
    * [poista Poistaa hakemistosta tiedoston tai alihakemiston.
    * Hyödyntää OmaLista-luokan poista-operaatiota.]
    * @param  poistettava [parametrina String tyyppinen poistettava tieto]
    * @return [palautetaan null, jos poistettava on jo tyhjä.
    * Muutoin palautetaan viite poistettuun tietoon.]
    */
   @Override
   public Tieto poista(String poistettava) {
      // Jos poistettavaa ei ole, palautetaan null
      if (poistettava == null) {
         return null;
      }
      // Muutoin luodaan Hakemisto-olio nimen poistamisen avuksi. 
      Hakemisto apu = new Hakemisto(new StringBuilder(poistettava), null);
      // Palautetaan poistettava nimi.     
      return (Tieto)lista.poista(apu);     
   }
   /**
    * [toString metodi palauttaa yliluokasta nimen ja tästä luokasta listan koon]
    * @return [palautetaan nimi yliluokasta sekä listan koko]
    */
   @Override
   public String toString() {
      return super.toString() + "/ " + lista.koko();
   }  
}