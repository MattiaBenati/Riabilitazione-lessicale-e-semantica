package parola;

import metodi.Caratteri;
import metodi.Costanti;

import java.io.File;

public class ParolaBuilder
{
    private File indirizzo;
    private File immagine;
    private String parola;
    private boolean vocali;
    private String simboli;
    private int difficolta;
    private String lettere;
    
    public Parola build()
    {
        return new Parola(indirizzo, parola, vocali, simboli, difficolta, lettere);
    }
    
    /**
     * setter dell'indirizzo/immagine
     *
     * @param indirizzo
     */
    public ParolaBuilder setIndirizzo(File indirizzo)
    {
        this.indirizzo = indirizzo;
        this.immagine = indirizzo;
        return this;
    }
    
    /**
     * setter della parola
     */
    public ParolaBuilder setParola()
    {
        // eliminazione indirizzo e formato immagine
        StringBuilder string = Caratteri.eliminazioneIndirizzoFormato(indirizzo);
        
        // eliminazione caratteri NON ammessi (simboli)
        Caratteri.eliminazioneCaratteri(string);
        
        // conversione in lettere SOLO minuscole
        Caratteri.conversioneMinuscole(string);
        
        // salvataggio del risultato
        parola = string.toString();
        
        return this;
    }
    
    /**
     * setter dei simboli ('+' e '-')
     */
    public ParolaBuilder setSimboli()
    {
        StringBuilder string = new StringBuilder();
        
        // impostazione SOLO SIMBOLI '-'
        if (!vocali)
        {
            // scorrimento lettere della parola
            for (int i = 0; i < parola.length(); ++i)
            {
                // verifica formato spazio
                if (parola.charAt(i) == Costanti.SPAZIO)
                    string.append(Costanti.SPAZIO);
                    
                    // verifica formato lettera
                else if ((parola.charAt(i) >= 'A' && parola.charAt(i) <= 'Z') || (parola.charAt(i) >= 'a' && parola.charAt(i) <= 'z'))
                    string.append('-');
                    
                    // verifica formato lettera accentata
                else if (parola.charAt(i) == 'à' || parola.charAt(i) == 'è' || parola.charAt(i) == 'é' || parola.charAt(i) == 'ì' || parola.charAt(i) == 'ò' || parola.charAt(i) == 'ù')
                    string.append('-');
            }
        }
        
        // impostazione SIMBOLI '+' E '-'
        else
        {
            // scorrimento lettere della parola
            for (int i = 0; i < parola.length(); ++i)
            {
                boolean check = false;
                
                // verifica formato spazio
                if (parola.charAt(i) == Costanti.SPAZIO)
                {
                    string.append(Costanti.SPAZIO);
                    continue;
                }
                
                // verifica formato lettera MAIUSCOLA/minuscola
                for (int j = 0; j < Costanti.CARATTERI.length; ++j)
                {
                    if (parola.charAt(i) == Costanti.CARATTERI[j])
                    {
                        string.append('+');
                        check = true;
                        break;
                    }
                }
                
                if (check)
                    continue;
                
                // verifica formato lettera accentata
                for (int j = 0; j < Costanti.ACCENTI.length; ++j)
                {
                    if (parola.charAt(i) == Costanti.ACCENTI[j])
                    {
                        string.append('+');
                        check = true;
                        break;
                    }
                }
                
                if (check)
                    continue;
                
                // verifica formato lettera consonante
                if ((parola.charAt(i) >= 'A' && parola.charAt(i) <= 'Z') || (parola.charAt(i) >= 'a' && parola.charAt(i) <= 'z'))
                    string.append('-');
            }
        }
        
        simboli = string.toString();
        
        return this;
    }
    
    /**
     * setter delle vocali
     *
     * @param vocali
     */
    public ParolaBuilder setVocali(boolean vocali)
    {
        this.vocali = vocali;
        return this;
    }
    
    /**
     * setter della difficoltà (100%, 75%, 50%)
     *
     * @param difficolta
     */
    public ParolaBuilder setDifficolta(int difficolta)
    {
        // percentuale = (valore * 100 / totale)
        // valore = (percentuale * totale) / 100
        
        // percentuale = difficolta (100% = difficile, 75% = medio, 50% = facile)
        // valore = numero lettere iniziali da mostrare
        // totale = numero lettere totali della parola
        
        difficolta = 100 - difficolta;
        this.difficolta = ((difficolta * parola.length()) / 100);
        return this;
    }
    
    /**
     * setter delle lettere (caratteri spazio vuoto)
     */
    public ParolaBuilder setLettere()
    {
        // inizializzazione con spazi
        lettere = " ".repeat(parola.length());
        
        // aggiunta delle lettere in base alla difficoltà
        for (int j = 0; j < this.difficolta; ++j)
        {
            Object[] risultato = Caratteri.aggiornamentoLettere("", lettere, parola);
            lettere = (String) risultato[1];
        }
        
        return this;
    }
}