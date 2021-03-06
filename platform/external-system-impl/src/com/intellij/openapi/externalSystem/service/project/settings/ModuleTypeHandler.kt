// Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.intellij.openapi.externalSystem.service.project.settings

import com.intellij.openapi.externalSystem.model.project.settings.ConfigurationData
import com.intellij.openapi.externalSystem.service.project.IdeModifiableModelsProvider
import com.intellij.openapi.module.Module

class ModuleTypeHandler: ConfigurationHandler {

  override fun apply(module: Module, modelsProvider: IdeModifiableModelsProvider, configuration: ConfigurationData) {
    val moduleTypes = configuration.find("moduleType")
    if (moduleTypes !is Map<*, *>) {
      return
    }

    moduleTypes.forEach { (k, v) ->
      val key = k as? String ?: return@forEach
      val value = v as? String ?: return@forEach
      if ("" == key) {
        module.setModuleType(value)
      } else {
        modelsProvider.findIdeModule(module.name + ".$key")?.setModuleType(value)
      }
    }

    (moduleTypes[""] as? String)?.let {
      module.setModuleType(it)
    }
  }
}