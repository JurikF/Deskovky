import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Deskovky extends JFrame {
    private JTextField textField1;
    private JButton previous;
    private JButton next;
    private JButton save;
    private JPanel panel;
    private JCheckBox anoCheckBox;
    private JRadioButton RB1;
    private JRadioButton RB3;
    private JRadioButton RB2;

    private int indexAktualniHry = 0;

    private int vybranaOblibenost = 1;

    private final List<Deskovka> seznamDeskovek = new ArrayList<>();

    public Deskovka ziskejDeskovku(int i) {
        return seznamDeskovek.get(i);
    }

    public List<Deskovka> getSeznamDeskovek() {
        return seznamDeskovek;
    }



    public Deskovky() {
        super("Deskovky");
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(RB1);
        btnGroup.add(RB2);
        btnGroup.add(RB3);
        RB1.addItemListener(e -> handleRadioButtonClick(1));
        RB2.addItemListener(e -> handleRadioButtonClick(2));
        RB3.addItemListener(e -> handleRadioButtonClick(3));

        next.addActionListener(e -> {

            if (indexAktualniHry < seznamDeskovek.size() -1) {
                indexAktualniHry++;
                zobrazDeskovku(ziskejDeskovku(indexAktualniHry));

            }
        });

        previous.addActionListener(e -> {
            if (indexAktualniHry > 0) {
                indexAktualniHry--;
                zobrazDeskovku(ziskejDeskovku(indexAktualniHry));
            }
        });

        save.addActionListener(e -> ulozDoSouboru());
        ctiZeSouboru();
        if (!getSeznamDeskovek().isEmpty()){
            zobrazDeskovku(ziskejDeskovku(indexAktualniHry));
        } else {
            JOptionPane.showMessageDialog(this, "List je prázdný!");
        }

    }
        private void handleRadioButtonClick(int score) {
            vybranaOblibenost = score;
        }


    public void ctiZeSouboru() {
        try (Scanner sc = new Scanner(new BufferedReader(new FileReader("Deskovky.txt")))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] bloky = line.split(";");
                String nazev = bloky[0];
                Boolean jeZakoupena = bloky[1].equals("ano");
                int oblibenost = Integer.parseInt(bloky[2]);
                Deskovka deskovka = new Deskovka(nazev, jeZakoupena, oblibenost);
                seznamDeskovek.add(deskovka);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Soubor nebyl nalezen: " + e.getLocalizedMessage());
        } catch (NumberFormatException e) {
            System.err.println("Špatné zapsání čísla: " + e.getLocalizedMessage());
        }
    }

    public void ulozDoSouboru() {
        Deskovka vybranaDeskovka = seznamDeskovek.get(indexAktualniHry);
        vybranaDeskovka.setNazev(textField1.getText());
        vybranaDeskovka.setJeZakoupena(anoCheckBox.isSelected());
        vybranaDeskovka.setOblibenost(vybranaOblibenost);


        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("Deskovky.txt")))) {
            for (Deskovka deskovka : seznamDeskovek) {
                writer.println(deskovka.getNazev() + ";" + (deskovka.isJeZakoupena() ? "ano" : "ne") + ";" + deskovka.getOblibenost());
            }
            JOptionPane.showMessageDialog(this, "Změny uloženy do souboru.");
        } catch (IOException e) {
            System.err.println("Chyba při zápisu do souboru: " + e.getLocalizedMessage());
        }
    }

    public void zobrazDeskovku(Deskovka deskovka) {
        textField1.setText(deskovka.getNazev());
        anoCheckBox.setSelected(deskovka.isJeZakoupena());
        switch (deskovka.getOblibenost()) {
            case 1 -> RB1.setSelected(true);
            case 2 -> RB2.setSelected(true);
            case 3 -> RB3.setSelected(true);
        }
    }
}


