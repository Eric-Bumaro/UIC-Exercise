import json
from django.http import HttpResponseRedirect, HttpResponse
from django.shortcuts import render, redirect
from django.urls import reverse
from .littleTools import *
import os
from .models import *
# Create your views here.
def login(request,error):
    if request.method == "GET":
        if error==3:
            try:
                del request.session['tname']
            except:
                del request.session['studentid']
        if 'login' in request.COOKIES.keys():
            login = request.get_signed_cookie("login",salt="hello").split(',')
            userid = login[0]
            pwd = login[1]
            usertype = login[2]
            return render(request, "View/login.html",
                          {"error": json.dumps(error), "id": userid, "pwd": pwd, "type": json.dumps(usertype)})
        return render(request, "View/login.html", {"error": json.dumps(error),"type": json.dumps("")})
    try:
        pwd = request.POST.get("pwd", "")
        tname=request.POST.get("tname","")
        usertype = request.POST.get("user", "")
        remind = request.POST.get("cookie", "")
    except Exception:
        return HttpResponseRedirect(reverse('uic:login', args=(2,)))
    result = []
    if (usertype == "Teacher"):
        result = Teacher.objects.filter(tname=tname).filter(password=pwd)
    elif (usertype == "Student"):
        id = int(request.POST.get("sid", "1"))
        result = Student.objects.filter(sid=id).filter(password=pwd)
    else:
        return HttpResponseRedirect(reverse('uic:login', args=(1,)))
    if (len(result) == 0):
        return HttpResponseRedirect(reverse('uic:login', args=(2,)))
    request.session.set_expiry(3*60*60)
    if (usertype== "Teacher"):
        response = redirect("uic:teaInfo")
        request.session['tname']=json.dumps(tname)
        if remind=="1":
            response.set_signed_cookie('login', tname + "," + pwd + "," + "Teacher",
                                   max_age=24 * 60 * 60 * 3, salt="hello")
        return response
    request.session['studentid'] = json.dumps(id)
    response = redirect("uic:stuInfor")
    if remind == "1":
        response.set_signed_cookie('login', str(id) + "," + pwd + "," + "Student",
                               max_age=24 * 60 * 60 * 3, salt="hello")
    return response
def register(request):
    if request.method=='GET':
        return render(request,"View/register.html")
    uname=request.POST.get("name","")
    password=request.POST.get("pwd","")
    email=request.POST.get("email","")
    type=request.POST.get("user","")
    if type=="Student":
        try:
            photo=request.FILES.get("photo","")
            id=Student.objects.last().sid+1
            phototype=photo.name.split(".")[1]
            photoName=str(Student.objects.last().sid+1)+"."+photo.name.split(".")[1]
            with open(os.path.join(".",".",os.getcwd(),"media","student_image",photoName),"wb") as fw:
                for chunk in photo.chunks():
                    fw.write(chunk)
            Student.objects.create(sname=uname, password=password, emailAdd=email,image=photoName)
        except:
            Student.objects.create(sname=uname,password=password,emailAdd=email)
        return HttpResponseRedirect(reverse('uic:suReg', args=(0,)))
    Teacher.objects.create(tname=uname,password=password,emailAdd=email)
    return HttpResponseRedirect(reverse('uic:suReg', args=(1,)))

def suReg(request,usertype):
    if(usertype=="0"):
        return render(request,"View/resSuc.html",{"stuID":Student.objects.last().sid,"type":0})
    if(usertype=="1"):
        return render(request, "View/resSuc.html", {"teaName": Teacher.objects.last().tname,"type":1})
    return HttpResponse("Fail")

def forgetpwd(request):
    if request.method=='GET':
        return render(request,"View/forgetpwd.html")
    pwd=request.POST.get("pwd","")
    if request.POST.get("user","")=="Student":
        sid=request.POST.get("sid","")
        Student.objects.filter(sid=int(sid)).update(password=pwd)
        return redirect('uic:suChange')
    tname=request.POST.get("tid","")
    Teacher.objects.filter(tname=tname).update(password=pwd)
    return redirect('uic:suChange')

def suChange(request):
    return render(request,"View/suChange.html")
