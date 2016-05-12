package Utility;

import display.HighscoreEntry;
import java.util.Comparator;


public class HSComparator implements Comparator<HighscoreEntry> {

    @Override
    public int compare(HighscoreEntry o1, HighscoreEntry o2) 
    {
        if (o1.getPunkte() > o2.getPunkte()) 
        {
            return -1;
        }
        else {
            return 1;
        }
    }
    
}
