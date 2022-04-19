package com.jaradomkar.realtimechat.model

data class Post(
    val otp: String,
    val otp_code: String,
    val email:String,
    val password:String,
    val cpassword:String
)

//for taking students
data class presentiClassData(
    val branch:String,
    val year:Int,
    val subject:String
)

data class ItemsViewModelStudents(
    val students:ArrayList<students>
)

//for taking students presenti data subject,branch,year
data class presentiClassInfo(
    val branch:String,
    val year:Int,
    val subject:String,
    val DayTime:String
)
//for uploading specific student presenti
data class setPresentData(
    val _id:String,
    val DayTime:String,
    val P_roll_numbers:ArrayList<String>
)

data class presentiClassIDresponse(
    val _id: String
)

data class EmailVerificationData(
    val email:String
)

data class EmailVerificationDataForSignUp(
    val email:String
)

data class userInfoData(
    val email:String
)

data class userInfoDataResponse(
    val _id: String,
    val name:String,
    val email: String,
    val phone: String,
    val branch: String,
    val year: Int,
    val date:String,
    val rollNumber: String
)

data class RegisterResponse(
    val UserInfo:userInfoDataResponse
)

data class teacherInfoData(
    val email: String
)

data class teacherInfoDataResponse(
    val _id: String,
    val name:String,
    val email: String,
    val phone: String,
    val department: String,
    val year: String
)

data class teacherResponse(
    val TeacherInfo:teacherInfoDataResponse
)

data class EmailVerificationSignUpOtpResponse(
    val email:String,
    val final__otp:String
)
data class EmailVerificationOtpResponse(
    val email:String,
    val final__otp:String
)

data class LoginData(
    val email:String,
    val password:String
)

data class ChangePasswordData(
    val otp:String,
    val otp_code:String,
    val email:String,
    val password: String,
    val cpassword: String
)

data class ChangedPasswordResponse(
    val responce:String
)

data class LoginResponce(
    val user:User,
    val token:String
)

data class signUpResponse(
    val token: String
)

data class tokenResponse(
    val tokenUser:User
)

data class User(
    val _id:String,
    val email: String,
    val isadmin:Boolean,
    val isteacher:Boolean
)


data class RegisterData(
    val name:String,
    val email:String,
    val phone:Long,
    val branch:String,
    val year:Int,
    val rollNumber:String
)

data class RegisterTeacher(
    val name:String,
    val email:String,
    val phone:Long,
    val department:String,
    val year:String,
    val password: String,
    val cpassword: String
)

data class RegisterTeacherResponse(
    val response: String
)

data class ItemsViewModel(
    val getTeachers:ArrayList<getTeachers>
)

data class getTeachers(
    val _id:String,
    val name: String,
    val email:String,
    val phone:String,
    val department: String,
    val year: String,
    val date: String
)


data class students(
    val _id:String,
    val name: String,
    val email:String,
    val phone:String,
    val branch: String,
    val year: Int,
    val rollNumber: String
)

data class totalAttendanceData(
    val branch:String,
    val rollNumber:String
)
data class totalAttendanceDataResponse(
    val totalPercentage:String
)

data class presentiCheckData(
    val branch: String,
    val year: Int,
    val subject: String,
    val DayTime: String
)

data class RollNumbers(
    val attendance:ArrayList<String>
)