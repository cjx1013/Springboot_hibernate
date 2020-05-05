package cn.itcast.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    static Configuration configuration = null;
    static SessionFactory sessionFactory = null;
    static Session session = null;
    static {
        configuration = new Configuration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    //提供返回SessionFactory的方法
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    //返回与本地线程绑定的session
    public static Session getSession(){
        return sessionFactory.getCurrentSession();
    }
}
