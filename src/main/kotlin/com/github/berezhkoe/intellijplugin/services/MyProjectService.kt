package com.github.berezhkoe.intellijplugin.services

import com.intellij.openapi.project.Project
import com.github.berezhkoe.intellijplugin.MyBundle

import com.intellij.psi.PsiElement
import java.util.function.BinaryOperator
import java.util.function.IntBinaryOperator

class MyProjectService(project: Project) {
    var stringRepresentation: String
        get() = this.toString()
        set(value) {
//            if (a               // +1 for `if`
//
//                && b && c       // +1
//                || d || e       // +1
//                && f            // +1
//            ) {
//            }
        }



    fun PsiElement.isFunction(a: Boolean, b: Boolean, c: Boolean, d: Boolean, e: Boolean, f: Boolean): Boolean {
        if (a               // +1 for `if`
            && b && c       // +1
            || d || e       // +1
            && f            // +1
        ) {
            return b
        }

        ppp


        lll
        

        if (a               // +1 for `if`
            &&              // +1
            !(b && c)       // +1
        ) {
            return c
        }
        return a
    }

    fun veryFirst(a: Boolean, b: Boolean, c: Boolean) {
        val items = listOf(1, 2, 3, 4, 5)

        // Lambdas are code blocks enclosed in curly braces.
        items.fold(0, {
            // When a lambda has parameters, they go first, followed by '->'
                acc: Int, i: Int ->
            print("acc = $acc, i = $i, ")
            val result = acc + i
            if (a == b) {
                print("")
            }
            println("result = $result")
            // The last expression in a lambda is considered the return value:
            result
        })
    }

    enum class Direction {
        NORTH, SOUTH, WEST, EAST
    }

    enum class IntArithmetics : BinaryOperator<Int>, IntBinaryOperator {
        PLUS {
            override fun apply(t: Int, u: Int): Int = t + u
        },
        TIMES {
            override fun apply(t: Int, u: Int): Int = t * u
        };

        override fun applyAsInt(t: Int, u: Int) = apply(t, u)
    }


    interface Inner {
        private fun testFirst(b: Boolean) {
            for (i in 1..10) {                  // +1
                while (true) {                  // +2 (nesting=1)
                    break                       // +1
                }
            }

            for (i in 1..10) {                  // +1
                while (true) {                  // +2 (nesting=1)
                    break                       // +1
                }
            }
        }
    }

    class Nested {
        companion object {
            private fun testFirst(b: Boolean) {
                for (i in 1..10) {                  // +1
                    while (true) {                  // +2 (nesting=1)
                        break                       // +1
                    }
                }

                for (i in 1..10) {                  // +1
                    while (true) {                  // +2 (nesting=1)
                        break                       // +1
                    }
                }
            }
        }

        private fun testFirst(b: Boolean) {
            for (i in 1..10) {                  // +1
                while (true) {                  // +2 (nesting=1)
                    break                       // +1
                }
            }

            for (i in 1..10) {                  // +1
                while (true) {                  // +2 (nesting=1)
                    break                       // +1
                }
            }
        }
    }

    companion object {
        private fun testFirst(b: Boolean) {
            for (i in 1..10) {                  // +1
                while (true) {                  // +2 (nesting=1)
                    break                       // +1
                }
            }

            for (i in 1..10) {                  // +1
                while (true) {                  // +2 (nesting=1)
                    break                       // +1
                }
            }
        }

        fun testtest(a: Boolean, b: Boolean, c: Boolean, d: Boolean, e: Boolean, f: Boolean) {
            a &&                                // +1
                    !((a || b && c))              // +1
                    || d || e                   // +1
                    && f                        // +1

//            a && b && c                         // +1
//
//            a && b || c                         // +2
        }
    }

    init {
        println(MyBundle.message("projectService", project.name))
        for (i in 1..10) {                  // +1
            while (true) {                  // +2 (nesting=1)
                break                       // +1
            }
        }

    }

    fun testFirst(b: Boolean) {
        for (i in 1..10) {                  // +1
            while (true) {                  // +2 (nesting=1)
                break                       // +1
            }
        }

        for (i in 1..10) {                  // +1
            while (true) {                  // +2 (nesting=1)
                break                       // +1
            }
        }
    }
//
//    fun testBinSeq(a: Boolean, b: Boolean, c: Boolean, d: Boolean, e: Boolean, f: Boolean) {
//        a ||                                // +1
//                a && b && c                 // +1
//                || d || e                   // +1
//                && f                        // +1
//
//        a && b && c                         // +1
//
//        a && b || c                         // +2
//        fun testtest() {
//            a ||                                // +1
//                    a && b && c                 // +1
//                    || d || e                   // +1
//                    && f                        // +1
//
//            a && b && c                         // +1
//
//            a && b || c                         // +2
//        }
//    }

    fun testLambda(a: Boolean, b: Boolean) {
        Runnable() {
            if (b) {                    // +2 (nesting=1)
                for (i in 1..10) {      // +3 (nesting=2)
                    while (true) {      // +4 (nesting=3)
                        break
                    }
                }
            }
        }
    }

//    fun testIfElse(b: Boolean) {
//        for (i in 1..10) {              // +1
//            while (true) {              // +2 (nesting=1)
//                if (b) {                // +3 (nesting=2)
//                    break               // +1
//                } else if (true) {      // +1
//
//                } else {                // +1
//
//                }
//            }
//        }
//    }
}
