package hr.ispit.ucenici;

import java.util.Arrays;

public class Ucenik {
    private String ime;
    private String prezime;
    private int[] ocjene;

    public Ucenik(String ime, String prezime) {
        this.ime = ime;
        this.prezime = prezime;
        this.ocjene = new int[3];
    }

    public String getIme() { return ime; }
    public void setIme(String ime) { this.ime = ime; }
    public String getPrezime() { return prezime; }
    public void setPrezime(String prezime) { this.prezime = prezime; }
    public int[] getOcjene() { return Arrays.copyOf(ocjene, ocjene.length); }

    public void setOcjene(int[] ocjene) {
        if (ocjene == null || ocjene.length != 3) throw new IllegalArgumentException("Potrebne su točno 3 ocjene.");
        for (int ocjena : ocjene) if (ocjena < 1 || ocjena > 5) throw new IllegalArgumentException("Ocjena mora biti od 1 do 5.");
        this.ocjene = Arrays.copyOf(ocjene, ocjene.length);
    }

    public double izracunajProsjek() {
        return Arrays.stream(ocjene).average().orElse(0.0);
    }

    public void prikaziPodatke() {
        System.out.printf("%s %s - prosjek: %.2f%n", ime, prezime, izracunajProsjek());
    }
}

