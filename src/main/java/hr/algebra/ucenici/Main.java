package hr.ispit.ucenici;

import java.util.Scanner;

public class Main {
    private static final String[] PREDMETI = {"matematike", "hrvatskog", "engleskog"};

    public static void main(String[] args) {
        Ucenik[] ucenici = new Ucenik[3];
        try (Scanner scanner = new Scanner(System.in)) {
            for (int i = 0; i < ucenici.length; i++) {
                System.out.printf("%nUčenik %d%n", i + 1);
                System.out.print("Ime: ");
                String ime = procitajNeprazanTekst(scanner);
                System.out.print("Prezime: ");
                String prezime = procitajNeprazanTekst(scanner);

                Ucenik ucenik = new Ucenik(ime, prezime);
                int[] ocjene = new int[3];
                for (int j = 0; j < ocjene.length; j++) ocjene[j] = procitajOcjenu(scanner, PREDMETI[j]);
                ucenik.setOcjene(ocjene);
                ucenici[i] = ucenik;
            }
        }

        System.out.println("\nPodaci o učenicima:");
        for (Ucenik ucenik : ucenici) ucenik.prikaziPodatke();
    }

    private static String procitajNeprazanTekst(Scanner scanner) {
        while (true) {
            String tekst = scanner.nextLine().trim();
            if (!tekst.isEmpty()) return tekst;
            System.out.print("Vrijednost ne smije biti prazna. Ponovite unos: ");
        }
    }

    private static int procitajOcjenu(Scanner scanner, String predmet) {
        while (true) {
            System.out.printf("Ocjena iz %s (1-5): ", predmet);
            String unos = scanner.nextLine().trim();
            try {
                int ocjena = Integer.parseInt(unos);
                if (ocjena >= 1 && ocjena <= 5) return ocjena;
            } catch (NumberFormatException ignored) { }
            System.out.println("Neispravan unos.");
        }
    }
}
