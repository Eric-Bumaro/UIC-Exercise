from __future__ import unicode_literals

from django.db import models


class Course(models.Model):
    cid = models.IntegerField(primary_key=True)
    cname = models.CharField( max_length=10, blank=True, null=True,unique=True)
    class Meta:
        db_table = 'uic_courses'

    def __str__(self):
        return u'Course:%s' % self.cname


class Student(models.Model):
    sid = models.AutoField(primary_key=True, )  # Field name made lowercase.
    sname = models.CharField(max_length=20, blank=True, null=True)  # Field name made lowercase.
    password = models.CharField(max_length=20, blank=True,
                                null=True)  # Field name made lowercase.
    register_time = models.DateTimeField(blank=True, null=True,auto_now_add=True)  # Field name made lowercase.
    image=models.ImageField(upload_to="student_image",null=True)
    emailAdd=models.EmailField(default="2161217535@qq.com")
    course_field = models.ForeignKey(Course,blank=True, null=True,on_delete=models.CASCADE,related_name="sts")
    # Field renamed because it was a Python reserved word.

    class Meta:
        db_table = 'uic_students'

    def __str__(self):
        return u'Student:%s' % self.sname

class Teacher(models.Model):
    tid = models.AutoField(primary_key=True,)
    tname = models.CharField(max_length=30, unique=True)
    password = models.CharField(max_length=20)
    emailAdd = models.EmailField(default="2161217535@qq.com")
    course = models.ManyToManyField(Course,through="Course_Teacher")

    class Meta:
        db_table = 'uic_teachers'

    def __str__(self):
        return u'Teacher:%s' % self.tname


class Course_Teacher(models.Model):
    id=models.AutoField(primary_key=True)
    cid=models.ForeignKey(Course,on_delete=models.CASCADE)
    tid=models.ForeignKey(Teacher,on_delete=models.CASCADE)
    class Meta:
        db_table = 'course_teacher'


