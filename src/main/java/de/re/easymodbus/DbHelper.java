package de.re.easymodbus;
import org.hibernate.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class DbHelper {

  private final SessionFactory sessionFactory;

  public DbHelper() {
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

 public List getData() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
   List result = session.createQuery("from modbus_table").list();
   //for (GroupData group : result) {
   //   System.out.println(group);
   // }
    session.getTransaction().commit();
    session.close();
    return result;
    }



}
