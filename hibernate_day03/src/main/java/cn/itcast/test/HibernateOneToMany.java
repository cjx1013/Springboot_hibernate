package cn.itcast.test;

import cn.itcast.entity.Customer;
import cn.itcast.entity.LinkMan;
import cn.itcast.utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

public class HibernateOneToMany {
    //演示一对多级联保存（复杂写法）
    @Test
    public void testAddDemo1(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction  = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //添加一个客户，为这个客户添加所有联系人
            //1、创建客户和联系人对象
            Customer customer = new Customer();
            customer.setCustName("传智播客");
            customer.setCustLevel("vip");
            customer.setCustSource("网络");
            customer.setCustPhone("110");
            customer.setCustMobile("999");

            LinkMan linkMan = new LinkMan();
            linkMan.setLkm_name("老王");
            linkMan.setLkm_gender("男");
            linkMan.setLkm_phone("666");

            //在客户里表示所有联系人，在联系人里表示客户
            linkMan.setCustomer(customer);
            customer.getLinkManSet().add(linkMan);

            session.save(customer);
            session.save(linkMan);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }

    }

    //演示一对多级联保存（简化写法，需在配置文件中配置）
    @Test
    public void testAddDemo2(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction  = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //添加一个客户，为这个客户添加所有联系人
            //1、创建客户和联系人对象
            Customer customer = new Customer();
            customer.setCustName("百度");
            customer.setCustLevel("vip");
            customer.setCustSource("网络");
            customer.setCustPhone("110");
            customer.setCustMobile("999");

            LinkMan linkMan = new LinkMan();
            linkMan.setLkm_name("老张");
            linkMan.setLkm_gender("男");
            linkMan.setLkm_phone("777");

            //在客户中保存联系人
            customer.getLinkManSet().add(linkMan);

            session.save(customer);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }

    }

    //演示一对多级联删除（需在配置文件中配置）
    @Test
    public void testDelete(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction  = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //根据id查询
            Customer customer = session.get(Customer.class, 2);
            //调用delete方法
            session.delete(customer);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }

    }

    //演示一对多修改操作
    @Test
    public void testUpdate(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction  = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //根据id查询客户，根据id查询联系人
            Customer customer = session.get(Customer.class,2);
            LinkMan linkMan = session.get(LinkMan.class,1);
            //设置持久态对象值
            //将联系人放到客户中
            customer.getLinkManSet().add(linkMan);
            //把客户放到联系人中
            linkMan.setCustomer(customer);

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
