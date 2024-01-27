import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.util.Comparator.comparingDouble;


class Record  {String id; double value; Record(String s,double v) {id=s;value=v;}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
class InputType {Stream<Stream<Record>> inputStream;}


public class KariStream {

        public static void main(String[] args) {
            Stream<Record> S1 = Arrays.asList(new Record[] {
                    new Record("A",1.2),
                    new Record("A",2.5),
                    new Record("A",0.7),
                    new Record("B",0.5),
                    new Record("B",1.6),
                    new Record("B",0.2)
            }).stream();
            Stream<Record> S2 = Arrays.asList(new Record[] {
                    new Record("A",0.8),
                    new Record("A",1.8),
                    new Record("A",0.1),
                    new Record("A",0.7),
                    new Record("B",2.5),
                    new Record("B",1.7),
                    new Record("B",2.6)
            }).stream();
            InputType input = new InputType();
            input.inputStream = Stream.of(S1,S2);
            process(input);
        }
        static void process(InputType input){
            input.inputStream.forEach(
                    streamOfRecords -> {
                        streamOfRecords.collect(
                                Collectors.groupingBy(Record::getId, Collectors.maxBy(comparingDouble(Record::getValue))))
                        .forEach((String id,Optional<Record> optRecord) -> {
                            System.out.print("["+id + " " +  optRecord.get().getValue()+"]");
                            });
                        });



        }

}
