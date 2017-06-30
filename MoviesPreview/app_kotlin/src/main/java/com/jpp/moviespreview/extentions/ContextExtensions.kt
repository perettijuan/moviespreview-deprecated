package com.jpp.moviespreview.extentions

import android.app.Activity
import android.app.Application
import android.content.Context
import com.jpp.moviespreview.domain.UseCaseFactory

/**
 * Extension functions for Context.
 *
 * Created by jpp on 6/29/17.
 */


/**
 * Retrieves the UseCaseFactory from the system.
 */
fun Context.getUseCaseFactoryAsSystem(): UseCaseFactory {
    return getApplicationAsContext().getSystemService(UseCaseFactory.USE_CASE_FACTORY_SYSTEM) as UseCaseFactory
}


/**
 * Extension function to retrieve the Application instance as Context from the Context.
 * Private for the moment.
 */
private fun Context.getApplicationAsContext(): Context {
    if (this is Application) {
        return this
    } else if (this is Activity) {
        // smart cast - ((Context)this).getApplication()
        return application
    } else {
        // smart cast - ((Context)this).getApplicationContext()
        return applicationContext
    }
}
