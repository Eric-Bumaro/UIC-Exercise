from django.http import HttpResponse, HttpResponseRedirect
from django.shortcuts import render
from django.urls import reverse
from django.views.generic import View
from uic.littleTools import *
from uic.models import *


def welcome(request):
    return render(request, 'View/welcome.html')



def getTeaKey(request):
    return HttpResponse(sendTeaKey())


def getUserKey(request,type,id):
    key=""
    if(type=="1"):
        try:
            id=id.replace("-"," ")
            to_add=Teacher.objects.filter(tname=id)[0].emailAdd
            key=sendEmail(to_add)
            return HttpResponse(key)
        except:
            return HttpResponse(2)
    if(type=="0"):
        try:
            to_add = Student.objects.filter(sid=int(id))[0].emailAdd
            key = sendEmail(to_add)
            return HttpResponse(key)
        except:
            return HttpResponse(3)
    return HttpResponse(1)


def sendCheckKey(request,emailAdd):
    key=""
    try:
        key=sendEmail(emailAdd)
    except:
        return HttpResponse(1)
    return HttpResponse(key)