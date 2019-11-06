# KotlinTest
kolin语言接触  
## 程序入口
```
fun main(){
    println("HelloWorld");
}
```
## 函数
```
//连个Int参数 返回Int类型的函数
fun sum(a:Int,b:Int):Int{
    return a+b
}
```
```
//自动推导返回值类型
fun sum(a: Int ,b: Int) = a + b
```
```
//返回无意义的值（无返回值）
fun printSum(int: Int, b: Int):Unit{
    println("sum of $a and $b is ${a + b}")
}
```
