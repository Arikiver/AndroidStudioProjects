fun fib(n: Int) :Int{
    if(n==0) return 0;
    else if(n==1) return 1;

    return fib(n-1)+fib(n-2);
}

//longest palindrom substring of a given string
fun palindrome(str: String): Boolean{ //:Unit if no return type than don't put any datatype
    val n = str.length;
    for(i in 0 until n/2){ //val i=0;i<n/2;i++ wrong
        if(str[i]!=str[n-i-1]) {
//          println("Not Palindrome");
            return false;
        }
    }
//      println("Palindrome");
    return true;
}

// fun largestNumber(n1:Int,n2:Int):Boolean{
// 	if(n1>n2) return true;
//     else return false;
// }

fun longestPalindromeSubString(str :String):String{
    val n =str.length;

    var longestPal = str.substring(0,1);
    for(i in 0 until n){


        for(j in i+1 until n){

            val subStr = str.substring(i,j+1);

            //var n_temp = str.length;

//    		for(i in 0 until n_temp/2){ //val i=0;i<n/2;i++ wrong
            //var check = subStr.length>longestPal.length;
            if(palindrome(subStr) && subStr.length>longestPal.length) {

                //if(i==n_temp/2) temp_pal = str_temp;
                longestPal = subStr;
            }
            // }

        }
    }
    return  longestPal;
}

fun main() {
//     println(fib(10));
//     palindrome("nitin")
//      palindrome("hello")
    print(longestPalindromeSubString("abbacd"));
}