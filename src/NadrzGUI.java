import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NadrzGUI extends JFrame {
    private Nadrz nadrz;
    private JLabel stavLabel;

    public NadrzGUI() {
        super("Aplikace pro práci s nádrží");
        setLayout(new FlowLayout());

        //Input pro kapacitu nadrze
        JTextField kapacitaField = new JTextField(5);
        add(new JLabel("Kapacita (l):"));
        add(kapacitaField);

        // ComboBox pro výběr typu obsahu
        JComboBox<TypObsahu> obsahComboBox = new JComboBox<>(TypObsahu.values());
        add(new JLabel("Typ obsahu:"));
        add(obsahComboBox);

        // Tlačítko pro vytvoření nádrže
        JButton vytvoritButton = new JButton("Vytvořit nádrž");
        add(vytvoritButton);

        stavLabel = new JLabel("Zatím nebyla vytvořena nádrž.");
        add(stavLabel);

        // Tlačítko pro naplnění
        JButton naplnitButton = new JButton("Naplnit");
        add(naplnitButton);

        naplnitButton.addActionListener(e -> {
            if (nadrz == null) {
                JOptionPane.showMessageDialog(null, "Nejdříve vytvořte nádrž!", "Chyba", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                int objem = Integer.parseInt(JOptionPane.showInputDialog("Zadejte objem k naplnění:"));
                nadrz.naplnit(objem);
                stavLabel.setText(nadrz.getStav());
            } catch (MyException_PlnaNadrz | NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Chyba", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Tlačítko pro odebrání
        JButton odebratButton = new JButton("Odebrat");
        add(odebratButton);

        odebratButton.addActionListener(e -> {
            if (nadrz == null) {
                JOptionPane.showMessageDialog(null, "Nejdříve vytvořte nádrž!", "Chyba", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                int objem = Integer.parseInt(JOptionPane.showInputDialog("Zadejte objem k odebrání:"));
                nadrz.odebrat(objem);
                stavLabel.setText(nadrz.getStav());
            } catch (MyException_PrazdnaNadrz | NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Chyba", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Akce pro vytvoření nádrže
        vytvoritButton.addActionListener(e -> {
            try {
                int kapacita = Integer.parseInt(kapacitaField.getText());
                if (kapacita <= 0) throw new NumberFormatException("Kapacita musí být kladná.");
                TypObsahu vybrany = (TypObsahu) obsahComboBox.getSelectedItem();
                nadrz = new Nadrz(kapacita, vybrany);
                stavLabel.setText(nadrz.getStav());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Zadejte platnou kladnou kapacitu!", "Chyba", JOptionPane.ERROR_MESSAGE);
            }
        });
        // Nastavení rozměrů okna
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new NadrzGUI();
    }
}
