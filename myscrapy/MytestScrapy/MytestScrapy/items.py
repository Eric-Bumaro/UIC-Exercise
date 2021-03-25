# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html

import scrapy


class MytestScrapyItem(scrapy.Item):
    # define the fields for your item here like:
    song_name = scrapy.Field()
    singer=scrapy.Field()
    time=scrapy.Field()
    url=scrapy.Field()
