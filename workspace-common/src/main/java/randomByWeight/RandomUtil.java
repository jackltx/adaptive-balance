package randomByWeight;

import java.util.*;

/**
 * 以当前可用线程数作为权重，确定目标服务器
 */
public class RandomUtil {
//    public static String randByWeight(Map<String, Long> weights) {
//        Preconditions.checkArgument(weights != null && !weights.isEmpty(), "权重数组不能为空");
//        double sum = 0;
//        for (String s : weights.keySet()) {
//            Preconditions.checkArgument(weights.get(s) != null, "权重不能为空");
//            sum += weights.get(s);
//        }
//        double r = randDbl(0, sum);
//        for (String name:weights.keySet()){
//            r -= weights.get(name);
//            if (r <= 0) {
//                return name;
//            }
//        }
//        throw new RuntimeException("加权随机计算错误？？？");
//    }
    public static String randByWeight(Map<String, Long> weights) {
        Preconditions.checkArgument(weights != null && !weights.isEmpty(), "权重不能为空");
        Map<String, Long> temp=weights;
        double sum = 0;
        for (String s : weights.keySet()) {
            if (weights.get(s)<0){
                weights.remove(s);
                continue;
            }
            Preconditions.checkArgument(weights.get(s) != null, "权重不能为空");
            sum += weights.get(s);
        }
        double r = randDbl(0, sum);
        if (weights==null||weights.isEmpty()){
            double v = randDbl(0, 3);
            String[] array=new String[3];
            int i=0;
            for (String s:temp.keySet()){
                array[i++] = s;
            }
            if (v<1){
                return array[0];
            }else if (v<2){
                return array[1];
            }else {
                return array[2];
            }
        }
        for (String name:weights.keySet()){
            r -= weights.get(name);
            if (r <= 0) {
                return name;
            }
        }

        throw new RuntimeException("加权随机计算失败");
    }

    /**
     * 计算一定范围内的一个随机浮点数
     */
    public static double randDbl(double lb, double rb) {
        return lb + Math.random() * (rb - lb);
    }

//    public static String randByWeight(Map<String, Long> weights) {
//        String resultName="";
//        int maxValue=Integer.MIN_VALUE;
//        for (String name:weights.keySet()){
//            if (weights.get(name)>maxValue){
//                maxValue=weights.get(name).intValue();
//                resultName=name;
//            }
//        }
//        return resultName;
//    }
}
