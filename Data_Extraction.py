import tweepy
from textblob import TextBlob
import csv
import time
import os

consumer_key = 'JPsnizM17oLhDwGKOhMwwHf6H'
consumer_secret='2Iaeh59zilmJWnaTxXSirhiJWu41yOiiGunhvfhQ8UcIWjLRDJ'

access_token='708342317007171585-BGOmmRl5kIw7NEfCKHn7KsUP5T8b1nG'
access_token_secret='RY9xgcJkrOYMDpikq9ZI5wUli5dBEUKDDzULRYG0QW8eQ'

auth = tweepy.OAuthHandler(consumer_key,consumer_secret)
auth.set_access_token(access_token,access_token_secret)

api = tweepy.API(auth)

try:
    os.remove('result.csv')
    print "File deleted"
except:
    pass

csvFile = open('result.csv', 'w')

#Use csv writer
csvWriter = csv.writer(csvFile)

data = tweepy.Cursor(api.search,q = "(bitcoin) AND (surge OR crash OR plunge OR high OR low OR future OR amazing OR good OR bad OR record)",since = "2017-10-20",until = "2017-10-30",lang = "en").items()
#most recent data is fetched first
i=0
while i<11:
    try:
        tweet = data.next()
        if tweet.user.followers_count > 100000: #collecting tweets made by users with min 100k followers
            i+=1
            # Write a row to the CSV file. I use encode UTF-8
            csvWriter.writerow([tweet.user.name.encode('utf-8', errors='ignore'),tweet.user.followers_count,tweet.created_at, tweet.text.encode('utf-8', errors='ignore'),tweet.id])
            print("------wrote a tweet-----")
    except tweepy.TweepError:
        print("---------------------In sleep In sleep In sleep In sleep----------------------------")
        time.sleep(150)
        continue
    except StopIteration:
        print("---------------------Something is wrong----------------------------")
        break


csvFile.close()
