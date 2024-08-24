fun fibNum(num1 : Int, num2 : Int){
    if (num2 > 50)
        return
    val nxt = (num1+num2)
    print(", $nxt")
    return fibNum(num2, nxt)
}

fun main(){
    print("0, 1")
    fibNum(0, 1)
}