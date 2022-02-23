package store;

import models.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.Query;
import java.util.List;

public class DbStore implements AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();
    private static final DbStore INSTANCE = new DbStore();

    private static final class Lazy {
        private static final DbStore INST = new DbStore();
    }

    public static DbStore instOf() {
        return Lazy.INST;
    }

    public Item add(Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    public boolean performItem(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Query query = session.createQuery(
                "update Item set done = true where id = :id"
        );
        query.setParameter("id", id);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public List<Item> findAll() {
        Session session = sf.openSession();
        session.beginTransaction();
        List result = session.createQuery("from Item order by done desc, created").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public List<Item> findAllPerformed() {
        Session session = sf.openSession();
        session.beginTransaction();
        List result = session.createQuery("from Item where done = true order by done desc, created").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public List<Item> findAllNotPerformed() {
        Session session = sf.openSession();
        session.beginTransaction();
        List result = session.createQuery("from Item where done = false order by done desc, created").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public void close() throws Exception {

    }
}
