import json

from django.http import HttpResponse, HttpResponseRedirect
from django.shortcuts import render, redirect
from django.urls import reverse

from .littleTools import *
from .models import Student
import os

def stuInfor(request):
    student = Student.objects.get(sid=json.loads(request.session['studentid']))
    return render(request, "Student/stuInfor.html",
                  {"user":student})

def stuChanInfo(request,changeType):
    if request.method=='GET':
        if(changeType=="emailAdd"):
            return render(request,"Student/stuChanInfo.html",{"changeType_js":json.dumps(changeType),"changeType_py":changeType})
        if(changeType=="image"):
            return render(request,"Student/stuChanInfo.html",{"changeType_js":json.dumps(changeType),"changeType_py":changeType})
        if(changeType=="name"):
            return render(request,"Student/stuChanInfo.html",{"changeType_js":json.dumps(changeType),"changeType_py":changeType})
        if(changeType=="password"):
            return render(request,"Student/stuChanInfo.html",{"changeType_js":json.dumps(changeType),"changeType_py":changeType,"sid":json.loads(request.session['studentid'])})
    student = Student.objects.filter(sid=json.loads(request.session['studentid']))
    if (changeType == "emailAdd"):
        newEmailAdd=request.POST.get("newEmailAdd","")
        student.update(emailAdd=newEmailAdd)
    if (changeType == "image"):
        photo=request.FILES.get("photo","")
        photoName = str(json.loads(request.session['studentid'])) + "." + photo.name.split(".")[1]
        os.remove(os.path.join(".", ".", os.getcwd(), "media", "student_image",str(student[0].image)))
        with open(os.path.join(".", ".", os.getcwd(), "media", "student_image", photoName), "wb") as fw:
            for chunk in photo.chunks():
                fw.write(chunk)
        student.update(image=photoName)
    if (changeType == "name"):
        newEmailAdd=request.POST.get("newName","")
        student.update(sname=newEmailAdd)
    if (changeType == "password"):
        newEmailAdd=request.POST.get("pwd","")
        student.update(password=newEmailAdd)
    return HttpResponseRedirect(reverse("uic:stuSuChange",args=(changeType,)))


def stuSuChange(request,changeType):
    return render(request, "Student/stuSuChange.html", {"changeType":changeType})