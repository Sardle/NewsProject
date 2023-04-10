package com.example.feature.di

interface FeatureDepsProvider {
    val deps: FeatureDeps

    companion object: FeatureDepsProvider by FeatureDepsStore
}