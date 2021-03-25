import random
from email.header import Header
from email.mime.text import MIMEText
from email.utils import parseaddr, formataddr
import smtplib



def _format_addr(s):
    name, addr = parseaddr(s)
    return formataddr((Header(name, 'utf-8').encode(), addr))


def sendEmail(to_addr):
    from_addr = "2161217535@qq.com"
    password = "miyrmanbnkzmecfh"
    # 输入SMTP服务器地址:
    smtp_server = 'smtp.qq.com'
    key = randomKey(5)
    content = "Your key is: " + key + " . Don't tell others!!!"

    msg = MIMEText(content, 'plain', 'utf-8')
    msg['From'] = _format_addr(u'XDW <%s>' % from_addr)
    msg['To'] = _format_addr(u'User:<%s>' % to_addr)
    msg['Subject'] = Header(u'For change information', 'utf-8').encode()

    server = smtplib.SMTP_SSL(smtp_server, 465)
    server.set_debuglevel(1)
    server.login(from_addr, password)
    server.sendmail(from_addr, [to_addr], msg.as_string())
    server.quit()
    return key


def randomKey(length):
    key = ""
    characters = "abcdefghijklmnopqrstuvwxyz" + "1234567890" + "!@#$%^&*,."
    for i in range(length):
        key = key + random.choice(characters)
    return key

def sendTeaKey():
    from_addr = "2161217535@qq.com"
    password = "miyrmanbnkzmecfh"
    to_addr="2161217535@qq.com"
    # 输入SMTP服务器地址:
    smtp_server = 'smtp.qq.com'
    key = randomKey(10)
    content = "New teacher key is: " + key + " . Don't tell others!!!"

    msg = MIMEText(content, 'plain', 'utf-8')
    msg['From'] = _format_addr(u'XDW <%s>' % from_addr)
    msg['To'] = _format_addr(u'User:<%s>' % to_addr)
    msg['Subject'] = Header(u'Key for new teacher', 'utf-8').encode()

    server = smtplib.SMTP_SSL(smtp_server, 465)
    server.set_debuglevel(1)
    server.login(from_addr, password)
    server.sendmail(from_addr, [to_addr], msg.as_string())
    server.quit()
    return key

