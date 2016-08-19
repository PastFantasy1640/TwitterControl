/*
 * (c) 2016 white TwitterControl.java
 */
package twittercontrol;

import java.io.FileInputStream;
import java.io.IOException;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

import java.util.Properties;
/**
 *
 * @author white
 */
public class TwitterControl {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  throws TwitterException{
        String path = System.getProperty("user.dir");
        //load properties file
        Properties conf = new Properties();
        try {
            conf.load(new FileInputStream(path + "/src/TwitterSettings.properties"));
        } catch (IOException e) {
            System.err.println("Cannot open TwitterSettings.properties.");
            e.printStackTrace();
            System.exit(-1);  // プログラム終了
        }
        // 読み込み
        
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
          .setOAuthConsumerKey(conf.getProperty("oauth.consumerKey"))
          .setOAuthConsumerSecret(conf.getProperty("oauth.consumerSecret"))
          .setOAuthAccessToken(conf.getProperty("oauth.accessToken"))
          .setOAuthAccessTokenSecret(conf.getProperty("oauth.accessTokenSecret"));
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        
        User user = twitter.verifyCredentials();
        
        //ユーザ情報取得
        System.out.println("なまえ　　　：" + user.getName());
        System.out.println("ひょうじ名　：" + user.getScreenName());
        System.err.println("ふぉろー数　：" + user.getFriendsCount());
        System.out.println("ふぉろわー数：" + user.getFollowersCount());
      
        //ついーとしてみる
        Status status = twitter.updateStatus("初めてTwitter4J使ってみました(^^)2/");
    }
}
