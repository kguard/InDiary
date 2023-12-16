package com.kguard.indiary.feature.person.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions

const val personMainRoute = "person_main_rout"
const val personDetailRoute = "person_detail_rout"
const val personEditRoute = "person_edit_rout"

fun NavController.navigateToPersonMain(navOptions: NavOptions? = null) {
    this.navigate(personMainRoute,navOptions)
}
fun NavController.navigateToPersonDetail(navOptions: NavOptions? = null) {
    this.navigate(personDetailRoute,navOptions)
}
fun NavController.navigateToPersonEdit(navOptions: NavOptions? = null) {
    this.navigate(personEditRoute,navOptions)
}