// create a BeanIO StreamFactory
        StreamFactory factory = StreamFactory.newInstance();
        // load the mapping file from the working directory
        factory.load(getClass().getClassLoader().getResource("m.xml").getFile());

        // create a BeanReader to read from "input.csv"
        BeanReader in = factory.createReader("File",
                new File("E.txt"));

        Object record = null;

        // read records from "nacha"
        while ((record = in.read()) != null) {

            System.out.println("***RecordName is :"+in.getRecordName()+"******");
            // process each record
            if ("FH".equals(in.getRecordName()) ) {


                FH fileH= (FH) record;

                System.out.println(fileH.getApplicationCode());
               
            }




        }
