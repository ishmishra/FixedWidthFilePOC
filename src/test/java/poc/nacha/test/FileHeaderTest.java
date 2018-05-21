package poc.nacha.test;

import com.univocity.parsers.common.processor.BeanWriterProcessor;
import com.univocity.parsers.fixed.FixedWidthFields;
import com.univocity.parsers.fixed.FixedWidthWriter;
import com.univocity.parsers.fixed.FixedWidthWriterSettings;
import org.junit.Test;
import poc.nachamfile.FileHeader;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class FileHeaderTest {

    @Test
    public void poc01WriteFixedWidthUsingAnnotatedBean() {

        // Writing to an in-memory byte array. This will be printed out to the standard output so you can easily see the result.
        ByteArrayOutputStream fixedWidthResult = new ByteArrayOutputStream();

        // CsvWriter (and all other file writers) work with an instance of java.io.Writer
        Writer outputWriter = new OutputStreamWriter(fixedWidthResult);

        //##CODE_START
        //FixedWidthFields lengths = new FixedWidthFields(1, 2, 10, 10, 5);
        FixedWidthFields fields = FixedWidthFields.forWriting(FileHeader.class);

        FixedWidthWriterSettings settings = new FixedWidthWriterSettings(fields);
        //settings.getFormat().setLineSeparator("\n");
        //settings.setAutoConfigurationEnabled(true);
        //settings.setHeaderWritingEnabled(false);
        //settings.getDefaultAlignmentForHeaders();


              // Any null values will be written as 0
        //settings.setNullValue("0");

        // Creates a BeanWriterProcessor that handles annotated fields in the FileHeader class.
        settings.setRowWriterProcessor(new BeanWriterProcessor<FileHeader>(FileHeader.class)    );
        //settings.setAutoConfigurationEnabled(true);

        // Sets the file headers so the writer knows the correct order when writing values taken from a FileHeader instance
       //settings.setHeaders("recordType", "fileNumber", "immediateDestinationCode", "immediateDestinationName", "pending");

        // Creates a writer with the above settings;
        FixedWidthWriter writer = new FixedWidthWriter(outputWriter, settings);


        // Writes the headers specified in the settings
        //writer.writeHeaders();

        // writes a fixed width row with empty values (as nothing was set in the FileHeader instance).
        //writer.processRecord(new FileHeader());

        FileHeader bean = new FileHeader();
        bean.setRecordType(1);
        bean.setFileNumber(23);
        bean.setImmediateDestinationCode(11223344);
        bean.setPending(true);
        bean.setImmediateDestinationName("POCNACHAMI");


        // writes a Fixed Width row with the values set in "bean".
        writer.processRecord(bean);
        writer.processRecord(bean);

        // you can still write rows passing in its values directly.
        //writer.writeRow(1, 24, 123456789, "POC NACHAM Creation", false);

        writer.close();

        //##CODE_END
        // Let's just print the resulting fixed width output
        System.out.println(fixedWidthResult.toString());
        //printAndValidate(fixedWidthResult.toString());
    }
}
