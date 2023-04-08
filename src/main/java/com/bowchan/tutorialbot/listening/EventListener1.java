package com.bowchan.tutorialbot.listening;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.emoji.EmojiAddedEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.utils.FileUpload;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class EventListener1 extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.getMessage().getContentDisplay().contains("此區域禁止聊天")&&event.getAuthor().getAsTag().equals(event.getJDA().getSelfUser().getAsTag())){
            event.getMessage().delete().queueAfter(3, TimeUnit.SECONDS);
            return;
        }
        if((event.getChannel().getName().equals("釣魚等級提升通知")||event.getChannel().getName().equals("釣魚專區")||event.getChannel().getName().equals("釣魚公告"))&&event.getAuthor().getAsTag().equals(event.getJDA().getSelfUser().getAsTag())==false){
            event.getMessage().delete().queue();
            event.getChannel().sendMessage(event.getAuthor().getAsMention() + " ***此區域禁止聊天*** 3秒後消失").queue();
            return;
        }
        if(event.getAuthor().getAsTag().equals("來釣我呀#4346")||event.getAuthor().getAsTag().equals("chatgpt#4764")||event.getChannel().getName().equals("open-ai")){
            return;
        }else if(event.getMessage().getContentDisplay().contains("哈密瓜")&&event.getMessage().getContentDisplay().contains("要不要")){

            event.getMessage().addReaction(event.getGuild().getEmojisByName("play",true).get(0)).queue();
            event.getChannel().asTextChannel().sendMessage("好呀~我最喜歡哈密瓜了").queue();

        }else if(event.getMessage().getContentDisplay().contains("哈密瓜")&&(event.getMessage().getContentDisplay().contains("照片")||event.getMessage().getContentDisplay().contains("圖片"))){

            File file = new File("picture","haha.jpeg");
            event.getChannel().asTextChannel().sendMessage("這是你要的哈密瓜").addFiles(FileUpload.fromData(file)).queue();

        }else if(event.getMessage().getContentDisplay().contains("哈密瓜")){
            event.getChannel().asTextChannel().sendMessage("哈密瓜有一種哈味").queue();
        }else if(event.getMessage().getContentDisplay().contains("所以說")){
            event.getChannel().asTextChannel().sendMessage("哈密瓜最棒").queue();
        }else if(event.getMessage().getContentDisplay().contains("閉嘴")){
            event.getChannel().sendMessage(event.getAuthor().getAsMention() + "不要打架! 不如來釣我~").queue();
        }else if(event.getMessage().getContentDisplay().contains("帶壞")||event.getMessage().getContentDisplay().contains("林亮瑜")){
            event.getChannel().sendMessage(event.getAuthor().getAsMention() + "不要吵架! 都衝著我來QAQ").queue();
        }else if (event.getMessage().getContentDisplay().contains("早安")){
            event.getChannel().asTextChannel().sendMessage("你各位早上好").queue();
        }else if (event.getMessage().getContentDisplay().equals("?")){
            event.getChannel().asTextChannel().sendMessage("?_?").queue();
        }else if (event.getMessage().getContentDisplay().contains("???")){
            event.getChannel().asTextChannel().sendMessage("??!").queue();
        }else if (event.getMessage().getContentDisplay().contains("在嗎")){
            event.getChannel().asTextChannel().sendMessage("不在").queue();
        }else if (event.getMessage().getContentDisplay().contains("...")){
            event.getChannel().asTextChannel().sendMessage("..?").queue();
        }else if (event.getMessage().getContentDisplay().contains("?ping")){
            event.getChannel().asTextChannel().sendMessage("輸入TK888必中五星神將.").queue();
        }else if(event.getMessage().getContentDisplay().contains("澳門")&&event.getMessage().getContentDisplay().contains("賭場")){
            File file = new File("picture","LLL.jpg");
            event.getChannel().asTextChannel().sendMessage("").addFiles(FileUpload.fromData(file)).queue();
        }else if(event.getMessage().getContentDisplay().contains("釣魚公告")){
            String message = event.getMessage().getContentDisplay().substring(event.getMessage().getContentDisplay().indexOf(":")+1);
            event.getJDA().getGuildCache().getElementById("1087392797054742659").getTextChannelsByName("釣魚公告",true).get(0).sendMessage(message).queue();
            event.getJDA().getGuildCache().getElementById("1039141210016727080").getTextChannelsByName("釣魚公告",true).get(0).sendMessage(message).queue();
        }


    }


    /*
    @Override
    public void onGuildReady(GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("hi","跟你說嗨"));

        OptionData optionData1 = new OptionData(OptionType.STRING,"message","你想叫機器人傳送的訊息",true);

        commandData.add(Commands.slash("say","機器人幫你說").addOptions(optionData1));

        OptionData optionData2 = new OptionData(OptionType.USER,"user","你要呼叫的對象",true);
        OptionData optionData3 = new OptionData(OptionType.INTEGER,"times","你要呼叫的次數",true);
        OptionData optionData4 = new OptionData(OptionType.STRING,"message","你要說的訊息",true);
        commandData.add(Commands.slash("hey","呼叫某人").addOptions(optionData2,optionData3,optionData4));

        OptionData optionData5 = new OptionData(OptionType.STRING,"website","你要搜尋的網站",true);
        optionData5.addChoice("Google","Google");
        optionData5.addChoice("維基百科","維基百科");
        optionData5.addChoice("YouTube","YouTube");
        OptionData optionData6 = new OptionData(OptionType.STRING,"information","你要搜尋的資訊",true);

        commandData.add(Commands.slash("search","搜尋").addOptions(optionData5,optionData6));

        OptionData optionData7 = new OptionData(OptionType.STRING,"language","翻譯成什麼語言",true);
        optionData7.addChoice("English","English");
        OptionData optionData8 = new OptionData(OptionType.STRING,"text","要翻譯的文字",true);
        commandData.add(Commands.slash("translate","翻譯").addOptions(optionData7,optionData8));


        event.getGuild().updateCommands().addCommands(commandData).queue();
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String command = event.getName();

        if(command.equals("hi")){
            event.reply("hi").setEphemeral(true).queue();
        }else if(command.equals("say")){
            String n = event.getOption("message").getAsString();
            event.getChannel().sendMessage(n).queue();
            event.reply("已傳送訊息").setEphemeral(true).queue();
        }else if(command.equals("hey")){
            User user = event.getOption("user").getAsUser();
            int times = event.getOption("times").getAsInt();
            String message = event.getOption("message").getAsString();
            for (int i = 0; i < times; i++){
                event.getChannel().sendMessage(user.getAsMention()+message).queue();

            }
            event.reply("已呼叫").setEphemeral(true).queue();
        }else if (command.equals("search")){
            String website = event.getOption("website").getAsString();
            String information = event.getOption("information").getAsString();
            if(website.equals("Google")){
                event.getChannel().sendMessage("https://www.google.com/search?q="+information).queue();
            }else if(website.equals("維基百科")){
                event.getChannel().sendMessage("https://zh.wikipedia.org/zh-tw/"+information).queue();
            }else if(website.equals("YouTube")){
                event.getChannel().sendMessage("https://www.youtube.com/results?search_query="+information).queue();
            }
            event.reply("已成功"+website+"搜尋"+information).queue();
        } else if (command.equals("translate")) {
            String language = event.getOption("language").getAsString();
            String text = event.getOption("text").getAsString();
            if(language.equals("English")){
                event.getChannel().sendMessage("https://translate.google.com/?sl=auto&tl=en&text="+text).queue();
            }
            event.reply("已成功將 "+text+"翻譯成"+language).queue();
        }
    }

*/
}
