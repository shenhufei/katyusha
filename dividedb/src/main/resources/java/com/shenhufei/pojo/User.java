package com.shenhufei.pojo;

import com.shenhufei.annotations.ignore;
import lombok.Data;

import java.util.Date;

/**
 * 用户实体类
 *
 * @author shenhufei
 *
 */
@Data
public class User {
    /*
     * private short shorts; private Short shortSs;
     * 
     * private Byte bytes; private byte bytess;
     * 
     * private int a; private Integer b;
     * 
     * private float c; private Float D;
     * 
     * private Double money; private double moneys;
     */
    private Long id;
    /*
     * private long g;
     * 
     * private Boolean j;
     */
    private boolean k;

    private Date p;

    private String name;
    private Integer age;
    @ignore
    private String test;

    public String getTest() {
        return test;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isK() {
        return k;
    }

    public void setK(boolean k) {
        this.k = k;
    }

    public Date getP() {
        return p;
    }

    public void setP(Date p) {
        this.p = p;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setTest(String test) {
        this.test = test;
    }

    /*
     * public short getShorts() { return shorts; }
     * 
     * public void setShorts(short shorts) { this.shorts = shorts; }
     * 
     * public Short getShortSs() { return shortSs; }
     * 
     * public void setShortSs(Short shortSs) { this.shortSs = shortSs; }
     * 
     * public Byte getBytes() { return bytes; }
     * 
     * public void setBytes(Byte bytes) { this.bytes = bytes; }
     * 
     * public byte getBytess() { return bytess; }
     * 
     * public void setBytess(byte bytess) { this.bytess = bytess; }
     * 
     * public int getA() { return a; }
     * 
     * public void setA(int a) { this.a = a; }
     * 
     * public Integer getB() { return b; }
     * 
     * public void setB(Integer b) { this.b = b; }
     * 
     * public float getC() { return c; }
     * 
     * public void setC(float c) { this.c = c; }
     * 
     * public Float getD() { return D; }
     * 
     * public void setD(Float d) { D = d; }
     * 
     * public Double getMoney() { return money; }
     * 
     * public void setMoney(Double money) { this.money = money; }
     * 
     * public double getMoneys() { return moneys; }
     * 
     * public void setMoneys(double moneys) { this.moneys = moneys; }
     * 
     * public long getG() { return g; }
     * 
     * public void setG(long g) { this.g = g; }
     * 
     * public Boolean getJ() { return j; }
     * 
     * public void setJ(Boolean j) { this.j = j; }
     * 
     * public boolean isK() { return k; }
     * 
     * public void setK(boolean k) { this.k = k; }
     * 
     * public Date getP() { return p; }
     * 
     * public void setP(Date p) { this.p = p; }
     * 
     * public void setTest(String test) { this.test = test; }
     * 
     * public User() { super(); }
     * 
     * public Long getId() { return id; }
     * 
     * public void setId(Long id) { this.id = id; }
     * 
     * public String getName() { return name; }
     * 
     * public void setName(String name) { this.name = name; }
     * 
     * public Integer getAge() { return age; }
     * 
     * public void setAge(Integer age) { this.age = age; }
     * 
     * public User(Long id, String name, Integer age) { super(); this.id = id;
     * this.name = name; this.age = age; }
     */
}
