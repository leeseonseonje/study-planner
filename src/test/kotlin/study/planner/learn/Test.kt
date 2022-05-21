package study.planner.learn

import org.junit.jupiter.api.Test

class Test {

    @Test
    fun nullTest() {

        var s: String? = "he"

        s ?:
            println("zz")
            println("zz")
            println("zz")

        s?.let {
            println("Hello")
            println("World")
            println(s)
        }

    }
}