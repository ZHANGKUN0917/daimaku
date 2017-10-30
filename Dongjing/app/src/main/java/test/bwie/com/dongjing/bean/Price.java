package test.bwie.com.dongjing.bean;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/20 14 30
 */

public class Price {
    private int price;

    public Price(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Price{" +
                "price=" + price +
                '}';
    }
}
