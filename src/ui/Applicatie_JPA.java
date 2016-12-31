package ui;

//import domein.Dier;
import domein.Dier;
import domein.Gebouw;
import domein.Soort;
import domein.Verzorger;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import util.JPAUtil;

//import domein.Soort;
//import java.util.ArrayList;
//import java.util.List;
//import javax.persistence.EntityManager;
//import util.JPAUtil;
public class Applicatie_JPA {

    public static void main(String[] args) {
        Soort krokodil = new Soort("Krokodil");
        Dier kroky = new Dier(1, "Kroky", 102, krokodil);
        Dier happy = new Dier(2, "Happy", 97, krokodil);

        Soort vogel = new Soort("Vogel");
        Dier beo = new Dier(3, "Beo", 0.102, vogel);
        Dier koko = new Dier(4, "Koko", 0.97, vogel);

        List<Dier> dieren = new ArrayList<>();
        dieren.add(kroky);
        dieren.add(happy);
        dieren.add(beo);
        dieren.add(koko);

        Verzorger dylan = new Verzorger(1, "Dylan");
        Verzorger brent = new Verzorger(2, "Brent");

        dylan.addDier(kroky);
        dylan.addDier(beo);

        brent.addDier(happy);
        brent.addDier(koko);
//        
        Gebouw reptielen = new Gebouw("reptielen", 2);
        Gebouw vogels = new Gebouw("vogels", 2);

        reptielen.addDier(kroky);
        reptielen.addDier(happy);

        vogels.addDier(beo);
        vogels.addDier(koko);

        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        dieren.stream()
                .forEach(dier -> entityManager.persist(dier));

        entityManager.persist(reptielen);
        entityManager.persist(vogels);

        entityManager.persist(dylan);
        entityManager.persist(brent);
        entityManager.getTransaction().commit();
        entityManager.close();
        JPAUtil.getEntityManagerFactory().close();
    }
}
