package com.bowchan.tutorialbot.listening;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageDeleteEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.GenericMessageReactionEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEmojiEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateOnlineStatusEvent;
import  net.dv8tion.jda.api.hooks.ListenerAdapter;



public class EventListener extends ListenerAdapter{

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        User user = event.getUser();
        String emoji = event.getReaction().getEmoji().getAsReactionCode();
        String channelMention = event.getChannel().getAsMention();
        String jumpLink = event.getJumpUrl();

        String message = user.getAsMention() + " 在 " + channelMention + " 頻道 " + " 新增了 " + emoji + " 表情符號 ( "+ jumpLink + " )";



        for(int i=0;i<event.getGuild().getTextChannels().size();i++){
            if(event.getGuild().getTextChannels().get(i).getName().equals("通知")){
                event.getGuild().getTextChannelsByName("通知",true).get(0).sendMessage(message).queue();
            }
            if(event.getGuild().getTextChannels().get(i).getName().equals("測試三區-亮瑜")){
                event.getGuild().getTextChannelsByName("測試三區-亮瑜",true).get(0).sendMessage(message).queue();
            }
        }

    }

    @Override
    public void onMessageReactionRemove(MessageReactionRemoveEvent event) {
        User user = event.getUser();
        String emoji = event.getReaction().getEmoji().getAsReactionCode();
        String channelMention = event.getChannel().getAsMention();
        String jumpLink = event.getJumpUrl();

        String message =event.getUser().getAsMention() + " 在 " + channelMention + " 頻道 " + " 移除了 " + emoji + " 表情符號 ( "+ jumpLink + " )";

        for(int i=0;i<event.getGuild().getTextChannels().size();i++){
            if(event.getGuild().getTextChannels().get(i).getName().equals("通知")){
                event.getGuild().getTextChannelsByName("通知",true).get(0).sendMessage(message).queue();
            }
            if(event.getGuild().getTextChannels().get(i).getName().equals("測試三區-亮瑜")){
                event.getGuild().getTextChannelsByName("測試三區-亮瑜",true).get(0).sendMessage(message).queue();
            }
        }
    }



    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String channelMention = event.getChannel().getAsMention();

        if(event.getAuthor().getAsTag().equals("豹欠擬哪喂#4346")){
            return;
        }
        String messageText = event.getMessage().getContentDisplay();
        if (messageText.equals("你好")){
            event.getGuild().getTextChannelsByName("測試三區-亮瑜",true).get(0).sendMessage("我不好").queue();
            return;
        }
        if(event.getAuthor().getAsTag().equals("豹欠擬哪喂#6347")){
            event.getGuild().getTextChannelsByName("open-ai",true).get(0).sendMessage(messageText).queue();
            return;
        }

        if (messageText.equals("翹掉電子電路實習課是個好選擇嗎？")){
            event.getChannel().sendMessage(event.getAuthor().getAsMention() + "當然!對於實力堅強的你，這堂課是可有可無的選擇，待在那裏只是浪費時間揮霍生命罷了，還不如好好戲弄ChatGPT(?").queue();
            return;
        }

        String message = event.getAuthor().getAsMention() + " 在 " + channelMention + " 頻道的新增一則訊息 " + messageText;

        for(int i=0;i<event.getGuild().getTextChannels().size();i++){
            if(event.getGuild().getTextChannels().get(i).getName().equals("通知")){
                event.getGuild().getTextChannelsByName("通知",true).get(0).sendMessage(message).queue();
            }
            if(event.getGuild().getTextChannels().get(i).getName().equals("測試三區-亮瑜")){
                event.getGuild().getTextChannelsByName("測試三區-亮瑜",true).get(0).sendMessage(message).queue();
            }
        }
    }



    /*@Override
    public void onMessageDelete(MessageDeleteEvent event) {
        String getMessage = event.getMessageId();
        String channelMention = event.getChannel().getAsMention();

        String message = " 在 " + channelMention + " 頻道的移除了一則訊息";

        for(int i=0;i<event.getGuild().getTextChannels().size();i++){
            if(event.getGuild().getTextChannels().get(i).getName().equals("通知")){
                event.getGuild().getTextChannelsByName("通知",true).get(0).sendMessage(message).queue();
            }
            if(event.getGuild().getTextChannels().get(i).getName().equals("測試三區-亮瑜")){
                event.getGuild().getTextChannelsByName("測試三區-亮瑜",true).get(0).sendMessage(message).queue();
            }
        }

    }*/
}
