package org.example.Bot;


import org.example.Commands;
import org.example.question.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class TelegramBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "offtimingbot";
    }

    @Override
    public String getBotToken() {
        return "6132329633:AAGwTqA0KEvSdAE17sMtou8vxly6uHlfTNE";
    }

    @Override
    public void onUpdateReceived(Update update) {
        String[] listCommand = {"/start: привет", "/question: запуск игры", "/stop: завершить игру"};
        Message message = update.getMessage();
        String answer = "";
        Long chatID = message.getChatId();

        if (message.hasText()) {
            String text = message.getText();
            SendMessage sendMessage = new SendMessage();
            if (text.equals("/start")) {
                sendMessage.setText("Привет! Я Telegram Bot написанный на Java");
                sendMessage.setChatId(message.getChatId());

                ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                List<KeyboardRow> keyboardRowList = new ArrayList<>();

                for (int i = 0; i < listCommand.length; i++) {
                    KeyboardRow keyboardRow = new KeyboardRow();
                    KeyboardButton keyboardButton = new KeyboardButton();
                    String buttonText = listCommand[i].split(":")[0].trim();
                    keyboardButton.setText(buttonText);
                    keyboardRow.add(keyboardButton);
                    keyboardRowList.add(keyboardRow);
                }

                replyKeyboardMarkup.setKeyboard(keyboardRowList);
                replyKeyboardMarkup.setOneTimeKeyboard(true);
                sendMessage.setReplyMarkup(replyKeyboardMarkup);

                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }


            else {
                String chatIDString = chatID.toString();
                try {
                    answer = Commands.start(text, chatIDString);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                sendMessage.setText(answer);
                sendMessage.setChatId(message.getChatId());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}



