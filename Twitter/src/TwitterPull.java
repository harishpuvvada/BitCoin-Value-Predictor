import com.opencsv.CSVWriter;
import twitter4j.*;
import twitter4j.auth.OAuth2Token;
import twitter4j.conf.ConfigurationBuilder;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

public class TwitterPull {


    public static void main(String args[]){

        ConfigurationBuilder build = new ConfigurationBuilder();
        build.setApplicationOnlyAuthEnabled(true);
        build.setOAuthConsumerKey("");
        build.setOAuthConsumerSecret("");
        Twitter twitter = null;
        try {
            OAuth2Token token = new TwitterFactory(build.build()).getInstance().getOAuth2Token();
            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true);
            cb.setApplicationOnlyAuthEnabled(true);
            cb.setOAuthConsumerKey("");
            cb.setOAuthConsumerSecret("");
            cb.setOAuth2TokenType(token.getTokenType());
            cb.setOAuth2AccessToken(token.getAccessToken());

            twitter = new TwitterFactory(cb.build()).getInstance();

        } catch (TwitterException e) {
            e.printStackTrace();
        }

        try {
            String outputFile = "tweets.csv";
            new File(outputFile).delete();

            Query query = new Query("bitcoin");
            query.setLang("en");
            query.setCount(100);
            query.setSince("2015-11-01");
            query.setUntil("2017-11-01");
            QueryResult result;
            Map<String, RateLimitStatus> ratelimits = twitter.getRateLimitStatus();
            System.out.println(ratelimits.get("/search/tweets"));
            result = twitter.search(query);
            System.out.println(result.getCount());

            CSVWriter writer = new CSVWriter(new FileWriter(outputFile));

            List<Status> tweets = result.getTweets();
            for(Status tweet: tweets){
                final String[] tweetToCSV = new String[] {tweet.getCreatedAt().toString(), tweet.getText(), String.valueOf(tweet.getFavoriteCount()), tweet.getRetweetedStatus().toString(), String.valueOf(tweet.getRetweetCount())};
                writer.writeNext(tweetToCSV);
                System.out.println(tweet + "\n");
            }
            writer.close();

        } catch (Exception e){
            System.out.println(e);
        }
    }

}


