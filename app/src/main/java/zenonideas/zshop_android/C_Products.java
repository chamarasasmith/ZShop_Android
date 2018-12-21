package zenonideas.zshop_android;

public class C_Products {

    private String pid;
    private String pname;
    private String qty;
    private String sp;
    private String cat;
    private String bname;
    private String img;
    private String des;
    private String min;
    private String st;

    public C_Products() {
    }

    public C_Products(String pid, String pname, String qty, String sp, String cat, String bname, String img,String des,String min,String st) {
        this.pid = pid;
        this.pname = pname;
        this.qty = qty;
        this.sp = sp;
        this.cat = cat;
        this.bname = bname;
        this.img = img;
        this.des = des;
        this.min = min;
        this.st=st;
    }

    public String getPid() {
        return pid;
    }

    public String getPname() {
        return pname;
    }

    public String getQty() {
        return qty;
    }

    public String getSp() {
        return sp;
    }

    public String getCat() {
        return cat;
    }

    public String getBname() {
        return bname;
    }

    public String getImg() {
        return img;
    }

    public String getSt() {
        return st;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public void setSp(String sp) {
        this.sp = sp;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }
}
