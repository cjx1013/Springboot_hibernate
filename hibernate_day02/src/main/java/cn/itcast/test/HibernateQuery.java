package cn.itcast.test;

import cn.itcast.entity.User;
import cn.itcast.utils.HibernateUtils;
import org.hibernate.*;
import org.hibernate.query.Query;
import org.junit.Test;

import java.util.List;

public class HibernateQuery {
    //Query对象查询所有
    @Test
    public void testQuery(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction  = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //创建Query对象
            //参数；from + 实体类名
            Query query = session.createQuery("from User");
            List<User> list = query.list();
            for (User user:list) {
                System.out.println(user);
            }
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }

    }

    //Criteria对象查询所有
    @Test
    public void testCriteria(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction  = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //创建Criteria对象
            //参数；实体类 class
            Criteria criteria = session.createCriteria(User.class);
            List<User> list = criteria.list();
            for (User user:list) {
                System.out.println(user);
            }
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }

    }

    //SqlQuery对象查询所有
    @Test
    public void testSqlQuery(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction  = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //创建SqlQuery对象
            //参数；普通sql语句
            SQLQuery sqlQuery = session.createSQLQuery("select * from t_user");
            //默认返回的list每部分是数组
//            List<Object[]> list = sqlQuery.list();
//            for (Object[] object:list) {
//                System.out.println(Arrays.toString(object));
//            }

            //让返回的list为对象形式
            sqlQuery.addEntity(User.class);
            List<User> list = sqlQuery.list();
            for (User user: list) {
                System.out.println(user);
            }
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }

    }
}
