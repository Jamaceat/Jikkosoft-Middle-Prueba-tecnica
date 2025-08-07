package co.com.utils;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    
    public static List<Integer[]> findIndexesToGetTarget(List<Integer> numbers,Integer target){
        List<Integer[]> result=new ArrayList<>();

        for (int i = 0; i < numbers.size(); i++) {
            for (int j = i+1; j < numbers.size(); j++) {
                if(numbers.get(i)+numbers.get(j)==target)result.add(new Integer[]{i,j});
            }
        }
        return result;
    }
}
