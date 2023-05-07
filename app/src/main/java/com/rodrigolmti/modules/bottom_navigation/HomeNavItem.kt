package com.rodrigolmti.modules.bottom_navigation

import com.rodrigolmti.modules.navigation.BottomNavItem
import com.rodrigolmti.modules.ui_kit.R

class HomeNavItem : BottomNavItem {
    override val screenRoute: String = "home"
    override val icon: Int = R.drawable.ic_home
    override val title: String = "Home"
}
