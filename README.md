
# BitCoin-Value-Predictor

#### Project in Progress....


## Abstract: 
The project attempts to predict the future value of Bitcoins by identifying the correlation between social media sentiment and market sentiment. We will achieve this by collecting user feeds from social media such as twitter, facebook and linkedin. Once we have our corpus we will map their associated sentiments using IBM Watson’s Natural Language Understanding API. While mapping sentiments to our corpus we attempt to capture granular level categories namely joy, anger, happiness, etc. We use these as feature vectors to our ML/DL algorithms. Then we compare the results of the different algorithms and choose the one with the best accuracy score.

## Technologies:
* Programming Languages: Python, Java
* Big data technologies: Spark ML, Spark-SQL, Hadoop Mapreduce
* Libraries: Pandas, Matplotlib, Scikit-learn, TensorFlow , Keras

## Data Sources:
1. Twitter Api to get the tweets about BitCoins/Cryptocurrencies.
2. LinkedIn Api to get the corpus on blogs.
3. Web Scraping to get data from News articles.

## References:
* J. Bollen and H. Mao. Twitter mood as a stock market predictor. IEEE Computer, 44(10):91–94.
* http://dataconomy.com/2014/07/bitcoin-big-data-can-predict-future-value-virtual-currency
* Mittall et Goel. Stock Prediction Using Twitter Sentiment Analysis
* http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.375.4517&rep=rep1&type=pdf
* https://arxiv.org/pdf/1610.09225.pdf
