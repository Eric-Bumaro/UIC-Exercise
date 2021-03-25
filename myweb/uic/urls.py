from django.conf.urls import url

import uic
from uic import views,student,teacher
app_name="uic"
urlpatterns = [
    url(r'^login/#error=(?P<error>[0-3]{1})$',views.login,name='login'),
    url(r'^register/$',views.register,name='register'),
    url(r'^register/successful/#usertype=(?P<usertype>[0-1]{1})$',views.suReg,name="suReg"),
    url(r'^forgetpwd/$',views.forgetpwd,name="forgetpwd"),
    url(r'^suChange/$',views.suChange,name="suChange"),
    url(r'^Student/stuInfo/$',student.stuInfor,name="stuInfor"),
    url(r'^Student/stuChange/#changeType=(?P<changeType>.*)$',student.stuChanInfo,name="stuChanInfo"),
    url(r'^Student/stuSuChange#changeType=(?P<changeType>.*)$',student.stuSuChange,name="stuSuChange"),
    url(r'^Teacher/teaInfo/$',teacher.teaInfor,name="teaInfo"),
    url(r'^Teacher/teaInfo/teaViewcourse/#num=(?P<num>\d*)&cname=(?P<cname>.*)$',teacher.teaViewcourse,name="teaViewcourse"),
    url(r'^Teacher/teaInfo/teaViewstu/#sid=(?P<sid>\d*)$', teacher.teaViewstu,name="teaViewstu"),
    url(r'^Teacher/teaChange/#changeType=(?P<changeType>.*)$',teacher.teaChanInfo,name="teaChange"),
    url(r'^Teacher/teaSuChange#changeType=(?P<changeType>.*)$',teacher.teaSuChange,name="teaSuChange"),
]