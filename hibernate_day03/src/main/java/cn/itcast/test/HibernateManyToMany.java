package cn.itcast.test;

import cn.itcast.manytomany.Role;
import cn.itcast.manytomany.User;
import cn.itcast.utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

public class HibernateManyToMany {
    //演示多对多级联保存
    @Test
    public void testSave(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction  = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //1、创建用户和角色对象
            User user1 = new User();
            user1.setUser_name("小明");
            user1.setUser_password("123");
            User user2 = new User();
            user2.setUser_name("小红");
            user2.setUser_password("123");

            Role r1 = new Role();
            r1.setRole_name("总经理");
            r1.setRole_memo("总经理");
            Role r2 = new Role();
            r2.setRole_name("秘书");
            r2.setRole_memo("秘书");
            Role r3 = new Role();
            r3.setRole_name("保安");
            r3.setRole_memo("保安");

            //2、建立关系，将角色放到用户中
            //user1---r1/r2
            user1.getRoleSet().add(r1);
            user1.getRoleSet().add(r2);
            //user2---r2/r3
            user2.getRoleSet().add(r2);
            user2.getRoleSet().add(r3);

            //3、保存用户
            session.save(user1);
            session.save(user2);

            //提交事务
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }

    }

    //演示多对多级联删除
    @Test
    public void testDelete(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction  = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            User user = session.get(User.class,1);
            session.delete(user);
            //提交事务
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }

    }

    //演示第三张表的维护
    @Test
    public void testTable1(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction  = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //1、查询角色和用户
            User user = session.get(User.class,1);
            Role role = session.get(Role.class,1);
            //2、让某个用户拥有某个角色
            user.getRoleSet().add(role);

            //提交事务
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }

    }

    //演示第三张表的维护
    @Test
    public void testTable2(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction  = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //1、查询角色和用户
            User user = session.get(User.class,1);
            Role role = session.get(Role.class,1);
            //2、让某个用户删除某个角色
            user.getRoleSet().remove(role);

            //提交事务
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
