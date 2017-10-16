package bean;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/15 12 34
 */

public class Bean {

    /**
     * itemtype : 0
     * createtime : 2017-10-10T17:33:37
     * detailUrl : https://item.m.jd.com/product/4338107.html?utm#_source=androidapp&utm#_medium=appshare&utm#_campaign=t#_335139774&utm#_term=QQfriends
     * title : 小米(MI)Air 13.3英寸全金属轻薄笔记本(i5-7200U 8G 256G PCle SSD MX150 2G独显 FHD 指纹识别 Win10）银

     * price : 5199
     * pscid : 40
     * salenum : 4343
     * subhead : 【i5 MX150 2G显存】全高清窄边框 8G内存 256固态硬盘 支持指纹识别 预装WIN10系统
     * images : https://m.360buyimg.com/n0/jfs/t6700/155/2098998076/156185/6cf95035/595dd5a5Nc3a7dab5.jpg!q70.jpg
     * pid : 57
     * sellerid : 1
     * bargainPrice : 11800
     */

    private int itemtype;
    private String createtime;
    private String detailUrl;
    private String title;
    private int price;
    private int pscid;
    private int salenum;
    private String subhead;
    private String images;
    private int pid;
    private int sellerid;
    private int bargainPrice;

    public int getItemtype() {
        return itemtype;
    }

    public void setItemtype(int itemtype) {
        this.itemtype = itemtype;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPscid() {
        return pscid;
    }

    public void setPscid(int pscid) {
        this.pscid = pscid;
    }

    public int getSalenum() {
        return salenum;
    }

    public void setSalenum(int salenum) {
        this.salenum = salenum;
    }

    public String getSubhead() {
        return subhead;
    }

    public void setSubhead(String subhead) {
        this.subhead = subhead;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getSellerid() {
        return sellerid;
    }

    public void setSellerid(int sellerid) {
        this.sellerid = sellerid;
    }

    public int getBargainPrice() {
        return bargainPrice;
    }

    public void setBargainPrice(int bargainPrice) {
        this.bargainPrice = bargainPrice;
    }
}
