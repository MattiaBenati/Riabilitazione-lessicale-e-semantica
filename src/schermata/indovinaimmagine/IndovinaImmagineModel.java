package schermata.indovinaimmagine;

import parola.Parola;

import java.io.File;
import java.util.ArrayList;

public class IndovinaImmagineModel
{
    ArrayList<Parola> parole;           // lista parole da analizzare
    ArrayList<Boolean> analizzate;      // lista immagini/parole analizzate/da analizzare
    ArrayList<ArrayList<File>> immaginiMostrate;        // lista-doppia per salvare le immagini mostrate
    ArrayList<ArrayList<Boolean>> analizzateMostrate;   // lista-doppia per salvare le immagini corrette/errate
}