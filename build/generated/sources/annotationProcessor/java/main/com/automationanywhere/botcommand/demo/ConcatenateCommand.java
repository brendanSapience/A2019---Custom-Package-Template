package com.automationanywhere.botcommand.demo;

import com.automationanywhere.bot.service.GlobalSessionContext;
import com.automationanywhere.botcommand.BotCommand;
import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;
import java.lang.ClassCastException;
import java.lang.Deprecated;
import java.lang.Object;
import java.lang.String;
import java.lang.Throwable;
import java.util.Map;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ConcatenateCommand implements BotCommand {
  private static final Logger logger = LogManager.getLogger(ConcatenateCommand.class);

  private static final Messages MESSAGES_GENERIC = MessagesFactory.getMessages("com.automationanywhere.commandsdk.generic.messages");

  @Deprecated
  public Optional<Value> execute(Map<String, Value> parameters, Map<String, Object> sessionMap) {
    return execute(null, parameters, sessionMap);
  }

  public Optional<Value> execute(GlobalSessionContext globalSessionContext,
      Map<String, Value> parameters, Map<String, Object> sessionMap) {
    logger.traceEntry(() -> parameters != null ? parameters.toString() : null, ()-> sessionMap != null ?sessionMap.toString() : null);
    Concatenate command = new Concatenate();
    if(parameters.get("firstString") == null || parameters.get("firstString").get() == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","firstString"));
    }

    if(parameters.get("secondString") == null || parameters.get("secondString").get() == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","secondString"));
    }

    if(parameters.get("firstString") != null && parameters.get("firstString").get() != null && !(parameters.get("firstString").get() instanceof String)) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","firstString", "String", parameters.get("firstString").get().getClass().getSimpleName()));
    }
    if(parameters.get("secondString") != null && parameters.get("secondString").get() != null && !(parameters.get("secondString").get() instanceof String)) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","secondString", "String", parameters.get("secondString").get().getClass().getSimpleName()));
    }
    try {
      Optional<Value> result =  Optional.ofNullable(command.action(parameters.get("firstString") != null ? (String)parameters.get("firstString").get() : (String)null ,parameters.get("secondString") != null ? (String)parameters.get("secondString").get() : (String)null ));
      logger.traceExit(result);
      return result;
    }
    catch (ClassCastException e) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.IllegalParameters","action"));
    }
    catch (BotCommandException e) {
      logger.fatal(e.getMessage(),e);
      throw e;
    }
    catch (Throwable e) {
      logger.fatal(e.getMessage(),e);
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.NotBotCommandException",e.getMessage()),e);
    }
  }
}
