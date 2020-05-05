package cn.itcast.test;

import cn.itcast.entity.User;
import cn.itcast.utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

public class HibernateTest {
    @Test
    public void testAdd(){
        //1、加载核心配置文件
        //到src下找名为hibernate.cfg.xml的配置文件
//        Configuration configuration = new Configuration();
//        configuration.configure();
        //2、创建SessionFactory对象
//        SessionFactory sessionFactory = configuration.buildSessionFactory();
        //调用工具类创建SessionFactory
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        //3、使用SessionFactory创建Session对象
        Session session = sessionFactory.openSession();
        //4、开启事务
        Transaction transaction = session.beginTransaction();
        //5、写具体的crud操作
        User user = new User();
        user.setUsername("老王");
        user.setPassword("123");
        user.setAddress("北京");
        //调用session的save方法实现添加操作
        session.save(user);
        //6、提交事务
        transaction.commit();
        //7、关闭资源
        session.close();
        sessionFactory.close();
    }

    @Test
    public void testGet(){
        //1、加载核心配置文件
        //2、创建SessionFactory对象
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        //3、使用SessionFactory创建Session对象
        Session session = sessionFactory.openSession();
        //4、开启事务
        Transaction transaction = session.beginTransaction();
        //5、根据id查询
        //参数1：实体类的class
        //参数2：id
        User user = session.get(User.class,1);
        System.out.println(user);
        //6、提交事务
        transaction.commit();
        //7、关闭资源
        session.close();
        sessionFactory.close();
    }

    @Test
    public void testUpdate(){
        //1、加载核心配置文件
        //2、创建SessionFactory对象
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        //3、使用SessionFactory创建Session对象
        Session session = sessionFactory.openSession();
        //4、开启事务
        Transaction transaction = session.beginTransaction();
        //5、测试修改
        // （1）根据id查询
        //（2）向返回的user设置修改的值
        User user = session.get(User.class,1);
        user.setUsername("小王");
        //调用session的update方法
        //执行过程：到user中找到uid，根据uid修改
        session.update(user);
        //6、提交事务
        transaction.commit();
        //7、关闭资源
        session.close();
        sessionFactory.close();
    }

    @Test
    public void testDelete(){
        //1、加载核心配置文件
        //2、创建SessionFactory对象
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        //3、使用SessionFactory创建Session对象
        Session session = sessionFactory.openSession();
        //4、开启事务
        Transaction transaction = session.beginTransaction();
        //5、测试删除
        // （1）根据id查询
        //（2）调用session的delete方法
        User user = session.get(User.class,1);
        session.delete(user);
        //6、提交事务
        transaction.commit();
        //7、关闭资源
        session.close();
        sessionFactory.close();
    }

    @Test
    public void testSaveOrUpdate(){
        //1、加载核心配置文件
        //2、创建SessionFactory对象
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        //3、使用SessionFactory创建Session对象
        Session session = sessionFactory.openSession();
        //4、开启事务
        Transaction transaction = session.beginTransaction();
        //5、测试SaveOrUpdate
        // (1)实体类为瞬时态（无id，与session无关联），做添加操作
//        User user  = new User();
//        user.setUsername("东方不败");
//        user.setPassword("456");
//        user.setAddress("新疆");
//        session.saveOrUpdate(user);
        // (2)实体类为托管态（有id，与session无关联），做修改操作
//            User user  = new User();
//            user.setUid(1);
//            user.setUsername("韦小宝");
//            user.setPassword("4556");
//            user.setAddress("天津");
//            session.saveOrUpdate(user);
        // (3)实体类为持久态（无id，与session有关联），做修改操作
        User user  = session.get(User.class,1);
        user.setUsername("小明");
        session.saveOrUpdate(user);
        //6、提交事务
        transaction.commit();
        //7、关闭资源
        session.close();
        sessionFactory.close();
    }

    //测试一级缓存
    @Test
    public void testCache(){
        //1、加载核心配置文件
        //2、创建SessionFactory对象
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        //3、使用SessionFactory创建Session对象
        Session session = sessionFactory.openSession();
        //4、开启事务
        Transaction transaction = session.beginTransaction();
        //5、测试
        // 根据id查询
        User user1 = session.get(User.class,1);
        System.out.println(user1);

        User user2 = session.get(User.class,1);
        System.out.println(user2);
        //6、提交事务
        transaction.commit();
        //7、关闭资源
        session.close();
        sessionFactory.close();
    }

    //事务代码规范写法
    @Test
    public void testTransaction(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction  = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //添加
            User user = new User();
            user.setUsername("大黄");
            user.setPassword("5200");
            user.setAddress("上海");
            session.save(user);
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
