package domein;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Verzorger implements Serializable {

    @Id
    private int nummer;
    private String naam;

    @ManyToOne
    private final List<Dier> dieren = new ArrayList<>();

    public Verzorger(int nummer, String naam) {
        this.nummer = nummer;
        this.naam = naam;
    }

    protected Verzorger() {
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

    public List<Dier> getDieren() {
        return dieren;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.nummer;
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
        final Verzorger other = (Verzorger) obj;
        if (this.nummer != other.nummer) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return naam;
    }

    public void addDier(Dier dier) {
        dieren.add(dier);
    }
}
