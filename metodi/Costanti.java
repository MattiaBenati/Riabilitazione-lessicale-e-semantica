package metodi;

import javax.swing.*;
import java.util.Objects;

public class Costanti
{
    public static final String TITOLO = "Riabilitazione";
    public static final ImageIcon ICONA = new ImageIcon(Objects.requireNonNull(Costanti.class.getClassLoader().getResource("risorse/icona.png")));
    public static final ImageIcon HOME = new ImageIcon(Objects.requireNonNull(Costanti.class.getClassLoader().getResource("risorse/sfondo.png")));
    public static final String[] FORMATI_IMMAGINE = {"jpg", "jpeg", "png"};
    public static final char[] CARATTERI = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
    public static final char[] ACCENTI = {'à', 'è', 'é', 'ì', 'ò', 'ù'};
    public static final char SPAZIO = ' ';
}