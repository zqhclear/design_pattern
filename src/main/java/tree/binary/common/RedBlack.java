package tree.binary.common;

/**
 * @Description: 红黑树
 * 特性：
 * 1.每个节点只能为red或者black
 * 2.根节点为black
 * 3.每个叶子节点都是black（为NIL的节点）
 * 4.red节点不能连续（即：一个red节点的父节点和子节点都不能为red）
 * 5.对于每个节点，从该点至null（树尾端）的任何路径，都含有相同个数的black节点（这一条就可能出现两个连续的black节点）。
 *
 *
 * <p>
 * 插入后修正树型结构的三种情况：
 * <p>
 * case1:当前节点的父节点为red，当前节点的祖父节点的另外一个子节点（叔叔节点）为red
 * a.将"父节点"和"叔叔节点"置为black;
 * b.将"祖父节点"置为red;
 * c.将"祖父节点"置为当前节点（即之后是操作该祖父节点）
 * <p>
 * case2:当前节点的"父节点"为red，但是其"叔叔节点"为black，并且当前节点为"父节点"的右子节点
 * a.将"父节点"置为“当前节点”
 * b."当前节点"左旋
 * <p>
 * case3:当前节点的"父节点"为red，但是"叔叔节点"为black，并且当前节点为左子节点
 * a.将"父节点"设为black
 * b.将"祖父节点"设为red
 * c.以"祖父节点"为支点进行右旋
 * @Author: zhongqionghua
 * @CreateDate: 2018/11/1 15:09
 */
public class RedBlack {
}
