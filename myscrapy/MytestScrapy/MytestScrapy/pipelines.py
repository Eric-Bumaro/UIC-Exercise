# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://docs.scrapy.org/en/latest/topics/item-pipeline.html


# useful for handling different item types with a single interface
from itemadapter import ItemAdapter
import openpyxl

class MytestScrapyPipeline(object):
    def __init__(self):
        self.wb=openpyxl.Workbook()
        self.ws=self.wb.active
        self.ws.append(["歌名","歌手","时间","链接"])
    def process_item(self, item, spider):
        line=[item['song_name'],item['singer'],item['time'],item['url']]
        self.ws.append(line)
        return item
    def close_spider(self,spider):
        self.wb.save("result.xlsx")
        self.wb.close()
