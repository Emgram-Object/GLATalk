package com.example.glatalk_project.Model

class UserModel(user_email:String,user_pwd: String,pwd_check: String,user_type: String,user_name : String,mobile_num : String,country_cd: String,guide_info: String,guide_time: String,ad_agree: Boolean) {
    var user_email : String = ""
    init {
        this.user_email = user_email
    }
    var user_pwd: String = ""
    init {
        this.user_pwd = user_pwd
    }
    var pwd_check: String = ""
    init {
        this.pwd_check = pwd_check
    }
    var user_type: String = ""
    init {
        this.user_type = user_type
    }
    var user_name : String = ""
    init {
        this.user_name = user_name
    }
    var mobile_num : String = ""
    init {
        this.mobile_num = mobile_num
    }
    var country_cd: String = ""
    init {
        this.country_cd = country_cd
    }
    var guide_info: String = ""
    init {
        this.guide_info = guide_info
    }
    var guide_time: String = ""
    init {
        this.guide_time = guide_time
    }
    var ad_agree: Boolean = false
    init {
        this.ad_agree = ad_agree
    }

    //data 클래스로 바꿔주기 (Model은 데이터 클래스)
    //DAO 따로 만들어주기(기본 클래스 만들고 데이터 뷰에서 가져오)
    // 네트워크 클래스는 통합적으로 하나 만들어주공...
    // 네트워크 - 데이터 클래스 api 분류 따라서 리케 리스폰 만들어서 거기서 API 관

}