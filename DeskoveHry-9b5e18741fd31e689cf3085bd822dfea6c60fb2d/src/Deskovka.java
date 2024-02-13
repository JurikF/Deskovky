public class Deskovka {
    private String nazev;
    private boolean jeZakoupena;
    private int oblibenost;

    public Deskovka(String nazev, boolean jeZakoupena, int oblibenost) {
        this.nazev = nazev;
        this.jeZakoupena = jeZakoupena;
        this.oblibenost = oblibenost;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public boolean isJeZakoupena() {
        return jeZakoupena;
    }

    public void setJeZakoupena(boolean jeZakoupena) {
        this.jeZakoupena = jeZakoupena;
    }

    public int getOblibenost() {
        return oblibenost;
    }

    public void setOblibenost(int oblibenost) {
        this.oblibenost = oblibenost;
    }
}
