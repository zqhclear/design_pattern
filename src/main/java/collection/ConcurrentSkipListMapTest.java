package collection;

/**
 * @desc: 跳表
 * 	1.redis是使用跳表来实现有序集合,其中，插入、删除、查找以及迭代输出有序序列这几个操作，
 * 	红黑树也可以完成，时间复杂度和跳表是一样的。但是，按照区间查找数据这个操作，红黑树的
 * 	效率没有跳表高。跳表可以在 O(logn) 时间复杂度定位区间的起点，然后在原始链表中顺序向后
 * 	查询就可以了，这样非常高效。
 *
 * 此外，相比于红黑树，跳表还具有代码更容易实现、可读性好、不容易出错、更加灵活等优点，
 * 因此 Redis 用跳表来实现有序集合。
 * @author: zhongqionghua
 * @create: 2019/5/14 9:37
 */
public class ConcurrentSkipListMapTest {
}
