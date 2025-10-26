package metodi;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public final class Immagini
{
    static final int raggio_angolo = 15;
    
    /**
     * setter dell'immagine della lista nella JLabel indicata
     *
     * @param label
     * @param immagine
     * @param altezza
     * @param larghezza
     */
    public static void setImmagineJLabel(JLabel label, File immagine, int altezza, int larghezza)
    {
        try
        {
            BufferedImage immagineDati = ImageIO.read(immagine);
            immagineDati = resizeImage(immagineDati, larghezza, altezza);
            immagineDati = makeRoundedCorner(immagineDati, raggio_angolo);
            
            // creazione dell'icona corrispondente all'immagine da stampare
            ImageIcon immagineIcona = new ImageIcon(immagineDati);
            
            // impostazione icona alla label corrente
            label.setIcon(immagineIcona);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * ridimensionamento scala dell'immagine analizzata
     *
     * @param originalImage
     * @param targetWidth
     * @param targetHeight
     * @return ritorna la stessa immagine passata a metodo ma con
     * scala diversa (più grande o piccola ma senza perdita di qualità)
     * @throws IOException
     */
    private static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException
    {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outputImage;
    }
    
    /**
     * creazione bordi curvi dell'immagine analizzata
     *
     * @param image
     * @param cornerRadius
     * @return ritorna la stessa immagine passata a metodo ma con
     * bordi curvi (più accentuati o meno in base al parametro passato)
     */
    private static BufferedImage makeRoundedCorner(BufferedImage image, int cornerRadius)
    {
        int w = image.getWidth() - 2;
        int h = image.getHeight() - 2;
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D g2 = output.createGraphics();
        
        // This is what we want, but it only does hard-clipping, i.e. aliasing
        // g2.setClip(new RoundRectangle2D ...)
        
        // so instead fake soft-clipping by first drawing the desired clip shape
        // in fully opaque white with antialiasing enabled...
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));
        
        // ... then compositing the image on top,
        // using the white shape from above as alpha source
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);
        
        g2.dispose();
        
        return output;
    }
}