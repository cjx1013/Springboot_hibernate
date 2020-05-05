package cn.itcast.manytomany;


import java.util.HashSet;
import java.util.Set;

public class Role {
    private Integer role_id;//角色id
    private String role_name;//角色名称
    private String role_memo;//角色描述
    //一个角色有多个用户
    private Set<User> userSet = new HashSet<User>();

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getRole_memo() {
        return role_memo;
    }

    public void setRole_memo(String role_memo) {
        this.role_memo = role_memo;
    }
}
