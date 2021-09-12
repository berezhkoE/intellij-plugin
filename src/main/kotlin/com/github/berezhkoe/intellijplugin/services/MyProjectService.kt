package com.github.berezhkoe.intellijplugin.services

import com.intellij.openapi.project.Project
import com.github.berezhkoe.intellijplugin.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
