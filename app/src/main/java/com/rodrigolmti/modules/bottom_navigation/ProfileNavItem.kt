package com.rodrigolmti.modules.bottom_navigation

import com.rodrigolmti.modules.navigation.BottomNavItem
import com.rodrigolmti.modules.ui_kit.R

class ProfileNavItem : BottomNavItem {
    override val screenRoute: String = "profile"
    override val icon: Int = R.drawable.ic_profile
    override val title: String = "Profile"
}