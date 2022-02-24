package store;

import models.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.Query;
import java.util.List;
import java.util.function.Function;

public class DbStore implements AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private static final class Lazy {
        private static final DbStore INST = new DbStore();
    }

    public static DbStore instOf() {
        return Lazy.INST;
    }

    public Item add(Item item) {
        int id = (int) this.tx(session -> session.save(item));
        item.setId(id);
        return item;
    }

    public boolean performItem(int id) {
        return this.tx(
                session -> {
                    Query query = session.createQuery("update Item set done = true where id = :id");
                    query.setParameter("id", id);
                    return query.executeUpdate() > 0;
                }
        );
    }

    public List<Item> findAll() {
        return this.tx(
                session -> session.createQuery("from Item order by done desc, created").list()
        );
    }

    public List<Item> findAllPerformed() {
        return this.tx(
                session -> session.createQuery("from Item where done = true order by done desc, created").list()
        );
    }

    public List<Item> findAllNotPerformed() {
        return this.tx(
                session -> session.createQuery("from Item where done = false order by done desc, created").list()
        );
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
