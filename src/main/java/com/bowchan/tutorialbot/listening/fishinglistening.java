package com.bowchan.tutorialbot.listening;

import com.bowchan.tutorialbot.fishing.fish;
import com.bowchan.tutorialbot.fishing.fisher;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.utils.FileUpload;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class fishinglistening extends ListenerAdapter {
    @Override
    public void onGuildReady(GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();

        commandData.add(Commands.slash("fishing","釣魚"));
        commandData.add(Commands.slash("fishprobability","釣魚機率"));

        commandData.add(Commands.slash("fisherinformation","你的資訊"));

        event.getGuild().updateCommands().addCommands(commandData).queue();
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String command = event.getName();

        fish Fish = new fish(event.getUser().getAsTag());
        fisher Fisher = new fisher();
        int grade = Integer.valueOf(String.valueOf(Fisher.fisherList.get(Fisher.getList(event.getUser().getAsTag())).get(1)));

        if (command.equals("fishing")&&event.getChannel().getName().equals("釣魚專區")){
            ArrayList getfish = Fish.fishingAct();

            if(getfish.get(0).equals("XSS")){
                event.getChannel().sendMessage("@everyone 恭喜 "+event.getUser().getAsMention()+" 釣到    ***~~"+getfish.get(0)+"  "+getfish.get(1)+"~~***  一隻! (機率:"+getfish.get(3)+"%)"+ "  經驗值 + "+ getfish.get(2)).addFiles(FileUpload.fromData((File) getfish.get(4))).queue();
            }else if(getfish.get(0).equals("SSR")){
                event.getChannel().sendMessage("恭喜 "+event.getUser().getAsMention()+" 釣到    ***~~"+getfish.get(0)+"  "+getfish.get(1)+"~~***  一隻! (機率:"+getfish.get(3)+"%)"+ "  經驗值 + "+ getfish.get(2)).addFiles(FileUpload.fromData((File) getfish.get(4))).queue();
            }else if(getfish.get(0).equals("SR")){
                event.getChannel().sendMessage("恭喜 "+event.getUser().getAsMention()+" 釣到    "+getfish.get(0)+"  "+getfish.get(1)+"    一隻!"+ "  經驗值 + "+ getfish.get(2)).queue();

            }else if(getfish.get(0).equals("S")){
                event.getChannel().sendMessage("恭喜 "+event.getUser().getAsMention()+" 釣到    "+getfish.get(0)+"  "+getfish.get(1)+"    一隻!"+ "  經驗值 + "+ getfish.get(2)).queue();
            }else {
                event.getChannel().sendMessage("恭喜 "+event.getUser().getAsMention()+" 釣到    "+getfish.get(0)+"  "+getfish.get(1)+"    一隻!"+ "  經驗值 + "+ getfish.get(2)).queue();
                if (getfish.get(1).equals("黃老頭")){
                    event.getChannel().sendMessage("...").queue();
                }
            }
            fisher Fisher1 = new fisher();
            int grade1 = Integer.valueOf(String.valueOf(Fisher1.fisherList.get(Fisher.getList(event.getUser().getAsTag())).get(1)));

            if (grade1 > grade){
                for (int i = grade;i<grade1;i++){
                    event.getGuild().getTextChannelsByName("釣魚等級提升通知",true).get(0).sendMessage(event.getUser().getAsMention()+" **等級提升**! "+"目前等級: "+(i+1)+"等").queue();
                }
            }
            event.reply("釣魚成功!").setEphemeral(true).queue();
        }else if (command.equals("fishing")){
            event.reply("此區域無法釣魚").setEphemeral(true).queue();
        } else if (command.equals("fishprobability")) {
            event.reply(Fish.probability()).setEphemeral(true).queue();
        }else  if (command.equals("fisherinformation")){
            fisher fisher = new fisher();
            int n = fisher.getList(event.getUser().getAsTag());
            String information =event.getUser().getAsMention()+"  等級:"+ fisher.fisherList.get(n).get(1)+"  經驗值:("+ fisher.fisherList.get(n).get(2)+"/"+ fisher.fisherList.get(n).get(3)+")  金錢:"+ fisher.fisherList.get(n).get(4);
            event.reply(information).setEphemeral(true).queue();
        }
    }
}
