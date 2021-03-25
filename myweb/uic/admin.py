from django.contrib import admin

# Register your models here.
from uic.models import *

admin.site.register(Course)
admin.site.register(Student)
admin.site.register(Teacher)
admin.site.register(Course_Teacher)