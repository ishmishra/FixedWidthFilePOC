package poc.nachamfile;

import com.univocity.parsers.annotations.*;
import com.univocity.parsers.fixed.FieldAlignment;

@Headers(sequence = {"recordType", "fileNumber", "immediateDestinationCode", "immediateDestinationName", "pending"}, extract = false, write = false)
public class FileHeader {

    // if the value parsed in the recordType column is "?" or "-", it will be replaced by null.
    @NullString(nulls = {"?", "-"})
    // if a value resolves to null, it will be converted to the String "1".
    @Parsed(defaultNullRead = "1")
    //We can choose padding,default value,start and end index along with alignment
    @FixedWidth(padding = ' ', from = 0, to = 1, alignment = FieldAlignment.LEFT)
    private Integer recordType;   // The attribute type defines which conversion will be executed when processing the value.
    // In this case, IntegerConversion will be used.
    // The attribute name will be matched against the column header in the file automatically.


    // you can also explicitly give the name of a column in the file.
    @Parsed(field = "fileNumber")
    @FixedWidth(padding = ' ', from = 1, to = 3, alignment = FieldAlignment.LEFT)
    private Integer fileNumber;

    // you can also explicitly give the name of a column in the file.
    @Parsed(field = "immediateDestinationCode")
    @FixedWidth(padding = '0', from = 3, to = 14, alignment = FieldAlignment.RIGHT)
    private Integer immediateDestinationCode;

    @Trim
    @LowerCase
    // the value for the immediateDestinationName attribute is in the column at index 13 (0 is the first column, so this means 14th column in the file)
    @Parsed
    @FixedWidth(padding = ' ', from = 14, to = 25, alignment = FieldAlignment.LEFT)
    private String immediateDestinationName;


    @Trim
    @LowerCase
    // values "no", "n" and "null" will be converted to false; values "yes" and "y" will be converted to true
    @BooleanString(falseStrings = {"no", "n", "null"}, trueStrings = {"yes", "y"})
    @Parsed
    @FixedWidth(padding = ' ', from = 26, to = 29, alignment = FieldAlignment.LEFT)

    private Boolean pending;


    //Getter and Setters
    public Integer getRecordType() {
        return recordType;
    }

    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
    }

    public Integer getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(Integer fileNumber) {
        this.fileNumber = fileNumber;
    }

    public Integer getImmediateDestinationCode() {
        return immediateDestinationCode;
    }

    public void setImmediateDestinationCode(Integer immediateDestinationCode) {
        this.immediateDestinationCode = immediateDestinationCode;
    }

    public String getImmediateDestinationName() {
        return immediateDestinationName;
    }

    public void setImmediateDestinationName(String immediateDestinationName) {
        this.immediateDestinationName = immediateDestinationName;
    }

    public Boolean getPending() {
        return pending;
    }

    public void setPending(Boolean pending) {
        this.pending = pending;
    }

    @Override
    public String toString() {
        return "FileHeader{" +
                "recordType=" + recordType +
                ", fileNumber=" + fileNumber +
                ", immediateDestinationCode=" + immediateDestinationCode +
                ", immediateDestinationName='" + immediateDestinationName + '\'' +
                ", pending=" + pending +
                '}';
    }
}
