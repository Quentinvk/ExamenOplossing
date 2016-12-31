package domein;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import util.JPAUtil;

@Entity
@NamedQuery(name = "Dier.findAll", query = "SELECT d FROM Dier d")
//@NamedQuery(name = "Dieren.soortNamen", query = "SELECT e FROM Dier WHERE e.soort := soort ORDER BY e.gewicht DESC")
public class Dier implements Serializable {

    // TypedQuery<Dier> q
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int nummer;

    private String naam;
    private double gewicht;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Soort soort;

    public Dier(int nummer, String naam, double gewicht, Soort soort) {
        this.nummer = nummer;
        this.naam = naam;
        this.gewicht = gewicht;
        this.soort = soort;
    }

    protected Dier() {
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
