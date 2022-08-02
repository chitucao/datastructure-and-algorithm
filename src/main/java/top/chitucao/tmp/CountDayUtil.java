package top.chitucao.tmp;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Pair;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CountDayUtil {

    public static int countInActiveDays(final List<Pair<Date, Date>> regions) {

        Date nowTime = new Date();
        int nowMonthDays = (int) DateUtil.between(DateUtil.beginOfMonth(nowTime), DateUtil.endOfMonth(nowTime), DateUnit.DAY) + 1;

        if (CollUtil.isEmpty(regions)) {
            return nowMonthDays;
        }

        int[][] intervals = new int[regions.size()][2];
        int i = 0;
        for (Pair<Date, Date> region : regions) {
            intervals[i][0] = DateUtil.dayOfMonth(region.getKey());
            intervals[i][1] = DateUtil.dayOfMonth(region.getValue());
            i++;
        }

        Arrays.sort(intervals, Comparator.comparingInt(e -> e[0]));

        int l = intervals[0][0];
        int r = intervals[0][1];

        int inActiveDays = nowMonthDays;
        for (int j = 1; j < intervals.length; j++) {
            int nowL = intervals[j][0];
            int nowR = intervals[j][1];
            if (r < nowL) {
                inActiveDays -= (r - l + 1);
                l = nowL;
                r = nowR;
            } else {
                r = Math.max(r, nowR);
            }

            if (j == intervals.length - 1) {
                inActiveDays -= (r - l + 1);
            }
        }

        return inActiveDays;
    }

    public static void main(String[] args) {

        List<Pair<Date, Date>> regions = new ArrayList<>();
        regions.add(new Pair<>(DateUtil.parse("2022-07-20"), DateUtil.parse("2022-07-20")));
        regions.add(new Pair<>(DateUtil.parse("2022-07-21"), DateUtil.parse("2022-07-27")));
        regions.add(new Pair<>(DateUtil.parse("2022-07-11"), DateUtil.parse("2022-07-21")));

        int inActiveDays = countInActiveDays(regions);

        System.out.println("inActiveDays：" + inActiveDays);

    }
}
