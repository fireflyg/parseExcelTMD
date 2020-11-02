package util;

/**
 * excel表格行对象
 */
public class ExcelCell {
    private String number;
    private String code;

    public ExcelCell() {

    }
    public ExcelCell(String number, String code) {
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
