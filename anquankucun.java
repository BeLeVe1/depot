public class anquankucun {
    public static void main(String[] args) {
        //final int LT = 4; // 订货提前期
        final double C1 = 120; // 备件单位订货成本
        final double C2 = 100; // 备件单位库存储存成本
        final double C3 = 2500; // 备件总缺货惩罚成本
        final double C4 = 240; // 备件单位再订货成本
        final double C5 = 50; // 备件固定订货成本
        final int[] demand = {0, 10, 2, 10, 14, 21, 2, 14, 4, 36, 9, 18, 0, 0, 4, 35, 30, 15, 2, 8, 4, 0, 0, 1, 12, 12, 0, 40, 13, 0, 5, 5, 0, 28, 76, 1, 0, 0, 30, 38, 0, 1};
        final double[] fre = {0, 8.534793842, 10.09810132, 10.31508013, 11.88633916, 16.47270942, 9.475161286, 11.28635819, 1.370564919, 30.66858344, 10.46347996, 0.30358873, 4.795947096, -0.925727037, 5.281951733, 38.35362721, 31.00961929, 8.138654796, 2.640773239, 12.3995013, 7.997452506, 2.626204935, 5.105678965, 4.156698667, 3.716183708, 7.75016202, 1.009250134, 34.21845072, 21.41498147, 6.128680899, 2.081066151, 4.449213659, 0.889316252, 26.60318476, 65.87986793, 7.910284604, 7.0588449, 5.869786113, 18.49102563, 36.33016799, 18.14084143, 9.29643654}; // 每个周期的预测需求量

        int[] forecast = new int[fre.length];
        for (int i = 0; i < fre.length; i++) {
            forecast[i] = (int) Math.round(fre[i]);
        }

        final int T = forecast.length;
        final int initialInventory = 40; // 初始库存量
        final int R = 1000; // 再订货点

        int ss = 24; //安全库存
        double totalCost = calculateTotalCost(R, initialInventory, demand, forecast, T, C1, C2, C3, C4, C5, ss);

        System.out.println(totalCost);

    }

    public static double calculateTotalCost(int R, int initialInventory, int[] demand, int[] forecast, int T,  double C1, double C2, double C3, double C4, double C5, int ss) {
        double totalCost = 0;
        int[] inventory = new int[T]; // 库存
        inventory[0] = initialInventory; // 期初库存
        int[] order = new int[T]; // Q

        int[] shortage = new int[T]; // 是否缺货
        int[] ss_order = new int[T]; // 触及安全库存补货
        double[] cost = new double[T]; // 成本
        for (int t = 1; t < T; t++) {
            int maxInventory = ss + forecast[t];

            if (inventory[t - 1] < R) {

                order[t] = maxInventory - Math.max(ss, inventory[t - 1]);
                if (order[t] < 0) {
                    order[t] = shortage[t - 1] + R - Math.max(0, inventory[t - 1]);
                } //防止i大于最大库存
                cost[t] = C1 * order[t] + C5;
                //订购费用
            }
            inventory[t] = Math.max(ss, inventory[t - 1]) + order[t] - demand[t];
            //成本计算
            if (inventory[t] < 0) {
                //如果发生缺货
                shortage[t] = -inventory[t] + ss;
                cost[t] += C2 * Math.max(0, inventory[t - 1]) / 2 //库存管理费
                        + C4 * shortage[t] //补货费用
                        + C5 + C3;
            } else if (inventory[t] < ss) {
                //触及安全库存
                ss_order[t] = ss;
                cost[t] += C2 * (inventory[t - 1] - inventory[t]) / 2 //库存管理费
                        + C4 * ss_order[t] //补货费用
                        + C5;
            }
        }
        totalCost = calculateSum(cost);

        return totalCost;
    }

    public static double calculateSum(double[] arr) {
        double sum = 0;
        for (double d : arr) {
            sum += d;
        }
        return sum;
    }
    public static double getdemand(String sparepartsName) {
        double sum = 0;
        for (double d : arr) {
            sum += d;
        }
        return sum;
    }
}
