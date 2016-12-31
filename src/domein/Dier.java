package domein;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Dier")
@NamedQuery(name = "Dieren.soortNamen", query = "SELECT e FROM Dieren e WHERE e.soort := soort ORDER BY e.gewicht DESC")
public class Dier implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int nummer;

    private String naam;
    private double gewicht;

    @ManyToOne
    private Soort soort;

    public Dier(int nummer, String naam, double gewicht, Soort soort) {
        this.nummer = nummer;
        this.naam = naam;
        this.gewicht = gewicht;
        this.soort = soort;
    }

    public int getNummer() {
        return nummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public double getGewicht() {
        return gewicht;
    }

    public void setGewicht(double gewicht) {
        this.gewicht = gewicht;
    }

    public Soort getSoort() {
        return soort;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.nummer;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dier other = (Dier) obj;
        if (this.nummer != other.nummer) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return naam;
    }
}
