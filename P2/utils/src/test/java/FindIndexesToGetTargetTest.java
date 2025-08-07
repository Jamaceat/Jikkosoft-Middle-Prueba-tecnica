import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import co.com.utils.Utils;

public class FindIndexesToGetTargetTest {

    @Test
    void getIndexesFromIntegerList() {
        
        List<Integer> exampleList = IntStream.rangeClosed(0, 20)
                .boxed()
                .collect(Collectors.toList()); // 0 1 2 ... 20

        Integer targetNumber = 25;

        List<Integer[]> result = Utils.findIndexesToGetTarget(exampleList, targetNumber);

        result.stream()
                .map(list -> list[0] + ": value->" + exampleList.get(list[0]) + "::" + list[1] + ": value->"
                        + exampleList.get(list[1]))
                .forEach(System.out::println);

        for (Integer[] integers : result) {
            
            assertEquals(targetNumber, exampleList.get(integers[0])+exampleList.get(integers[1]),"El resultado no es el esperado");
        }


    }

        @Test
    void getIndexesFromRandomIntegerList() {
        Integer lowerRange=10;
        Integer upperRange=50;
        List<Integer> exampleList = IntStream.rangeClosed(lowerRange,upperRange).boxed().collect(Collectors.toList());
        Collections.shuffle(exampleList);

        Integer targetNumber = 35;

        List<Integer[]> result = Utils.findIndexesToGetTarget(exampleList, targetNumber);

        result.stream()
                .map(list -> list[0] + ": value->" + exampleList.get(list[0]) + "::" + list[1] + ": value->"
                        + exampleList.get(list[1]))
                .forEach(System.out::println);

        for (Integer[] integers : result) {
            
            assertEquals(targetNumber, exampleList.get(integers[0])+exampleList.get(integers[1]),"El resultado no es el esperado");
        }


    }

            @Test
    void getIndexesFromRandomIntegerListSize2Succesful() {
        Integer lowerRange=10;
        Integer upperRange=11;
        List<Integer> exampleList = IntStream.rangeClosed(lowerRange,upperRange).boxed().collect(Collectors.toList());
        Collections.shuffle(exampleList);

        assertEquals(2, exampleList.size(),"Se espera que la lista de numero sea 2 ");
        Integer targetNumber = 21;

        List<Integer[]> result = Utils.findIndexesToGetTarget(exampleList, targetNumber);

        result.stream()
                .map(list -> list[0] + ": value->" + exampleList.get(list[0]) + "::" + list[1] + ": value->"
                        + exampleList.get(list[1]))
                .forEach(System.out::println);

        for (Integer[] integers : result) {
            
            assertEquals(targetNumber, exampleList.get(integers[0])+exampleList.get(integers[1]),"El resultado no es el esperado");
        }


    }

                @Test
    void getIndexesFromRandomIntegerListSize2Imposible() {
        Integer lowerRange=10;
        Integer upperRange=11;
        List<Integer> exampleList = IntStream.rangeClosed(lowerRange,upperRange).boxed().collect(Collectors.toList());
        Collections.shuffle(exampleList);

        assertEquals(2, exampleList.size(),"Se espera que la lista de numero sea 2 ");
        Integer targetNumber = 22;

        exampleList.stream().forEach(System.out::println);

        List<Integer[]> result = Utils.findIndexesToGetTarget(exampleList, targetNumber);

        result.stream()
                .map(list -> list[0] + ": value->" + exampleList.get(list[0]) + "::" + list[1] + ": value->"
                        + exampleList.get(list[1]))
                .forEach(System.out::println);

        assertTrue(result.isEmpty());
    }

}
