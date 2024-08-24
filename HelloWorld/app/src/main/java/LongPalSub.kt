fun longPal(s: String) : String{
    val n = s.length
    if (n == 0) return ""

    val lp = Array(n) { BooleanArray(n) }
    var start = 0;
    var maxLen = 1;

    // single length will always be palindromic
    for (i in 0 until n) lp[i][i] = true;

    // 2 length palindrome check
    for (i in 0 until n-1){
        if (s[i] == s[i+1]) {
            lp[i][i + 1] = true
            start = i
            maxLen = 2
        }
    }

    // >2 length plindrome check
    for (len in 3 until n+1){
        for(i in 0 until n-len+1 ){
            val j = i + len - 1
            if (s[i] == s[j] && lp[i+1][j-1]){
                lp[i][j] = true
                start = i
                maxLen = len
            }
        }
    }
    return s.substring(start, start+maxLen)
}

fun main(){
    print(longPal("abbacd"))
}

// aaraabbaba