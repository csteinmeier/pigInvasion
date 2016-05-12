package display;

import java.util.Comparator;

public class HighscoreEntry
{
    private String name;
    private int punkte;
    
    public HighscoreEntry(String name, int punkte)
    {
        this.name = name;
        this.punkte = punkte;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPunkte() {
        return punkte;
    }

    public void setPunkte(int punkte) {
        this.punkte = punkte;
    }
}
