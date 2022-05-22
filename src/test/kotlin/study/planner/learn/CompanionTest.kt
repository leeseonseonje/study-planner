package study.planner.learn

import lombok.Builder

class CompanionTest {

    var name: String?

    var age: Int?

    private constructor(name: String, age: Int) {
        this.name = name
        this.age = age
    }

    companion object {
        fun of(name: String, age: Int): CompanionTest {
            return CompanionTest(name, age)
        }
    }
}