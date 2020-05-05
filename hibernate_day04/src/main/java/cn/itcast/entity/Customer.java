package cn.itcast.entity;

import java.util.HashSet;
import java.util.Set;

public class Customer {
    //客户id
    private Integer cid;
    //客户名称
    private String CustName;
    //客户级别
    private String CustLevel;
    //客户来源
    private String CustSource;
    //联系电话
    private String CustPhone;
    //手机
    private String CustMobile;

    //一个客户对应多个联系人
    //hibernate中要求用Set集合表示多个
    private Set<LinkMan> linkManSet = new HashSet<LinkMan>();

    public Set<LinkMan> getLinkManSet() {
        return linkManSet;
    }

    public void setLinkManSet(Set<LinkMan> linkManSet) {
        this.linkManSet = linkManSet;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCustName() {
        return CustName;
    }

    public void setCustName(String custName) {
        CustName = custName;
    }

    public String getCustLevel() {
        return CustLevel;
    }

    public void setCustLevel(String custLevel) {
        CustLevel = custLevel;
    }

    public String getCustSource() {
        return CustSource;
    }

    public void setCustSource(String custSource) {
        CustSource = custSource;
    }

    public String getCustPhone() {
        return CustPhone;
    }

    public void setCustPhone(String custPhone) {
        CustPhone = custPhone;
    }

    public String getCustMobile() {
        return CustMobile;
    }

    public void setCustMobile(String custMobile) {
        CustMobile = custMobile;
    }


}
