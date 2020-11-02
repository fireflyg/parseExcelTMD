package test;

/**
 * excel表格行对象
 */
public class TestExcelCell {
    private String number;        //提煤单号
    private String code;          //加密提煤单号

    public TestExcelCell() {

    }
    public TestExcelCell(String number, String code) {
        this.number = number;
        this.code = code;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    @Override
    public String toString() {
        return "ExcelCell{" +
                "number='" + number + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

}
