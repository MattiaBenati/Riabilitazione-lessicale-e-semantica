package metodi;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Directory
{
    private static Directory uniqueInstance;
    
    private Directory() {}
    
    public static Directory getInstance()
    {
        if(uniqueInstance == null)
            uniqueInstance = new Directory();
        return uniqueInstance;
    }
    
    /**
     * getter della directory da analizzare
     *
     * @return ritorna l'indirizzo della directory scelta dall'utente
     * tramite il pannello fileChooser
     */
    public String getDirectory()
    {
        // creazione del pannello
        JDialog dialog = new JDialog();
        dialog.setIconImage(Costanti.ICONA.getImage());
        
        // selezione del solo formato directory
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleziona cartella");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        int opzione = fileChooser.showOpenDialog(dialog);
        
        // verifica scelta con successo
        if (opzione == JFileChooser.APPROVE_OPTION)
        {
            File file = fileChooser.getSelectedFile();
            return file.toString();
        }
        
        return null;
    }
    
    /**
     * lettura dei file presenti nella directory e creazione lista di questi
     *
     * @param indirizzo
     * @return ritorna un ArrayList di file corrispondenti
     * a quelli presenti nella directory e di formato
     * supportato (es: jpg, jpeg, png)
     */
    public ArrayList<File> letturaFile(String indirizzo)
    {
        try
        {
            ArrayList<File> arrayFile = new ArrayList<>();
            
            // apertura della directory
            File directory = new File(indirizzo);
            File[] listaFile = directory.listFiles();
            
            // lettura dalla directory
            for (int i = 0; i < Objects.requireNonNull(listaFile).length; ++i)
            {
                if (listaFile[i].isFile() && verificaFile(listaFile[i].toString()))
                {
                    arrayFile.add(listaFile[i]);
                }
            }
            
            // riordino casuale della lista
            Collections.shuffle(arrayFile);
            
            return arrayFile;
        }
        catch (NullPointerException e)
        {
            return null;
        }
    }
    
    /**
     * verifica del formato del file
     *
     * @param file
     * @return ritorna true se il formato del file
     * è supportato o false altrimenti
     */
    private boolean verificaFile(String file)
    {
        String formato = "";
        
        // salvataggio temporaneo del formato
        int verifica = file.lastIndexOf('.');
        
        if (verifica >= 0)
            formato = file.substring(verifica + 1);
        
        // verifica del formato supportato
        for (int i = 0; i < Costanti.FORMATI_IMMAGINE.length; ++i)
        {
            if (formato.equals(Costanti.FORMATI_IMMAGINE[i]) || formato.equals(Costanti.FORMATI_IMMAGINE[i].toUpperCase()))
                return true;
        }
        
        return false;
    }
}