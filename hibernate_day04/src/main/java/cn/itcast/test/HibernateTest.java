package cn.itcast.test;

import cn.itcast.entity.Customer;
import cn.itcast.entity.LinkMan;
import cn.itcast.utils.HibernateUtils;
import org.hibernate.*;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class HibernateTest {
    //演示对象导航查询
    @Test
    public void testSelect1(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //1、根据id查询
            Customer customer = session.get(Customer.class, 1);
            //2、再查询出该客户的所有联系人
            //直接得到set集合，没有发送sql语句
            Set<LinkMan> linkManSet = customer.getLinkManSet();
            //发送sql语句(关联级别延迟的默认操作)
            System.out.println(linkManSet.size());

        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }

    }

    //演示hibernate检索策略(立即查询和延迟查询（类级别延迟和关联级别延迟）)
    @Test
    public void testSelec2(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //根据id查询
            //立即查询,调用get方法，立即发送sql语句查询数据库
//            Customer customer = session.get(Customer.class, 1);
//            System.out.println(customer.getCid());
            //类级别查询,根据id查询，调用load方法，不会立马发送sql语句
            //返回的对象只有id，当查询id以外的值时发送sql语句查询数据库
            Customer customer = session.load(Customer.class, 1);
            System.out.println(customer.getCid());
            System.out.println(customer.getCustName());
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }

    }

    //演示批量抓取（需配置）
    @Test
    public void testSelec3(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
           //查询所有客户和所有联系人
            Criteria criteria = session.createCriteria(Customer.class);
            List<Customer> list = criteria.list();
            for (Customer customer:list) {
                System.out.println(customer.getCid()+":"+customer.getCustName());
                Set<LinkMan> linkManSet = customer.getLinkManSet();
                for (LinkMan linkman:linkManSet) {
                    System.out.println(linkman.getLkm_id()+":"+linkman.getLkm_name());
                }
            }
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }

    }
}
