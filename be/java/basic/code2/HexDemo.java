package com.test;
/* ====================================================
 * 进制 是一种进位的方式  x进制 表示逢x进1
 * 
 * 计算机的电子原件的状态 开 关
 * 10110110
 * 
 * 如果我们表达数据仅仅用这两种状态 那么能够表达的数据是比较少的
 * 而我们常见的数据 英文字母 数字 标点符号 这就很多了
 * 两个状态肯定是不够的
 * 为了能够表示更多的数据 国际化标准组织就规定 用8个这样的信号来表示一个数据 这个数据的单位叫 字节
 * 后来 我们就通过数字 1 0 分别来表示开和关 这个时候 我们把上面的数据用 1 0 改进
 * 由这样的 1 0 组成的数据就是二进制数据
 * 
 * 1byte = 8bit
 * 1k = 1024byte
 * 1m = 1024k
 * 1g = 1024m
 * 1t = 1024g
 * 
 * 但是呢 用二进制表达数据的表现形式有点长 所以呢 我们就要简化一下 如何简化呢
 * 
 * 八进制表示
 * 把二进制的数据 从右开始 每三位一组合 最左边不够的时候 补0 然后 分别计算出对应的十进制数值
 * 最后 在把每个十进制的数据组合起来 就是一个八进制数据
 * 
 * 010 100 100 --- 转为十进制 = 八进制
 * 
 * 十六进制表示
 * 对于八进制表现形式还不是最简单的 还可以用十六进制来表现
 * 把二进制的数据 从右开始 每四位一组合 最左边不够的时候 补0 然后 分别计算出对应的十进制数值 
 * 最后 再把每个十进制的数据组合起来 就是一个十六进制数据
 * 
 * 1011 0110 --- 转为十进制 = 十六进制
 * 
 * 规则 进制越大 表现形式越短
 * ====================================================
 * 进制转换 其他进制到十进制的转换
 * 
 * 12345 = 1*10^4 + 2*10^3 + 3*10^2 + 4*10^1 + 5*10^0
 *       = 10000 + 2000 + 300 + 40 + 5
 * 
 * 系数 每一个位上的数据值本身就是系数
 * 基数 x进制的基数就是x
 * 权 我们针对每一个位上的数据进行编号 从右边 并且是从0开始编号 这个编号就是该位上数据的权值
 * 
 * 第一个位上的系数*基数^权次幂相加
 * 
 * 二进制 100 --- 十进制 4
 * 100 = 1*2^2 + 0*2^1 + 0*2^0
 *     = 4 + 0 + 0
 *     = 4
 *     
 * 八进制 100 --- 十进制 64
 * 100 = 1*8^2 + 0*8^1 + 0*8^0
 *     = 64 + 0 + 0
 *     = 64
 *     
 * 十六进制 100 --- 十进制 256
 * 100 = 1*16^2 + 0*16^1 + 0*16^0
 *     = 256 + 0 + 0
 *     = 256
 *     
 * 得到下面数据的十进制值
 * 0b10101 = 1*2^4 + 1*2^2 + 1*2^0
 *         = 16 + 4 + 1
 *         = 21
 *         
 * 0123 = 1*8^2 + 2*8^1 + 3*8^0
 *      = 64 + 16 + 3
 *      = 83
 *      
 * 0x3c = 3*16^1 + c*16^0
 *      = 48 + 12
 *      = 60
 *      
 * 十进制转其他进制
 * 除基取余 直到商为0 余数反转
 * 
 * 得到下面数据的二进制 十进制 十六进制 52分别得到二进制 十进制 十六进制
 * 
 * 得到二进制
 * 52 / 2 = 26  0
 * 26 / 2 = 13  0
 * 13 / 2 = 6   1
 * 6  / 2 = 3   0
 * 3  / 2 = 1   1
 * 1  / 2 = 0   1
 * 
 * 0b110100
 * 
 * 得到八进制
 * 52 / 8 = 6   4
 * 6  / 8 = 0   6
 * 
 * 064
 * 
 * 得到十六进制
 * 52 / 16 = 3  4
 * 3  / 16 = 0  3
 * 
 * 0x34
 * 
 * 快速转换法
 * 8421码 是bcd码一种
 * 它表达的意思是每一个二进制位上的数据对应一个固定的值
 * 只需要把对应的位置的数据值给相加 即可得到该二进制对应的十进制的值
 * 
 * 二进制  1   1   1   1   1   1   1   1
 * 十进制  128 64  32  16  8   4   2   1
 * 
 * 二进制到十进制的转换
 * 1010100 = 64 + 16 + 4 = 84
 * 十进制到二进制的转换
 * 100 = 0b1100100
 * 
 * 问题 任意的x进制到y进制的转换 请部肿么办
 * x进制 --- 十进制
 * 十进制 --- y进制
 * 
 * 二进制到八进制 十六进制
 * 二进制到十进制 十进制到八或者十六进制
 * 拆分组合法
 * 
 * 0b1011001 --- 八进制
 * 0b1011001 = 64 + 16 + 8 + 1 = 89(十进制) = 0131
 * 89 / 8 = 11  1
 * 11 / 8 = 1   3
 * 1  / 8 = 0   1
 * 
 * 0b1011001 001 011 001 = 0131
 * ====================================================
 * 原码 反码 补码
 * 
 * 有符号数据的表示法 原码 反码 补码
 * 
 * 为什么讲 因为计算机在操作的时候 都是采用数据对应的二进制的补码来计算的
 * 
 * 原码 反码 补码分别表示 +7 和 -7
 * 
 * 7 的二进制 111
 * 
 * 原码 正数的原码最高位是0 负数的原码最高位是1 其他的是数值位
 * 
 *    符号位  数值位
 * +7 0    0000111 
 * -7 1    0000111
 * 
 * 反码 正数的反码与原码相同 负数的反码与原码符号相同 数值位取反 就是1变0 0变1
 * 
 *    符号位  数值位
 * +7 0    0000111
 * -7 1    1111000
 * 
 * 补码 正数的补码与原码相同 负数的补码是在反码的基础上加1
 * 
 *    符号位  数值位
 * +7 0    0000111
 * -7 1    1111001
 * 
 * 有符号数据表示法的练习
 * 
 * 已知某数x的原码为 10110100B 试求x的补码和反码
 * 
 *    符号位  数值位
 * 原码 1    0110100
 * 反码 1    1001011
 * 补码 1    1001100
 * 
 * 已知某数x的补码11101110B 试求其原码
 *    符号位  数值位
 * 补码 1    1101110
 * 反码 1    1101101
 * 原码 1    0010010
 * ====================================================
 */
/**
 * 不同进制的数据表现
 * 二进制 由0 1 组成 以0b开头
 * 八进制 由0,1 ... 7组成 以0开头
 * 十进制 由0,1 ... 9组成 默认整数是十进制
 * 十六进制 由0,1 ... 9,a,b,c,d,e,f(大小写均可)组成 以0x开头
 *
 */

public class HexDemo {
	public static void main(String[] args) {
		System.out.println(100); // 十进制
		
		System.out.println(0b100); // 二进制
		System.out.println(0100); // 八进制
		System.out.println(0x100); // 十六进制
	}
}
