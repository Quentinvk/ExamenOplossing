package ui;

import domein.DomeinController;

public class Applicatie1 {

    public static void main(String[] args) {
        DomeinController dc = new DomeinController();
        System.out.println("\nDieren van soort krokodil: ");
        System.out.println(dc.geefDierenVanSoortMetNaam("Krokodil"));
        System.out.println("");

        System.out.println("\nGemiddelde gewicht dieren in gebouw Reptielen: ");
        System.out.println(dc.geefGemiddeldeGewichtVanDierenInGebouwMetNaam("Reptielen"));

        System.out.println("\nNamen van dieren van verzorger 1: ");
        System.out.println(dc.geefNamenVanDierenVanVerzorgerMetNummer(1));

//        System.out.println("Namen van verzorgers in gebouw Reptielen: ");
//        System.out.println(dc.geefVerzorgersInGebouwMetNaam("Reptielen"));
        System.out.println("\nDieren per soort: ");
        System.out.println(dc.maakOverzichtVolgensSoort());
    }
}
