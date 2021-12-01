package com.github.berezhkoe.intellijplugin.services

import com.intellij.openapi.project.Project
import com.github.berezhkoe.intellijplugin.MyBundle

import com.intellij.psi.PsiElement
import java.util.function.BinaryOperator
import java.util.function.IntBinaryOperator

class MyProjectService(project: Project) {

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
