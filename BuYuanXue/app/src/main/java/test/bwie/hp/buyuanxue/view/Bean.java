package test.bwie.hp.buyuanxue.view;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/9/28 19 45
 */

public class Bean {
   private String code;
    private String phone;

    public Bean(String code, String phone) {
        this.code = code;
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "code='" + code + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
