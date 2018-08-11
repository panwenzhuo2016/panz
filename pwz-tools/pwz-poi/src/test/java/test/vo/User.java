package test.vo;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018/8/11 12:38
 **/
public class User {
    private String name;
    private String id;
    private String phone;
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public User(String name, String id, String phone, String sex) {
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.sex = sex;
    }
}
