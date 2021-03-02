import json
import threading
import time

from django.shortcuts import render, redirect
import speech_recognition as sr
import os
from ffmpy3 import FFmpeg
def upload(request):
    return render(request, "upload.html")

def wait(request):
    audio = request.FILES.get("AudioFile", "")
    language_name = request.POST.get("language", "")
    languageDict = {"中文": "cmn-Hans-CN", "English": "en-US"}
    language = languageDict[language_name]
    if not os.path.exists('Audio'):
        os.mkdir('Audio')
    with open(os.path.join(os.getcwd(), 'Audio', audio.name), 'wb') as fw:
        for chunck in audio.chunks():
            fw.write(chunck)
    myThread(language, audio.name).start()
    return render(request, "wait.html")


class myThread(threading.Thread):
    def __init__(self, language, audio_name):
        super().__init__()
        self.language = language
        self.audio_name = audio_name

    def run(self):
        try:
            global done
            done = 0
            change = os.path.join("Audio", self.audio_name)
            output = os.path.join("Audio", self.audio_name.split('.', 1)[0] + "_.wav")
            ff = FFmpeg(inputs={change: None}, outputs={output: '-vn -ar 44100 -ac 2 -ab 192 -f wav'})
            ff.cmd
            ff.run()
            r = sr.Recognizer()
            test = sr.AudioFile(output)
            with test as source:
                audio = r.record(source)
            os.remove(output)
            os.remove(change)
            global result
            result = r.recognize_google(audio, language=self.language, show_all=True)
            done = 1;
        except Exception as e:
            global error
            error = e
            done = 2
        # with open("./template/data.json", "w") as file:
        #     json.dump({"wait": {"done": done}}, file)
def recognize(request):
    # with open("./template/data.json", "w") as file:
    #     pass
    if(done==0):
        return redirect("/wait/",permanent=True)
    if (done == 1):
        return render(request, "result.html", {'result': result['alternative'][0]['transcript']})
    elif (done == 2):
        return render(request, "error.html", {'error': error})