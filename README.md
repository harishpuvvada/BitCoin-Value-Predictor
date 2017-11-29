
# BitCoin-Value-Predictor

## Abstract: 
The project attempts to predict the future value of Bitcoins by identifying the correlation between social media sentiment and market sentiment. We will achieve this by collecting user feeds from social media such as twitter, facebook and linkedin. Once we have our corpus we will map their associated sentiments using IBM Watson’s Natural Language Understanding API. While mapping sentiments to our corpus we attempt to capture granular level categories namely joy, anger, happiness, etc. We use these as feature vectors to our ML/DL algorithms. Then we compare the results of the different algorithms and choose the one with the best accuracy score.

## Technologies:
1. Spark ML Spark-SQL
2. Hadoop Mapreduce
3. Libraries: Pandas, Matplotlib, Scikit learn, TensorFlow , Keras
4. Programming Languages: Python, Java

## Data Sources:
1. Twitter Api to get the tweets about BitCoins/Cryptocurrencies.
2. LinkedIn Api to get the corpus on blogs.
3. Web Scraping to get data from News articles.

## References:
 J. Bollen and H. Mao. Twitter mood as a stock market predictor. IEEE Computer, 44(10):91–94.
 http://dataconomy.com/2014/07/bitcoin-big-data-can-predict-future-value-virtual-currency
 Mittall et Goel. Stock Prediction Using Twitter Sentiment Analysis

