// TestNadrz.java
public class TestNadrz {
    public static void main(String[] args) {
        try {
            Nadrz nadrz = new Nadrz(100, TypObsahu.VODA);
            System.out.println(nadrz.getStav());
            nadrz.naplnit(50);
            System.out.println(nadrz.getStav());
            nadrz.odebrat(20);
            System.out.println(nadrz.getStav());
            nadrz.naplnit(60);  // Tento pokus by měl vyvolat výjimku
        } catch (MyException_PlnaNadrz | MyException_PrazdnaNadrz e) {
            System.out.println("Chyba: " + e.getMessage());
        }
    }
}
