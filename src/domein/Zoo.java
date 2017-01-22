package domein;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import persistentie.PersistentieController;

public class Zoo {

    private List<Verzorger> verzorgers;
    private List<Dier> dieren;
    private List<Gebouw> gebouwen;

    public Zoo() {
        this.dieren = new PersistentieController().geefDieren();
        this.verzorgers = new PersistentieController().geefVerzorgers();
        this.gebouwen = new PersistentieController().geefGebouwen();
    }

    /*
     * Geeft alle dieren terug die behoren tot de diersoort met de opgegeven naam. De lijst van
     * dieren moet gesorteerd zijn op gewicht (laag naar hoog).
     */
    public List<Dier> geefDierenVanSoortMetNaam(String soortNaam) {
        return dieren.stream()
                .filter(e -> e.getSoort().toString().equals(soortNaam))
                .sorted(Comparator.comparing(Dier::getGewicht))
                .collect(Collectors.toList());
    }

    /*
     * Geeft het gemiddelde gewicht terug van alle dieren die verblijven in het gebouw met de
     * opgegeven naam. Geeft 0 terug indien er geen gebouw is met deze naam.
     */
    public double geefGemiddeldeGewichtVanDierenInGebouwMetNaam(String gebouwNaam) {
        double resultaat = 0;
        try {
            resultaat = gebouwen.stream()
                    .filter(e -> e.getNaam().equals(gebouwNaam))
                    .findFirst()
                    .get()
                    .getDieren()
                    .stream()
                    .mapToDouble(e -> e.getGewicht())
                    .average()
                    .getAsDouble();
        } catch (NoSuchElementException e) {
            System.out.println("Er is geen gebouw met naam "+gebouwNaam);
        }
        return resultaat;
    }

    /*
     * Geeft de namen van de dieren terug die verzorgd worden door de verzorger met het opgegeven
     * nummer. Geeft een lege lijst terug indien er geen verzorger is met dit nummer.
     */
    public List<String> geefNamenVanDierenVanVerzorgerMetNummer(int verzorgerNummer) {
        List<String> lijst = new ArrayList<>();
        try{
            lijst = verzorgers.stream()
                .filter(e -> e.getNummer() == verzorgerNummer)
                .findFirst()
                .get()
                .getDieren()
                .stream()
                .map(dier -> dier.getNaam()).collect(Collectors.toList());
        }catch (NoSuchElementException e) {
            System.out.println("Er is geen verzorger met nummer" + verzorgerNummer);
        }
        return lijst;
    }

//    /*
//     * Geeft een lijst terug van verzorgers die een of meerdere dieren verzorgen die verblijven in
//     * het gebouw met de opgegeven naam. Geeft een lege lijst terug indien er geen gebouw is met
//     * deze naam.
    //    * NU DUMMY UITWERKING voor test gebruik in deze toepassing 
//  *  NIET VERDER UITWERKEN
//     */
    public List<Verzorger> verzorgersInGebouwMetNaam(String gebouwNaam) {
        if (gebouwNaam.equalsIgnoreCase("Reptielen")) {
            return verzorgers;
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * De methode maakOverzichtVolgensSoort geeft een overzicht (Map) terug van
     * alle dieren per Soort.
     */
    //TODO
    // TODO METHODE ....maakOverzichtVolgensSoort() ... hier uitschrijven
    //
    public String maakOverZichtVolgensSoort() {
        Map<Soort, List<Dier>> mapje = dieren.stream()
                .collect(Collectors.groupingBy(Dier::getSoort));

        return mapje.entrySet()
                .stream()
                .map(e -> e.getKey() + " => " + e.getValue())
                .collect(Collectors.joining("\n"));
    }
}
