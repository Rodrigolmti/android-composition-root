package com.rodrigolmti.modules.bottom_navigation

import com.rodrigolmti.modules.navigation.BottomNavItem
import com.rodrigolmti.modules.ui_kit.R

class OrdersNavItem : BottomNavItem {
    override val screenRoute: String = "orders"
    override val icon: Int = R.drawable.ic_order
    override val title: String = "Orders"
}