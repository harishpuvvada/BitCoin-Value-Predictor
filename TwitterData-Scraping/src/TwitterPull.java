import com.opencsv.CSVWriter;
import twitter4j.*;
import twitter4j.auth.OAuth2Token;
import twitter4j.conf.ConfigurationBuilder;

import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TwitterPull {


    public static void main(String args[]){

        int counter = 0;
        long minID = Long.MAX_VALUE;

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
//            query.setUntil("2017-11-09");
            QueryResult result;
            Map<String, RateLimitStatus> ratelimits = twitter.getRateLimitStatus();
            System.out.println("Printing the object:" + ratelimits.get("/search/tweets"));
            int rateLimit = ratelimits.get("/search/tweets").getRemaining();
            System.out.println("Initial rate limit is:" + rateLimit);
            Thread.sleep(1000);

            CSVWriter writer = new CSVWriter(new FileWriter(outputFile));

            do {

                boolean show = true;

                if(rateLimit <= 0){
//                    if(rateLimit == 10){
//                        ratelimits = twitter.getRateLimitStatus();
//                        rateLimit = ratelimits.get("/search/tweets").getRemaining();
//                        continue;
//                    }
                    ratelimits = twitter.getRateLimitStatus();
                    System.out.println("Printing the object:" + ratelimits.get("/search/tweets"));
                    System.out.println("Going to sleep till:" + ratelimits.get("/search/tweets").getSecondsUntilReset());
                    int sleepTime = ratelimits.get("/search/tweets").getSecondsUntilReset() + 10;
                    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                    Date date = new Date();
                    Calendar calendar = Calendar.getInstance();
                    System.out.println("Current Time:" + calendar.getTime());
                    calendar.add(Calendar.SECOND, sleepTime);
                    System.out.println("Run Time:" + calendar.getTime());
                    Thread.sleep(sleepTime * 1000);
                    ratelimits = twitter.getRateLimitStatus();
                    rateLimit = ratelimits.get("/search/tweets").getRemaining();
                    System.out.println("No of rateLimits:" + rateLimit);
                }


                result = twitter.search(query);


                List<Status> tweets = result.getTweets();
                for(Status tweet: tweets){
                    final String[] tweetToCSV = new String[] {tweet.getCreatedAt().toString(), tweet.getText()};
                    writer.writeNext(tweetToCSV);
                    if(show){
                        System.out.println(tweet + "\n");
                        show = false;
                    }
                    counter++;
                    if(tweet.getId() < minID) { minID = tweet.getId(); }
                }
                rateLimit--;
                query.setMaxId(minID-1);
                System.out.println("The number of queries in count number:" + counter + " is:" + query.getCount());
                System.out.println("The rate limit is" + rateLimit);

            } while (query.getCount() > 0);

            writer.close();
            System.out.println(counter);

        } catch (Exception e){
            System.out.println(e);
        }
    }

}


