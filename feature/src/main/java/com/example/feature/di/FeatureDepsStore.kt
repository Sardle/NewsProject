package com.example.feature.di

import kotlin.properties.Delegates.notNull

object FeatureDepsStore : FeatureDepsProvider {

    override var deps: FeatureDeps by notNull()
}