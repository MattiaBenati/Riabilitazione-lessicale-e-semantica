package metodi;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ThreadLocalRandom;

public final class Caratteri
{
    /**
     * eliminazione indirizzo antecedente alla parola e il suo formato
     *
     * @param indirizzo
     * @return ritorna la parola vera e propria eliminando l'indirizzo
     * antecedente a questa e il suo formato (es: .jpg, .png)
     */
    public static StringBuilder eliminazioneIndirizzoFormato(File indirizzo)
    {
        // eliminazione indirizzo antecedente alla parola
        Path path = Paths.get(indirizzo.toString());
        Path fileName = path.getFileName();
        
        // eliminazione formato dell'immagine
        StringBuilder string = new StringBuilder(fileName.toString());
        
        for (int j = 0; j < string.length(); ++j)
        {
            // eliminazione nome formato immagine
            if (string.charAt(j) == '.')
            {
                string.delete(j, string.length());
                break;
            }
        }
        
        return string;
    }
    
    /**
     * eliminazione di caratteri NON ammessi nella parola (simboli)
     *
     * @param string
     */
    public static void eliminazioneCaratteri(StringBuilder string)
    {
        for (int i = 0; i < string.length(); ++i)
        {
            boolean check = false;
            
            // verifica presenza carattere ammesso
            if ((string.charAt(i) >= 65 && string.charAt(i) <= 90) || (string.charAt(i) >= 97 && string.charAt(i) <= 122))
                check = true;
            
            else if (string.charAt(i) == Costanti.SPAZIO)
                check = true;
            
            else
            {
                for (char c : Costanti.ACCENTI)
                {
                    if (string.charAt(i) == c)
                    {
                        check = true;
                        break;
                    }
                }
            }
            
            // eliminazione carattere NON ammesso
            if (!check)
                string.delete(i, i + 1);
        }
    }
    
    /**
     * conversione in caratteri SOLO minuscoli
     *
     * @param string
     */
    public static void conversioneMinuscole(StringBuilder string)
    {
        for (int j = 0; j < string.length(); ++j)
        {
            if (string.charAt(j) >= 65 && string.charAt(j) <= 90)
                string.replace(j, j + 1, Character.toString(string.charAt(j) + 32));
        }
    }
    
    // il PRIMO VALORE DI RITORNO corrisponde a "parola indovinata" (1), "parola già indovinata" (0), "parola non ancora indovinata" (-1)
    // il SECONDO VALORE DI RITORNO corrisponde alle nuove "lettere" generate
    
    /**
     * aggiornamento delle lettere mostrate
     *
     * @param input
     * @param lettere
     * @param parola
     * @return
     * il PRIMO valore di ritorno corrisponde a "parola indovinata" (1), "ultimo carattere aggiunto" (2), "parola già indovinata" (0), "parola non ancora indovinata" (-1);
     * il SECONDO valore di ritorno corrisponde alle nuove "lettere" generate
     */
    public static Object[] aggiornamentoLettere(String input, String lettere, String parola)
    {
        // parola indovinata (true = parola APPENA indovinata e qui vai avanti)
        if (input.equals(parola))
        {
            lettere = parola;
            return new Object[]{1, lettere};
        }
        
        // parola già indovinata e quindi niente modifica (false = NON andare avanti anche se indovinata)
        if (lettere.equals(parola))
            return new Object[]{0, lettere};
        
        // parola NON ancora indovinata
        while (true)
        {
            int min_val = 0;
            int max_val = parola.length();
            
            // generazione numero casuale
            ThreadLocalRandom random = ThreadLocalRandom.current();
            int random_num = random.nextInt(min_val, max_val);
            
            // verifica posizione libera
            if (lettere.charAt(random_num) == ' ' && parola.charAt(random_num) != ' ')
            {
                lettere = lettere.substring(0, random_num) + parola.charAt(random_num) + lettere.substring(random_num + 1);
                break;
            }
        }
        
        // ultimo carattere aggiunto e quindi parola indovinata
        if (lettere.equals(parola))
            return new Object[]{2, lettere};
        
        return new Object[]{-1, lettere};
    }
    
    /**
     * setter dei simboli '+' e '-'
     *
     * @param parola
     * @param vocali
     * @return
     * '+' e '-' se vocali è true;
     * solo '-' se vocali è false
     */
    public static String setSimboli(String parola, boolean vocali)
    {
        StringBuilder string = new StringBuilder();
        
        // impostazione SOLO SIMBOLI -
        if (!vocali)
        {
            // scorrimento delle lettere della parola analizzata
            for (int i = 0; i < parola.length(); ++i)
            {
                // verifica formato spazio
                if (parola.charAt(i) == Costanti.SPAZIO)
                    string.append(Costanti.SPAZIO);
                    
                    // verifica formato lettera
                else if ((parola.charAt(i) >= 65 && parola.charAt(i) <= 90) || (parola.charAt(i) >= 97 && parola.charAt(i) <= 122))
                    string.append('-');
                    
                    // verifica formato lettera accentata
                else if (parola.charAt(i) == 'à' || parola.charAt(i) == 'è' || parola.charAt(i) == 'é' || parola.charAt(i) == 'ì' || parola.charAt(i) == 'ò' || parola.charAt(i) == 'ù')
                    string.append('-');
            }
        }
        
        // impostazione SIMBOLI + E -
        else
        {
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
                if ((parola.charAt(i) >= 65 && parola.charAt(i) <= 90) || (parola.charAt(i) >= 97 && parola.charAt(i) <= 122))
                    string.append('-');
            }
        }
        
        return string.toString();
    }
}