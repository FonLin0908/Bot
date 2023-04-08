package com.bowchan.tutorialbot;

import com.bowchan.tutorialbot.listening.EventListener1;
import com.bowchan.tutorialbot.listening.fishinglistening;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

public class Bot {

    private  final Dotenv config;
    private final ShardManager shardManager;

    public Bot(){
        config = Dotenv.configure().load();
        String token = config.get("TOKEN");

        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.playing("Fishing"));
        builder.enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.MESSAGE_CONTENT);

        shardManager = builder.build();

        //shardManager.addEventListener(new EventListener());
        shardManager.addEventListener(new fishinglistening());
        shardManager.addEventListener(new EventListener1());
    }



    public Dotenv getConfig(){
        return config;
    }

    public ShardManager getShardManager() {
        return shardManager;
    }

    public static void main(String[] args) {
        Bot bot = new Bot();
    }
}
