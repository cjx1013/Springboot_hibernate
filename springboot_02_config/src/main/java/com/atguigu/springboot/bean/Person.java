package com.atguigu.springboot.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 将配置文件中配置的每个属性的值映射到这个组件中
 * @ConfigurationProperties：
 * 告诉springboot本类中所有属性和配置文件中相关的配置进行绑定
 * @ConfigurationProperties(prefix = "person"):默认从全局配置文件获取值
 * prefix = "person"：配置文件中哪个下面的所有属性进行一一映射
 * 只有这个组件是容器的组件，才能容器提供@ConfigurationProperties功能
 * @PropertySource(value = "classpath:person.properties")加载指定配置文件
 */
@PropertySource(value = "classpath:person.properties")
@Controller
@ConfigurationProperties(prefix = "person")
public class Person {
//    @Value("${person.last-name}")
    private String lastName;
//    @Value("#{11*2}")
    private Integer age;
//    @Value("true")
    private Boolean boss;
    private Date birth;

    private Map<String, Object> maps;
    private List<Object> lists;
    private Dog dog;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Boolean getBoss() {
        return boss;
    }

    public void setBoss(Boolean boss) {
        this.boss = boss;
    }

    public Map<String, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    public List<Object> getLists() {
        return lists;
    }

    public void setLists(List<Object> lists) {
        this.lists = lists;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    @Override
    public String toString() {
        return "Person{" +
                "lastName='" + lastName + '\'' +
                ", age=" + age +
                ", birth=" + birth +
                ", boss=" + boss +
                ", maps=" + maps +
                ", lists=" + lists +
                ", dog=" + dog +
                '}';
    }
}
