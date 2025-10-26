package parola;

import java.io.File;

public class Parola
{
    private File indirizzo;
    private File immagine;
    private String parola;
    private boolean vocali;
    private String simboli;
    private int difficolta;
    private String lettere;
    
    Parola(File indirizzo, String parola, boolean vocali, String simboli, int difficolta, String lettere)
    {
        this.indirizzo = indirizzo;
        this.immagine = indirizzo;
        this.parola = parola;
        this.vocali = vocali;
        this.simboli = simboli;
        this.difficolta = difficolta;
        this.lettere = lettere;
    }
    
    // metodi getter (per ottenere il valore delle variabili)
    public File getIndirizzo()
    {
        return indirizzo;
    }
    
    public File getImmagine()
    {
        return indirizzo;
    }
    
    public String getParola()
    {
        return parola;
    }
    
    public boolean getVocali()
    {
        return vocali;
    }
    
    public String getSimboli()
    {
        return simboli;
    }
    
    public int getDifficolta()
    {
        return difficolta;
    }
    
    public String getLettere()
    {
        return lettere;
    }
    
    // metodi update (per aggiornare il valore delle variabili)
    public void updateIndirizzo(File indirizzo)
    {
        this.indirizzo = indirizzo;
        this.immagine = indirizzo;
    }
    
    public void updateParola(String parola)
    {
        this.parola = parola;
    }
    
    public void updateVocali(boolean vocali)
    {
        this.vocali = vocali;
    }
    
    public void updateSimboli(String simboli)
    {
        this.simboli = simboli;
    }
    
    public void updateDifficolta(int difficolta)
    {
        this.difficolta = difficolta;
    }
    
    public void updateLettere(String lettere)
    {
        this.lettere = lettere;
    }
}