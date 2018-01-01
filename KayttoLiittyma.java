// Kuuluu pakkaukseen oope2017ht
package oope2017ht;

// Tuodaan apulaiset-kansio, Tieto-luokka, omalista-kansio, sekä Hakemisto-luokka
import apulaiset.*;
import oope2017ht.tiedot.Tieto;
import oope2017ht.omalista.*;
import oope2017ht.tiedot.Hakemisto;

/**
  * Käyttöliittymä-luokka, jossa luetaan käyttäjän syötteet, sekä
  * tehdään suurin osa tulostuksista
  * <p>
  * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2017.
  * <p>
  * @author Sara Kumpulainen 
  * Informaatiotieteiden yksikkö, Tampereen yliopisto.
  */
 
public class KayttoLiittyma {  
   // Tulkki -tyyppinen attribuutti komentotulkille
   public Tulkki tulkki;
   /**
    * Luodaan komennoista vakiot. 
    */
   /**
    * Välilyönti
    */
   public static final String EROTIN = " ";
   /**
    * Luodaan hakemisto
    */
   public static final String LUOALIHAKEMISTO = "md";
   /**
    * Luodaan tiedosto
    */
   public static final String LUOTIEDOSTO = "mf";
   /**
    * Vaihdetaan hakemisto
    */
   public static final String VAIHDAHAKEMISTO = "cd";
   /**
    * Listataan hakemiston tiedot
    */
   public static final String LISTAATIEDOT = "ls";
   /**
    * Listataan hakemisto rekursiivisesti
    */
   public static final String LISTAAREKURSIIVISESTI = "find";
   /**
    * Poistetaan tiedosto
    */
   public static final String POISTATIEDOSTO = "rm";
   /**
    * Kopioidaan tiedosto
    */
   public static final String KOPIOIUUDEKSITIEDOSTOKSI = "cp";
   /**
    * Nimetään tiedosto uudelleen
    */
   public static final String NIMEAUUDESTAAN = "mv";
   /**
    * Lopetetaan ohjelma
    */
   public static final String LOPETAOHJELMA = "exit";
   /**
    * Virheilmoitus
    */
   public static final String VIRHE = "Error!";
   /**
    * ohjelman lopetus
    */
   public static final String SULJETAAN = "Shell terminated.";
    /**
     * [KayttoLiittyma parametriton rakentaja.
     * luodaan uusi tulkki, jotta käyttöliittymä- ja
     * tulkki-luokat voivat toimia yhdessä. Kutsutaan
     * tulkki-luokan kayttoHakemisto getteriä]
     */
   public KayttoLiittyma(){
      tulkki = new Tulkki();
      tulkki.kayttoHakemisto();    
    }
    /**
     * [tulosta tulostetaan hakemisto OmaLista -muodossa.]
     * @param lista [OmaLista- tyyppinen lista(hakemistojen sisältö)]
     */
   public static void tulosta(OmaLista lista) {
      // Jos lista ei ole tyhjä
      if (lista != null) {
        // Käydään läpi koko lista ja tulostetaan listan sisältö
         for (int i = 0; i < lista.koko(); i++) {
            System.out.print(lista.alkio(i));
            if (i < lista.koko())
               System.out.println();           
         }
      }
   }
   /**
    * [tulkitseKomennot Operaatio tulkitsee käyttäjän komennot ja siirtyy
    * niiden perusteella haluttuun tulkki- luokan metodiin.]
    * @throws IllegalArgumentException [heittää poikkeuksen jos napataan virheitä]
    */
   public void tulkitseKomennot() throws IllegalArgumentException{

      String tiedosto = "";
      // Muuttuja käyttäjän antamalle syötteelle    
      String syote;
      // Alustetaan koko tyhjäksi
      int koko = 0;
      // Lippu sille, jatketaanko käyttäjältä syötteiden kyselyä.
      // Alustetaan trueksi.
      boolean jatketaan = true;
      // Kysellään niin kauan kunnes ohjelma halutaan lopettaa.
      do {
         // Yritetään napata virheet  
         try { 
            // Tulostetaan hakemistopolku
            System.out.print(tulkki.annaPolku());

            System.out.print(">");
            // Luetaan käyttäjän syöte
            syote = In.readString();
            // Katkotaan syöte
            String[] katkottu = syote.split("[ ]");            
            /**
             * Jos syöte on "md", luodaan uusi hakemisto
             */
            if (syote.startsWith(LUOALIHAKEMISTO+EROTIN)) {
               // Jos katkottu syöte on pituudeltaan 2 
               if (katkottu.length == 2) {
                  // Sijoitetaan osat muuttujiin
                  String a = katkottu[0];
                  String b = katkottu[1];
  
                  int i = 0;
                  int valit = 0;
                  // Tarkastetaan ettei ole ylimääräisiä välilyöntejä
                  while(i < syote.length()){
                     char temp = syote.charAt(i);         
                     if(syote.charAt(i) ==' '){
                        valit++;
                     }
                     i++;
                  }   
                  // jos ylimääräisiä löytyy, tulostetaan virheilmoitus 
                  if (valit >1) {
                     System.out.println(VIRHE);
                  }
                  else {
                     // Muutoin siirrytään komentotulkin metodiin parametrilla
                     boolean inputOk = tulkki.luoHakemisto(b);
                     // Jos palautuu falsena, tulostetaan virheilmoitus
                     if (inputOk==false) {
                        System.out.println(VIRHE);
                     }
                  }
               }
               // Jos pituus ei ole sallittu, tulostetaan virheilmoitus
               else {
                  System.out.println(VIRHE);
               }
            }
            /**
             * [if jos käyttäjän syöte alkaa "mf" komennolla,
             * voidaan siirtyä haluttuun tulkki-luokan metodiin. Metodi luo uuden tiedoston
             * käyttäjän antamalla parametrilla.]
             * @param  syote.startsWith(LUOTIEDOSTO+EROTIN)
             * [parametrina "mf" ja välilyönti]
             * @return [paluuarvona saadaan uusi tiedosto tosina,
             * jos sen luonti onnistuu. falsena, jos luontin epäonnistuu.]
             */
            else if (syote.startsWith(LUOTIEDOSTO+EROTIN)){
               // Tarkastetaan katkotun syötteen sallittu pituus ja sijoitetaan
               // osat muuttujiin.
               if (katkottu.length == 3) {
                  String b = katkottu[1];
                  String c = katkottu[2];
                  // Saadaan koko integer-muodossa
                  koko = Integer.parseInt(c);
                  // Kutsutaan tulkki-luokan metodia
                  boolean inputOk =  tulkki.luoTiedosto(b, koko);
                  // Jos palautuu falsena, tulostetaan virheilmoitus
                  if (inputOk == false) {
                     System.out.println(VIRHE);
                   }
               }
               // Jos koko ei sallittu, tulostetaan virheilmoitus
               else {
                  System.out.println(VIRHE);
               }           
            }
            /**
             * [if if jos käyttäjän syöte alkaa "cd" komennolla ja välilyönnillä
             * voidaan siirtyä haluttuun tulkki-luokan metodiin. Metodi siirtyy haluttuun hakemistoon]
             * @param  syote.startsWith(VAIHDAHAKEMISTO+EROTIN)
             * [parametrina "cd" ja välilyönti]
             * @return [paluuarvona haluttu hakemisto jos siirtyminen onnistuu.
             * Muutoin false.]
             */
            else if (syote.startsWith(VAIHDAHAKEMISTO+EROTIN)){
               // Tarkastetaan katkotun syötteen sallittu pituus ja 
               // sijoitetaan loppuosa muuttujaan. 
               if (katkottu.length == 2) {
                  String b = katkottu[1];
                  // Kutsutaan Tulkki-luokan metodia
                  boolean inputOk = tulkki.vaihdaHakemisto(b);
                  // Jos palautuu falsena, tulostetaan virheilmoitus
                  if (inputOk == false) {
                     System.out.println(VIRHE);
                  }
               }
               // Jos koko ei sallittu, tulostetaan virheilmoitus
               else {
                 System.out.println(VIRHE);
               } 
          
            }
            /**
             * [if jos käyttäjän syöte alkaa "cd" komennolla,
             * voidaan siirtyä haluttuun tulkki-luokan metodiin. Metodi palauttaa
             * käyttäjän hakemiston juureen.]
             * @param  syote.startsWith(VAIHDAHAKEMISTO) [parametrina "cd"]
            * @return [paluuarvona hakemiston juuri]
             */
            else if (syote.startsWith(VAIHDAHAKEMISTO)){
               // Kutsutaan tulkki-luokan metodia
               tulkki.vaihdaHakemisto();

            }
            /**
             * [if jos käyttäjän parametrit "ls" ja välilyönti, voidaan kutsua tulkki-luokan
             * metodia joka listaa hakemiston tiedot näytölle.]
             * @param  syote.startsWith(LISTAATIEDOT+EROTIN) [parametrina "ls" ja välilyönti]
             * @return [paluuarvona true, jos parametrilla löytyy tietoa hakemistosta. Muutoin
             * false ]
             */
            else if (syote.startsWith(LISTAATIEDOT+EROTIN)){
               // Tarkastetaan syötteen sallittu pituus ja sijoitetaan muuttujaan loppuosa
               if (katkottu.length == 2) {
                  String b = katkottu[1]; 
                  // Kutsutaan tulkki-luokan metodia
                  boolean inputOk = tulkki.listaaTiedot(b);
                  // jos palautuu tosina, voidaan tulostaa haettu tieto
                  if (inputOk == true) {
                     System.out.println(tulkki.kayttoHakemisto().hae(b));
                  }
                  // Muutoin tulostetaan virheilmoitus
                  else {
                     System.out.println(VIRHE);
                  }
               }
               // Jos koko ei sallittu, tuilostetaan virheilmoitus
               else {
                  System.out.println(VIRHE);
               }  
            }
            /**
             * [if jos käyttäjän parametri "ls", tulostetaan hakemiston sisältö näytölle.]
             * @param  syote.equals(LISTAATIEDOT) [parametrina "ls"]
             * @return [paluuarvona käyttöhakemiston tiedoista, onko se tyhjä kutsuttaessa
             * nykyisen hakemiston getteriä]
             */
            else if (syote.equals(LISTAATIEDOT)){
               // Jos nykyinen hakemisto ei ole tyhjä..
               if (tulkki.kayttoHakemisto() != null) {
                  // Luodaan OmaLista -tyyppinen lista hakemiston sisällöstä.
                  OmaLista lista = (OmaLista)tulkki.kayttoHakemisto().sisalto();
                  // Tulostetaan lista
                  tulosta(lista);
               }
               // Muutoin tulostetaan virheilmoitus
               else {
                  System.out.println(VIRHE);
               }               

            }
            /**
             * [if jos käyttäjän parametri "find", kutsutaan tulkki-luokan metodia 
             * joka listaa hakemiston sisällön rekursiivisesti esijärjestyksessä]
             * @param  syote.equals(LISTAAREKURSIIVISESTI) [parametrina "find"]
             * @return [paluuarvona merkkijono]
             */
            else if (syote.equals(LISTAAREKURSIIVISESTI)){
               // Kutsutaan tulkki-luokan parametritonta listaaRekursiivisesti -metodia
               tulkki.listaaRekursiivisesti();
            }
            /**
             * [if jos käyttäjän parametrina "rm" ja välilyönti, kutsutaan tulkkiluokan
             * metodia, joka poistaa tiedoston varmistamatta käyttäjältä]
             * @param  syote.startsWith(POISTATIEDOSTO+EROTIN) [parametrina "rm" ja välilyönti]
             * @return [palauttaa true, jos tiedoston poisto onnistuu. Muuten paluuarvo false.]
             */
            else if (syote.startsWith(POISTATIEDOSTO+EROTIN)){
               // Tarkastetaan syötteen sallittu pituus ja sijoitetaan loppuosa muuttujaan
               if (katkottu.length == 2) {
                  String b = katkottu[1];
                  // Kutsutaan tulkki-luokan metodia
                  boolean inputOk = tulkki.poistaTiedosto(b);
                  // jos palautuu falsena, tulostetaan virheilmoitus
                  if (inputOk == false) {
                     System.out.println(VIRHE);
                  }
               }
               // jos koko ei sallittu, tulostetaan virheilmoitus
               else {
                 System.out.println(VIRHE);
               }        
            }
            /**
             * [if käyttäjän parametrina "cp" ja välilyönti, voidaan siirtyä tulkki-luokan
             * metodiin joka kopioi halutun tiedoston]
             * @param  syote.startsWith(KOPIOIUUDEKSITIEDOSTOKSI+EROTIN) [parametrina "cp" ja välilyönti]
             * @return [paluuarvona true, jos kopiointi onnistuu. Muutoin paluuarvo false.]
             */
            else if (syote.startsWith(KOPIOIUUDEKSITIEDOSTOKSI+EROTIN)){
               // Tarkastetaan sallittu pituus syötteelle ja sijoitetaan parametrit muuttujiin 
               if (katkottu.length == 3){
                  String b = katkottu[1];
                  String c = katkottu[2];
                  // Kutsutaan tulkki-luokan metodia
                  boolean inputOk = tulkki.kopioiUudeksiTiedostoksi(b, c);
                  // jos palautuu falsena, tulostetaan virheilmoitus
                  if (inputOk == false) {
                     System.out.println(VIRHE);
                  }
               }
               // Jos koko ei sallittu, tulostetaan virheilmoitus.
               else {
                 System.out.println(VIRHE);
               }
            }
            /**
             * [if jos käyttäjän syöte "mv" ja välilyönti, voidaan siirtyä tulkki-luokan
             * metodiin joka nimeää halutun tiedoston uudella nimellä ]
             * @param  syote.startsWith(NIMEAUUDESTAAN+EROTIN) [parametrina "mv" ja välilyönti]
             * @return [paluuarvona true, jos tiedoston uudelleennimeäminen onnistuu. Muutoin
             * paluuarvona false.]
             */
            else if (syote.startsWith(NIMEAUUDESTAAN+EROTIN)){
               // Tarkastetaan sallittu pituus syötteelle ja sijoitetaan parametrit muuttujiin 
               if (katkottu.length == 3){
                  String b = katkottu[1];
                  String c = katkottu[2];
                  // Tarkastetaan että komennossa muistetaan antaa uusi nimi
                  if (!(c.equals(""))) {
                     // kutsutaan tulkki-luokan metodia
                     boolean inputOk = tulkki.nimeaUudestaan(b, c);
                     // Jos palautuu falsena, tulostetaan virheilmoitus
                     if (inputOk == false) {
                        System.out.println(VIRHE);
                     }
                  }
                  // tulostetaan virheilmoitus jos ei anneta parametria uudelleen nimettäväksi
                  // tiedostoksi
                  else {
                     System.out.println(VIRHE);
                  }
               }
               // Jos koko ei sallittu, tulostetaan virheilmoitus
               else {
                 System.out.println(VIRHE);
               }
            }
            /**
             * [if jos käyttäjän syöte "exit", kutsutaan tulkki-luokan metodia lopetaOhjelma.]
             * @param  syote.startsWith(LOPETAOHJELMA) [parametrina "exit"]
             * @return [paluuarvona true, jos käyttäjän syöte "exit". Muutoin paluuarvo false.]
             */
            else if (syote.startsWith(LOPETAOHJELMA)) {
               // Kutsutaan tulkki-luokan metodia
               boolean inputOk = tulkki.lopetaOhjelma(syote);
               // jos syöte oli "exit", jatketaan muuttuu falseksi ja tulostetaan "jäähyväisviesti."
               // Ohjelma lopetetaan.
               if (inputOk == true) {
                  jatketaan = false;
                  System.out.println(SULJETAAN);
               }
            }
            // Jos syöte ei mikään edellämainituista vakioista, tulostetaan virheilmoitus
            else {
               System.out.println(VIRHE);
            }

         }
         // Napataan virheet
         catch (IllegalArgumentException e) {
            System.out.println(VIRHE);
         }
              
      }
      // Käyttäjältä kysytään syötteitä niin kauan kuin jatketaan on true-arvoinen
      while(jatketaan);   
   }   
}