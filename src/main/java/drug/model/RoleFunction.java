package drug.model;

public class RoleFunction {
    private Integer rfno;

    private String roleno;

    private String functionno;

    public Integer getRfno() {
        return rfno;
    }

    public void setRfno(Integer rfno) {
        this.rfno = rfno;
    }

    public String getRoleno() {
        return roleno;
    }

    public void setRoleno(String roleno) {
        this.roleno = roleno == null ? null : roleno.trim();
    }

    public String getFunctionno() {
        return functionno;
    }

    public void setFunctionno(String functionno) {
        this.functionno = functionno == null ? null : functionno.trim();
    }
}