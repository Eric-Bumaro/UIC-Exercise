import bs4
import requests
import scrapy
from ..items import MytestScrapyItem

class MytestScrapySpider(scrapy.Spider):
    name="MytestScrapy"
    allowed_domains=["c.y.qq.com"]
    singer_name="河图"
    start_urls=[]
    params = {
        'ct': '24',
        'qqmusic_ver': '1298',
        'new_json': '1',
        'remoteplace': 'txt.yqq.song',
        'searchid': '70717568573156220',
        't': '0',
        'aggr': '1',
        'cr': '1',
        'catZhida': '1',
        'lossless': '0',
        'flag_qc': '0',
        'p': 0,
        'n': '20',
        'w': singer_name,
        'g_tk': '714057807',
        'loginUin': '0',
        'hostUin': '0',
        'format': 'json',
        'inCharset': 'utf8',
        'outCharset': 'utf-8',
        'notice': '0',
        'platform': 'yqq.json',
        'needNewCode': '0'
    }
    origin_url = "https://c.y.qq.com/soso/fcgi-bin/client_search_cp?"
    for x in range(4):
        url=origin_url
        for key,value in params.items():
            if(key=='p'):
                value=str(x+1)
            url=url+key+"="+value+"&"
        start_urls.append(url)
    def parse(self,response):
        json_music = response.json()
        data = json_music['data']['song']['list']
        for i in data:
            item=MytestScrapyItem()
            item['song_name']=i['name']
            item['singer'] =json_music['data']['keyword']
            time=int(i['interval'])
            item['time'] =str(int(time/60))+"分"+str(time%60)+"秒"
            item['url']="https://y.qq.com/n/yqq/song/"+i['mid']+".html"
            yield item



