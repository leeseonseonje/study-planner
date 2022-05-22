package study.planner.learn

import org.junit.jupiter.api.Test

class CompanionObjectTest {

    @Test
    fun create() {
        val of = CompanionTest.of("name", 20)

        println(of.name)
        println(of.age)
    }
}