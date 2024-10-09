package com.cho.dietapp.ui.common

import com.cho.dietapp.R


//메인 화면 네비게이션  바

enum class DietAppScreen(val icon : Int) {

    Diet(R.drawable.tap1_icon), // 다이어트
    Home(R.drawable.tap2_icon), // 홈
    Profile(R.drawable.tap3_icon) // 정보

}


// 프로필 화면

enum class  ProfileScreens {
    ProfileInfo,
    ProfileEdit
}


enum class DietRecordScreens {
    DietRecordAdd,
    DietRecordInfo
}