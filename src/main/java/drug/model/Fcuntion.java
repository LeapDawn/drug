package drug.model;

public class Fcuntion {
    private String functionno;

    private String name;

    private String url;

    private String remark;

    public String getFunctionno() {
        return functionno;
    }

    public void setFunctionno(String functionno) {
        this.functionno = functionno == null ? null : functionno.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}