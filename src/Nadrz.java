// Výčtový typ pro typy obsahu v nádrži
enum TypObsahu {
    VODA("Voda"), OLEJ("Olej"), BENZIN("Benzín");

    private final String nazev;

    TypObsahu(String nazev) {
        this.nazev = nazev;
    }

    @Override
    public String toString() {
        return nazev;
    }
}

// Vlastní výjimky pro chyby přeplnění a vyčerpání nádrže
class MyException_PlnaNadrz extends Exception {
    public MyException_PlnaNadrz(String message) {
        super(message);
    }
}

class MyException_PrazdnaNadrz extends Exception {
    public MyException_PrazdnaNadrz(String message) {
        super(message);
    }
}

// Třída Nadrz
class Nadrz {
    private int stav;  // Aktuální objem v nádrži
    private int kapacita;  // Maximální kapacita
    private TypObsahu typObsahu;  // Typ obsahu

    // Konstruktor třídy
    public Nadrz(int kapacita, TypObsahu typObsahu) {
        this.kapacita = kapacita;
        this.stav = 0;  // Začínáme s prázdnou nádrží
        this.typObsahu = typObsahu;
    }

    // Metoda pro plnění nádrže
    public void naplnit(int objem) throws MyException_PlnaNadrz {
        if (stav + objem > kapacita) {
            throw new MyException_PlnaNadrz("Pokus o přeplnění nádrže typu " + typObsahu + "!");
        }
        stav += objem;
    }

    // Metoda pro odběr z nádrže
    public void odebrat(int objem) throws MyException_PrazdnaNadrz {
        if (stav - objem < 0) {
            throw new MyException_PrazdnaNadrz("Pokus o nadměrné odebrání z prázdné nádrže typu " + typObsahu + "!");
        }
        stav -= objem;
    }

    // Získání stavu nádrže
    public String getStav() {
        int procenta = (int) ((double) stav / kapacita * 100);
        return stav + "/" + kapacita + "L" + " (" + procenta + "%), typ nádrže: " + typObsahu;
    }
}

