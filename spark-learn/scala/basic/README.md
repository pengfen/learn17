intro.html (介绍 安装）

1. 编写 UrlCount

2. 编写 Master

3. 编写 VariDemo (变量, 懒值)

4. 编写 TypeDemo 类型

5. 编写 BlockDemo 块表达式

6. 编写 ForDemo 循环

7. 编写 MethodDemo (方法 函数)


8. 编写 MetaDemo (元组　下划线)

9. 编写 ArrayDemo (定长数组)

10. 编写 ArrayBufferDemo (变长数组)

11. 编写 ForArray (遍历数组)

12. 编写 YieldArray (数组转换)

13. 编写 MapArray (映射)

14. 编写 JoinArray (拉链)

集合 (序列Seq 集Set 映射Map) --->　所有的集合都扩展自Iterable特质
15. 编写 ListDemo (List) ---> collect.ImmutListDemo

16. 编写 MutListDemo (可变序列) ---> collect.MutListDemo

17. 编写 ImmutSetDemo (不可变Set) ---> collect.ImmutSetDemo

18. 编写 MutSetDemo (可变set) ---> collect.MutSetDemo

19. 编写 MutMapDemo (可变map)


20. 编写 Learn (练习)

21. 编写 WelScala (scala特性)


22. 编写 Person

23. 编写 People

24. 编写 Student (介绍主构造器)

25. 编写 Queen (介绍构造器)

26. 编写 SingletonDemo (单例类)

27. 编写 Dog (伴生对象)

28. 编写 ApplyDemo (apply方法)

29. 编写 AppDemo (应用程序对象)

类的继承 实现
30. 编写 Animal

31. 编写 Human

32. 编写 Chinese

32. 编写 Monkey

33. 编写 Flyable

34. Case (匹配) ---> cases 包


35. 编写 UpperBound (泛型) ---> bound.html
scala泛型

class Person[T] {

	def choose[T <: Comparable[T]](firit: T, second: T): T  = {
		first
	}
}



隐式转换：我自己的隐式上下文 object MyPredef{
	implicit 函数
	implicit 值
}


viewbound要求传入一个隐式转换函数

class Chooser[T <% Ordered[T]] {
	def bigger(first: T, second: T) : T = {
		if(first > second) first else second
	}
}

class Chooser[T] {
	def bigger(first: T, second: T)(implicit ord: T => Ordered[T]) : T = {
		if(first > second) first else second
	}
}


contextbound要求传入一个隐式转换值

class Chooser[T: Ordering] {
	def bigger(first: T, second: T) : T = {
		val ord = implicitly[Ordering[T]]
		if(ord.gt(first, second)) first else second
	}
}

class Chooser[T] {
	def bigger(first: T, second: T)(implicit ord : Ordering[T]) : T = {
		if(ord.gt(first, second)) first else second
	}
}

[+T]
[-T]


spark
one stack to rules them all

RDD Spark-SQL Spark-Streaming Graphx MLlib

Master Worker

Master管理所有的Worker，进而进行资源的调度

Worker管理当前计算节点，Worker会启动一个Executor来完成真正的计算


36. 编写 scala-intro.html