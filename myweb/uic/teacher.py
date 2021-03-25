import json
import math

from django.core.paginator import *
from django.http import HttpResponse, HttpResponseRedirect
from django.shortcuts import render
from django.urls import reverse

from .littleTools import *
from .models import *


def teaInfor(request):
    teacher=Teacher.objects.get(tname=json.loads(request.session["tname"]))
    course=teacher.course.all()
    for i in course:
        print(str(i.cid)+" "+i.cname)
    return render(request,"Teacher/teaInfor.html",{"teacher":teacher,"course_set":course})

def teaChanInfo(request,changeType):
    if request.method == 'GET':
        if (changeType == "emailAdd"):
            return render(request, "Teacher/teaChanInfo.html",
                          {"changeType_js": json.dumps(changeType), "changeType_py": changeType})
        if (changeType == "tname"):
            return render(request, "Teacher/teaChanInfo.html",
                          {"changeType_js": json.dumps(changeType), "changeType_py": changeType})
        if (changeType == "password"):
            return render(request, "Teacher/teaChanInfo.html",
                          {"changeType_js": json.dumps(changeType), "changeType_py": changeType,
                           "tname": json.loads(request.session['tname'])})
    teacher = Teacher.objects.filter(tname=json.loads(request.session['tname']))
    if (changeType == "emailAdd"):
        newEmailAdd=request.POST.get("newEmailAdd","")
        teacher.update(emailAdd=newEmailAdd)
    if (changeType == "tname"):
        newEmailAdd=request.POST.get("newName","")
        teacher.update(sname=newEmailAdd)
    if (changeType == "password"):
        newEmailAdd=request.POST.get("pwd","")
        teacher.update(password=newEmailAdd)
    return HttpResponseRedirect(reverse("uic:teaSuChange",args=(changeType,)))
def teaViewcourse(request,num,cname):
    try:
        json.loads(request.session["tname"])
    except KeyError:
        return HttpResponse("Fail")
    course=Course.objects.get(cname=cname)
    students=course.sts.all().order_by()
    if int(num)<1:
        num=1
    else:
        num=int(num)
    pager=Paginator(students,10)
    try:
        prepage_data=pager.page(num)
    except EmptyPage:
        prepage_data=pager.page(pager.num_pages)
    begin=(num-int(math.ceil(10.0/2)))
    if begin<1:
        begin=1
    end=begin+4
    if end>pager.num_pages:
        end=pager.num_pages
    if end<=5:
        begin=1
    else:
        begin=end-4
    pagelist=range(begin,end+1)
    for i in prepage_data:
        print(i)
    return render(request,"Teacher/teaViewcourse.html",{"pager":pager,'perpage_data':prepage_data,"pagelist":pagelist,"cname":cname})


def teaViewstu(request,sid):
    try:
        json.loads(request.session["tname"])
    except KeyError:
        return HttpResponse("Fail")
    student = Student.objects.get(sid=sid)
    return render(request, "Teacher/teaViewstu.html",{"user":student})


def teaSuChange(request,changeType):
    try:
        json.loads(request.session["tname"])
    except KeyError:
        return HttpResponse("Fail")
    return render(request, "Teacher/teaSuChange.html", {"changeType":changeType})