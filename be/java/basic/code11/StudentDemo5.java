package com.test;

/*
 * public boolean equals(Object obj):指示其他某个对象是否与此对象“相等”。 
 * 这个方法，默认情况下比较的是地址值。比较地址值一般来说意义不大，所以我们要重写该方法。
 * 怎么重写呢?
 * 		一般都是用来比较对象的成员变量值是否相同。
 * 重写的代码优化：提高效率，提高程序的健壮性。
 * 最终版：
 * 		其实还是自动生成。
 * 
 * 看源码：
 * 		public boolean equals(Object obj) {
 * 			//this - s1
 * 			//obj - s2
 *       	return (this == obj);
 *   	}
 * 
 * ==:
 * 		基本类型：比较的就是值是否相同
 * 		引用类型：比较的就是地址值是否相同
 * equals:
 * 		引用类型：默认情况下，比较的是地址值。
 * 		不过，我们可以根据情况自己重写该方法。一般重写都是自动生成，比较对象的成员变量值是否相同
 */
public class StudentDemo5 {
	public static void main(String[] args) {
		Student5 s1 = new Student5("林青霞", 27);
		Student5 s2 = new Student5("林青霞", 27);
		System.out.println(s1 == s2); // false
		Student5 s3 = s1;
		System.out.println(s1 == s3);// true
		System.out.println("---------------");

		System.out.println(s1.equals(s2)); // obj = s2; //false
		System.out.println(s1.equals(s1)); // true
		System.out.println(s1.equals(s3)); // true
		Student5 s4 = new Student5("风清扬",30);
		System.out.println(s1.equals(s4)); //false
		
		Demo2 d = new Demo2();
		System.out.println(s1.equals(d)); //ClassCastException

	}
}

class Demo2 {}