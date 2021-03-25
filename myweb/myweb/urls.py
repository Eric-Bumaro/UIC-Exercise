"""myweb URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/2.2/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.conf.urls import url
from django.conf.urls.static import static
from django.contrib import admin
from django.urls import path, include
from django.views.static import serve

from myweb import views, settings
from myweb.settings import *

urlpatterns = [
    path('admin/', admin.site.urls),
    url(r'^user/',include('uic.urls',namespace='user')),
    url(r'^welcome/$',views.welcome),
    path('getTeaKey/$', views.getTeaKey, name='getTeaKey'),
    url(r'^getUserKey/#type=(?P<type>[0-1]{1})&id=(?P<id>.*)', views.getUserKey, name='getUserKey'),
    url(r'^sendCheckKey/#emailAdd=(?P<emailAdd>.*)',views.sendCheckKey,name='sendCheckKey'),
    url(r'^media/(?P<path>.*)', serve,{'document_root': settings.MEDIA_ROOT}),
    url(r'^static/(?P<path>.*)', serve, {'document_root': settings.STATICFILES_DIRS[0]}),
    ]

def showsql():
    from django.db import connection
    print(connection.queries[-1]['sql'])
# if DEBUG:
#     urlpatterns+=url(r'^meida/(?P<path>.*)/$',serve,{"document_root":MEDIA_ROOT})